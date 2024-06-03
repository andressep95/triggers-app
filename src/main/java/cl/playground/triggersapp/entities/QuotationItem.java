package cl.playground.triggersapp.entities;

public class QuotationItem {
    private int quotationId;
    private int productId;
    private int quantity;
    private Product product;

    public QuotationItem() {}

    public QuotationItem(int quotationId, int productId, int quantity, Product product) {
        this.quotationId = quotationId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "QuotationItem{" +
                "quotationId=" + quotationId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
