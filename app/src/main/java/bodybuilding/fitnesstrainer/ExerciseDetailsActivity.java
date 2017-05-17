package bodybuilding.fitnesstrainer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import adapter.SlidingImage_Adapter;
import model.ExerciseDetails;
import model.ImageDetails;
import zoom_image.FullScreenVideoView;

public class ExerciseDetailsActivity extends AppCompatActivity {
    ImageView btn_back;
    Toolbar toolbar;
    private static final int[] IMAGES = {R.drawable.chest, R.drawable.chest, R.drawable.chest, R.drawable.chest};
    ArrayList<ExerciseDetails> list_itemdetails;
    ArrayList<ImageDetails>list_imagedetails;
    TextView txt_title, exercise_text;
    String subCategoryID,sub_catName, uriPath;
    ImageView btn_profile;
    ImageView btn_start_exercise;
    FullScreenVideoView videoView;

    // Determine the center of the screen to center 'earth'
   public static Display display;
    String rawpath = "android.resource://bodybuilding.fitnesstrainer/raw/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        // initialized layout variables
        videoView = (FullScreenVideoView) findViewById(R.id.video);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_title = (TextView) findViewById(R.id.title);
        exercise_text = (TextView) findViewById(R.id.exercise_text);
        btn_profile = (ImageView) findViewById(R.id.profile);
//        btn_start_exercise = (ImageView)findViewById(R.id.timer);

        //Set toolbar
        setSupportActionBar(toolbar);
//      getSupportActionBar().setDisplayShowTitleEnabled(false);

        list_itemdetails = new ArrayList<>();
        list_imagedetails = new ArrayList<>();

        //get categoryId
        Intent intent = getIntent();
        subCategoryID = intent.getExtras().getString("sub_catID");
        sub_catName = intent.getExtras().getString("sub_catName");

        System.out.println("=========sub_catID===" + subCategoryID);
        System.out.println("=========sub_catName===" + sub_catName);

        switch (sub_catName){
            case "Dumbbell Side Bends":
                uriPath = rawpath +"dumbbellsidebend";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.absworkout_1)));
                break;

            case "Flat Bench Lying Leg Raise":
                uriPath = rawpath +"flat_bench_lying_legraise";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.absworkout_2)));
                break;

            case "Hanging Knee Raise":
                uriPath = rawpath +"d_abs_leg_raises";
                System.out.println("==hello==");
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.absworkout_3)));
                break;

            case "Decline Crunch":
                uriPath = rawpath +"decline_crunch";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.absworkout_4)));
                break;

            case "Leg Raises":
                uriPath = rawpath +"leg_raises";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.absworkout_5)));
                break;

            case "EZ Bar Curl":
                uriPath = rawpath +"ez_bar_curl";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_6)));
                break;

            case "Hammer Curls":
                uriPath = rawpath +"hammer_curls";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_7)));
                break;

            case "Straight Bench Press":
                uriPath = rawpath +"straight_bench_press";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_8)));
                break;

            case "Push Up":
                uriPath = rawpath +"push_up";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.chestworkout_9)));
                break;

            case "Straight Bar Triceps Pushdown":
                uriPath = rawpath +"pushdown";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.tricepsworkout_10)));
                break;


            case "Close Push Ups":
                uriPath = rawpath +"closepushup";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.tricepsworkout_11)));
                break;

            case "Decline Bench Press":
                uriPath = rawpath +"decline_benchpress";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.chestworkout_12)));
                break;

            case "Decline Dumbbell Bench Press":
                uriPath = rawpath +"decline_dumbbell_bench_press";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.chestworkout_13)));
                break;

            case "Incline Bench Press":
                uriPath = rawpath +"incline_bench_press";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.chestworkout_14)));
                break;

            case "Incline Fly Dumbbell Bench Press":
                uriPath = rawpath +"incline_flydumbbell_bench_press";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.chestworkout_15)));
                break;

            case "Incline Dumbbell Curls":
                uriPath = rawpath +"incline_dumbbell_curls";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_16)));
                break;

            case "Wide Grip Standing Barbell Curls":
                uriPath = rawpath +"wide_grip_standing_barbell_curls";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_17)));
                break;

            case "Zottman Curl":
                uriPath = rawpath +"zottman_curl";
                exercise_text.setText(Html.fromHtml(getResources().getString(R.string.bicepsworkout_18)));
                break;

        }
        txt_title.setText("" + sub_catName);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                System.out.println("====onback press=");
//                SlidingImage_Adapter.videoView.stopPlayback();
                ExerciseDetailsActivity.this.finish();
                deleteCache(ExerciseDetailsActivity.this);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        MediaController mediaController = new MediaController(ExerciseDetailsActivity.this);
        mediaController.setAnchorView(videoView);

        System.out.println("========video path" + uriPath);
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        Typeface face = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.ttf");
        exercise_text.setTypeface(face);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ExerciseDetailsActivity.this.finish();
        deleteCache(ExerciseDetailsActivity.this);
//        SlidingImage_Adapter.videoView.stopPlayback();
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
