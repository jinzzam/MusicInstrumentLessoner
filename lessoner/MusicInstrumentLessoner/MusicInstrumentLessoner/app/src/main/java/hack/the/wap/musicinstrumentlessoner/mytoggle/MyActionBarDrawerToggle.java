package hack.the.wap.musicinstrumentlessoner.mytoggle;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MyActionBarDrawerToggle extends ActionBarDrawerToggle {
    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
    }

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
    }
}
