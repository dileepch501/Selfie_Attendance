package in.dileep.spteam.selfie_attendance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.app.PendingIntent.getActivity;

public class MainActivity3 extends AppCompatActivity {
    Button takepick, submit;
    ImageView imv1;
    String str="dileep";
    Context context;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        takepick = (Button) findViewById(R.id.takepic);
        submit = (Button) findViewById(R.id.submit);
        imv1 = (ImageView) findViewById(R.id.imv1);

        takepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imv1.setImageBitmap(photo);
            Bitmap tmpBmp = photo.copy(Bitmap.Config.RGB_565, true);
            Paint myRectPaint = new Paint();
            myRectPaint.setStrokeWidth(5);
            myRectPaint.setColor(Color.RED);
            myRectPaint.setStyle(Paint.Style.STROKE);

            Bitmap tempBitmap = Bitmap.createBitmap(tmpBmp.getWidth(), tmpBmp.getHeight(), Bitmap.Config.RGB_565);
            Canvas tempCanvas = new Canvas(tempBitmap);
            tempCanvas.drawBitmap(tmpBmp, 0, 0, null);
            FaceDetector faceDetector = new
                    FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(true).build();
            if (!faceDetector.isOperational()) {
                //new AlertDialog.Builder(v.getContext()).setMessage("Could not set up the face detector!").show();
                return;

            }
            Frame frame = new Frame.Builder().setBitmap(tmpBmp).build();
            final SparseArray<Face> faces = faceDetector.detect(frame);

            for (int i = 0; i < faces.size(); i++) {
                Face thisFace = faces.valueAt(i);
                float x1 = thisFace.getPosition().x;
                float y1 = thisFace.getPosition().y;
                float x2 = x1 + thisFace.getWidth();
                float y2 = y1 + thisFace.getHeight();
                tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
            }
            imv1.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
        }
              submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nh=new Intent(getApplicationContext(),MainActivity4.class);
                startActivity(nh);
            }
        });

    }
}
