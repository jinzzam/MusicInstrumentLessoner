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
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.myactivity.MainActivity;
import hack.the.wap.musicinstrumentlessoner.myactivity.UserGroupDetailActivity;
import hack.the.wap.musicinstrumentlessoner.mylayout.GroupLayout;
import hack.the.wap.musicinstrumentlessoner.model.myservice.GroupService;
import hack.the.wap.musicinstrumentlessoner.session.Session;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    private static View groupFragmentView;
    private static LinearLayout llFragGroup;
    private static Session session;
    private GroupService groupService;
    private OnFragmentInteractionListener mListener;

    private static HashMap<String, MiGroupDto> userGroups;


    {
        groupService = GroupService.getInstance();
    }

    public GroupFragment() {
        session = Session.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupFragment newInstance(String param1, String param2) {
        GroupFragment fragment = new GroupFragment();
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
        groupFragmentView = inflater.inflate(R.layout.fragment_group, container, false);
        llFragGroup = groupFragmentView.findViewById(R.id.llFragGroup);
        groupService.getGroup();
        userGroups = session.getUserGroups();
        for (MiGroupDto dto : userGroups.values()) {
            if (groupService.isMyGroup(dto)) {
                GroupLayout atom = new GroupLayout(getContext());
                atom.setCustomAttr(dto);
                atom.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.getInstance(), UserGroupDetailActivity.class);
                    intent.putExtra("data", dto);
                    startActivity(intent);
                });
                llFragGroup.addView(atom);
            }
        }
        return groupFragmentView;
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
