package hack.the.wap.musicinstrumentlessoner.mylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugImageMatch;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserGroupDto;

/*
참고 사이트 : https://medium.com/@douglas.iacovelli/the-beauty-of-custom-views-and-how-to-do-it-79c7d78e2088
           http://gun0912.tistory.com/38
 */

public class StoreLayout extends LinearLayout {
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
        inflate(getContext(), R.layout.store_layout, this);
        ivStoreLayGroupImage = findViewById(R.id.ivStoreGroupImage);
        tvStoreLayGroupTitle = findViewById(R.id.tvStoreLayGroupTitle);
        tvStoreLayMain = findViewById(R.id.tvStoreLayMain);
        tvStoreLayInstrument = findViewById(R.id.tvStoreLayInstrument);
        tvStoreLayGenre = findViewById(R.id.tvStoreLayGenre);
    }

    private void getAttrs(AttributeSet attributeSet) {
    }

    private void getAttrs(AttributeSet attributeSet, int defStyle) {
    }

    public void setCustomAttr(UserGroupDto dto) {
        ivStoreLayGroupImage.setImageResource(DebugImageMatch.getImageFromName(dto.getName()));
        tvStoreLayGroupTitle.setText(dto.getName());
        tvStoreLayMain.setText(dto.getMain());
        String instrument = getResources().getString(R.string.store_lay_instrument);
        for (String atom : dto.getInstrument().values()) {
            instrument = instrument + atom + getResources().getString(R.string.store_lay_comma);
        }
        instrument = instrument.substring(0, instrument.length() - getResources().getString(R.string.store_lay_comma).length());
        tvStoreLayInstrument.setText(instrument);
        String genre = getResources().getString(R.string.store_lay_genre);
        for (String atom : dto.getGenre().values()) {
            genre = genre + atom + getResources().getString(R.string.store_lay_comma);
        }
        genre = genre.substring(0, genre.length() - getResources().getString(R.string.store_lay_comma).length());
        tvStoreLayGenre.setText(genre);
    }
}
