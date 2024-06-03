package cl.playground.triggersapp.events;

public class ProductCreatedEvent {
    private String name;
    private double price;

    public ProductCreatedEvent(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductCreatedEvent{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
