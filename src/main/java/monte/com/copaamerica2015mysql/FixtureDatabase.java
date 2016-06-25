package monte.com.copaamerica2015mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Miguel on 5/6/2015.
 */
public class FixtureDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CAFixtureDB";
    public static final String TABLE_FIXTURE = "FixtureTable";
    public static final String TABLE_TEAMS = "TeamsTable";
    public static final int DATABASE_VERSION = 1;

    //***************** TEAMS TABLE ***********************
    public static final String TEAM_ID = "_id_team";
    public static final String TEAM_NAME = "team_name";
    public static final String TEAM_GAMES_PLAYED = "team_matches_played";
    public static final String TEAM_WINS = "team_wins";
    public static final String TEAM_DRAWS = "team_draws";
    public static final String TEAM_LOSES = "team_loses";
    public static final String TEAM_GOALS_FAVOR = "team_goals_favor";
    public static final String TEAM_GOALS_AGAINST = "team_goals_against";
    public static final String TEAM_POINTS = "team_points";
    public static final String TEAM_GROUP = "team_group";
    public static final String TEAM_CUPS = "team_cups";
    //////////////////////////////////////////////////////////

    //***************** FIXTURE TABLE **********************
    public static final String FIXTURE_ID = "_id_fixture";
    public static final String FIXTURE_DATE = "fixture_date";
    public static final String FIXTURE_TEAM1 = "fixture_team1";
    public static final String FIXTURE_TEAM2 = "fixture_team2";
    public static final String FIXTURE_PHASE = "fixture_phase";
    public static final String FIXTURE_WINNER_TEAM = "fixture_winner_team";
    ///////////////////////////////////////////////////////////

    private static final String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_TEAMS +
            " ( " + TEAM_ID + " VARCHAR(3) PRIMARY KEY, " +
            TEAM_NAME + " VARCHAR(50), " +
            TEAM_GAMES_PLAYED + " INT, " +
            TEAM_WINS + " INT, " +
            TEAM_DRAWS + " INT, " +
            TEAM_LOSES + " INT, " +
            TEAM_GOALS_FAVOR + " INT, " +
            TEAM_GOALS_AGAINST + " INT, " +
            TEAM_POINTS + " INT, " +
            TEAM_GROUP + " VARCHAR(1), " +
            TEAM_CUPS + " INT);";

    private static final  String CREATE_FIXTURE_TABLE = "CREATE TABLE " + TABLE_FIXTURE +
            " ( " + FIXTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIXTURE_DATE + " INTEGER, " +
            FIXTURE_TEAM1 + " VARCHAR(3), " +
            FIXTURE_TEAM2 + " VARCHAR(3), " +
            FIXTURE_PHASE + " VARCHAR(15), " +
            FIXTURE_WINNER_TEAM + " VARCHAR(3));";



    public FixtureDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEAMS_TABLE);
        db.execSQL(CREATE_FIXTURE_TABLE);
