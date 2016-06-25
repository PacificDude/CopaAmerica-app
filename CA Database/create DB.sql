DROP DATABASE IF EXISTS CAFIXTUREDB;
CREATE DATABASE CAFIXTUREDB;

USE CAFIXTUREDB;

CREATE TABLE teams_table(
	team_id varchar(3) not null,
	team_name varchar(50) not null,
    team_matches_played tinyint default '0',
    team_wins tinyint default '0',
    team_draws tinyint default '0',
    team_loses tinyint default '0',
    team_goals_favor tinyint default '0',
    team_goals_against tinyint default '0',
    team_points tinyint default '0',
    team_group varchar(1),
    team_cups tinyint,
    PRIMARY KEY (team_id)
);

CREATE TABLE fixture_table(
	fixture_id int not null auto_increment,
    fixture_date int,
    fixture_team1 varchar(3),
    fixture_team2 varchar(3),
    fixture_phase varchar(15),
    fixture_winner_team varchar(3) null,
    PRIMARY KEY (fixture_id),
    CONSTRAINT fk_team1 FOREIGN KEY (fixture_team1) REFERENCES teams_table(team_id),
    CONSTRAINT fk_team2 FOREIGN KEY (fixture_team2)  REFERENCES teams_table(team_id),
    CONSTRAINT fk_winner FOREIGN KEY (fixture_winner_team) REFERENCES teams_table(team_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('ARG', 'Argentina', 'B', 14);

INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('BOL', 'Bolivia', 'A', 1);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('BRA', 'Brazil', 'C', 8);

INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('CHL', 'Chile', 'A', 0);

INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('COL', 'Colombia', 'C', 1);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('ECU', 'Ecuador', 'A', 0);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('JAM', 'Jamaica', 'B', 0);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('MEX', 'Mexico', 'A', 2);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('PRY', 'Paraguay', 'B', 2);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('PER', 'Peru', 'C', 2);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('URY', 'Uruguay', 'B', 15);
    
INSERT INTO teams_table(team_id, team_name, team_group, team_cups)
	VALUES ('VEN', 'Venezuela', 'C', 0);

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434051000, 'CHL', 'ECU', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434137400, 'MEX', 'BOL', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434207600, 'URY', 'JAM', 'Group B');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434216600, 'ARG', 'PRY', 'Group B');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434294000, 'COL', 'VEN', 'Group C');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434303000, 'BRA', 'PER', 'Group C');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434387600, 'ECU', 'BOL', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434396600, 'CHL', 'MEX', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434474000, 'PRY', 'JAM', 'Group B');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434483000, 'ARG', 'URY', 'Group B');
    
INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434571200, 'BRA', 'COL', 'Group C');
    
INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434655800, 'PER', 'VEN', 'Group C');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434733200, 'MEX', 'ECU', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434742200, 'CHL', 'BOL', 'Group A');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434812400, 'URY', 'PRY', 'Group B');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434821400, 'ARG', 'JAM', 'Group B');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434898800, 'COL', 'PER', 'Group C');

INSERT INTO fixture_table(fixture_date, fixture_team1, fixture_team2, fixture_phase)
	VALUES (1434907800,  'BRA', 'VEN', 'Group C');

SELECT * FROM fixture_table;