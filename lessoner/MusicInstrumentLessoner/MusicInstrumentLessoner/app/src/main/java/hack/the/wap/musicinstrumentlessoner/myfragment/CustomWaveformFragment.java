package hack.the.wap.musicinstrumentlessoner.myfragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.semantive.waveformandroid.waveform.Segment;
import com.semantive.waveformandroid.waveform.WaveformFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hack.the.wap.musicinstrumentlessoner.myactivity.PracticeDetailActivity;
import hack.the.wap.musicinstrumentlessoner.session.PresentFile;

public class CustomWaveformFragment extends WaveformFragment {

    /**
     * Provide path to your audio file.
     *
     * @return
     */
    @Override
    protected String getFileName() {
        return PresentFile.fileName;
    }

    /**
     * Optional - provide list of segments (start and stop values in seconds) and their corresponding colors
     *
     * @return
     */
    @Override
    protected List<Segment> getSegments() {
        List<Segment> arr=new ArrayList<>();
        if (!PresentFile.fileName.equals("/storage/self/primary/Music/놀람 교향곡/T/recorded_audio.mp3")) {
            arr = Arrays.asList(
                    new Segment(3.0, 4.0, Color.rgb(238, 23, 104)),
                    new Segment(90.2, 100.8, Color.rgb(238, 23, 104)),
                    new Segment(120.2, 130.6, Color.rgb(238, 23, 104)),
                    new Segment(140.4, 145.9, Color.rgb(238, 23, 104)));
        }
        return arr;
    }
}