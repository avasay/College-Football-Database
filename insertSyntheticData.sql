SELECT ' ' AS 'Begin Loading Synthetic Data';
SELECT 'LOAD DATA LOCAL INFILE PlayersFile.txt INTO TABLE Players; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'PlayersFile.txt' INTO TABLE Players FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE TeamFile.txt INTO TABLE Teams; ' AS 'Executing command ';
 LOAD DATA LOCAL INFILE 'TeamFile.txt' INTO TABLE Teams FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE GameFile.txt INTO TABLE Games; ' AS 'Executing command ';
 LOAD DATA LOCAL INFILE 'GameFile.txt' INTO TABLE Games FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE ConfFile.txt INTO TABLE Conferences; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'ConfFile.txt' INTO TABLE Conferences FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE DefenseFile.txt INTO TABLE DefensePerformance; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'DefenseFile.txt' INTO TABLE DefensePerformance FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE OffenseFile.txt INTO TABLE OffensePerformance;' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'OffenseFile.txt' INTO TABLE OffensePerformance FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE KickFile.txt INTO TABLE KickerPerformance; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'KickFile.txt' INTO TABLE KickerPerformance FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE PlayerTeamFile.txt INTO TABLE PlayerInTeam; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'PlayerTeamFile.txt' INTO TABLE PlayerInTeam FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE PlayerPerfFile.txt INTO TABLE PlayerPerformance; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'PlayerPerfFile.txt' INTO TABLE PlayerPerformance FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE TeamGameFile.txt INTO TABLE TeamInGame; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'TeamGameFile.txt' INTO TABLE TeamInGame FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT 'LOAD DATA LOCAL INFILE ConfMembersFile.txt INTO TABLE ConferenceMembership; ' AS 'Executing command ';
LOAD DATA LOCAL INFILE 'ConfMembersFile.txt' INTO TABLE ConferenceMembership FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

SELECT ' ' AS 'Finished Loading Synthetic Data';