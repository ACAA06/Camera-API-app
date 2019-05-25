package e.clemadr06.sugandhintern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void record(View view)
    {
        Intent i=new Intent(this,record.class);
        startActivity(i);
    }
    public void vrecord(View view)
    {
        Intent i=new Intent(this,videofrag.class);
        startActivity(i);//first it goes to video frag
    }
    public void arecord(View view)
    {
        Intent i=new Intent(this,audio.class);
        startActivity(i);
    }
    public void srecord(View view)
    {
        Intent i=new Intent(this,showrecord.class);
        startActivity(i);
    }
}
