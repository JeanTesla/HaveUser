package com.example.haveuser.presentation.action.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.haveuser.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddUserFragment extends Fragment {

    private TextInputEditText nameEditText;

    private TextInputEditText cpfCnpjEditText;

    private TextInputLayout cpfCnpjInputLayout;

    private TextInputEditText birthDateEditText;

    private TextInputEditText emailEditText;

    private TextInputEditText cepEditText;

    private TextInputEditText publicPlaceEditText;

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_user, container, false);

        FloatingActionButton returnButton = rootView.findViewById(R.id.returnButtonAddUserFrag);

        Switch typeSwitch = rootView.findViewById(R.id.typeSwitch);
        Switch sexSwitch = rootView.findViewById(R.id.sexSwitch);

        Button submitButton = rootView.findViewById(R.id.submitButton);
        ImageButton imagePickerButton = rootView.findViewById(R.id.imagePickerButton);
        nameEditText = rootView.findViewById(R.id.nameEditText);
        cpfCnpjEditText = rootView.findViewById(R.id.cpfcnpjEditText);
        cpfCnpjInputLayout = rootView.findViewById(R.id.cpfCnpjInputLayout);
        birthDateEditText = rootView.findViewById(R.id.birthDateEditText);
        emailEditText = rootView.findViewById(R.id.emailEditText);
        cepEditText = rootView.findViewById(R.id.cepEditText);
        publicPlaceEditText = rootView.findViewById(R.id.publicPlaceEditText);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        Log.d("teste", "Selected URI: " + uri);
                        imagePickerButton.setImageURI(uri);
                    } else {
                        Log.d("teste", "No media selected");
                    }
                });

        typeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String switchFieldText, cpfcnpjFieldText;
                boolean isEnableSexSwitch, isEnableBirthDateEditText;

                if (isChecked) {
                    switchFieldText = "Pessoa Jurídica";
                    cpfcnpjFieldText = "CNPJ";
                    isEnableSexSwitch = false;
                    isEnableBirthDateEditText = false;
                }else{
                    switchFieldText = "Pessoa Física";
                    cpfcnpjFieldText = "CPF";
                    isEnableSexSwitch = true;
                    isEnableBirthDateEditText = true;
                }

                typeSwitch.setText(switchFieldText);
                sexSwitch.setEnabled(isEnableSexSwitch);
                cpfCnpjInputLayout.setHint(cpfcnpjFieldText);
                birthDateEditText.setEnabled(isEnableBirthDateEditText);
            }
        });

        sexSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setText(isChecked ? "Masculino" : "Feminino");
            }
        });

        imagePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validateName();
            }
        });

        return rootView;
    }

    private boolean validateName() {
        String val = nameEditText.getText().toString();
        if (val.isEmpty() || val.length() < 30) {
            nameEditText.setError("Esse campo precisa ter mais de 30 caracteres");
            return false;
        }
        nameEditText.setError(null);
        return true;

    }

//    private boolean validateUsername() {
//        String val = username.getEditText().getText().toString().trim();
//        String checkspaces = "Aw{1,20}z";
//        if (val.isEmpty()) {
//            username.setError("Field can not be empty");
//            return false;
//        } else if (val.length() > 20) {
//            username.setError("Username is too large!");
//            return false;
//        } else if (!val.matches(checkspaces)) {
//            sername.setError("No White spaces are allowed!");
//            return false;
//        } else {
//            username.setError(null);
//            username.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateEmail() {
//        String val = email.getEditText().getText().toString().trim();
//        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
//        if (val.isEmpty()) {
//            email.setError("Field can not be empty");
//            return false;
//        } else if (!val.matches(checkEmail)) {
//            email.setError("Invalid Email!");
//            return false;
//        } else {
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validatePassword() {
//        String val = password.getEditText().getText().toString().trim();
//        String checkPassword = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";
//        if (val.isEmpty()) {
//            password.setError("Field can not be empty");
//            return false;
//        } else if (!val.matches(checkPassword)) {
//            password.setError("Password should contain 4 characters!");
//            return false;
//        } else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateGender() {
//        if (radioGroup.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            return true;
//        }
//    }

    private void saveUser() {

    }
}

//        new Thread(new Runnable() {
//            public void run() {
//                AppDatabase db = Room.databaseBuilder(
//                        getActivity().getApplicationContext(),
//                        AppDatabase.class,
//                        "default-db").build();
//                // db.userDAO().insertAll(new UserEntity("jean","jean@gmail"));
//            }
//        }).start();