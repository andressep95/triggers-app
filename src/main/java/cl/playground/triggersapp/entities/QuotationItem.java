package cl.playground.triggersapp.entities;

public class QuotationItem {
    private int quotationId;
    private int productId;
    private int quantity;

    public QuotationItem() {}

    public QuotationItem(int quotationId, int productId, int quantity) {
        this.quotationId = quotationId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "QuotationItem{" +
                "quotationId=" + quotationId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
