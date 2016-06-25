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
 * Created by Miguel on 5/23/2015.
 */

public class GroupsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    List<fields_row_team_by_groups> dataGroups;
    List<fields_groups> dataTitleGroups;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    int i = 0;

    public GroupsRecyclerAdapter(Context context,
                                 ArrayList<fields_row_team_by_groups> listGroups,
                                 ArrayList<fields_groups> listTitleGroup){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataTitleGroups = listTitleGroup;
        this.dataGroups = listGroups;
    }

    //this tell the layout that there are 2 types of items in the recyclerview (header and items)
    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0 || position == 5 || position == 10){
            type = TYPE_HEADER;
        }else{
            type = TYPE_ITEM;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER){
            View v = inflater.inflate(R.layout.row_team_by_group_header, parent, false);
            headerElementsViewHolder holder = new headerElementsViewHolder(v);
            return holder;
        }else{
            View v = inflater.inflate(R.layout.row_team_by_group_item, parent, false);
            itemElementsViewHolder holder = new itemElementsViewHolder(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof headerElementsViewHolder){
            headerElementsViewHolder headerHolder = (headerElementsViewHolder)holder;
            fields_groups current = dataTitleGroups.get(i);
            headerHolder.tvGroupName.setText(current.groupName);
            i++;
        }else{
            itemElementsViewHolder itemHolder = (itemElementsViewHolder)holder;
            fields_row_team_by_groups current = dataGroups.get(position - i);

            itemHolder.ivTeamFlag.setImageResource(current.flag);
            itemHolder.tvTeamName.setText(current.teamName);
            itemHolder.tvGP.setText(String.valueOf(current.gamesPlayed));
            itemHolder.tvWins.setText(String.valueOf(current.wins));
            itemHolder.tvDraws.setText(String.valueOf(current.draws));
            itemHolder.tvLoses.setText(String.valueOf(current.loses));
            itemHolder.tvGF.setText(String.valueOf(current.goalsFavor));
            itemHolder.tvGA.setText(String.valueOf(current.goalsAgainst));
            itemHolder.tvDif.setText(String.valueOf(current.goalsDif));
            itemHolder.tvPoints.setText(String.valueOf(current.points));
        }
    }

    @Override
    public int getItemCount() {
        int sum = 0;
        sum = dataGroups.size() + dataTitleGroups.size();
        return sum;
    }

    public static class headerElementsViewHolder extends RecyclerView.ViewHolder{
        TextView tvGroupName;

        public headerElementsViewHolder(View itemView) {
            super(itemView);
            tvGroupName = (TextView)itemView.findViewById(R.id.tvGroup);
        }
    }

    public static class itemElementsViewHolder extends RecyclerView.ViewHolder{
        ImageView ivTeamFlag;
        TextView tvTeamName, tvGP, tvWins, tvDraws, tvLoses, tvGF, tvGA, tvDif, tvPoints;

        public itemElementsViewHolder(View itemView) {
            super(itemView);
            ivTeamFlag = (ImageView)itemView.findViewById(R.id.ivFlag);
            tvTeamName = (TextView)itemView.findViewById(R.id.tvTeam);
            tvGP = (TextView)itemView.findViewById(R.id.tvGP);
            tvWins = (TextView)itemView.findViewById(R.id.tvWins);
            tvDraws = (TextView)itemView.findViewById(R.id.tvDraws);
            tvLoses = (TextView)itemView.findViewById(R.id.tvLoses);
            tvGF = (TextView)itemView.findViewById(R.id.tvGF);
            tvGA = (TextView)itemView.findViewById(R.id.tvGA);
            tvDif = (TextView)itemView.findViewById(R.id.tvDifGoals);
            tvPoints = (TextView)itemView.findViewById(R.id.tvPoints);
        }
    }
}
