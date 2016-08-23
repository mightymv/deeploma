--
--Create user betshop identified by "betshop!";
--Grant connect,resource to betshop;

--DROP
--  TABLE Competitions CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE Matches CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE Results CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE Sports CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE Teams CASCADE CONSTRAINTS ;
--  
--  
--  DROP
-- TABLE BetOdds CASCADE CONSTRAINTS ;
--
--DROP
-- TABLE BettingGames CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE BettingSubGames CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE TicketRows CASCADE CONSTRAINTS ;
--
--DROP
--  TABLE Tickets CASCADE CONSTRAINTS ;
--  
--DROP
--  TABLE USERS CASCADE CONSTRAINTS ;
--  
--DROP SEQUENCE SQ_TICKETS_ID ;
--
--DROP SEQUENCE SQ_TICKETROWS_ID ;
--
--DROP SEQUENCE SQ_USERS_ID ;

CREATE
  TABLE Users
  (
    id       INTEGER NOT NULL ,
    name     VARCHAR2 (30) NOT NULL,
    surname  VARCHAR2 (30) NOT NULL,
    username VARCHAR2 (50) NOT NULL,
    password VARCHAR2 (50) NOT NULL,    
    status   VARCHAR2 (10) default 'ACTIVE'
  ) ;
ALTER TABLE Users ADD CONSTRAINT Users_PK PRIMARY KEY ( id ) ;
ALTER TABLE Users ADD CONSTRAINT Users_USERNAME_UN UNIQUE ( username ) ;

CREATE
  TABLE Competitions
  (
    id      INTEGER  NOT NULL ,
    name    VARCHAR2 (50 CHAR)  NOT NULL ,
    sportId INTEGER  NOT NULL
  ) ;
ALTER TABLE Competitions ADD CONSTRAINT PK_Competitions PRIMARY KEY ( id ) ;


CREATE
  TABLE Matches
  (
    id            INTEGER NOT NULL ,
    homeTeamId      INTEGER NOT NULL ,
    visitorTeamId   INTEGER NOT NULL ,
    startTime     DATE NOT NULL ,
    competitionId INTEGER NOT NULL ,
    matchStatus   VARCHAR2 (15)
  ) ;
ALTER TABLE Matches ADD CONSTRAINT Matches_PK PRIMARY KEY ( id ) ;


CREATE
  TABLE Results
  (
    id          INTEGER NOT NULL ,
    resultType  VARCHAR2 (20) NOT NULL ,
    homeTeam    INTEGER NOT NULL ,
    visitorTeam INTEGER NOT NULL ,
    matchId     INTEGER NOT NULL,
    resultStatus VARCHAR2 (20) NOT NULL
  ) ;
ALTER TABLE Results ADD CONSTRAINT Results_PK PRIMARY KEY ( id ) ;


CREATE
  TABLE Sports
  (
    id   INTEGER NOT NULL ,
    name VARCHAR2 (20 CHAR)
  ) ;
ALTER TABLE Sports ADD CONSTRAINT PK_Sports PRIMARY KEY ( id ) ;


CREATE
  TABLE Teams
  (
    id      INTEGER CONSTRAINT NNC_Competitions_id NOT NULL ,
    name    VARCHAR2 (50 CHAR) CONSTRAINT NNC_Competitions_name NOT NULL ,
    sportId INTEGER CONSTRAINT NNC_Competitions_sportId NOT NULL
  ) ;
ALTER TABLE Teams ADD CONSTRAINT Teams_PK PRIMARY KEY ( id ) ;


ALTER TABLE Competitions ADD CONSTRAINT FK_Competitions_Sports FOREIGN KEY (
sportId ) REFERENCES Sports ( id ) ON
DELETE CASCADE ;

ALTER TABLE Matches ADD CONSTRAINT Matches_Competitions_FK FOREIGN KEY (
competitionId ) REFERENCES Competitions ( id ) ON
DELETE CASCADE ;

