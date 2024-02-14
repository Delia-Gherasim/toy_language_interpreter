package repository;

import model.utils.ProgrState;

import java.util.List;

public interface IRepository {
    //ProgrState getCurrentProgramState() throws Exception;
    List<ProgrState> getPrgList();
    void setPrgList(List<ProgrState> prgList);
    void add(ProgrState programState);
    //void logProgrState() throws Exception;
    void logProgrState(ProgrState prg) throws Exception;

}
