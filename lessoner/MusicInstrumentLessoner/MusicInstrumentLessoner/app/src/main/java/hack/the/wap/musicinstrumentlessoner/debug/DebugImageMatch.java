package hack.the.wap.musicinstrumentlessoner.debug;

import android.support.v7.app.AppCompatActivity;

import hack.the.wap.musicinstrumentlessoner.R;

public class DebugImageMatch extends AppCompatActivity {
    public static DebugImageMatch instance;

    private DebugImageMatch() {

    }

    public static int getImageFromName(String stringName) {
        switch (stringName) {
            case "박유진":
                return R.drawable.youjin_round;
            case "한가인":
                return R.drawable.gain_round;
            case "GirlsDay mina":
                return R.drawable.mina;
            case "Beethoven":
                return R.drawable.beethoven_round;
            case "Segyong":
                return R.drawable.segyong_round;
            case "namolppam@pocket.mon":
                return R.drawable.namolppami_round;
            case "나몰빼미":
                return R.drawable.namolppami_round;
            case "한효주":
                return R.drawable.hyoju_round;
            case "Kanna":
                return R.drawable.kanna_round;
            case "Haydn":
                return R.drawable.haydn_round;
            case "Jia":
                return R.drawable.jia_round;
            case "강동원":
                return R.drawable.dongwon_round;
            case "전지현":
                return R.drawable.jihyun_round;
            case "김사랑":
                return R.drawable.sarang_round;
            case "김우빈":
                return R.drawable.woobin_round;
            case "마동석":
                return R.drawable.dongsuck_round;
            case "우지호":
                return R.drawable.jiho_round;
            case "김태희":
                return R.drawable.taehie_round;
            case "김유정":
                return R.drawable.youjung_round;
            case "유소현":
                return R.drawable.sohyun_round;
            case "배주현":
                return R.drawable.joohyun_round;
            case "김제니":
                return R.drawable.jeny_round;
            case "Lim jung hyeon":
                return R.drawable.canon_rock_round;
            case "Folk song":
                return R.drawable.taryeong_round;
            case "Metallica":
                return R.drawable.enter_the_sand_man_round;
            case "Yoon suk joon":
                return R.drawable.airplane_round;
            case "Chopin":
                return R.drawable.chopin_round;
            case "Celine dion":
                return R.drawable.mhwgo_round;
            case "Mozart":
                return R.drawable.turkie_round;
            case "Czerny":
                return R.drawable.czerny80_round;
            case "Vivaldi":
                return R.drawable.vivaldi_round;
            case "Folk song2":
                return R.drawable.arirang_round;
            case "Schubert":
                return R.drawable.schubert_round;
            case "Han in hyun":
                return R.drawable.realsanae_round;
            case "WAP THE HACK":
                return R.drawable.wap_round;
            case "피아노리브레 강남센터":
                return R.drawable.music_place_round;
            case "SMMA아카데미":
                return R.drawable.kanna_round;
            case "위드피아노 위례센터":
                return R.drawable.with_place_round;
            case "PMC 실용음악학원":
                return R.drawable.pmc_place_round;
            case "브레인기타학원":
                return R.drawable.brain_place_round;
            default:
                return R.drawable.logo_user_round;
        }
    }

    public static DebugImageMatch getInstance() {
        if (instance == null) {
            instance = new DebugImageMatch();
        }
        return instance;
    }
}
