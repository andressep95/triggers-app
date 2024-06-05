package cl.playground.triggersapp.commands;

public class UpdateQuotationCommand {
    private final int id;
    private final double total;

    public UpdateQuotationCommand(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }
}
