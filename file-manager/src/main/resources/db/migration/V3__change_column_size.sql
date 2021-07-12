ALTER TABLE file_meta
DROP COLUMN file_size;

ALTER TABLE file_meta
ADD COLUMN file_size integer NOT NULL;