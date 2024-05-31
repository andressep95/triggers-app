
CREATE TABLE quotations (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10, 2) DEFAULT 0
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    price NUMERIC(10, 2)
);

CREATE TABLE quotation_items (
    quotation_id INT REFERENCES quotations(id),
    product_id INT REFERENCES products(id),
    quantity INT,
    PRIMARY KEY (quotation_id, product_id)
);

CREATE FUNCTION calculate_total() RETURNS TRIGGER AS $$
BEGIN
    UPDATE quotations
    SET total = (
        SELECT SUM(p.price * qi.quantity)
        FROM quotation_items qi
                 JOIN products p ON qi.product_id = p.id
        WHERE qi.quotation_id = NEW.quotation_id
    )
    WHERE id = NEW.quotation_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calculate_total
AFTER INSERT OR UPDATE ON quotation_items
FOR EACH ROW EXECUTE FUNCTION calculate_total();