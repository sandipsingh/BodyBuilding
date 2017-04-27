package adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;


import java.util.ArrayList;

import bodybuilding.fitnesstrainer.ExerciseDetailsActivity;
import bodybuilding.fitnesstrainer.R;
import model.ImageDetails;
import zoom_image.FullScreenVideoView;


public class SlidingImage_Adapter extends PagerAdapter {


    private int IMAGES[];
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ImageDetails> list;
    ImageView zoom_image;
    SlidingImage_Adapter image_adapter;

    public static FullScreenVideoView videoView;
    public SlidingImage_Adapter(ExerciseDetailsActivity context, ArrayList<ImageDetails> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.sliding_image, view, false);

        assert imageLayout != null;
        videoView= (FullScreenVideoView) imageLayout
                .findViewById(R.id.image);


        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(videoView);
        String vodeo = list.get(position).getimage_Details();


        if(!((Activity) context).isFinishing())
        {
            //show dialog
        }

        String uriPath = "android.resource://bodybuilding.fitnesstrainer/raw/" + list.get(position).getimage_Details();
        System.out.println("========video path" + uriPath);
        System.out.println("========video url" + list.get(position).getimage_Details());
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}