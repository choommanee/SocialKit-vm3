package co.aquario.socialkit.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dd.processbutton.FlatButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.otto.Subscribe;

import co.aquario.socialkit.MainApplication;
import co.aquario.socialkit.R;
import co.aquario.socialkit.event.RegisterEvent;
import co.aquario.socialkit.event.RegisterFailedEvent;
import co.aquario.socialkit.event.RegisterSuccessEvent;
import co.aquario.socialkit.handler.ApiBus;
import co.aquario.socialkit.util.PrefManager;

public class RegisterFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PrefManager prefManager;

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = MainApplication.get(getActivity()).getPrefManager();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private LinearLayout btnRegister;
    private String name;

    private MaterialEditText etUsername;
    private MaterialEditText etPassword;
    private MaterialEditText etRepeatPassword;
    private MaterialEditText etEmail;
    private RadioGroup radioGroupGender;
    private RadioButton radioGender;
    private FlatButton btnRequestOtp;
    private MaterialEditText etPhone;

    public int checkedId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        etEmail = (MaterialEditText) rootView.findViewById(R.id.et_email);
        etUsername = (MaterialEditText) rootView.findViewById(R.id.et_username);
        etPassword = (MaterialEditText) rootView.findViewById(R.id.et_password);
        etRepeatPassword = (MaterialEditText) rootView.findViewById(R.id.et_repeat_password);
        etPhone = (MaterialEditText) rootView.findViewById(R.id.et_phone);
        radioGroupGender = (RadioGroup) rootView.findViewById(R.id.et_gender);
        btnRequestOtp = (FlatButton) rootView.findViewById(R.id.btn_request_otp);

        btnRequestOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etPhone.getText().toString().trim().equals("")) {
                    String mobile = etPhone.getText().toString().trim();
                    Toast.makeText(getActivity().getApplicationContext(),"OTP is sent to "+mobile,Toast.LENGTH_SHORT).show();
                }
                    //ApiBus.getInstance().post(new RequestOtpEvent("0917366196",""));
            }
        });

        checkedId = radioGroupGender.getCheckedRadioButtonId();
        radioGender = (RadioButton) radioGroupGender.findViewById(checkedId);

        radioGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String numeral = null;
                switch (checkedId) {
                    case R.id.selectMale:
                        numeral = "male";
                        break;
                    case R.id.selectFemale:
                        numeral = "female";
                        break;

                }
                Toast.makeText(getActivity().getApplicationContext(), "You selected the " + numeral + " radio button.", Toast.LENGTH_SHORT).show();
            }
        });


        btnRegister = (LinearLayout) rootView.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etUsername.getText().toString().trim() + " " + System.currentTimeMillis();
                String gender = radioGender.getText().toString().toLowerCase();
                Log.e("gender",gender);
                RegisterEvent event = new RegisterEvent(
                        name,
                        etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        etEmail.getText().toString().trim(),
                        gender);

                ApiBus.getInstance().post(event);
            }
        });



        return rootView;
    }

    @Subscribe public void onRegisterSuccess(RegisterSuccessEvent event) {

        Toast.makeText(getActivity().getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();

        Log.e("DEBUG_REGISTER",event.getRegisterData().user.toString());

        /*
        prefManager
                .username().put(event.getRegisterData().user.username)
                .userId().put(event.getRegisterData().user.id)
                //.token().put(event.getRegisterData().token)
                .cover().put(event.getRegisterData().user.cover)
                .avatar().put(event.getRegisterData().user.avatar)
                .isLogin().put(true)
                .commit();
                */

        getFragmentManager().beginTransaction().replace(R.id.login_container, new RegisterSuccessFragment(),"REGISTER_SUCCESS").commit();
    }

    @Subscribe public void onRegisterFailed(RegisterFailedEvent event) {
        Toast.makeText(getActivity().getApplicationContext(),event.msg,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
