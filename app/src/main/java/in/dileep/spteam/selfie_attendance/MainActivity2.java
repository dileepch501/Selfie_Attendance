package in.dileep.spteam.selfie_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout l1,l2;
    Button b1;
    Animation anim1,anim2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l1=(LinearLayout)findViewById(R.id.l1);
        l2=(LinearLayout) findViewById(R.id.l2);
        b1=(Button) findViewById(R.id.b1);
        anim1= AnimationUtils.loadAnimation(this,R.anim.updown);
        anim2=AnimationUtils.loadAnimation(this,R.anim.downup);
        l1.setAnimation(anim1);
        l2.setAnimation(anim2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Checking network connectivity", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                    Toast.makeText(getApplicationContext(),"Connection matched",Toast.LENGTH_SHORT).show();
                    //Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i =new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i);

            }
        });
    }
}
