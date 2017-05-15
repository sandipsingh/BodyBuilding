package bodybuilding.fitnesstrainer;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.SubCategoryAdapter;
import au.com.bytecode.opencsv.CSVReader;
import model.SubCategory_Item;

public class SubcategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView btn_back;
    GridView gridView;
    SubCategoryAdapter adapter;

    String categoryID, categoryName;
    /**
     * Called when the activity is first created.
     */
    private Spinner spMainSelectCategory;
    private TextView tvMainSelectedCate;
    ArrayList<HashMap<String, String>> myList;
    private ArrayList<String> categoryList = new ArrayList<String>();
    ArrayList<SubCategory_Item> list_subcategory;
    TextView txt_title;

ImageView btn_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        gridView = (GridView) findViewById(R.id.gridview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_title=(TextView)findViewById(R.id.txt_title);
        btn_profile=(ImageView)findViewById(R.id.profile);
        setSupportActionBar(toolbar);

        list_subcategory = new ArrayList<>();
        myList = new ArrayList<>();


        //get categoryId

        Intent intent = getIntent();
        categoryID = intent.getExtras().getString("cat_id");
        categoryName = intent.getExtras().getString("cat_name");
        System.out.println("=========cat_id===" + categoryID);

        txt_title.setText("" + categoryName+" WORKOUT");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });


        List<String[]> list = new ArrayList<String[]>();
        String next[] = {};
        try {
            InputStreamReader csvStreamReader = new InputStreamReader(
                    SubcategoryActivity.this.getAssets().open(
                            "new_subcategory.csv"));
            CSVReader reader = new CSVReader(csvStreamReader);
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);

                } else {
                    break;
                }

            }
            System.out.println("=============list==" + list);
            System.out.println("=============list size==" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < list.size(); i++) {
            categoryList.add(list.get(i)[3]);
            String SUBCATEGORY_ID = list.get(i)[0];
            String CATEGORY_ID = list.get(i)[1];
            String IMAGE_PATH = list.get(i)[2];
            String CATEGORY_NAME = list.get(i)[3];
            System.out.println("=============categoryID match==" + categoryID + "====" + CATEGORY_ID);

            if (categoryID.equals(CATEGORY_ID)) {
                list_subcategory.add(new SubCategory_Item(SUBCATEGORY_ID, CATEGORY_ID, IMAGE_PATH,CATEGORY_NAME));
            }


        }

        for (int i = 0; i < list_subcategory.size(); i++) {
            System.out.println("=======list item=======" + list_subcategory.get(i).getImagePath());
        }
        if (list_subcategory.size() != 0) {

            adapter = new SubCategoryAdapter(SubcategoryActivity.this, list_subcategory);
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.getSelector().setAlpha(0);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("====next");
                    Intent intent1=new Intent(getApplicationContext(),ExerciseDetailsActivity.class);
                    intent1.putExtra("sub_catID",list_subcategory.get(position).getSub_categoryID());
                    intent1.putExtra("sub_catName",list_subcategory.get(position).getCategory_name());
                    startActivity(intent1);

                }
            });

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SubcategoryActivity.this.finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExerciseDetailsActivity.deleteCache(SubcategoryActivity.this);
    }

}
