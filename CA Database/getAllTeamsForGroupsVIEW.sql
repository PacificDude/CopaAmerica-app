CREATE VIEW getAllTeamsForGroups AS
SELECT team_id, 
	team_name, 
    team_matches_played,
    team_wins,
    team_draws,
    team_loses,
    team_goals_favor,
    team_goals_against,
    team_points
FROM teams_table
ORDER BY team_group, 
	team_points DESC,
    team_goals_favor - team_goals_against DESC
    
