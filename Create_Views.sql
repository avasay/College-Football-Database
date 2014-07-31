-- ------------------------------------------------------------------------------------
-- VIEWS
-- ------------------------------------------------------------------------------------

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

-- Who is the best Offense Player in the league
CREATE VIEW BestOffensivePlayer AS
SELECT FirstName, LastName, (PassingYards+RushingYards+ReceivingYards) AS Yards
FROM OffensePerformances
WHERE (PassingYards+RushingYards+ReceivingYards)=
(SELECT MAX(PassingYards+RushingYards+ReceivingYards)
FROM OffensePerformances);


-- Who is the worst Offense Player in the league
CREATE VIEW WorstOffensivePlayer AS
SELECT FirstName, LastName, (PassingYards+RushingYards+ReceivingYards) AS Yards
FROM OffensePerformances
WHERE (PassingYards+RushingYards+ReceivingYards)=
(SELECT MIN(PassingYards+RushingYards+ReceivingYards)
FROM OffensePerformances);



-- Display all Players, Their teams, and their defense performance
CREATE VIEW DefensePerformances AS
SELECT PlayersAndTeamNames.FirstName, PlayersAndTeamNames.LastName, 
PlayersAndTeamNames.SchoolName,
DefensePerformance.GameID, 
DefensePerformance.PassingYardsAllowed, DefensePerformance.RushingYardsAllowed,
DefensePerformance.Sacks, DefensePerformance.Interceptions 
FROM PlayersAndTeamNames, DefensePerformance
WHERE PlayersAndTeamNames.SSN = DefensePerformance.SSN;


-- Best Defensive Player in the League
CREATE VIEW BestDefensivePlayer AS
SELECT FirstName, LastName, (PassingYardsAllowed+RushingYardsAllowed) AS YardsAllowed
FROM DefensePerformances
WHERE (PassingYardsAllowed+RushingYardsAllowed)=(SELECT MAX(PassingYardsAllowed+RushingYardsAllowed)
FROM DefensePerformances);

-- Who is the worst Defensive Player in the league
CREATE VIEW WorstDefensivePlayer AS 
SELECT FirstName, LastName, (PassingYardsAllowed+RushingYardsAllowed) AS YardsAllowed
FROM DefensePerformances
WHERE (PassingYardsAllowed+RushingYardsAllowed)=(SELECT MIN(PassingYardsAllowed+RushingYardsAllowed)
FROM DefensePerformances);


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
CREATE VIEW BestTeam AS 
SELECT Team, AvgScore
FROM AverageTeamScore
WHERE AvgScore=
(SELECT MAX(AvgScore)
FROM AverageTeamScore);

-- Who is the worst team in the league based on combined total score?
CREATE VIEW WorstTeam AS
SELECT Team, AvgScore
FROM AverageTeamScore
WHERE AvgScore=
(SELECT MIN(AvgScore)
FROM AverageTeamScore);
