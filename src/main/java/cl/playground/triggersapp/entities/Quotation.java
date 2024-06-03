package cl.playground.triggersapp.entities;

import java.sql.Timestamp;
import java.util.List;

public class Quotation {
    private int id;
    private Timestamp createdAt;
    private double total;
    private List<QuotationItem> items;

    public Quotation() {}

    public Quotation(int id, Timestamp createdAt, double total) {
        this.id = id;
        this.createdAt = createdAt;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public List<QuotationItem> getItems() {
        return items;
    }

    public void setItems(List<QuotationItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Quotation{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
