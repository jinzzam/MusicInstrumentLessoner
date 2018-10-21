package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class AddTemplateActivity extends AppCompatActivity {
    private Session session = Session.getInstance();
    private ImageView ivAddTemplateActLeftArrow;
    private ImageView ivAddTemplateActUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        ivAddTemplateActLeftArrow = findViewById(R.id.ivAddTemplateActLeftArrow);
        ivAddTemplateActUser = findViewById(R.id.ivAddTemplateActUser);
        ivAddTemplateActUser.setImageResource(DebugImageMatch.getImageFromName(session.getMainUser().getName()));
        viewSetListener();
    }

    private void viewSetListener() {
        ivAddTemplateActLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }
}