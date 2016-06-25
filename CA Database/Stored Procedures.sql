use cafixturedb;

drop procedure if exists player_list;
drop procedure if exists player_list_by_country;
drop procedure if exists get_player_name_by_id;
drop procedure if exists get_oldest_player;

delimiter $$
create procedure player_list()
begin
	select play.player_name, pos.position_name, c.country_name from players play
    left join (positions pos, countries c)
    on (pos.position_id = play.player_position_id 
		and c.country_id = play.player_country_id);
end $$

create procedure player_list_by_country(in country_id varchar(3))
begin 
	select * from players
    where player_country_id = country_id
    order by player_name asc;
end $$

create procedure get_player_name_by_id(in p_id int, out p_name varchar(50))
begin
	select player_name into p_name from players
    where player_id = p_id;
end $$

create procedure get_oldest_player(out p_name varchar(50))
begin
	select player_name from players
    order by player_dob asc limit 1;
end $$


call player_list;
/*
call get_oldest_player(@name);
select @name;
call get_player_name_by_id(5, @name);
select @name;
*/
