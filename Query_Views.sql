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
