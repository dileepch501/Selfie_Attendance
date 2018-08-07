package in.dileep.spteam.selfie_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView siv;
    private TextView stv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        siv=(ImageView) findViewById(R.id.siv);
        stv=(TextView) findViewById(R.id.stv);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.splash);
        siv.startAnimation(myanim);
        stv.startAnimation(myanim);
        final Intent i =new Intent(this,MainActivity2.class);
        Thread t1=new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                }
                finally{
                    startActivity(i);
                    finish();
                }
            }
        };
        t1.start();

    }
}
