SELECT 'USE avasay-ncaaf' AS ' ';
USE avasay-ncaaf;

 INSERT INTO Players VALUES('123456789','Johnny','Manziel','123 Market', 'Tyler','TX','2225557777',120,180,24);
 INSERT INTO Players VALUES('113456789','AJ','McCaron','113 Main', 'Birmingham','AL','2125557777',110,110,21);
 INSERT INTO Players VALUES('111456789','Ben','Malena','111 Church', 'Miami','FL','2115557777',111,118,24);
 INSERT INTO Players VALUES('111156789','Bo','Wallace','110 Lane', 'Abeelen','MI','2111557777',125,185,29);
 INSERT INTO Players VALUES('111116789','Brandon','Allen','125 Groin', 'Athens','AK','2111157777',150,150,24);
 INSERT INTO Players VALUES('111111789','Garrett','Gilbert','213 Lourdes', 'Greece','TX','2111117777',220,280,20);

 INSERT INTO Players VALUES('111111189','Taylor','Bertolet','222 Granite', 'Irving','TX','2211117977',200,180,21);
 INSERT INTO Players VALUES('111111119','Bryson','Rose','44 Night', 'Pluck','MI','2222117977',100,140,26);
INSERT INTO Players VALUES('111111129','Cade','Foster','33 Meek', 'Long','AL','2221117977',200,130,25);
INSERT INTO Players VALUES('111111139','Zack','Hocker','55 Groin', 'Lipps','AK','2221217977',250,190,28);
INSERT INTO Players VALUES('111111111','Chris','Boswell','66 Richardson', 'Richardson','TX','3221217977',205,120,29);

INSERT INTO Players VALUES('111111110','Julian','Horton','43 Dixon', 'Lame','AK','3331217977',230,190,19);


INSERT INTO Teams VALUES('Texas A&M','Aggies','College Station','TX','Reveille','Kyle Field','Kevin Sumlin','Mark Snyder','Jake Spavital');
 INSERT INTO Teams VALUES('Alabama','Crimson Tide','Tuscaloosa','AL','Big Al','Bryan-Denny','Nick Saban','Kirby Smart','Doug Nussmeier');
 INSERT INTO Teams VALUES('Texas','Longhorns','Austin','TX','Bevo','Darell K Royal','Mack Brown','Manny Diaz','Major Applewhite');
 INSERT INTO Teams VALUES('Mississippi','Rebels','Oxford','MI','Rebel Black Bear','Vaught-Hemingway Stadium','Hugh Freeze','Dave Womack','Maurice Harris');
 INSERT INTO Teams VALUES('Notre Dame','Fighting Irish','South Bend','IN','Fighting Sioux','Notre Dame Stadium','Brian Kelly','Bob Diaco','Chuck Martin');
 INSERT INTO Teams VALUES('Oregon','Ducks','Eugene','OR','Oregon Duck','Autzen Staidum','Mark Helfrich','Nick Alliotti','Scott Frost');
 INSERT INTO Teams VALUES('SMU','Mustangs','Dallas','TX','Peruna','Moody Coliseum','June Jones','Tom Mason','Hal Mumme');
 INSERT INTO Teams VALUES('Rice','Owls','Houston','TX','Sammy the Owl','Rice Stadium','David Bailiff','Chuck Driesbach','Ronnie Vinklarek');
 INSERT INTO Teams VALUES('Sam Houston','Bearkats','Houston','TX','Sammy Bearkat','Elliott Bowers Stadium','Willie Fritz','Mike Collins','Doug Ruse');
 INSERT INTO Teams VALUES('Arkansas','Razorbacks','Fayetteville','AK','Tusk','Reynolds Razorback Stadium','Bret Bielema','Chris Ash','Jim Chaney');


INSERT INTO Games VALUES(DEFAULT,'2013-09-14','College Station','14:30:00','Texas A&M','Alabama',49,42);
INSERT INTO Games VALUES(DEFAULT,'2013-08-31','College Station','12:00:00','Texas A&M','Rice',52,31);
INSERT INTO Games VALUES(DEFAULT,'2013-09-07','College Station','18:00:00','Texas A&M','SMU',42,13);
INSERT INTO Games VALUES(DEFAULT,'2013-09-21','College Station','18:00:00','Texas A&M','Sam Houston',65,28);
INSERT INTO Games VALUES(DEFAULT,'2013-09-28','Fayetteville','18:00:00','Arkansas','Texas A&M',45,33);
INSERT INTO Games VALUES(DEFAULT,'2013-10-12','Oxford','19:30:00','Mississippi','Texas A&M',0,0);


SELECT ' ' AS 'Inserting rows into Conferences';
INSERT INTO Conferences VALUES('SEC','Southeastern Conference','I','Birmingham, Alabama',1932);
INSERT INTO Conferences VALUES('Pac-12','Pacific-12 Conference','I','Walnut Creek, California',1915);
INSERT INTO Conferences VALUES('MWC','Mountain West Conference','I','Colorado Springs, Colorado',1999);
INSERT INTO Conferences VALUES('Big 12','Big 12 Conference','I','Irving, Texas',1996);
INSERT INTO Conferences VALUES('Big Ten','Big Ten Conference','I','Park Ridge, Illinois',1896);
INSERT INTO Conferences VALUES('ACC','Atlantic Conference','I','Greensboro, North Carolina',1953);


