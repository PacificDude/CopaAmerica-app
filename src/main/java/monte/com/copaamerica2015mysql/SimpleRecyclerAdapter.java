package monte.com.copaamerica2015mysql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel on 5/3/2015.
 */

/*********************** STEPS TO CREATE A RECYCLERVIEW ****************************
    Add this line in gradle(Module) under dependencies: compile 'com.android.support:recyclerview-v7:+' to import it
    Design the desired layout for your recyclerview
    Add the recyclerview in your activity layout (<android.support.v7.widget.RecyclerView ...)
    Create a Contructor in a new Class for the fields in the recyclerview
    Create the Adapter that will pass the data of objects type of the line above, this new adapter will extend...
    ... Recyclerview.Adapter, then create the subclass ViewHolder inside the Adapter.
    In OnCreate the custom layout should be inflated and in onCreateViewHolder is where the datasource is passed
    Set up the LayoutManager

*/


public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.elementsViewHolder>
    //once the ViewHolder is created it must be replaced in the methods' arguments which
    //had a "RecyclerView.ViewHolder" name. Note the arguments' values are now "elementsViewHolder" in onCreate & onBind
{
    private LayoutInflater inflater;
    List<fields_row_fixture> fixtureData; //array of objects which will contain data passed from array in db

    public SimpleRecyclerAdapter(Context context, ArrayList<fields_row_fixture> listFixture){
        //inflater = LayoutInflater.from(context); //another way to inflate (by slidenerd)
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.fixtureData = listFixture;
    }

    @Override
    public elementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_fixture, parent, false); //"parent" is the RecyclerView in this case
        /*above the 3rd parameters "false" means rowfixture should be inflated but not made part of
        the parent and it should use the LayoutParams specified by parent. "True" means rowfixture should be
        inflated and made part of the parent.
        */
        elementsViewHolder holder = new elementsViewHolder(v);
        return holder;
        //Basically the custom row (row_fixture) is inflated and passed to the root view which is passed to the ViewHolder
    }

    /*

    long dv = Long.valueOf(timestamp_in_string)*1000;// its need to be in milisecond
    Date df = new java.util.Date(dv);
    String vv = new SimpleDateFormat("MM dd, yyyy hh:mma").format(df);

     */

    @Override
    public void onBindViewHolder(elementsViewHolder holder, int position) {
        fields_row_fixture current = fixtureData.get(position);
        //it returns the current object from the array list fixtureData in the position "position"

        long dateSeconds = Long.valueOf(current.date)*1000; //milliseconds stored in db * 1000 to get in in seconds
        Date df = new Date(dateSeconds);
        String titleDate = new SimpleDateFormat("EEE dd, MMMM yyyy h:mma").format(df);

        //then pass it to the viewHolder
        holder.tvDate.setText(titleDate);
        holder.tvPhase.setText(current.phase);
        holder.tvTeam1.setText(current.teamName1);
        holder.tvTeam2.setText(current.teamName2);
        holder.ivTeam1.setImageResource(current.flag1);
        holder.ivTeam2.setImageResource(current.flag2);
    }

    @Override
    public int getItemCount() {
        return fixtureData.size(); //size of the datasource
    }

    //subclass viewHolder required for the Adapter.
    public static class elementsViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvGroup, tvTeam1, tvTeam2, tvPhase;
        ImageView ivTeam1, ivTeam2;

        public elementsViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvGroup = (TextView)itemView.findViewById(R.id.tvGroup);
            tvTeam1 = (TextView)itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = (TextView)itemView.findViewById(R.id.tvTeam2);
            tvPhase = (TextView)itemView.findViewById(R.id.tvPhase);
            ivTeam1 = (ImageView)itemView.findViewById(R.id.ivTeam1);
            ivTeam2 = (ImageView)itemView.findViewById(R.id.ivTeam2);

        }
    }
/*
    //***** PASSING DATA FROM ARRAY IN FixtureDatabase TO LOCAL ARRAY IN THIS CLASS ******
    public static List<fields_row_fixture> getData(){
        List<fields_row_fixture> data = new ArrayList<>();
        int[] icons = {R.drawable.ar, R.drawable.bo, R.drawable.br, R.drawable.cl,
            R.drawable.co, R.drawable.ec, R.drawable.jm, R.drawable.mx,
            R.drawable.pe, R.drawable.py, R.drawable.uy, R.drawable.ve};

        return getData();
    }
*/
}
