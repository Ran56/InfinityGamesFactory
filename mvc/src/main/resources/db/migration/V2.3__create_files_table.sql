CREATE TABLE Files (
        id                  BIGSERIAL NOT NULL UNIQUE,
	    original_s3Key		VARCHAR NOT NULL,
	    bucket_name			VARCHAR NOT NULL,
	    uuid_s3key			VARCHAR NOT NULL UNIQUE,
	    user_id		        BIGINT NOT NULL
);
ALTER TABLE Files ADD CONSTRAINT files_pk PRIMARY KEY ( id );
ALTER TABLE Files
    ADD CONSTRAINT files_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id );