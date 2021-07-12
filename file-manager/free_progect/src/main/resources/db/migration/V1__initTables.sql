CREATE SEQUENCE hibernate_sequence
    START WITH 66
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

CREATE TABLE base_time_entity
(
    id         bigserial NOT NULL,
    created_at timestamp DEFAULT current_timestamp,
    updated_at timestamp DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id        bigserial NOT NULL,
    role_name varchar(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id          bigserial    NOT NULL,
    email       varchar(255) NULL UNIQUE,
    father_name varchar(255) NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    password  varchar(255) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES base_time_entity (id)
);

CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE products
(
    description varchar(255) NULL,
    title       varchar(255) NOT NULL,
    id          bigserial    NOT NULL,
    product_price_id bigint NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES base_time_entity (id)
);

CREATE TABLE orders
(
    total_price bigint NULL,
    id          bigserial NOT NULL,
    user_id     bigint NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (id) REFERENCES base_time_entity (id)
);

CREATE TABLE prices
(
    id   bigserial NOT NULL,
    cost bigint NULL,
    CONSTRAINT prices_pkey PRIMARY KEY (id)
);

CREATE TABLE products_prices
(
    id         bigserial NOT NULL,
    price_id   bigint NULL,
    product_id bigint NULL,
    CONSTRAINT products_prices_pkey PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES base_time_entity (id),
    FOREIGN KEY (price_id) REFERENCES prices (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE order_items
(
    id               bigserial NOT NULL,
    quantity         int NULL,
    order_id         bigint NULL,
    product_price_id bigint    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_price_id) REFERENCES products_prices (id)
);