// *************** FILLING DEFAULT DATA INTO TABLES ************************

        fillTeamsTable(db, "ARG","Argentina","B",14);
        fillTeamsTable(db, "BOL","Bolivia","A",1);
        fillTeamsTable(db, "BRA","Brasil","C",8);
        fillTeamsTable(db, "CHL","Chile","A",0);
        fillTeamsTable(db, "COL","Colombia","C",1);
        fillTeamsTable(db, "ECU","Ecuador","A",0);
        fillTeamsTable(db, "JAM","Jamaica","B",0);
        fillTeamsTable(db, "MEX","Mexico","A",2);
        fillTeamsTable(db, "PRY","Paraguay","B",2);
        fillTeamsTable(db, "PER","Peru","C",2);
        fillTeamsTable(db, "URY","Uruguay","B",15);
        fillTeamsTable(db, "VEN","Venezuela","C",0);

        fillFixtureTable(db, 1434051000, "CHL", "ECU", "Group A");
        fillFixtureTable(db, 1434137400, "MEX", "BOL", "Group A");
        fillFixtureTable(db, 1434207600, "URY", "JAM", "Group B");
        fillFixtureTable(db, 1434216600, "ARG", "PRY", "Group B");
        fillFixtureTable(db, 1434294000, "COL", "VEN", "Group C");
        fillFixtureTable(db, 1434303000, "BRA", "PER", "Group C");
        fillFixtureTable(db, 1434387600, "ECU", "BOL", "Group A");
        fillFixtureTable(db, 1434396600, "CHL", "MEX", "Group A");
        fillFixtureTable(db, 1434474000, "PRY", "JAM", "Group B");
        fillFixtureTable(db, 1434483000, "ARG", "URY", "Group B");
        fillFixtureTable(db, 1434571200, "BRA", "COL", "Group C");
        fillFixtureTable(db, 1434655800, "PER", "VEN", "Group C");
        fillFixtureTable(db, 1434733200, "MEX", "ECU", "Group A");
        fillFixtureTable(db, 1434742200, "CHL", "BOL", "Group A");
        fillFixtureTable(db, 1434812400, "URY", "PRY", "Group B");
        fillFixtureTable(db, 1434821400, "ARG", "JAM", "Group B");
        fillFixtureTable(db, 1434898800, "COL", "PER", "Group C");
        fillFixtureTable(db, 1434907800, "BRA", "VEN", "Group C");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXTURE);
    }
    //**********************************************************************************************//
    //**************** FILL DEFAULT VALUES ON TABLE *******************
    public void fillTeamsTable(SQLiteDatabase db, String countryID, String countryName, String group, int numCups){
        ContentValues initialValues = new ContentValues();
        initialValues.put(TEAM_ID, countryID);
        initialValues.put(TEAM_NAME, countryName);
        initialValues.put(TEAM_GROUP, group);
        initialValues.put(TEAM_CUPS, numCups);
        db.insert(TABLE_TEAMS,null,initialValues);
    }

    public void fillFixtureTable(SQLiteDatabase db, int date, String t1, String t2, String phase){
        ContentValues initialValues = new ContentValues();
        initialValues.put(FIXTURE_DATE, date);
//        initialValues.put(FIXTURE_TIME, time);
        initialValues.put(FIXTURE_TEAM1, t1);
        initialValues.put(FIXTURE_TEAM2, t2);
        initialValues.put(FIXTURE_PHASE, phase);

        db.insert(TABLE_FIXTURE, null, initialValues);
    }
    //**********************************************************************************************//
    //***************************** DB UPDATE ******************************************//
    public void updateTeamsTable(ArrayList<fields_row_team_by_groups> arrayObjData){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrayObjData.size(); i++) {
            ContentValues currentValue = new ContentValues();
            currentValue.put(TEAM_GAMES_PLAYED, arrayObjData.get(i).getMatchesPlayed());
            currentValue.put(TEAM_WINS, arrayObjData.get(i).getWins());
            currentValue.put(TEAM_DRAWS, arrayObjData.get(i).getDraws());
            currentValue.put(TEAM_LOSES, arrayObjData.get(i).getLoses());
            currentValue.put(TEAM_GOALS_FAVOR, arrayObjData.get(i).getGoalsFavor());
            currentValue.put(TEAM_GOALS_AGAINST, arrayObjData.get(i).getGoalsAgainst());
            currentValue.put(TEAM_POINTS, arrayObjData.get(i).getPoints());
            //currentValue.put(TEAM_CUPS, arrayObjData.get(i).getCups());
            String[] id = {arrayObjData.get(i).getTeamID()};
            _("testing... " + arrayObjData.get(i).getTeamID());
            db.update(TABLE_TEAMS, currentValue, TEAM_ID + " = ?", id);
        }
    }

    //******************************** CURSORS ******************************************//
    public Cursor readAllFixture(){
        SQLiteDatabase db = this.getReadableDatabase();
/*
        String[] fixtureColumns = {FIXTURE_ID,
                FIXTURE_DATE,
                FIXTURE_TEAM1,
                FIXTURE_TEAM2,
                FIXTURE_PHASE};

        Cursor cursor =  db.query(TABLE_FIXTURE, fixtureColumns, null, null, null, null, FIXTURE_DATE);
        */
        /*
        select f.fixture_team1, f.fixture_team2, f.fixture_date, f.fixture_phase, t1.team_name, t2.team_name
        from fixture_table f LEFT JOIN teams_table t1
        ON (f.fixture_team1 = t1._id_team)
        LEFT JOIN teams_table t2
        ON (f.fixture_team2 = t2._id_team)
        */

        String sql = "SELECT ta." + TEAM_NAME + ", tb." + TEAM_NAME + ", " +
                "f." + FIXTURE_TEAM1 + ", f." + FIXTURE_TEAM2 + ", f." + FIXTURE_DATE + ", f." + FIXTURE_PHASE +
                " FROM " + TABLE_FIXTURE +
                " f INNER JOIN " + TABLE_TEAMS + " ta" +
                " ON f." + FIXTURE_TEAM1 + " = ta." + TEAM_ID +
                " INNER JOIN " + TABLE_TEAMS + " tb" +
                " ON f." + FIXTURE_TEAM2 + " = tb." + TEAM_ID +
                " ORDER BY " + FIXTURE_DATE + ";";


  /*      String sql = "SELECT " +
                " ta.team_name, tb.team_name," +
                " f.fixture_team1, f.fixture_team2, f.fixture_date, f.fixture_phase " +
                " FROM" +
                " FixtureTable f INNER JOIN TeamsTable ta ON f.fixture_team1 = ta._id_team" +
                " INNER JOIN TeamsTable tb ON f.fixture_team2 = tb._id_team;";
*/
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;

    }

    public Cursor readFixtureByTeam(String team){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] fixtureColumns = {FIXTURE_ID,
                FIXTURE_DATE,
                FIXTURE_TEAM1,
                FIXTURE_TEAM2,
                FIXTURE_PHASE};
        //"select fixture_date, fixture_phase, t.team_name from fixture_table f INNER JOIN teams_table t ON (f.fixture_team1 = t._id_team);"


        Cursor cursor = db.query(TABLE_FIXTURE,fixtureColumns,
                FIXTURE_TEAM1 + "=? OR " + FIXTURE_TEAM2 + "=?",
                new String[]{team,team}, null, null, FIXTURE_DATE);


        return cursor;
    }

    public Cursor readTeamsByCups(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] teamsColumns = {TEAM_ID, TEAM_NAME,TEAM_CUPS};
        return db.query(TABLE_TEAMS, teamsColumns, null, null, null, null, TEAM_CUPS + " DESC");
    }

    //returns items to GroupsActivity
    public Cursor readAllTeamsOrderByGroup(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] teamsColumns =
                {TEAM_ID, TEAM_NAME, TEAM_GAMES_PLAYED, TEAM_WINS, TEAM_DRAWS, TEAM_LOSES,
                 TEAM_GOALS_FAVOR, TEAM_GOALS_AGAINST, TEAM_POINTS, TEAM_GROUP, TEAM_CUPS};
        return db.query(TABLE_TEAMS, teamsColumns, null, null, null, null,
                TEAM_GROUP + ", " +
                TEAM_POINTS + " DESC, " +
                TEAM_GOALS_FAVOR + " - " + TEAM_GOALS_AGAINST + " DESC, " +
                TEAM_GOALS_FAVOR + " DESC, " +
                TEAM_NAME);
    }

    //******************************* METHODS AND FUNCTIONS ***************************************//
    // pass the desires fields from cursor to Fixture Activity
    public static ArrayList<fields_row_fixture> readFixture(Cursor cursor) {
        ArrayList<fields_row_fixture> listFixture = new ArrayList<>();
        String date;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                /*Log.d("readFixture ", cursor.getString(cursor.getColumnIndex(FIXTURE_TEAM1)) + ":" +
                        cursor.getString(cursor.getColumnIndex(FIXTURE_TEAM2)) + ":" +
                        cursor.getString(cursor.getColumnIndex("team_name")) + ":" +
                        cursor.getString(cursor.getColumnIndex("team_name"))
                );*/
                fields_row_fixture objFixture = new fields_row_fixture();
                //date = new java.util.Date(cursor.getColumnIndex(FIXTURE_DATE));
                objFixture.setTeamName1(cursor.getString(0));
                objFixture.setTeamName2(cursor.getString(1));
                objFixture.setTeamID1(cursor.getString(cursor.getColumnIndex(FIXTURE_TEAM1)));
                objFixture.setTeamID2(cursor.getString(cursor.getColumnIndex(FIXTURE_TEAM2)));
                //objFixture.setTeamName1(cursor.getString(cursor.getColumnIndex("team1")));
                //objFixture.setTeamName2(cursor.getString(cursor.getColumnIndex("team2")));
                objFixture.setDate(cursor.getInt(cursor.getColumnIndex(FIXTURE_DATE)));
                objFixture.setPhase(cursor.getString(cursor.getColumnIndex(FIXTURE_PHASE)));

                objFixture.setFlag1(getTeamCodeByName(objFixture.getTeamID1()));
                objFixture.setFlag2(getTeamCodeByName(objFixture.getTeamID2()));
                listFixture.add(objFixture);
            } while (cursor.moveToNext());
        }
        return listFixture;
    }

    //pass the desires fields from cursor to Teams Activity
    public static ArrayList<fields_row_team_by_cups> readTeams(Cursor cursor){
        ArrayList<fields_row_team_by_cups> listTeams = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do{
                fields_row_team_by_cups objTeams = new fields_row_team_by_cups();
                objTeams.setTeamID(cursor.getString(cursor.getColumnIndex(TEAM_ID)));
                objTeams.setName(cursor.getString(cursor.getColumnIndex(TEAM_NAME)));
                objTeams.setFlag(getTeamCodeByName(objTeams.getTeamID()));
                objTeams.setCups(cursor.getInt(cursor.getColumnIndex(TEAM_CUPS)));
                listTeams.add(objTeams);
            }while (cursor.moveToNext());
        }
        return listTeams;
    }
