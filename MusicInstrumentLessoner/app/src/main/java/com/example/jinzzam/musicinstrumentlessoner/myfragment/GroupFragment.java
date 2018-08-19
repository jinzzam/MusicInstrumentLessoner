package com.example.jinzzam.musicinstrumentlessoner.myfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.myactivity.MainActivity;
import com.example.jinzzam.musicinstrumentlessoner.myactivity.UserGroupDetailActivity;
import com.example.jinzzam.musicinstrumentlessoner.mylayout.GroupLayout;
import com.example.jinzzam.musicinstrumentlessoner.session.Session;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static View groupFragmentView;
    private static LinearLayout llFragGroup;
    private static Session session;
    private static HashMap<String, GroupDto> userGroups;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static GroupFragment instance;

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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        groupFragmentView = inflater.inflate(R.layout.frag_group, container, false);
        llFragGroup = groupFragmentView.findViewById(R.id.llFragGroup);
        userGroups = session.getUserGroups();
        for (GroupDto dto : userGroups.values()) {
            if (dto.isMine()) {
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

    public static GroupFragment getInstance() {
        if (instance == null)
            instance = new GroupFragment();
        return instance;
    }
}
