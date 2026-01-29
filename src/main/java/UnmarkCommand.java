public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Pompompurin.purinException {
        if (index < 0 || index >= tasks.size()) {
            throw new Pompompurin.purinException("Beeboo =( That task number is out of range.");
        }
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.markAsNotDone();

        storage.save(tasks);

        ui.showLine();
        System.out.println("Gotcha, I've marked this task as not done yet:");
        System.out.println("  " + taskToUnmark);
        ui.showLine();
    }
}