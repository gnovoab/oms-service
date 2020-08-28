-- -----------------------------------------
--  Table structure for order
-- -----------------------------------------

CREATE SEQUENCE order_id_seq;

CREATE TABLE orders (
  id int8 NOT NULL DEFAULT nextval('order_id_seq'),
  reference text NOT NULL,
  products jsonb NOT NULL,
  customer jsonb NOT NULL,
  delivery_address jsonb NOT NULL,
  promotion int4,
  amount float8 NOT NULL,
  status int4 NOT NULL,
  date timestamp(6) NOT NULL  DEFAULT now(),

  PRIMARY KEY (id)
);

ALTER SEQUENCE order_id_seq OWNED BY orders.id;



-- -----------------------------------------
--  Table structure for promotion
-- -----------------------------------------

CREATE SEQUENCE promotion_id_seq;

CREATE TABLE promotion (
  id int8 NOT NULL DEFAULT nextval('promotion_id_seq'),
  name text NOT NULL,
  description text NOT NULL,
  discount float8 NOT NULL,
  PRIMARY KEY (id)
);

ALTER SEQUENCE promotion_id_seq OWNED BY promotion.id;

