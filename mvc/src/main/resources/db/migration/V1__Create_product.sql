CREATE TABLE product (
    id serial NOT NULL,
    title varchar(255) NOT NULL,
    price decimal NOT NULL,
    description varchar(255) NULL ,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);