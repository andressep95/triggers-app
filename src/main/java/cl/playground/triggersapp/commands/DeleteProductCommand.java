package cl.playground.triggersapp.commands;

public class DeleteProductCommand {
    private int id;

    public DeleteProductCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
