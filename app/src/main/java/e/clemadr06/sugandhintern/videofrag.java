package e.clemadr06.sugandhintern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class videofrag extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, video.newInstance())
                    .commit();
        }
    }

}
