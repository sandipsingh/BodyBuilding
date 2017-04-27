package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import bodybuilding.fitnesstrainer.R;
import model.SubCategory_Item;


/**
 * Created by Pradeep on 7/27/2016.
 */
public class SubCategoryAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    int images[];
    String category_name[];
    ArrayList<SubCategory_Item>list_subcategory;

    public SubCategoryAdapter(Context context, ArrayList<SubCategory_Item>list_subcategory) {
        this.context = context;
        this.list_subcategory = list_subcategory;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return list_subcategory.size();
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
            convertView = inflater.inflate(R.layout.subcategory_listitems, null);
            holder.img_category = (ImageView) convertView.findViewById(R.id.img_category);
            holder.txt_categoryname=(TextView)convertView.findViewById(R.id.category_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();

        }






        if(!((Activity) context).isFinishing())
        {
            // load image
            try {
                // get input stream
                InputStream ims = context.getAssets().open(list_subcategory.get(position).getImagePath());
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                holder.img_category.setImageDrawable(d);
            }
            catch(IOException ex) {

            }
            holder.txt_categoryname.setText(""+list_subcategory.get(position).getCategory_name());

        }
//            ((Activity) context).finish();

        return convertView;
    }

    class Holder {
        TextView txt_categoryname = null;
        ImageView img_category;

    }
}
