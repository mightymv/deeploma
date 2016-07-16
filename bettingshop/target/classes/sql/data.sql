

Insert into SPORTS (ID,NAME) values (1,'Footbal');
Insert into SPORTS (ID,NAME) values (2,'Basketball');


Insert into COMPETITIONS (ID,NAME,SPORTID) values (100,'Premier League',1);
Insert into COMPETITIONS (ID,NAME,SPORTID) values (200,'Primera',1);
Insert into COMPETITIONS (ID,NAME,SPORTID) values (300,'Bundes League',1);
Insert into COMPETITIONS (ID,NAME,SPORTID) values (400,'Italia 1',1);


Insert into TEAMS (ID,NAME,SPORTID) values (1001,'Manchester United',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1002,'Chelsea',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1003,'Liverpool',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1004,'Newcastle',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1005,'Arsenal',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1006,'Everton',1);
Insert into TEAMS (ID,NAME,SPORTID) values (1007,'Leicester City',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2001,'Real Madrid',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2002,'Barcelona',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2003,'Valencia',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2004,'Sevilla',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2005,'Saragosa',1);
Insert into TEAMS (ID,NAME,SPORTID) values (2006,'Atletico Madrid',1);

Insert into MATCHES (ID,HOMETEAMID,VISITORTEAMID,STARTTIME,COMPETITIONID,MATCHSTATUS) values (1001,1001,1002,to_date('2016-JUN-20 21:18','YYYY-MON-DD HH24:MI'),100,'NOT_FINISHED');
Insert into MATCHES (ID,HOMETEAMID,VISITORTEAMID,STARTTIME,COMPETITIONID,MATCHSTATUS) values (1002,1003,1004,to_date('2016-JUN-21 21:28','YYYY-MON-DD HH24:MI'),100,'NOT_FINISHED');


INSERT INTO RESULTS (ID, RESULTTYPE, HOMETEAM, VISITORTEAM, MATCHID, RESULTSTATUS) VALUES (1, 'HALF_TIME_GOALS', 1, 0, 1001, 'VERIFIED');
INSERT INTO RESULTS (ID, RESULTTYPE, HOMETEAM, VISITORTEAM, MATCHID, RESULTSTATUS) VALUES (2, 'FULL_TIME_GOALS', 3, 0, 1001, 'ACTIVE');
