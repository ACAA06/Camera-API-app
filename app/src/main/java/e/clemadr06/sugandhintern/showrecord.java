package e.clemadr06.sugandhintern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class showrecord extends Activity implements
        OnPreparedListener, OnVideoSizeChangedListener, SurfaceHolder.Callback {
    public Button play;
    private int mVideoWidth;
    private int mVideoHeight;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;
public MediaPlayer mediaPlayer;
    private MediaPlayer mMediaPlayer;
    private SurfaceView mPreview;
    private SurfaceHolder holder;
    public ImageView myImage;
    public Bitmap myBitmap;
    public keys s;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        File imgFile = new  File(this.getFilesDir(), "image.jpg");

        setContentView(R.layout.activity_showrecord);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Recordings");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        play=(Button)findViewById(R.id.play) ;

        if(imgFile.exists()){

            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            myImage = (ImageView)findViewById(R.id.showimage);
            myImage.setImageBitmap(myBitmap);

        }
        mPreview = findViewById(R.id.surface);
        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        if (width == 0 || height == 0) {  return;       }
        mIsVideoSizeKnown = true;
        mVideoWidth = width;
        mVideoHeight = height;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        mIsVideoReadyToBePlayed = true;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Bundle extras = getIntent().getExtras();
        playVideo();
    }
    private void playVideo() {
        doCleanUp();
        int flag=0;
        try {
            File file=new File(getFilesDir(), "video.mp4");
                    String pathToFile = file.getAbsolutePath();
                    // Create a new media player and set the listeners
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(pathToFile);
                    mMediaPlayer.setDisplay(holder);
                    mMediaPlayer.prepare();
                    mMediaPlayer.setOnPreparedListener(this);
                    mMediaPlayer.setOnVideoSizeChangedListener(this);
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        } catch (Exception e) {
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void doCleanUp() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        mIsVideoReadyToBePlayed = false;
        mIsVideoSizeKnown = false;
    }

    private void startVideoPlayback() {

        holder.setFixedSize(mVideoWidth, mVideoHeight);


        play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
              File file=new File(getFilesDir(), "record.3gp");
                String pathToFile = file.getAbsolutePath() ;
//create mediaplayer
                mediaPlayer = new MediaPlayer();
//set audio file path
                try {
                    mediaPlayer.setDataSource(pathToFile);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//Prepare mediaplayer
                try {
                    mediaPlayer.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//start mediaPlayer

                if (mMediaPlayer.isPlaying()&&mediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    mMediaPlayer.start();
                    play.setBackgroundResource(R.drawable.pause);

                }
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    public void onCompletion(MediaPlayer mp)
                    {
                        // Do whatever u need to do here
                        play.setBackgroundResource(R.drawable.play);
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                });
            }
        });

    }


}


