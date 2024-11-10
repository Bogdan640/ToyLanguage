package View.Commands;

public class ExitCmd extends Command{
    public ExitCmd(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
