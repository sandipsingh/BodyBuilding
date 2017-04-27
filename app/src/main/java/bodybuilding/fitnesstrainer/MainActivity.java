package bodybuilding.fitnesstrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {
    CategoryAdapter adapter;
    GridView gridView;
    int images[] = {R.drawable.abs, R.drawable.biceps,
            R.drawable.chest, R.drawable.triceps};
    /*int images[]={R.drawable.abs,R.drawable.back,R.drawable.biceps,
            R.drawable.calf,R.drawable.chest,R.drawable.forearms,
            R.drawable.legs,R.drawable.shoulure,R.drawable.triceps};*/
  /*  String category_name[]={"ABS","BACK","BICEPS",
            "CALF","CHEST","FOREARMS",
            "LEGS","SHOULDER","TRISEPT"};*/

    String category_name[] = {"ABS", "BICEPS",
            "CHEST", "TRICEPS"};

    String categoryID[] = {"abs_001", "biceps_002", "chest_003",
            "tricept_004"};
    ImageView btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (GridView) findViewById(R.id.gridview);
        btn_profile = (ImageView) findViewById(R.id.profile);
        setSupportActionBar(toolbar);



        String str=" abc d 1234567890pqr 54897";
        Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
        Matcher matcher = pattern.matcher(str);
        for(int i = 0 ; i < matcher.groupCount(); i++) {
            matcher.find();
            System.out.println("=====Find number"+matcher.group());
        }

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        adapter = new CategoryAdapter(MainActivity.this, images, category_name, categoryID);
        gridView.setAdapter(adapter);
        gridView.getSelector().setAlpha(0);
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SubcategoryActivity.class);
                intent.putExtra("cat_id", categoryID[position]);
                intent.putExtra("cat_name", category_name[position]);
                System.out.println("===========cat_id===" + categoryID[position]);
                startActivity(intent);


            }
        });
    }
}
