package hack.the.wap.musicinstrumentlessoner.myfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.NotificationDto;
import hack.the.wap.musicinstrumentlessoner.myactivity.MainActivity;
import hack.the.wap.musicinstrumentlessoner.mylayout.MiNotificationLayout;
import hack.the.wap.musicinstrumentlessoner.mylayout.NotificationLayout;
import hack.the.wap.musicinstrumentlessoner.session.Session;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MainActivity mainActivity;
    private static View notificationFragmentView;
    private static EditText etMySearchLaySearchText;
    private static LinearLayout llFragNotification;
    private static Session session;
    private static ArrayList<NotificationDto> notifications;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        mainActivity = MainActivity.getInstance();
        session = Session.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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

        notificationFragmentView = inflater.inflate(R.layout.fragment_notification, container, false);
        llFragNotification = notificationFragmentView.findViewById(R.id.llFragNotification);
        notifications = session.getNotifications();

        for (NotificationDto dto : notifications) {
            if(dto.isTrueUser()){
                NotificationLayout atom = new NotificationLayout(getContext());
                atom.setCustomAttr(dto);
                llFragNotification.addView(atom);
            }else{
                MiNotificationLayout atom = new MiNotificationLayout(getContext());
                atom.setCustomAttr(dto);
                llFragNotification.addView(atom);
            }
        }
        return notificationFragmentView;
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

    public void removeAllNotification(){
        notifications.clear();
        llFragNotification.removeAllViews();
    }


    public void removeRecentNotification() {
        notifications.remove(0);
        llFragNotification.removeViewAt(0);
    }
}
