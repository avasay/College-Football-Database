--  ----------------------------------------------------------------------------
-- ATTRIBUTE and TUPLE-BASED CONSTRAINTS
-- CHECK and ASSERTION are ignored by MySQL engine. It doesn't work.
-- ----------------------------------------------------------------------------
-- No two teams should have a coach with the same name
ALTER TABLE Teams ADD CONSTRAINT OneCoachName 
	CHECK (HeadCoach NOT IN (SELECT HeadCoach FROM Teams )) ;

-- No player should be younger than 17 or older than 30
ALTER TABLE Players ADD CONSTRAINT NotTooYoungOrOld 
	CHECK (Age>=17 AND Age<=30) ;

-- No two teams shoudl have a mascot with the same name
ALTER TABLE Teams ADD CONSTRAINT OneMascotName 
	CHECK (Mascot NOT IN (SELECT Mascot FROM Teams )) ;

-- No team should be in two different conferences
ALTER TABLE ConferenceMembership ADD CONSTRAINT JustOneMembership 
	CHECK (SchoolName NOT IN (SELECT SchoolName FROM ConferenceMembership)) ;
