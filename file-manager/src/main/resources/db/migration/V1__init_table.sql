CREATE TABLE file_meta(
    id bigserial NOT NULL,
    hash uuid NOT NULL,
    file_name varchar(255) NOT NULL,
    sub_type integer,
    CONSTRAINT file_meta_pkey PRIMARY KEY (id),
    CONSTRAINT hash_code_key UNIQUE (hash)
);