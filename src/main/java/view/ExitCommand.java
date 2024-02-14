package view;

public class ExitCommand extends Command{

    public ExitCommand(String id, String descr) {
        super(id, descr);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
