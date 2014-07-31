-----------------------------------------------------------------------------------
-- Created this table to contain log errors when there's errors such as duplicate
-- mostly used in Triggers Before Insert operations
-----------------------------------------------------------------------------------
/*
create table errorTable(message_field varchar(100) unique) engine INNODB;

DELIMITER //

CREATE PROCEDURE fbError(IN errorText VARCHAR(100))

BEGIN
    
DECLARE errorMess varchar(100);
   
 select concat(DATE_FORMAT(now(),"%Y%m%d %T")," : ", errorText) into errorMess;
    
INSERT IGNORE INTO errorTable VALUES (errorMess);
    
INSERT INTO errorTable VALUES (errorMess);

END;
//

DELIMITER ;

*/

------------------------------------------------------------------------------
-- ATTRIBUTE and TUPLE-BASED CONSTRAINTS
-- CHECK and ASSERTION are ignored by MySQL engine. It doesn't work.
------------------------------------------------------------------------------
--No two teams should have a coach with the same name
ALTER TABLE Teams ADD CONSTRAINT OneCoachName 
	CHECK (HeadCoach NOT IN (SELECT HeadCoach FROM Teams )) ;

--No player should be younger than 17 or older than 30
ALTER TABLE Players ADD CONSTRAINT NotTooYoungOrOld 
	CHECK (Age>=17 AND Age<=30) ;

--No two teams shoudl have a mascot with the same name
ALTER TABLE Teams ADD CONSTRAINT OneMascotName 
	CHECK (Mascot NOT IN (SELECT Mascot FROM Teams )) ;

--No team should be in two different conferences
ALTER TABLE ConferenceMembership ADD CONSTRAINT JustOneMembership 
	CHECK (SchoolName NOT IN (SELECT SchoolName FROM ConferenceMembership)) ;




-------------------------------------------------------------------------------
-- ASSERTION
-- MySQL does not support ASSERTION, so the code below returns ERROR
-------------------------------------------------------------------------------
--Do not allow a conference to have more than 10 members
/*
CREATE ASSERTION ConferenceMembershipUpTo10 CHECK

(10 >=ALL
(SELECT COUNT(schoolname) 
FROM ConferenceMembership 

GROUP BY ConferenceName)
);
*/


--------------------------------------------------------------------------------
-- TRIGGER
--------------------------------------------------------------------------------
--Do not allow a conference to have more than 10 members
DELIMITER //

CREATE TRIGGER ConferenceMembershipUpTo10

AFTER INSERT ON ConferenceMembership

FOR EACH ROW BEGIN
	
   IF((SELECT COUNT(SchoolName) 
        
         FROM ConferenceMembership 
		
         WHERE ConferenceName=NEW.ConferenceName
		
         GROUP BY ConferenceName) > 10) THEN 
	
              DELETE FROM ConferenceMembership
	
             WHERE (ConferenceName=New.ConferenceName
	
             AND SchoolName=New.SchoolName);

END IF;

END; //

DELIMITER ;

-- Player should be between 17 and 30 years old inclusive
 
DELIMITER //

CREATE TRIGGER InsertAge17And30
AFTER INSERT ON Players

FOR EACH ROW BEGIN

   IF 17 > new.Age OR 30 < new.Age THEN
	
      DELETE FROM Players WHERE Age = new.Age;

   END IF;

END; 
//

DELIMITER ;

-- DROP TRIGGER InsertAge17And30;


-- No two teams should have a coach with the same name

DELIMITER //

CREATE TRIGGER CoachNotOnTwoTeams

BEFORE INSERT ON Teams

FOR EACH ROW BEGIN
   
IF NEW.HeadCoach IN(SELECT HeadCoach FROM Teams) THEN
      
    INSERT INTO Teams VALUES(
      
    NEW.SchoolName, NEW.NickName, NEW.City, NEW.State, 
      
    NEW.Mascot, NEW.Stadium,"N/A", NEW.DefensiveCoordinator, 
	  
    NEW.OffensiveCoordinator);
   
END IF;

END; 
//

DELIMITER ;

-- DROP TRIGGER CoachNotOnTwoTeams

-- Team cannot be in another conference. Also means a team should not be 
-- mentioned twice in the ConferenceMembership

DELIMITER //

CREATE TRIGGER TeamNotOnTwoConferences

BEFORE INSERT ON ConferenceMembership

FOR EACH ROW BEGIN
   
   IF NEW.SchoolName IN(SELECT SchoolName FROM ConferenceMembership)
   THEN
      
        call fbError("Error: Cannot have duplicate team in conference. Aborting insert.");
   
END IF;

END; 
//

DELIMITER ;

-- Can't add a Kicker in the OffensePerformance table
-- Test SSN=205109323
DELIMITER //

CREATE TRIGGER KickerNoTDefenseOffense

BEFORE INSERT ON OffensePerformance

FOR EACH ROW BEGIN
   
   IF NEW.SSN IN(SELECT SSN FROM KickerPerformance)
   THEN
 
       call fbError("Error: Aborting Insert. Player is a Kicker. Cannot be an Offense Player.");

