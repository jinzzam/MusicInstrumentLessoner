package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.myfragment.MyNoteFragment;
import hack.the.wap.musicinstrumentlessoner.myfragment.TemplateFragment;

public class DebugActivity extends AppCompatActivity implements
        MyNoteFragment.OnFragmentInteractionListener, TemplateFragment.OnFragmentInteractionListener {
    private static ImageView ivDebugActLeftArrow;
    private static ImageView ivStudentProfile;
    private static FrameLayout flDebugFragment;
    private static MyNoteFragment myNoteFragment;
    private static TemplateFragment templateFragment;
    private static Button btnDebug;

    private String studentName;

    {
        myNoteFragment = new MyNoteFragment();
        templateFragment = new TemplateFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Intent intent = getIntent();
        studentName = intent.getStringExtra("data");
        ivDebugActLeftArrow = findViewById(R.id.ivDebugActLeftArrow);
        ivStudentProfile = findViewById(R.id.ivStudentProfile);
        flDebugFragment = findViewById(R.id.flDebugFragment);
        btnDebug = findViewById(R.id.btnDebug);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.flDebugFragment, myNoteFragment);
        fragmentTransaction.commit();
        setListener();
        ivStudentProfile.setImageResource(DebugImageMatch.getImageFromName(studentName));
    }

    private void setListener() {
        ivDebugActLeftArrow.setOnClickListener((v) -> {
            finish();
        });
        btnDebug.setOnClickListener((v) -> {
            if (btnDebug.getText().toString().equals(getResources().getString(R.string.btnShowDetail))) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.flDebugFragment, templateFragment);
                fragmentTransaction.commit();
                btnDebug.setText(R.string.btnShowOver);
            } else if (btnDebug.getText().toString().equals(getResources().getString(R.string.btnShowOver))) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.flDebugFragment, myNoteFragment);
                fragmentTransaction.commit();
                btnDebug.setText(R.string.btnShowDetail);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
