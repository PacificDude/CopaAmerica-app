package monte.com.copaamerica2015mysql;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class FixtureActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    FixtureDatabase myDB;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        recyclerView = (RecyclerView)findViewById(R.id.fixtureList);

        myDB = new FixtureDatabase(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String keyName = bundle.getString("ID_KEY");
            Log.d("Fixture", keyName);
            c = myDB.readFixtureByTeam(keyName);
        }else {
            c = myDB.readAllFixture();
            _("Cursor obtained");
        }

        //setting the adapter. This is a reference to the class created "SimpleRecycleAdapter.java"
        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(this, myDB.readFixture(c));
        recyclerView.setAdapter(adapter);
        _("Recycler adapter set up");
        //creates the layout manager using LinearLayoutManager which provided similar functions to ListView
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        _("Layout manager set up");
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fixture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.groups:
                startActivity(new Intent(this,GroupsActivity.class));
                break;
            case R.id.teams:
                startActivity(new Intent(this,TeamsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void _ (String message){
        Log.d("FixtureDatabase##########", message);
    }
}
