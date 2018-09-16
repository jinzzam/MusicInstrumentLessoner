package hack.the.wap.musicinstrumentlessoner.myfragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.myactivity.MainActivity;
import hack.the.wap.musicinstrumentlessoner.myactivity.TemplateDetailActivity;
import hack.the.wap.musicinstrumentlessoner.mylayout.TemplateLayout;
import hack.the.wap.musicinstrumentlessoner.model.myservice.TemplateService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TemplateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TemplateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TemplateFragment extends Fragment {
    private static View templateFragmentView;
    private static LinearLayout llFragTemplate;
    private static Session session;
    private TemplateService templateService = TemplateService.getInstance(this.getContext());
    private static HashMap<String, MusicTemplateAssignmentDto> assignments;
    private static HashMap<String, MusicTemplateDto> templates;

    private OnFragmentInteractionListener mListener;

    public TemplateFragment() {
        session = Session.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemplateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TemplateFragment newInstance(String param1, String param2) {
        TemplateFragment fragment = new TemplateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        templateFragmentView = inflater.inflate(R.layout.fragment_template, container, false);
        llFragTemplate = templateFragmentView.findViewById(R.id.llFragTemplate);

        assignments = session.getTemplateAssignments();
        templates = session.getTemplates();
        for (MusicTemplateAssignmentDto assignmentDto : assignments.values()) {
            TemplateLayout atom = new TemplateLayout(getContext());
            atom.setCustomAttr(assignmentDto);
            atom.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.getInstance(), TemplateDetailActivity.class);
                intent.putExtra("data", assignments);
                startActivity(intent);
            });
            llFragTemplate.addView(atom);
        }
        return templateFragmentView;
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
}
