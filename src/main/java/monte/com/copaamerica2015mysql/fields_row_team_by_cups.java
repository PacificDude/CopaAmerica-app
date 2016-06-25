package monte.com.copaamerica2015mysql;

/**
 * Created by Miguel on 5/19/2015.
 */
public class fields_row_team_by_cups {
    int teamFlag;
    int teamCups;
    String teamName, teamID;

    public fields_row_team_by_cups(){}

    public fields_row_team_by_cups(int flag, String id, String name, int cups){
        teamFlag = flag;
        teamID = id;
        teamName = name;
        teamCups = cups;
    }

    public void setFlag(int flag){ this.teamFlag = flag;}
    public void setTeamID(String teamID){this.teamID = teamID;}
    public void setName(String name){this.teamName = name;}
    public void setCups(int cups){this.teamCups = cups;}

    public String getTeamID(){return  teamID;}
    public String getTeamName(){
        return teamName;
    }
}