INSERT INTO DefensePerformance VALUES(1,'123456789',110,0,40,1);
INSERT INTO DefensePerformance VALUES(1,'113456789',220,0,0,1);
INSERT INTO DefensePerformance VALUES(2,'111456789',0,3340,0,2);
INSERT INTO DefensePerformance VALUES(6,'111156789',0,650,0,2);
INSERT INTO DefensePerformance VALUES(5,'111116789',0,220,0,3);
INSERT INTO DefensePerformance VALUES(3,'111111789',65,54,6,4);

INSERT INTO OffensePerformance VALUES(1,'123456789',300,200,0,4);
INSERT INTO OffensePerformance VALUES(1,'113456789',197,230,0,5);
INSERT INTO OffensePerformance VALUES(2,'111456789',0,240,140,3);
INSERT INTO OffensePerformance VALUES(6,'111156789',150,200,0,2);
INSERT INTO OffensePerformance VALUES(5,'111116789',200,110,0,1);
INSERT INTO OffensePerformance VALUES(3,'111111789',130,100,0,2);

INSERT INTO KickerPerformance VALUES(1,'111111189',3,2);
INSERT INTO KickerPerformance VALUES(6,'111111119',3,2);
INSERT INTO KickerPerformance VALUES(1,'111111129',1,1);
INSERT INTO KickerPerformance VALUES(5,'111111139',6,2);
INSERT INTO KickerPerformance VALUES(2,'111111111',4,3);

INSERT INTO PlayerInTeam VALUES('123456789','Texas A&M','Quarterback', 2);
INSERT INTO PlayerInTeam VALUES('113456789','Alabama','Quarterback', 10);
INSERT INTO PlayerInTeam VALUES('111456789','Texas A&M','Running Back', 1);
INSERT INTO PlayerInTeam VALUES('111111111','Rice','Kicker', 9);
INSERT INTO PlayerInTeam VALUES('111156789','Mississippi','Quarterback', 14);
INSERT INTO PlayerInTeam VALUES('111111110','Arkansas','Wide Receiver', 2);

INSERT INTO PlayerPerformance VALUES(1,'123456789');
INSERT INTO PlayerPerformance VALUES(1,'113456789');
INSERT INTO PlayerPerformance VALUES(2,'111456789');
INSERT INTO PlayerPerformance VALUES(6,'111156789');
INSERT INTO PlayerPerformance VALUES(5,'111116789');
INSERT INTO PlayerPerformance VALUES(3,'111111789');

INSERT INTO PlayerPerformance VALUES(1,'111111189');
INSERT INTO PlayerPerformance VALUES(6,'111111119');
INSERT INTO PlayerPerformance VALUES(1,'111111129');
INSERT INTO PlayerPerformance VALUES(5,'111111139');
INSERT INTO PlayerPerformance VALUES(2,'111111111');

INSERT INTO TeamInGame VALUES(5,'Arkansas');
INSERT INTO TeamInGame VALUES(1,'Texas A&M');
INSERT INTO TeamInGame VALUES(6,'Mississippi');
INSERT INTO TeamInGame VALUES(3,'SMU');
INSERT INTO TeamInGame VALUES(2,'Rice');


INSERT INTO ConferenceMembership VALUES('SEC','Alabama');
INSERT INTO ConferenceMembership VALUES('SEC','Texas A&M');
INSERT INTO ConferenceMembership VALUES('SEC','Mississippi');
INSERT INTO ConferenceMembership VALUES('Pac-12','Oregon');
INSERT INTO ConferenceMembership VALUES('ACC','Notre Dame');


SELECT 'SELECT * FROM Players; ' AS '';
SELECT * FROM Players;

SELECT 'SELECT * FROM Teams; ' AS '';
SELECT * FROM Teams;

SELECT 'SELECT * FROM Games; ' AS '';
SELECT * FROM Games;

SELECT 'SELECT * FROM Conferences; ' AS '';
SELECT * FROM Conferences;

SELECT 'SELECT * FROM DefensePerformance; ' AS '';
SELECT * FROM DefensePerformance;

SELECT 'SELECT * FROM OffensePerformance; ' AS '';
SELECT * FROM OffensePerformance;

SELECT 'SELECT * FROM KickerPerformance; ' AS '';
SELECT * FROM KickerPerformance;

SELECT 'SELECT * FROM PlayerInTeam; ' AS '';
SELECT * FROM PlayerInTeam;

SELECT 'SELECT * FROM PlayerPerformance; ' AS '';
SELECT * FROM PlayerPerformance;

SELECT 'SELECT * FROM TeamInGame; ' AS '';
SELECT * FROM TeamInGame;

SELECT 'SELECT * FROM ConferenceMembership; ' AS '';
SELECT * FROM ConferenceMembership;