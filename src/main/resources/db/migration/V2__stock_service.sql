ALTER TABLE products ADD COLUMN IF NOT EXISTS stock_amount INTEGER DEFAULT 0;
ALTER TABLE products ADD COLUMN IF NOT EXISTS price REAL DEFAULT 0;

CREATE UNIQUE INDEX IF NOT EXISTS unique_product_name ON PRODUCTS(name);

CREATE TABLE IF NOT EXISTS product_reservation(
    id					SERIAL          PRIMARY KEY,
    product_id          SERIAL          NOT NULL,
	expiration_date		TIMESTAMPTZ		NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	amount              INTEGER         NOT NULL DEFAULT (0),
    expired             BOOLEAN         NOT NULL DEFAULT (FALSE),
    confirmed           BOOLEAN         NOT NULL DEFAULT (FALSE),
	CONSTRAINT			fk_product_id	FOREIGN KEY(product_id)	REFERENCES products(id) ON DELETE CASCADE
);
