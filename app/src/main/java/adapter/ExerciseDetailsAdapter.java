package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bodybuilding.fitnesstrainer.R;
import model.ExerciseDetails;


/**
 * Created by Pradeep on 7/27/2016.
 */
public class ExerciseDetailsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

   ArrayList<ExerciseDetails>list;


    public ExerciseDetailsAdapter(Context context, ArrayList<ExerciseDetails>list) {
        this.context = context;

        this.list=list;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.exercise_itemdetails, null);
            holder.txt_categoryname=(TextView)convertView.findViewById(R.id.item_details);
            holder.txt_itemposition=(TextView)convertView.findViewById(R.id.item_position);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();

        }


        Typeface face = Typeface.createFromAsset(context.getAssets(), "Quicksand-Regular.ttf");
        holder.txt_categoryname.setTypeface(face);
        if(list.get(position).getItem_Details().equals("Muscles Targeted :")
                ||list.get(position).getItem_Details().equals("Tips :")
        ||list.get(position).getItem_Details().contains("first")
               )


        {
            holder.txt_categoryname.setTypeface(null, Typeface.BOLD);
            holder.txt_itemposition.setVisibility(View.GONE);
        }
        if(list.get(position).getItem_Details().contains("Grab")
                ||list.get(position).getItem_Details().contains("first")
                ||list.get(position).getItem_Details().contains("weights")
                ||list.get(position).getItem_Details().contains("slightly")
                ){
            holder.txt_categoryname.setTypeface(face);

        }

        holder.txt_categoryname.setText(""+list.get(position).getItem_Details());
        holder.txt_itemposition.setText((position + 1)+". " );
        System.out.println("======tips==" + list.get(position).getItem_Details());
//
        return convertView;
    }

    class Holder {
        TextView txt_categoryname,txt_itemposition;


    }
}
