package hack.the.wap.musicinstrumentlessoner.myfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.mylayout.StoreLayout;
import hack.the.wap.musicinstrumentlessoner.session.Session;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {
    private static final String TAG = "STORE_FRAGMENT";
    private static View storeFragmentView;
    private static LinearLayout llFragStore;
    private static Session session;
    private MiGroupDto miGroupDto;
    private static HashMap<String, MiGroupDto> userGroups;

    private OnFragmentInteractionListener mListener;

    public StoreFragment() {
        Log.e(TAG, "StoreFragment: 생성자입니다.");
        session = Session.getInstance();
        userGroups = new HashMap<>();
        miGroupDto = new MiGroupDto();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
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
        storeFragmentView = inflater.inflate(R.layout.fragment_store, container, false);
        llFragStore = storeFragmentView.findViewById(R.id.llFragStore);
        for (MiGroupDto dto : session.getUserGroups().values()) {
            StoreLayout atom = new StoreLayout(getContext());
            atom.setCustomAttr(dto);
            llFragStore.addView(atom);
        }
        return storeFragmentView;
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
