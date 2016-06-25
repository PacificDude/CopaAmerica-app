package monte.com.copaamerica2015mysql;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Miguel on 6/13/2015.
 */
public class JSONParser {
    String uri = "http://10.0.2.2/mySQLsync/getJSONString.php";

    NetworkDAO networkDAO = new NetworkDAO();

    String request="";

    //creating an object that contain all the fields needed to pass it later on to teams_table in DB
    ArrayList<fields_row_team_by_groups> objTeam = new ArrayList<fields_row_team_by_groups>();

    public JSONParser() throws IOException {
        request = networkDAO.request(uri);
    }

    public ArrayList<fields_row_team_by_groups> fetchTeams() throws JSONException {
        //pass the data into JSONObject
        JSONObject root = new JSONObject(request);

        //get the data associated with the array named "teams"
        JSONArray teamsArray = root.getJSONArray("Teams");

        for (int i = 0; i < teamsArray.length(); i++ ){
            //translating the data from JSON to Java

            //create a Team DTO object that will hold  the value from JSON
            fields_row_team_by_groups team = new fields_row_team_by_groups();

            //get the current Team JSON object
            JSONObject jsonTeam = teamsArray.getJSONObject(i);

            //get the values from JSON and save it to Java
            String id = jsonTeam.getString("team_id");
            team.setTeamID(id);

            String name = jsonTeam.getString("team_name");
            team.setTeamName(name);

            int mp = jsonTeam.getInt("team_matches_played");
            team.setGamesPlayed(mp);

            int tw = jsonTeam.getInt("team_wins");
            team.setTeamWins(tw);

            int td = jsonTeam.getInt("team_draws");
            team.setTeamDraws(td);

            int tl = jsonTeam.getInt("team_loses");
            team.setTeamLoses(tl);

            int tgf = jsonTeam.getInt("team_goals_favor");
            team.setTeamGF(tgf);

            int tga = jsonTeam.getInt("team_goals_against");
            team.setTeamGA(tga);

            int tp = jsonTeam.getInt("team_points");
            team.setPoints(tp);

            int tc = jsonTeam.getInt("team_cups");
            team.setTeamCups(tc);

            //add out team object to the collection of team "objTeam"
            objTeam.add(team);
        }
        return objTeam;
    }


}
