use cafixturedb;

drop table if exists players;
drop table if exists positions;
drop table if exists countries;

create table positions(
	position_id varchar(5),
    position_name varchar(20),
    primary key (position_id)
    )Engine InnoDB;

insert into positions(position_id, position_name)
values
('GOLKP', 'Goalkeeper'),
('DEFSN', 'Defense'),
('MIDFL', 'Midfield'),
('STRIK', 'Striker');

create table countries(
	country_id varchar(3),
    country_name varchar(20),
    primary key (country_id)
    )Engine InnoDB;

insert into countries(country_id, country_name)
values
('ARG', 'Argentina'),
('BOL', 'Bolivia'),
('CHL', 'Chile'),
('BRA', 'Brazil'),
('ECU', 'Ecuador'),
('PER', 'Peru'),
('COL', 'Colombia');

create table players(
	player_id int not null auto_increment,
    player_name varchar(50),
    player_position_id varchar(5),
    player_dob int,
    player_country_id varchar(3),
    player_goals tinyint,
    primary key (player_id),
    index (player_position_id),
    index (player_country_id),
    foreign key (player_position_id) 
		references positions(position_id)
        on update cascade on delete restrict,
    foreign key (player_country_id)
		references countries(country_id)
        on update cascade on delete restrict
    )Engine InnoDB;
    
insert into players (
	player_name, 
    player_position_id,
    player_dob,
    player_country_id,
    player_goals)
values 
('Miguel', 'MIDFL', 495152700, 'PER', 0),
('Alfredo', 'DEFSN', 387649500, 'PER', 0),
('Christian', 'STRIK', 380809500, 'BRA', 0),
('Vladi', 'GOLKP', 507039900, 'CHL', 0),
('Luis', 'MIDFL', 417464700, 'COL', 0),
('Hernan', 'DEFSN', 333397500, 'ECU', 0);

    