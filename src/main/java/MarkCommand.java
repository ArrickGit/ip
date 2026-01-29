public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException {
        if (index < 0 || index >= tasks.size()) {
            throw new Pompompurin.purinException("Beeboo =( That task number is out of range.");
        }

        Task taskToMark = tasks.get(index);
        taskToMark.markAsDone();

        storage.save(tasks);

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskToMark);
        ui.showLine();
    }
}