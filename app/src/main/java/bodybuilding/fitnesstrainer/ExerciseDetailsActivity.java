package bodybuilding.fitnesstrainer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ravindu1024.indicatorlib.ViewPagerIndicator;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.ExerciseDetailsAdapter;
import adapter.SlidingImage_Adapter;
import adapter.SubCategoryAdapter;
import au.com.bytecode.opencsv.CSVReader;
import model.ExerciseDetails;
import model.ImageDetails;
import model.SubCategory_Item;

public class ExerciseDetailsActivity extends AppCompatActivity {
    ImageView btn_back;
    Toolbar toolbar;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final int[] IMAGES = {R.drawable.chest, R.drawable.chest, R.drawable.chest, R.drawable.chest};
    private ArrayList<String> ImagesArray = new ArrayList<>();
    SlidingImage_Adapter mAdapter;
    ViewPager viewPager;
    ArrayList<String>mList;
    ListView listView;
    ExerciseDetailsAdapter adapter;
    private ArrayList<String> categoryList = new ArrayList<String>();
    ArrayList<ExerciseDetails> list_itemdetails;
    ArrayList<ImageDetails>list_imagedetails;
    TextView txt_title,txt_itemname;
    String subCategoryID,sub_catName;
    ImageView btn_profile;

    // Determine the center of the screen to center 'earth'
   public static Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        // initilized lauout variable
        listView=(ListView)findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_title=(TextView)findViewById(R.id.title);
        btn_profile=(ImageView)findViewById(R.id.profile);

        display = getWindowManager().getDefaultDisplay();
        //Set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list_itemdetails = new ArrayList<>();
        list_imagedetails=new ArrayList<>();
        //get categoryId
         Intent intent = getIntent();
        subCategoryID = intent.getExtras().getString("sub_catID");
        sub_catName = intent.getExtras().getString("sub_catName");

        System.out.println("=========sub_catID===" + subCategoryID);
        System.out.println("=========sub_catName===" + sub_catName);

        txt_title.setText("" + sub_catName);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                SlidingImage_Adapter.videoView.stopPlayback();
                ExerciseDetailsActivity.this.finish();
                deleteCache(ExerciseDetailsActivity.this);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);

            }
        });








        List<String[]> list1 = new ArrayList<String[]>();
        String next1[] = {};
        try {
            InputStreamReader csvStreamReader = new InputStreamReader(
                    ExerciseDetailsActivity.this.getAssets().open(
                            "new_videodetaits.csv"));
            CSVReader reader = new CSVReader(csvStreamReader);
            for (; ; ) {
                next1 = reader.readNext();
                if (next1 != null) {
                    list1.add(next1);

                } else {
                    break;
                }

            }
//            System.out.println("=============list==" + list);
//            System.out.println("=============list size==" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < list1.size(); i++) {
            categoryList.add(list1.get(i)[2]);
            String ITEM_ID = list1.get(i)[0];
            String SUB_CATEGORY_ID = list1.get(i)[1];
            String IMAGE_DETAILS = list1.get(i)[2];
            System.out.println("=============categoryID match==" + subCategoryID + "====" + SUB_CATEGORY_ID);

            if (subCategoryID.equals(SUB_CATEGORY_ID)) {

                list_imagedetails.add(new ImageDetails(ITEM_ID,SUB_CATEGORY_ID,IMAGE_DETAILS));
            }


        }

        //code to add header and footer to listview
        LayoutInflater inflater = getLayoutInflater();
        View header = (View) inflater.inflate(R.layout.exercises_header, listView,
                false);
        listView.addHeaderView(header, null, false);

        // Initilization
        ViewPagerIndicator indicator = (ViewPagerIndicator) header.findViewById(R.id.pager_indicator);
        txt_itemname=(TextView)header.findViewById(R.id.name);
        viewPager = (ViewPager) header.findViewById(R.id.pager);

        if(list_imagedetails.size()!=0){
            mAdapter = new SlidingImage_Adapter(ExerciseDetailsActivity.this, list_imagedetails);
            viewPager.setAdapter(mAdapter);
            indicator.setPager(viewPager);
            mAdapter.notifyDataSetChanged();
        }


        List<String[]> list = new ArrayList<String[]>();
        String next[] = {};
        try {
            InputStreamReader csvStreamReader = new InputStreamReader(
                    ExerciseDetailsActivity.this.getAssets().open(
                            "new_itemsdetails.csv"));
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
            categoryList.add(list.get(i)[2]);
            String ITEM_ID = list.get(i)[0];
            String SUB_CATEGORY_ID = list.get(i)[1];
            String DETAILS = list.get(i)[2];
            System.out.println("=============categoryID match==" + subCategoryID + "====" + SUB_CATEGORY_ID);

            if (subCategoryID.equals(SUB_CATEGORY_ID)) {

                list_itemdetails.add(new ExerciseDetails(ITEM_ID,SUB_CATEGORY_ID,DETAILS));
            }


        }

        for (int i = 0; i < list_itemdetails.size(); i++) {
            System.out.println("=======list item=======" + list_itemdetails.get(i).getItemId());
        }
        if (list_itemdetails.size() != 0) {
            adapter=new ExerciseDetailsAdapter(ExerciseDetailsActivity.this,list_itemdetails);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.getSelector().setAlpha(0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
//        txt_itemname.setText("" + sub_catName);
//        txt_itemname.setVisibility(View.GONE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            indicator.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ExerciseDetailsActivity.this.finish();
        deleteCache(ExerciseDetailsActivity.this);
        SlidingImage_Adapter.videoView.stopPlayback();



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
    }



    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
