package hack.the.wap.musicinstrumentlessoner.debug;

import android.support.v7.app.AppCompatActivity;

import hack.the.wap.musicinstrumentlessoner.R;

public class DebugImageMatch extends AppCompatActivity{
    public static DebugImageMatch instance;

    private DebugImageMatch() {

    }

    public static int getImageFromName(String stringName) {
        switch (stringName) {
            case "AOA Choa":
                return R.drawable.choa_round;
            case "Twice Dahyun":
                return R.drawable.dahyun_round;
            case "GirlsDay mina":
                return R.drawable.mina;
            case "Beethoven":
                return R.drawable.beethoven_round;
            case "Segyong":
                return R.drawable.segyong_round;
            case "Gain":
                return R.drawable.gain_round;
            case "Hyoju":
                return R.drawable.hyoju_round;
            case "Kanna":
                return R.drawable.kanna_round;
            case "Haydn":
                return R.drawable.haydn_round;
            case "Jia":
                return R.drawable.jia_round;
            case "Dongwon":
                return R.drawable.dongwon_round;
            case "Jihyun":
                return R.drawable.jihyun_round;
            case "Sarang":
                return R.drawable.sarang_round;
            case "Woobin":
                return R.drawable.woobin_round;
            case "Dongsuck":
                return R.drawable.dongsuck_round;
            case "Jiho":
                return R.drawable.jiho_round;
            case "Taehie":
                return R.drawable.taehie_round;
            case "Youjung":
                return R.drawable.youjung_round;
            case "Sohyun":
                return R.drawable.sohyun_round;
            case "Joohyun":
                return R.drawable.joohyun_round;
            case "Jeny":
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
            case "WAP":
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
}
