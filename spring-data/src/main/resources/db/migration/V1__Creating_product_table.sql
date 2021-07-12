CREATE TABLE product
(
    id    serial       NOT NULL,
    title varchar(255) NOT NULL UNIQUE,
    price bigint       NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)