Create table Games(
        id                  BIGSERIAL not null unique,
		gamesName		    varchar(50) not null unique,
		price			    NUMERIC(5,2) not null,
		genre			    varchar not null,
		players             varchar not null,
		releaseTime       	varchar not null,
		supportPlatform   	varchar(30) not null,
		developer		    varchar(30) not null,
		supportedLanguages	varchar not null
);
Alter table Games
Add constraint gamesId_pk Primary key (id);