END IF;

END; 
//

DELIMITER ;


-- Can't add a player performance with that game ID unless he is in that game
DELIMITER //

CREATE TRIGGER PlayerMustBeInGame

AFTER INSERT ON OffensePerformance

FOR EACH ROW BEGIN
   
IF NEW.SSN NOT IN(SELECT SSN 
                     
FROM PlayerInTeam, Games
					 
WHERE PlayerInTeam.SchoolName=Games.HomeTeam
					 
OR PlayerInTeam.SchoolName=Games.VisitingTeam)
   THEN
        
DELETE FROM OffensePerformance 
WHERE SSN=NEW.SSN;

END IF;

END; 

//
DELIMITER ;

--------------------------------------------------------------------------------------
-- VIEWS
--------------------------------------------------------------------------------------

-- Create a view of the player names, their team names, and their conference they belong to.
CREATE VIEW PlayersAndTeamNames AS

SELECT Players.SSN, Players.FirstName, Players.LastName, PlayerInTeam.SchoolName, 
ConferenceMembership.ConferenceName

From Players, PlayerInTeam, ConferenceMembership

WHERE Players.SSN=PlayerInTeam.SSN 
AND PlayerInTeam.SchoolName=ConferenceMembership.SchoolName;

-- Then, using the view above, display all the players names, their teams, and their offense performance
CREATE VIEW OffensePerformances AS
SELECT PlayersAndTeamNames.FirstName, PlayersAndTeamNames.LastName, 
PlayersAndTeamNames.SchoolName,
OffensePerformance.GameID, 
OffensePerformance.PassingYards, OffensePerformance.RushingYards,

OffensePerformance.ReceivingYards, OffensePerformance.Touchdowns 

FROM PlayersAndTeamNames, OffensePerformance

WHERE PlayersAndTeamNames.SSN = OffensePerformance.SSN;

-- Display all Players, Their teams, and their defense performance
CREATE VIEW DefensePerformances AS
SELECT PlayersAndTeamNames.FirstName, PlayersAndTeamNames.LastName, 
PlayersAndTeamNames.SchoolName,
DefensePerformance.GameID, 
DefensePerformance.PassingYardsAllowed, DefensePerformance.RushingYardsAllowed,

DefensePerformance.Sacks, DefensePerformance.Interceptions 

FROM PlayersAndTeamNames, DefensePerformance

WHERE PlayersAndTeamNames.SSN = DefensePerformance.SSN;

-- Who is the best Defensive Player in the league
SELECT FirstName, LastName, (PassingYardsAllowed+RushingYardsAllowed) AS YardsAllowed

FROM DefensePerformances

WHERE (PassingYardsAllowed+RushingYardsAllowed)=(SELECT MAX(PassingYardsAllowed+RushingYardsAllowed)

FROM DefensePerformances);

-- Who is the worst Defensive Player in the league
SELECT FirstName, LastName, (PassingYardsAllowed+RushingYardsAllowed) AS YardsAllowed

FROM DefensePerformances

WHERE (PassingYardsAllowed+RushingYardsAllowed)=(SELECT MIN(PassingYardsAllowed+RushingYardsAllowed)

FROM DefensePerformances);

-- Who is the best Offense Player in the league
SELECT FirstName, LastName, (PassingYards+RushingYards+ReceivingYards) AS Yards

FROM OffensePerformances

WHERE (PassingYards+RushingYards+ReceivingYards)=

(SELECT MAX(PassingYards+RushingYards+ReceivingYards)

FROM OffensePerformances);

-- Who is the worst Offense Player in the league
SELECT FirstName, LastName, (PassingYards+RushingYards+ReceivingYards) AS Yards

FROM OffensePerformances

WHERE (PassingYards+RushingYards+ReceivingYards)=

(SELECT MIN(PassingYards+RushingYards+ReceivingYards)

FROM OffensePerformances);

-- Average HomeScore and VisitingScore of each team
CREATE VIEW AverageHomeVisitingTeamScore AS 

SELECT HomeTeam AS Team, AVG(HomeTeamScore) AS AverageScore

FROM Games

GROUP BY HomeTeam

UNION

SELECT VisitingTeam AS Team, AVG(VisitingTeamScore) AS AverageScore

FROM Games

GROUP BY VisitingTeam;

-- Combines Average Score of each team by using the view above. A view that uses a view



CREATE VIEW AverageTeamScore AS

SELECT Team, AVG(AverageScore) AS AvgScore
FROM AverageHomeVisitingTeamScore

GROUP BY Team;


-- Who is the best team in the league based on combined total score?
SELECT Team, AvgScore

FROM AverageTeamScore

WHERE AvgScore=
(SELECT MAX(AvgScore)

FROM AverageTeamScore);

-- Who is the worst team in the league based on combined total score?
SELECT Team, AvgScore

FROM AverageTeamScore

WHERE AvgScore=
(SELECT MIN(AvgScore)

FROM AverageTeamScore);
