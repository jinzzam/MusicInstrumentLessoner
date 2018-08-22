package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import hack.the.wap.musicinstrumentlessoner.R;

public class SplashActivity extends AppCompatActivity {
    /*
        debug field
     */
    private Button btnDeb;

    /*
        static field
     */
    private static SplashActivity instance;

    /*
        init block
     */
    {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashActivity.getInstance(), LoginActivity.class);
            intent.putExtra("state", "launch");
            startActivity(intent);
            finish();
        }).start();
    }

    public static SplashActivity getInstance(){
        return instance;
    }
}
