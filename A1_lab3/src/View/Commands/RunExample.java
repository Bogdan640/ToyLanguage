package View.Commands;

import Controller.Ctrl;

public class RunExample extends Command {
    private Ctrl ctrl;
    public RunExample(String key, String description, Ctrl ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
