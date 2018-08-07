package in.dileep.spteam.selfie_attendance;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity4 extends AppCompatActivity {

    TextView atext1,text2,text3;
    Button but;
    String result="Failed...? something wrong..";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        atext1=(TextView) findViewById(R.id.atext1);
        but=(Button) findViewById(R.id.confrom);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dbtask().execute();
            }
        });

    }

class Dbtask extends AsyncTask<String,String,String>{

    String str="";
    ProgressDialog pro;
    @Override
    protected void onPreExecute() {

        pro=new ProgressDialog(MainActivity4.this);
        pro.setTitle("Connecting");
        pro.setMessage("please wait a sec...");
        pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pro.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pro.show();
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://10.1.1.166:3306/andip", "dileep", "dileep");
            Statement st = con.createStatement();
            st.executeUpdate("update attendance set present='YES'  WHERE id='15NG5A0501'");
            result="Attendance successfully marked to";
            //ResultSet rs = st.executeQuery("");
            ResultSet rs2=st.executeQuery("select id from attendance where present='YES'");
            while(rs2.next()){
                str=rs2.getString(1);
            }


        }
        catch (Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        pro.dismiss();
        text2.setText(result);
        text3.setText(str);
    }


}
}
