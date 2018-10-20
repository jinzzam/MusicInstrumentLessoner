package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import hack.the.wap.musicinstrumentlessoner.R;

public class AddTemplateActivity extends AppCompatActivity {
    private ImageView ivAddTemplateActLeftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        ivAddTemplateActLeftArrow = findViewById(R.id.ivAddTemplateActLeftArrow);
        viewSetListener();
    }

    private void viewSetListener() {
        ivAddTemplateActLeftArrow.setOnClickListener(v -> {
            finish();
        });
    }
}