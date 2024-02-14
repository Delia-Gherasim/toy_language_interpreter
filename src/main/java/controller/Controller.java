package controller;

import model.utils.ProgrState;
import model.value.IValue;
import model.value.RefValue;
import repository.IRepository;
import repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository repo;
    boolean flag;
    String path;
    ExecutorService executor= Executors.newFixedThreadPool(2);
    public Controller(IRepository repo, boolean flag) {
        this.repo = repo;
        this.flag = flag;
    }

    public List<ProgrState> removeCompletedPrg(List<ProgrState> inPrgList) {
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgrState> prgList) throws Exception {
        for (ProgrState progrState : prgList) {
            repo.logProgrState(progrState);
        }

        List<Callable<ProgrState>> callList = prgList.stream()
                .map((ProgrState p) -> (Callable<ProgrState>) () -> p.oneStep())
                .collect(Collectors.toList());

        List<ProgrState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        for (ProgrState prg : prgList) {
            repo.logProgrState(prg);
        }
        repo.setPrgList(prgList);
    }

    public void allStep() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        List<ProgrState> prgList = repo.getPrgList();
        while (!prgList.isEmpty()) {
            unsafeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }

        executor.shutdownNow();
        repo.setPrgList(prgList);

        prgList.forEach(prg -> {
            System.out.println(prg.getOut().toString());
        });
    }


   public void unsafeGarbageCollector(List<ProgrState> prgList) {
        List<Integer> symTableAddr = prgList.stream()
                .flatMap(p -> p.getSymTable().getContent().values().stream())
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());

        List<Integer> heapAddr = getAddrFromSymbTable(prgList.get(0).getHeap().getContent().values());

        Map<Integer, IValue> newHeap = prgList.get(0).getHeap().entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (ProgrState prg : prgList) {
            prg.getHeap().setContent(newHeap);
        }
    }

    List<Integer> getAddrFromSymbTable(Collection<IValue> symbTableValues) {
        return symbTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }
    public IRepository getRepo(){
        return this.repo;
    }
}
