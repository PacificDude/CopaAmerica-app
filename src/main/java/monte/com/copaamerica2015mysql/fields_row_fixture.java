package monte.com.copaamerica2015mysql;

/**
 * Created by Miguel on 5/14/2015.
 */
public class fields_row_fixture {
//this was created following slidnerd #14 video
    int date;
    String phase, teamID1, teamID2, teamName1, teamName2;
    int flag1, flag2;


    public fields_row_fixture(){}

    public fields_row_fixture(String t1, String t2, int d, String ph, int f1, int f2){
        date = d;
        phase = ph;
        teamName1 = t1;
        teamName2 = t2;
        flag1 = f1;
        flag2 = f2;
    }

    public void setTeamID1(String id1){this.teamID1 = id1;}
    public void setTeamID2(String id2){this.teamID2 = id2;}
    public void setDate(int d){this.date = d;}
    public void setPhase(String ph){this.phase = ph;}
    public void setTeamName1(String t1){this.teamName1 = t1;}
    public void setTeamName2(String t2){this.teamName2 = t2;}
    public void setFlag1(int f1){this.flag1 = f1;}
    public void setFlag2(int f2){this.flag2 = f2;}

    public String getTeamID1(){return  teamID1;}
    public String getTeamID2(){return  teamID2;}
    public String getTeamName1(){
        return teamName1;
    }
    public String getTeamName2(){
        return teamName2;
    }




}
