package monte.com.copaamerica2015mysql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Miguel on 5/19/2015.
 */
public class TeamsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        {
    private LayoutInflater inflater;
    List<fields_row_team_by_cups> teamsData;
    int TYPE_HEADER = 0;
    int TYPE_ITEM = 1;
    Context context;
    private clickListener clickListener;

    public TeamsRecycleAdapter(Context context, ArrayList<fields_row_team_by_cups> listTeams){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.teamsData = listTeams;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEADER;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER){
            View v = inflater.inflate(R.layout.row_number_of_cups, parent, false);
            headerViewHolder holder = new headerViewHolder(v);
            return holder;
        }else{
            View v = inflater.inflate(R.layout.row_team_by_cups, parent, false);
            itemsViewHolder holder = new itemsViewHolder(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof itemsViewHolder) {
            fields_row_team_by_cups current = teamsData.get(position - 1);
            itemsViewHolder itemHolder = (itemsViewHolder)holder;
            itemHolder.ivTeamFlag.setImageResource(current.teamFlag);
            itemHolder.tvTeamName.setText(current.teamName);
            itemHolder.tvTeamCups.setText(String.valueOf(current.teamCups));
        }
    }

    @Override
    public int getItemCount() {
        return teamsData.size() + 1;
    }

    public class headerViewHolder extends RecyclerView.ViewHolder{
        public headerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class itemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivTeamFlag;
        TextView tvTeamName, tvTeamCups;

        public itemsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this); //enables a click listener to each layout
            ivTeamFlag = (ImageView)itemView.findViewById(R.id.ivFlag);
            tvTeamName = (TextView)itemView.findViewById(R.id.tvTeamName);
            tvTeamCups = (TextView)itemView.findViewById(R.id.tvTeamCups);
        }

        //once View.OnClickListener is implemented, this abs method need to be added.
        @Override
        public void onClick(View v) { //itemView in ViewHolder goes to "v" argument
            if (clickListener != null){
                clickListener.itemClicked(v, getPosition(), tvTeamName.getText().toString());
            }
        }
    }

    //when creating an interface the method need to be empty, but its childs need to fill this variables
    // (see TeamsActivity)
    public interface clickListener{
        public void itemClicked(View view, int position, String name);
    }

    // method object that implements a clickListener interface
    public void setClickListener(clickListener activity){
        this.clickListener = activity;
    }

}
