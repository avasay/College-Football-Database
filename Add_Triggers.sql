-- Do not allow a conference to have more than 10 members
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

