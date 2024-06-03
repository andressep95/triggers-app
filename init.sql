-- Crear las tablas
CREATE TABLE IF NOT EXISTS quotations (
                                          id SERIAL PRIMARY KEY,
                                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          total NUMERIC(10, 2) DEFAULT 0
);

CREATE TABLE IF NOT EXISTS products (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(100),
                                        price NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS quotation_items (
                                               quotation_id INT REFERENCES quotations(id),
                                               product_id INT REFERENCES products(id),
                                               quantity INT,
                                               PRIMARY KEY (quotation_id, product_id)
);

CREATE TABLE IF NOT EXISTS usuarios (
                                        id SERIAL PRIMARY KEY,
                                        nombre VARCHAR(50),
                                        apellido VARCHAR(50),
                                        telefono VARCHAR(15)
);

-- Crear función para calcular el total
CREATE OR REPLACE FUNCTION calculate_total() RETURNS TRIGGER AS $$
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

-- Crear trigger para ejecutar la función después de cada inserción o actualización en quotation_items
DROP TRIGGER IF EXISTS trg_calculate_total ON quotation_items;
CREATE TRIGGER trg_calculate_total
    AFTER INSERT OR UPDATE ON quotation_items
    FOR EACH ROW EXECUTE FUNCTION calculate_total();

-- Insertar datos iniciales
INSERT INTO usuarios (nombre, apellido, telefono) VALUES
                                                      ('Andres', 'Sepulveda', '9 2312 2411'),
                                                      ('Alejandro', 'Valenzuela', '9 4112 6444'),
                                                      ('Felipe', 'Kessi', '9 3222 1222'),
                                                      ('Nelson', 'Nelcarca', '9 2333 4555'),
                                                      ('Rigoberto', 'Allirue', '9 1555 4333');

-- Insertar productos
INSERT INTO products (name, price) VALUES
                                       ('Producto A', 10.00),
                                       ('Producto B', 15.50),
                                       ('Producto C', 20.00),
                                       ('Producto D', 25.00),
                                       ('Producto E', 30.00);

-- Insertar cotizaciones
INSERT INTO quotations DEFAULT VALUES; -- Cotización 1
INSERT INTO quotations DEFAULT VALUES; -- Cotización 2
INSERT INTO quotations DEFAULT VALUES; -- Cotización 3
INSERT INTO quotations DEFAULT VALUES; -- Cotización 4
INSERT INTO quotations DEFAULT VALUES; -- Cotización 5

-- Insertar items de cotización
INSERT INTO quotation_items (quotation_id, product_id, quantity) VALUES
                                                                     (1, 1, 2), -- 2 x Producto A
                                                                     (1, 2, 1), -- 1 x Producto B
                                                                     (2, 2, 3), -- 3 x Producto B
                                                                     (2, 3, 2), -- 2 x Producto C
                                                                     (3, 1, 1), -- 1 x Producto A
                                                                     (3, 4, 2), -- 2 x Producto D
                                                                     (4, 3, 1), -- 1 x Producto C
                                                                     (4, 5, 1), -- 1 x Producto E
                                                                     (5, 1, 3), -- 3 x Producto A
                                                                     (5, 5, 2); -- 2 x Producto E
