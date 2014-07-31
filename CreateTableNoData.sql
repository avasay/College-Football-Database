SELECT 'USE avasay-ncaaf' AS ' ';
USE ncaaf;

CREATE TABLE Players (
	SSN	CHAR(9) PRIMARY KEY NOT NULL,
	FirstName	VARCHAR(25),
	LastName 	VARCHAR(25),
	Address	VARCHAR(50),
	City	VARCHAR(25),
	State	CHAR(2),
	Phone	CHAR(10),
	Weight	FLOAT,
	Height	FLOAT,
	Age	INT
);

SHOW TABLES;
SELECT 'SHOW COLUMNS in Players' AS 'Table Player Successfully Created! ';
SHOW COLUMNS IN Players;


CREATE TABLE Teams (
	SchoolName 	VARCHAR(100) PRIMARY KEY NOT NULL,
	NickName		VARCHAR(25),
	City		VARCHAR(25),
	State		CHAR(2),
	Mascot		VARCHAR(15),
	Stadium		VARCHAR(15),
	HeadCoach	VARCHAR(50),
	DefensiveCoordinator	VARCHAR(50),
	OffensiveCoordinator	VARCHAR(50)
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN Teams; ' AS 'Table Teams Successfuly Created! ';
SHOW COLUMNS IN Teams;

CREATE TABLE PlayerInTeam (
	SSN		CHAR(9) NOT NULL,
	SchoolName 	VARCHAR(100) NOT NULL,
	Position		VARCHAR(25),
	JerseyNum 	INT,
	PRIMARY KEY(SSN, SchoolName),
	FOREIGN KEY(SSN) REFERENCES Players(SSN)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(SchoolName) REFERENCES Teams(SchoolName)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN PlayerInTeam; ' AS 'Table PlayerInTeam Successfully Created! ';
SHOW COLUMNS IN PlayerInTeam;


CREATE TABLE Games (
	ID		INT PRIMARY KEY NOT NULL,
	Date		DATE,
	Place		VARCHAR(25),
	Time		TIME,
	HomeTeam		VARCHAR(100),
	VisitingTeam 	VARCHAR(100),
	HomeTeamScore 	INT,
	VisitingTeamScore 	INT,
	FOREIGN KEY (HomeTeam) REFERENCES Teams(SchoolName)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
	FOREIGN KEY (VisitingTeam) REFERENCES Teams(SchoolName)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN Games;' AS 'Table Games Successfully Created! ';
SHOW COLUMNS IN Games;


CREATE TABLE TeamInGame(
	GameID	INT NOT NULL,
	SchoolName VARCHAR(100) NOT NULL,
	PRIMARY KEY(GameID, SchoolName),
	FOREIGN KEY(GameID) REFERENCES Games(ID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(SchoolName) REFERENCES Teams(SchoolName)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN TeamInGame; ' AS 'Table TeamInGame Successfully Created! ';
SHOW COLUMNS IN TeamInGame;



CREATE TABLE Conferences (
	NickName	VARCHAR(25) PRIMARY KEY NOT NULL,
	FullName	VARCHAR(50),
	Division	VARCHAR(3),
	Headquarter VARCHAR(50),
	Founded	INT
);


SHOW TABLES;
SELECT 'SHOW COLUMNS IN Conferences; ' AS 'Table Conferences Successully Created! ';
SHOW COLUMNS IN Conferences;


CREATE TABLE PlayerPerformance(
	GameID	INT NOT NULL,
	SSN	CHAR(9) NOT NULL,
	PRIMARY KEY(GameID, SSN),
	FOREIGN KEY(SSN) REFERENCES Players(SSN)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(GameID) REFERENCES Games(ID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN PlayerPerformance;' AS 'Table PlayerPerformance Successully Created! ';
SHOW COLUMNS IN PlayerPerformance;


CREATE TABLE DefensePerformance(
	GameID		INT NOT NULL,
	SSN		CHAR(9) NOT NULL,
	PassingYardsAllowed	INT,
	RushingYardsAllowed	INT,
	Sacks		INT,
	Interceptions	INT,
	PRIMARY KEY(GameID, SSN),
	FOREIGN KEY (GameID) REFERENCES Games(ID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (SSN) REFERENCES Players(SSN)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN DefensePerformance; ' AS 'Table DefensePerformance Successfuly Created! ';
SHOW COLUMNS IN DefensePerformance;

CREATE TABLE OffensePerformance(
	GameID		INT NOT NULL,
	SSN		CHAR(9) NOT NULL,
	PassingYards	INT,
	RushingYards	INT,
	ReceivingYards	INT,
	Touchdowns	INT,
	PRIMARY KEY(GameID, SSN),
	FOREIGN KEY (GameID) REFERENCES Games(ID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (SSN) REFERENCES Players(SSN)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN OffensePerformance; ' AS 'Table OffensePerformance Successfuly Created! ';
SHOW COLUMNS IN OffensePerformance;


CREATE TABLE KickerPerformance(
	GameID		INT NOT NULL,
	SSN		CHAR(9) NOT NULL,
	FieldGoals		INT,
	ExtraPoints		INT,
	PRIMARY KEY(GameID, SSN),
	FOREIGN KEY (GameID) REFERENCES Games(ID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (SSN) REFERENCES Players(SSN)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN KickerPerformance;' AS 'Table KickerPerformance Successfully Created!';
SHOW COLUMNS IN KickerPerformance;



CREATE TABLE ConferenceMembership(
	ConferenceName 	VARCHAR(25) NOT NULL,
	SchoolName 	VARCHAR(100) NOT NULL,
	PRIMARY KEY(ConferenceName, SchoolName),
	FOREIGN KEY(ConferenceName) REFERENCES Conferences(NickName)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(SchoolName) REFERENCES Teams(SchoolName)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

SHOW TABLES;
SELECT 'SHOW COLUMNS IN ConferenceMembership;' AS 'Table ConferenceMembership Successfuly Created! ';
SHOW COLUMNS IN ConferenceMembership;

