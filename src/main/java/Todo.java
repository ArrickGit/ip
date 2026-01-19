public class Todo extends Task{

    public Todo(String descirption) {
        super(descirption);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
