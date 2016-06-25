package monte.com.copaamerica2015mysql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GroupsActivity extends ActionBarActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FixtureDatabase myDB;
    Cursor c;
    private static final String PHP_ADDRESS = "http://10.0.2.2/mySQLsync/getJSONString.php";
    private static final String TEAMS = "Teams";
    private static final String TEAM_ID = "_id_team";
    private static final String TEAM_NAME = "team_name";
    private static final String TEAM_GAMES_PLAYED = "team_matches_played";
    private static final String TEAM_WINS = "team_wins";
    private static final String TEAM_DRAWS = "team_draws";
    private static final String TEAM_LOSES = "team_loses";
    private static final String TEAM_GOALS_FAVOR = "team_goals_favor";
    private static final String TEAM_GOALS_AGAINST = "team_goals_against";
    private static final String TEAM_POINTS = "team_points";
    //public static final String TEAM_GROUP = "team_group";
    //public static final String TEAM_CUPS = "team_cups";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
/*
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
*/
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        recyclerView = (RecyclerView)findViewById(R.id.groupsList);
        myDB = new FixtureDatabase(this);
        c = myDB.readAllTeamsOrderByGroup();

        GroupsRecyclerAdapter adapter = new GroupsRecyclerAdapter(this,myDB.readTeamsByGroup(c),myDB.readTitleGroup());
        recyclerView.setAdapter(adapter);
        _("Loading Recyclerview");
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void updateDatafromSQLite(){
        _("TODO");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_groups, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.refresh:
                //try {
                    new backgroundTask().execute();
                    /*
                    JSONParser jb = new JSONParser();
                    //jb.fetchTeams().size()
                    myDB.updateTeamsTable(jb.fetchTeams(),12);
                    loadDatafromSQLite();
                    */
                /*} catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            case R.id.fixture:
                startActivity(new Intent(this,FixtureActivity.class));
                break;
            case R.id.teams:
                startActivity(new Intent(this,TeamsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class backgroundTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // CONNECTION TO THE WEBSITE
                URL url = new URL(PHP_ADDRESS);
                _("valid URL");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                // PARSE JSON TO ARRAY OBJECT
                String jsonString = stringBuilder.toString();
                _("URL read successfully");
                // UPDATE DATABASE
                myDB.updateTeamsTable(jsonTeamsArray(jsonString));
                _("Teams Table in Database updated");
                updateDatafromSQLite();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                _("Invalid URL");
            } catch (IOException e) {
                e.printStackTrace();
                _("Connection error: " + e);
            }
            return null;
        }
    }

    public ArrayList<fields_row_team_by_groups> jsonTeamsArray(String jsonString){
        ArrayList<fields_row_team_by_groups> objTeamsArray = new ArrayList<fields_row_team_by_groups>();
        JSONObject jsonTeamRow;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TEAMS);

            for (int i = 0 ; i < jsonArray.length(); i++){
                /*
                objTeamsArray will be an array of objects, so it needs one object per team
                this means you need to create a new object each time (objTeamRow)
                 */
                fields_row_team_by_groups objTeamRow = new fields_row_team_by_groups();
                //each jsonArray contains a objects, so it each one of those need to be stored into a JSONObject
                jsonTeamRow = jsonArray.getJSONObject(i);
                objTeamRow.setTeamID(jsonTeamRow.getString(TEAM_ID));
                objTeamRow.setTeamName(jsonTeamRow.getString(TEAM_NAME));
                objTeamRow.setGamesPlayed(jsonTeamRow.getInt(TEAM_GAMES_PLAYED));
                objTeamRow.setTeamWins(jsonTeamRow.getInt(TEAM_WINS));
                objTeamRow.setTeamDraws(jsonTeamRow.getInt(TEAM_DRAWS));
                objTeamRow.setTeamLoses(jsonTeamRow.getInt(TEAM_LOSES));
                int w = jsonTeamRow.getInt(TEAM_GOALS_FAVOR);
                objTeamRow.setTeamGF(w);
                int l = jsonTeamRow.getInt(TEAM_GOALS_AGAINST);
                objTeamRow.setTeamGA(l);
                objTeamRow.setTeamDif(w, l);
                objTeamRow.setPoints(jsonTeamRow.getInt(TEAM_POINTS));
                objTeamsArray.add(objTeamRow);
                _(String.valueOf(i) + "- Added new row to objTeamsArray");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            _("JSONException error. Json Object can't be created: " + e);
        }
        _("jsonTeamsArray--> Returning objTeamsArray...");
        return objTeamsArray;
    }

    public void _ (String message){
        Log.d("GroupsActivity##########", message);
    }
/* ******** DEPRECATED METHOD ***************
    private void connectToServer(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.0.2.2/mySQLsync/getJSONString.php");
        BufferedReader bufferedReader = null;
    }
*/
/*
    public class networkingTask extends AsyncTask{
        public static final int NETWORK_STATE_REGISTER = 1;

        @Override
        protected Object doInBackground(Object[] params) {
            _("doInBackground");
            connectToServer((String) params[0], (int) params[1]);  //website might be different
            return null;
        }
    }

    private void connectToServer(String url, int state){
        HttpClient httpClient = new DefaultHttpClient();
        _("doing post to url now");
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;

        switch (state){
            case networkingTask.NETWORK_STATE_REGISTER:
                postParameters.add(new BasicNameValuePair("userName", username));
                postParameters.add(new BasicNameValuePair("password", password));
                valid = true;
                break;
            default:
                _("Warning unknown state");
        }

        if (valid){
            _("valid = true");
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");

            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
                httpPost.setEntity(entity);
                HttpResponse httpResponse = httpClient.execute(httpPost);

                bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

                String line = "";
                String lineSeparator = System.getProperty("line.separator");
                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line + lineSeparator);
                }
                bufferedReader.close();
            } catch (UnsupportedEncodingException e) {
                _("Networking UNsupport error: " + e.getMessage());
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                _("Networking Protocol error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                _("Networking IO Exception error: " + e.getMessage());
                e.printStackTrace();
            }
        }else{
            _("valid = false. Will do nothing");
        }
    }

    private void decodeJSON(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String name = jsonObject.getString("team_name");
            int points = jsonObject.getInt("team_points");
            _("JSON Content");
            _("Team name:"+name);
            _("Points:"+points);

        } catch (JSONException e) {
            _("Warning JSON Exception");
            e.printStackTrace();
        }

    }
*/
}
