package com.example.haveuser.presentation.action.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.haveuser.R;
import com.example.haveuser.domain.entities.UserEntity;
import com.example.haveuser.domain.enums.SexoEnum;
import com.example.haveuser.domain.enums.TipoPessoaEnum;
import com.example.haveuser.presentation.action.viewmodel.AddEditUserViewModel;
import com.example.haveuser.utils.FileUtil;
import com.example.haveuser.utils.MaskUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.Objects;

public class AddEditUserFragment extends Fragment {

    private EditText loginNameEditText;

    private EditText passwordEditText;

    private EditText nameEditText;

    private MaskedEditText cpfCnpjEditText;

    private TextInputLayout cpfCnpjInputLayout;

    private MaskedEditText birthDateEditText;

    private EditText emailEditText;

    private EditText publicPlaceEditText;

    private ImageButton imagePickerButton;

    private SwitchCompat typeSwitch;

    private AddEditUserViewModel addEditUserViewModel;

    private SwitchCompat sexSwitch;

    private Uri pickedImageUri;

    private Integer mUserId;

    public AddEditUserFragment() {}

    public AddEditUserFragment(int userId) {
        this.mUserId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_user, container, false);

        addEditUserViewModel = new AddEditUserViewModel(this.getActivity().getApplication());

        FloatingActionButton returnButton = rootView.findViewById(R.id.returnButtonAddUserFrag);

        typeSwitch = rootView.findViewById(R.id.typeSwitch);
        sexSwitch = rootView.findViewById(R.id.sexSwitch);

        Button submitButton = rootView.findViewById(R.id.submitButton);
        imagePickerButton = rootView.findViewById(R.id.imagePickerButton);
        loginNameEditText = rootView.findViewById(R.id.loginNameEditText);
        passwordEditText = rootView.findViewById(R.id.passwordEditText);
        nameEditText = rootView.findViewById(R.id.nameEditText);
        cpfCnpjEditText = rootView.findViewById(R.id.cpfcnpjEditText);
        cpfCnpjInputLayout = rootView.findViewById(R.id.cpfCnpjInputLayout);
        birthDateEditText = rootView.findViewById(R.id.birthDateEditText);
        emailEditText = rootView.findViewById(R.id.emailEditText);
        publicPlaceEditText = rootView.findViewById(R.id.publicPlaceEditText);


        if(Objects.nonNull(mUserId)){
            addEditUserViewModel.getUser(mUserId);
            submitButton.setText("Salvar edição");
        }

        addEditUserViewModel.findedUser.observe(getViewLifecycleOwner(), user -> {

            if(Objects.isNull(user)) return;

            if(Objects.nonNull(user.getFoto())){
                imagePickerButton.setImageBitmap(FileUtil.encodedBase64ToBitmap(user.getFoto()));
            }

            loginNameEditText.setText(user.getUserName());
            passwordEditText.setText(user.getPassword());
            nameEditText.setText(user.getNome());
            cpfCnpjEditText.setText(user.getCpfCnpj());
            birthDateEditText.setText(user.getDataNascimento());
            emailEditText.setText(user.getEmail());
            publicPlaceEditText.setText(user.getEndereco());
        });

        addEditUserViewModel.isPersisted.observe(getViewLifecycleOwner(), result -> {

            if (Objects.isNull(result)) return;

            if (!result) {
                Toast.makeText(
                        getContext(), "Já existe um usuário com esse nome de usuário",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG)
                    .show();

            getActivity().getSupportFragmentManager().popBackStack();
        });

        addEditUserViewModel.isUpdated.observe(getViewLifecycleOwner(), result -> {

            if (Objects.isNull(result)) return;

            if (!result) {
                Toast.makeText(
                        getContext(), "Erro ao tentar alterar esse usuário.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getContext(), "Usuário alterado com sucesso!", Toast.LENGTH_LONG)
                    .show();

            getActivity().getSupportFragmentManager().popBackStack();
        });

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia = registerForActivityResult(
                new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        imagePickerButton.setImageURI(uri);
                        pickedImageUri = uri;
                    } else {
                        Toast.makeText(getContext(), "Nenhuma imagem selecionada", Toast.LENGTH_LONG)
                                .show();
                    }
                });

        typeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            cpfCnpjEditText.setText("");
            birthDateEditText.setText("");

            String switchFieldText, cpfCnpjFieldText;
            boolean isEnableSexSwitch, isEnableBirthDateEditText;

            if (isChecked) {
                switchFieldText = "Pessoa Jurídica";
                cpfCnpjFieldText = "CNPJ";
                isEnableSexSwitch = false;
                isEnableBirthDateEditText = false;
                cpfCnpjEditText.setMask(MaskUtil.FORMAT_CNPJ);
            } else {
                switchFieldText = "Pessoa Física";
                cpfCnpjFieldText = "CPF";
                isEnableSexSwitch = true;
                isEnableBirthDateEditText = true;
                cpfCnpjEditText.setMask(MaskUtil.FORMAT_CPF);
            }

            typeSwitch.setText(switchFieldText);
            sexSwitch.setEnabled(isEnableSexSwitch);
            cpfCnpjInputLayout.setHint(cpfCnpjFieldText);
            birthDateEditText.setEnabled(isEnableBirthDateEditText);
        });

        sexSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                buttonView.setText(isChecked ? "Masculino" : "Feminino"));

        imagePickerButton.setOnClickListener(v -> pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build()));

        returnButton.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());

        submitButton.setOnClickListener(v -> saveUser());

        return rootView;
    }

    private void saveUser() {

        boolean isEdition = Objects.nonNull(mUserId);

        if (!validateAllFields()) {
            Toast.makeText(getContext(), "Dados inválidos, verifique os alertas nos campos.", Toast.LENGTH_LONG).show();
            return;
        }

        String base64EncodedPhoto = "";

        if (Objects.nonNull(pickedImageUri)) {
            base64EncodedPhoto = FileUtil.resizeBase64Image(
                    FileUtil.fileUriToBase64(pickedImageUri,
                            getContext().getContentResolver()
                    )
            );
        }

        UserEntity currentUserData = new UserEntity(
                nameEditText.getText().toString(),
                loginNameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                base64EncodedPhoto,
                publicPlaceEditText.getText().toString(),
                emailEditText.getText().toString(),
                birthDateEditText.getText().toString(),
                sexSwitch.isChecked() ? SexoEnum.MASCULINO : SexoEnum.FEMININO,
                typeSwitch.isChecked() ? TipoPessoaEnum.JURIDICA : TipoPessoaEnum.FISICA,
                cpfCnpjEditText.getText().toString()
        );

        if(isEdition){
            addEditUserViewModel.updateUser(currentUserData, mUserId);
        }else{
            addEditUserViewModel.persistUser(currentUserData);
        }

    }

    private boolean validateAllFields() {
        return addEditUserViewModel.validateUserName(loginNameEditText) &&
                addEditUserViewModel.validatePassword(passwordEditText) &&
                addEditUserViewModel.validateName(nameEditText) &&
                addEditUserViewModel.validateCpfAndCnpj(typeSwitch.isChecked(), cpfCnpjEditText) &&
                addEditUserViewModel.validateBirthDate(typeSwitch.isChecked(), birthDateEditText) &&
                addEditUserViewModel.validateEmail(emailEditText) &&
                addEditUserViewModel.validatePublicPlace(publicPlaceEditText);
    }

}
