package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class StoreLayout extends LinearLayout {
    private static final String TAG = "STORE_LAYOUT";
    private ImageView ivStoreLayGroupImage;
    private TextView tvStoreLayGroupTitle;
    private TextView tvStoreLayMain;
    private TextView tvStoreLayInstrument;
    private TextView tvStoreLayGenre;

    {
        initView();
    }

    public StoreLayout(Context context) {
        super(context);
    }

    public StoreLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public StoreLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StoreLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_store, this);
        ivStoreLayGroupImage = findViewById(R.id.ivStoreGroupImage);
        tvStoreLayGroupTitle = findViewById(R.id.tvStoreLayGroupTitle);
        tvStoreLayMain = findViewById(R.id.tvStoreLayMain);
        tvStoreLayInstrument = findViewById(R.id.tvStoreLayInstrument);
        tvStoreLayGenre = findViewById(R.id.tvStoreLayGenre);
    }

    private void getAttrs(AttributeSet attributeSet) {
    }

    public void setCustomAttr(MiGroupDto dto) {
        Log.e(TAG, "setCustomAttr: " + dto.toString() );
        ivStoreLayGroupImage.setImageResource(DebugImageMatch.getImageFromName(dto.getGroupName()));
        tvStoreLayGroupTitle.setText(dto.getGroupName());
        tvStoreLayMain.setText(dto.getInfo());
        String instrument = getResources().getString(R.string.storeLayInstrument) + dto.getInstruments();
        tvStoreLayInstrument.setText(instrument);
        String genre = getResources().getString(R.string.storeLayGenre) + dto.getGenres();
        tvStoreLayGenre.setText(genre);
        Log.e(TAG, "setCustomAttr: 주요악기 :  " + instrument);
        Log.e(TAG, "setCustomAttr: 주요장르 :  " + genre);

    }
}
