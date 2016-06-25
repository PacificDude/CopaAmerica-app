package monte.com.copaamerica2015mysql;

/**
 * Created by Miguel on 5/19/2015.
 */
public class fields_row_team_by_groups {
    String groupName, teamID, teamName;
    int gamesPlayed, wins, draws, loses, goalsFavor, goalsAgainst, goalsDif, points, cups;
    int flag;

    public fields_row_team_by_groups(){

    }

    public fields_row_team_by_groups(String team, int flag, int gp, int wins, int draws, int loses, int gf, int ga, int points){
        this.teamName = team;
        this.flag = flag;
        this.gamesPlayed = gp;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goalsFavor = gf;
        this.goalsAgainst = ga;
        this.goalsDif = gf - ga;
        this.points = points;
    }

    public void setGroupName(String groupName){this.groupName = groupName;}

    public void setTeamID(String teamID){this.teamID = teamID;}
    public void setTeamName(String teamName){this.teamName = teamName;}
    public void setFlag(int flag){this.flag = flag;}
    public void setGamesPlayed(int gamesPlayed){this.gamesPlayed = gamesPlayed;}
    public void setTeamWins(int teamWins){this.wins = teamWins;}
    public void setTeamDraws(int teamDraws){this.draws = teamDraws;}
    public void setTeamLoses(int teamLoses){this.loses = teamLoses;}
    public void setTeamGF(int teamGF){
        this.goalsFavor = teamGF;
        setTeamDif(this.goalsFavor, this.goalsAgainst);
    }
    public void setTeamGA(int teamGA){
        this.goalsAgainst = teamGA;
        setTeamDif(this.goalsFavor, this.goalsAgainst);
    }
    public void setTeamDif(int teamGF, int teamGA){
        this.goalsDif = teamGF - teamGA;
    }
    public void setPoints(int points){this.points = points;}
    public void setTeamCups(int cups){
        this.cups = cups;
    }

    public int getDifGoals(){
        int dif = 0;
        dif = this.goalsFavor - this.goalsAgainst;
        return dif;
    }

    public String getTeamID(){return this.teamID;}
    public String getTeamName(){return this.teamName;}
    public int getMatchesPlayed(){return this.gamesPlayed;}
    public int getWins(){return this.wins;}
    public int getDraws(){return this.draws;}
    public int getLoses(){return this.loses;}
    public int getGoalsFavor(){return this.goalsFavor;}
    public int getGoalsAgainst(){return this.goalsAgainst;}
    public int getPoints(){return this.points;}
    public int getCups(){return this.cups;}

}
