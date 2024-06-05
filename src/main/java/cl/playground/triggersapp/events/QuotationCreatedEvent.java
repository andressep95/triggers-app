package cl.playground.triggersapp.events;

public class QuotationCreatedEvent {
    private final int id;
    private final double total;

    public QuotationCreatedEvent(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return id + "," + total;
    }
}
