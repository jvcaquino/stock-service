CREATE VIEW available_stock AS SELECT products.id AS product_id, name, price, stock_amount - COALESCE(sum(amount), 0) AS available_amount
FROM products LEFT JOIN product_reservation ON products.id = product_reservation.product_id AND expired = FALSE
GROUP BY products.id ORDER BY product_id;

CREATE FUNCTION get_stock(integer) RETURNS INTEGER
	AS 'SELECT available_amount FROM available_stock WHERE product_id = $1;'
	LANGUAGE SQL IMMUTABLE RETURNS NULL ON NULL INPUT;

CREATE OR REPLACE FUNCTION enough_stock() RETURNS TRIGGER AS $enough_stock$
	BEGIN
		IF (get_stock(NEW.product_id) < NEW.amount) THEN
			RAISE EXCEPTION 'Unable to create reservation due to insufficient stock';
	 	END IF;
	 	RETURN NEW;
	END;
$enough_stock$ LANGUAGE PLPGSQL;

CREATE TRIGGER enough_stock BEFORE INSERT ON product_reservation FOR EACH ROW EXECUTE PROCEDURE enough_stock();