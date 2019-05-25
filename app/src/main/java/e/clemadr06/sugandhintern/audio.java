package e.clemadr06.sugandhintern;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class audio extends AppCompatActivity {
    private Button play, stop, record,save;
    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private  MediaPlayer mediaPlayer;
    private Boolean mStartRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        save=(Button)findViewById(R.id.save);
         mStartRecording=Boolean.FALSE;



        stop.setEnabled(false);
        play.setEnabled(false);
        save.setEnabled(false);

        //outputFile = "/storage/emulated/0/Android/data/e.clemadr06.sugandhintern/files/record.3gp";
        final keys s=new keys();
        File file = new File(this.getFilesDir(), "record.3gp");
        outputFile=file.getAbsolutePath();
        s.setRecording(outputFile);
        Log.e("PATH",outputFile);
        if(myAudioRecorder==null){
           // Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_LONG).show();
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);}

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(myAudioRecorder==null){
                        // Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_LONG).show();
                        myAudioRecorder = new MediaRecorder();
                        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        myAudioRecorder.setOutputFile(outputFile);}
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                   // Toast.makeText(getApplicationContext(), "try catch", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                stop.setEnabled(true);
                record.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            stoprecording();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(), "Audio saved successfully", Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Log.e("output",outputFile);
                    Toast.makeText(getApplicationContext(), "Playing Audio!!!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    // make something
                }
            }
        });
    }
    public void stoprecording()
    {
        try {
            myAudioRecorder.stop();
            myAudioRecorder.reset();
            myAudioRecorder.release();

        } catch (RuntimeException stopException) {
            // make something ..
        }
        myAudioRecorder = null;
        record.setEnabled(true);
        stop.setEnabled(false);
        play.setEnabled(true);
        save.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Audio Recorder successfully", Toast.LENGTH_LONG).show();
    }
}