ALTER TABLE Matches ADD CONSTRAINT Matches_Teams1_FK FOREIGN KEY ( homeTeamId )
REFERENCES Teams ( id ) ON
DELETE CASCADE ;

ALTER TABLE Matches ADD CONSTRAINT Matches_Teams2_FK FOREIGN KEY ( visitorTeamId
) REFERENCES Teams ( id ) ON
DELETE CASCADE ;

ALTER TABLE Results ADD CONSTRAINT Results_Matches_FK FOREIGN KEY ( matchId )
REFERENCES Matches ( id ) ON
DELETE CASCADE ;


ALTER TABLE Teams ADD CONSTRAINT Teams_Sports_FK FOREIGN KEY ( sportId )
REFERENCES Sports ( id ) ON
DELETE CASCADE ;




CREATE
  TABLE BetOdds
  (
    id           INTEGER NOT NULL ,
    matchId      INTEGER NOT NULL ,
    value        NUMBER NOT NULL ,
    subGameId    INTEGER NOT NULL ,
    betOddStatus VARCHAR2 (10) NOT NULL
  ) ;
ALTER TABLE BetOdds ADD CONSTRAINT BetOdd_PK PRIMARY KEY ( id ) ;


CREATE
  TABLE BettingGames
  (
    id   INTEGER NOT NULL ,
    name VARCHAR2 (20 CHAR)
  ) ;
ALTER TABLE BettingGames ADD CONSTRAINT BettingGame_PK PRIMARY KEY ( id ) ;


CREATE
  TABLE BettingSubGames
  (
    id   INTEGER NOT NULL ,
    name VARCHAR2 (40 CHAR) ,
    short_name VARCHAR2 (40 CHAR),
    bettGameId INTEGER NOT NULL
  ) ;
ALTER TABLE BettingSubGames ADD CONSTRAINT BettingSubGames_PK PRIMARY KEY ( id )
;



CREATE
  TABLE TicketRows
  (
    id              INTEGER NOT NULL ,
    ticketId        INTEGER NOT NULL ,
    betOddId        INTEGER NOT NULL ,
    matchid       	INTEGER NOT NULL ,
    subgame       	VARCHAR(5) NOT NULL ,
    odd         	NUMBER NOT NULL ,
    ticketRowStatus VARCHAR2 (20 CHAR) default 'ACTIVE'
  ) ;
ALTER TABLE TicketRows ADD CONSTRAINT TicketRows_PK PRIMARY KEY ( id ) ;


CREATE
  TABLE Tickets
  (
    id            INTEGER NOT NULL ,
    TIME          DATE ,
    cumulativeOdd NUMBER ,
    userId        INTEGER ,
    ticketStatus  VARCHAR2 (20 CHAR) default 'ACTIVE'
  ) ;
ALTER TABLE Tickets ADD CONSTRAINT Tickets_PK PRIMARY KEY ( id ) ;



ALTER TABLE BetOdds ADD CONSTRAINT BetOdds_BettingSubGames_FK FOREIGN KEY (
subGameId ) REFERENCES BettingSubGames ( id ) ;

ALTER TABLE BettingSubGames ADD CONSTRAINT BettingSubGames_BettingG_FK
FOREIGN KEY ( bettGameId ) REFERENCES BettingGames ( id ) ;

ALTER TABLE TicketRows ADD CONSTRAINT TicketRows_BetOdds_FK FOREIGN KEY (
betOddId ) REFERENCES BetOdds ( id ) ;

ALTER TABLE TicketRows ADD CONSTRAINT TicketRows_matches_FK FOREIGN KEY (
matchid ) REFERENCES Matches ( id ) ;

ALTER TABLE TicketRows ADD CONSTRAINT TicketRows_Tickets_FK FOREIGN KEY (
ticketId ) REFERENCES Tickets ( id ) ;

CREATE SEQUENCE SQ_TICKETS_ID 
START WITH 100000;

CREATE SEQUENCE SQ_TICKETROWS_ID 
START WITH 1000;

CREATE SEQUENCE SQ_USERS_ID 
START WITH 5000;