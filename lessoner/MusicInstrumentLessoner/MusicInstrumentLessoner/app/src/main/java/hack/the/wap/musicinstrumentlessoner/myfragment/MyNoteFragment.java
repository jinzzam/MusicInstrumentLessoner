package hack.the.wap.musicinstrumentlessoner.myfragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Random;

import hack.the.wap.musicinstrumentlessoner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyNoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNoteFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static LinearLayout llFragMyNote;
    private static BarChart barChart;
    private static BarChart barChart2;
    private static PieChart pieChart;
    private static PieChart pieChart2;
    private static PieChart pieChart3;
    private static PieChart pieChart4;
    private static View myNoteFragmentView;
    private static Random random = new Random();

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNoteFragment newInstance(String param1, String param2) {
        MyNoteFragment fragment = new MyNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myNoteFragmentView = inflater.inflate(R.layout.fragment_my_note, container, false);
        barChart = myNoteFragmentView.findViewById(R.id.bcTest);
        barChart2 = myNoteFragmentView.findViewById(R.id.bcTest2);
        pieChart = myNoteFragmentView.findViewById(R.id.pcTest);
        pieChart2 = myNoteFragmentView.findViewById(R.id.pcTest2);
        pieChart3 = myNoteFragmentView.findViewById(R.id.pcTest3);
        pieChart4 = myNoteFragmentView.findViewById(R.id.pcTest4);
        DEBUG_SET_DUMMY_ONE();
        DEBUG_SET_DUMMY_TWO();
        DEBUG_SET_DUMMY_THREE();
        DEBUG_SET_DUMMY_FOUR();
        DEBUG_SET_DUMMY_FIVE();
        DEBUG_SET_DUMMY_SIX();
        return myNoteFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void DEBUG_SET_DUMMY_ONE() {
        ArrayList<Float> instruments1 = new ArrayList<>();
        instruments1.add(1.f);
        ArrayList<Float> instruments2 = new ArrayList<>();
        instruments2.add(2.f);
        ArrayList<Float> instruments3 = new ArrayList<>();
        instruments3.add(3.f);
        ArrayList<Float> lates1 = new ArrayList<>();
        lates1.add(77.7f);
        ArrayList<Float> lates2 = new ArrayList<>();
        lates2.add(12.2f);
        ArrayList<Float> lates3 = new ArrayList<>();
        lates3.add(10.1f);
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(instruments1.get(0), lates1.get(0)));
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(instruments2.get(0), lates2.get(0)));
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(instruments3.get(0), lates3.get(0)));
        BarDataSet dataSet1 = new BarDataSet(entries1, "플루트");
        dataSet1.setColor(Color.rgb(0xC7, 0x70, 0x6F));
        BarDataSet dataSet2 = new BarDataSet(entries2, "기타");
        dataSet2.setColor(Color.rgb(0x17, 0x8B, 0xB7));
        BarDataSet dataSet3 = new BarDataSet(entries3, "피아노");
        dataSet3.setColor(Color.rgb(0xF1, 0xE1, 0x94));
        BarData data = new BarData(dataSet1, dataSet2, dataSet3);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        YAxis yAxis = barChart.getAxis(YAxis.AxisDependency.LEFT);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
        yAxis = barChart.getAxis(YAxis.AxisDependency.RIGHT);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(false);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);

    }

    public void DEBUG_SET_DUMMY_TWO() {
        ArrayList<String> xVal = new ArrayList<>();
        for(int i=1;i<=7;i++){
            xVal.add("08-0"+i);
        }
        ArrayList<Float> yVal = new ArrayList<>();
        for(int i=0;i<7;i++){
            yVal.add(80.0f+random.nextInt(200)/10);
        }
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<7;i++){
            entries.add(new BarEntry(i, yVal.get(i)));
        }
        BarDataSet dataSet = new BarDataSet(entries, "You");
        dataSet.setColor(R.color.colorMainBackGround);
        BarData data = new BarData(dataSet);
        XAxis xAxis = barChart2.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        YAxis yAxis = barChart2.getAxis(YAxis.AxisDependency.LEFT);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
        yAxis = barChart2.getAxis(YAxis.AxisDependency.RIGHT);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(false);
        xAxis.setValueFormatter((value,axis)-> xVal.get((int)value));
        barChart2.setData(data);
        barChart2.getLegend().setEnabled(false);
        barChart2.getDescription().setEnabled(false);
    }

    public void DEBUG_SET_DUMMY_THREE() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        PieEntry pieEntry1 = new PieEntry(40.5f,"운지(음정)");
        PieEntry pieEntry2 = new PieEntry(30.3f,"박자");
        PieEntry pieEntry3 = new PieEntry(29.2f,"주법(음색)");
        pieEntries.add(pieEntry1);
        pieEntries.add(pieEntry2);
        pieEntries.add(pieEntry3);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0xC7, 0x70, 0x6F));
        colors.add(Color.rgb(0x17, 0x8B, 0xB7));
        colors.add(Color.rgb(0xF1, 0xE1, 0x94));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
    }
    public void DEBUG_SET_DUMMY_FOUR() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        PieEntry pieEntry1 = new PieEntry(60.f,"터닝");
        PieEntry pieEntry2 = new PieEntry(20.f,"하모닉스");
        PieEntry pieEntry3 = new PieEntry(15.f,"글리산도");
        PieEntry pieEntry4 = new PieEntry(5.f,"기타");
        pieEntries.add(pieEntry1);
        pieEntries.add(pieEntry2);
        pieEntries.add(pieEntry3);
        pieEntries.add(pieEntry4);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0xC7, 0x70, 0x6F));
        colors.add(Color.rgb(0x17, 0x8B, 0xB7));
        colors.add(Color.rgb(0xF1, 0xE1, 0x94));
        colors.add(Color.rgb(0x5C, 0x5F, 0x6B));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart2.setData(data);
        pieChart2.setDrawEntryLabels(false);
        pieChart2.getDescription().setEnabled(false);
    }

    public void DEBUG_SET_DUMMY_FIVE() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        PieEntry pieEntry1 = new PieEntry(35.f,"스트로크");
        PieEntry pieEntry2 = new PieEntry(30.f,"태핑");
        PieEntry pieEntry3 = new PieEntry(30.f,"하모닉스");
        PieEntry pieEntry4 = new PieEntry(5.f,"기타");
        pieEntries.add(pieEntry1);
        pieEntries.add(pieEntry2);
        pieEntries.add(pieEntry3);
        pieEntries.add(pieEntry4);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0xC7, 0x70, 0x6F));
        colors.add(Color.rgb(0x17, 0x8B, 0xB7));
        colors.add(Color.rgb(0xF1, 0xE1, 0x94));
        colors.add(Color.rgb(0x5C, 0x5F, 0x6B));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart3.setData(data);
        pieChart3.setDrawEntryLabels(false);
        pieChart3.getDescription().setEnabled(false);
    }

    public void DEBUG_SET_DUMMY_SIX() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        PieEntry pieEntry1 = new PieEntry(80.f,"포르테");
        PieEntry pieEntry2 = new PieEntry(10.f,"피아노");
        PieEntry pieEntry3 = new PieEntry(5.f,"트릴");
        PieEntry pieEntry4 = new PieEntry(5.f,"기타");
        pieEntries.add(pieEntry1);
        pieEntries.add(pieEntry2);
        pieEntries.add(pieEntry3);
        pieEntries.add(pieEntry4);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0xC7, 0x70, 0x6F));
        colors.add(Color.rgb(0x17, 0x8B, 0xB7));
        colors.add(Color.rgb(0xF1, 0xE1, 0x94));
        colors.add(Color.rgb(0x5C, 0x5F, 0x6B));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart4.setData(data);
        pieChart4.setDrawEntryLabels(false);
        pieChart4.getDescription().setEnabled(false);
    }
}
