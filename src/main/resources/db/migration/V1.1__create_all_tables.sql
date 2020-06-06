
create table Companies (
        id                  BIGSERIAL not null unique,
	    companyName			varchar(30) not null unique,
	    industry			varchar,
	    description			varchar not null,
	    number 		        Integer,
	    location			varchar not null,
	    webPageAddress		varchar not null
);
Alter table Companies
Add constraint companiesId_pk Primary Key(id);


create table Consoles(

        id                  BIGSERIAL not null unique,
	    consoleName			varchar(30) not null,
	    price				NUMERIC(5,2) not null,
	    issueTime			varchar not null,
	    color				varchar(30) not null,
	    developer			varchar(30) not null,
	    whatIncluded        varchar not null

);
Alter table Consoles
Add constraint consolesId_pk Primary key (id);


Create table Games(
        id                  BIGSERIAL not null unique,
		gamesName		    varchar(50) not null unique,
		overview			varchar not null,
		price			    NUMERIC(5,2) not null,
		releaseTime       	varchar not null,
		supportPlatform   	varchar(30) not null,
		developer		    varchar(30) not null,
		whatIncluded		varchar not null
);
Alter table Games
Add constraint gamesId_pk Primary key (id);



