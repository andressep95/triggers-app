package cl.playground.triggersapp.commands;

public class DeleteQuotationCommand {
    private final int id;

    public DeleteQuotationCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
