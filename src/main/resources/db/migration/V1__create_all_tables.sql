
CREATE TABLE Companies (
        id                  BIGSERIAL NOT NULL UNIQUE,
	    name			    VARCHAR (30) NOT NULL UNIQUE,
	    industry			VARCHAR,
	    description			VARCHAR,
	    location			VARCHAR,
	    webPageAddress		VARCHAR
);
ALTER TABLE Companies
ADD CONSTRAINT companiesId_pk PRIMARY KEY(id);


CREATE TABLE Consoles(
        id                  BIGSERIAL NOT NULL UNIQUE,
	    name			    VARCHAR(30) NOT NULL,
	    price				MONEY,
	    issueTime			DATE,
	    color				VARCHAR,
	    company_id		    BIGINT NOT NULL,
	    whatIncluded        VARCHAR
);
ALTER TABLE Consoles
ADD CONSTRAINT consolesId_pk PRIMARY KEY (id);


CREATE TABLE Games(
        id                  BIGSERIAL NOT NULL UNIQUE,
		name		        VARCHAR(50) NOT NULL UNIQUE,
		price			    MONEY,
		genre			    VARCHAR,
		players             VARCHAR,
		releaseTime       	DATE,
		console_id   	    BIGINT NOT NULL,
		supportedLanguages	VARCHAR
);
ALTER TABLE Games
ADD CONSTRAINT gamesId_pk PRIMARY KEY (id);


ALTER TABLE Consoles
    ADD CONSTRAINT console_company_fk FOREIGN KEY ( company_id )
        REFERENCES Companies ( id );
ALTER TABLE Games
    ADD CONSTRAINT game_console_fk FOREIGN KEY ( console_id )
        REFERENCES Consoles ( id );
