package in.prax.phanasedairy.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.prax.phanasedairy.R;
import in.prax.phanasedairy.model.CustomerModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddNewCustomerFragment extends Fragment {
    View view;
    @BindView(R.id.edtNewCustomerName)
    EditText edtNewCustomerName;
    @BindView(R.id.edtNewCustomerMobile)
    EditText edtNewCustomerMobile;
    @BindView(R.id.btnAddNewCustomer)
    AppCompatButton btnAddNewCustomer;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    public AddNewCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_new_customer, container, false);
        unbinder = ButterKnife.bind(this, view);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("customer_data");
        myRef.child("data").push().setValue(new CustomerModel("kiran","1111111"));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAddNewCustomer)
    public void onViewClicked() {
        Log.e("Add new customer", "onViewClicked: " );
    myRef.setValue(new CustomerModel(edtNewCustomerName.getText().toString(),edtNewCustomerMobile.getText().toString()));
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