/*
    public static ArrayList<fields_row_team_by_groups> readGroups(Cursor cursor){
        ArrayList<fields_row_team_by_groups> listGroups = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do{
                fields_row_team_by_groups objGroups = new fields_row_team_by_groups();

                objGroups.setTeamName(cursor.getString(cursor.getColumnIndex(TEAM_ID)));
                objGroups.setFlag(getTeamCodeByName(objGroups.teamName));
                objGroups.setGamesPlayed(cursor.getInt(cursor.getColumnIndex(TEAM_GAMES_PLAYED)));
                objGroups.setTeamWins(cursor.getInt(cursor.getColumnIndex(TEAM_WINS)));
                objGroups.setTeamDraws(cursor.getInt(cursor.getColumnIndex(TEAM_DRAWS)));
                objGroups.setTeamLoses(cursor.getInt(cursor.getColumnIndex(TEAM_LOSES)));
                objGroups.setTeamGF(cursor.getInt(cursor.getColumnIndex(TEAM_GOALS_FAVOR)));
                objGroups.setTeamGA(cursor.getInt(cursor.getColumnIndex(TEAM_GOALS_AGAINST)));
            }while(cursor.moveToNext());
        }
        return listGroups;
    }
*/
    //pass the desires fields from cursor to Groups Activity
    public static ArrayList<fields_row_team_by_groups> readTeamsByGroup(Cursor cursor) {
        ArrayList<fields_row_team_by_groups> listTeams = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do{
                fields_row_team_by_groups objTeams = new fields_row_team_by_groups();

                objTeams.setTeamID(cursor.getString(cursor.getColumnIndex(TEAM_ID)));
                objTeams.setTeamName(cursor.getString(cursor.getColumnIndex(TEAM_NAME)));
                objTeams.setFlag(getTeamCodeByName(objTeams.teamID));
                objTeams.setGamesPlayed(cursor.getInt(cursor.getColumnIndex(TEAM_GAMES_PLAYED)));
                objTeams.setTeamWins(cursor.getInt(cursor.getColumnIndex(TEAM_WINS)));
                objTeams.setTeamDraws(cursor.getInt(cursor.getColumnIndex(TEAM_DRAWS)));
                objTeams.setTeamLoses(cursor.getInt(cursor.getColumnIndex(TEAM_LOSES)));
                objTeams.setTeamGF(cursor.getInt(cursor.getColumnIndex(TEAM_GOALS_FAVOR)));
                objTeams.setTeamGA(cursor.getInt(cursor.getColumnIndex(TEAM_GOALS_AGAINST)));
                objTeams.setTeamDif(objTeams.getGoalsFavor(),objTeams.getGoalsAgainst());
                objTeams.setPoints(cursor.getInt(cursor.getColumnIndex(TEAM_POINTS)));
                listTeams.add(objTeams);
            }while (cursor.moveToNext());
        }
        return listTeams;
    }

    public static ArrayList<fields_groups> readTitleGroup(){
        ArrayList<fields_groups> listTitles = new ArrayList<>();

        {fields_groups objTitle = new fields_groups();
        objTitle.setGroupName("Group A");
        listTitles.add(objTitle);}
        {fields_groups objTitle = new fields_groups();
        objTitle.setGroupName("Group B");
        listTitles.add(objTitle);}
        {fields_groups objTitle = new fields_groups();
        objTitle.setGroupName("Group C");
        listTitles.add(objTitle);}

        return listTitles;
    }

    //In order to get find the according image for the corresponding country, this function is needed
    public static int getTeamCodeByName(String teamID){
        int code = 0;
        switch (teamID){
            case "ARG":
                code = R.drawable.ar; break;
            case "BOL":
                code = R.drawable.bo; break;
            case "BRA":
                code = R.drawable.br; break;
            case "CHL":
                code = R.drawable.cl; break;
            case "COL":
                code = R.drawable.co; break;
            case "ECU":
                code = R.drawable.ec; break;
            case "JAM":
                code = R.drawable.jm; break;
            case "MEX":
                code = R.drawable.mx; break;
            case "PER":
                code = R.drawable.pe; break;
            case "PRY":
                code = R.drawable.py; break;
            case "URY":
                code = R.drawable.uy; break;
            case "VEN":
                code = R.drawable.ve; break;
            default:
                break;
        }
        return code;
    }
    public void _ (String message){
        Log.d("FixtureDatabase##########", message);
    }

}
