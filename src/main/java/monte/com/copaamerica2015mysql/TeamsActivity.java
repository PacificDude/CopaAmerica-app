package monte.com.copaamerica2015mysql;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TeamsActivity extends ActionBarActivity implements TeamsRecycleAdapter.clickListener  {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FixtureDatabase myDB;
    Cursor c;
    String BUNDLE_KEY = "ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        recyclerView = (RecyclerView)findViewById(R.id.teamsList);
        myDB = new FixtureDatabase(this);
        c = myDB.readTeamsByCups();

        TeamsRecycleAdapter adapter = new TeamsRecycleAdapter(this,myDB.readTeams(c));
        adapter.setClickListener(this); //enabling a click listener which is implemented on TeamsRecycleAdapter
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //this abs method need to be added when implementing clickListener from TeamsRecyclerAdapter
    @Override
    public void itemClicked(View view, int position, String name) {
        Intent intent = new Intent(this, FixtureActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, name);

        intent.putExtras(bundle);

        Log.d("ABC", "Position: " + position + " " + name);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.fixture:
                startActivity(new Intent(this,FixtureActivity.class));
                break;
            case R.id.groups:
                startActivity(new Intent(this,GroupsActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
