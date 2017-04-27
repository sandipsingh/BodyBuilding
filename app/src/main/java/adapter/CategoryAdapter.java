package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import bodybuilding.fitnesstrainer.R;


/**
 * Created by Pradeep on 7/27/2016.
 */
public class CategoryAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    int images[];
    String category_name[];
    String categoryID[];

    public CategoryAdapter(Context context, int images[], String category_name[], String categoryID[]) {
        this.context = context;
        this.images = images;
        this.category_name=category_name;
        this.categoryID=categoryID;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return images.length;
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
            convertView = inflater.inflate(R.layout.category_listitems, null);
            holder.img_category = (ImageView) convertView.findViewById(R.id.img_category);
            holder.txt_categoryname=(TextView)convertView.findViewById(R.id.category_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();

        }
        holder.img_category.setImageResource(images[position]);
        holder.txt_categoryname.setText(""+category_name[position]);

        return convertView;
    }

    class Holder {
        TextView txt_categoryname = null;
        ImageView img_category;

    }
}
