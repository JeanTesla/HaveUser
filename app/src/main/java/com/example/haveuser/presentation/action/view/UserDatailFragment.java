package com.example.haveuser.presentation.action.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.haveuser.R;
import com.example.haveuser.presentation.action.viewmodel.UserDetailViewModel;
import com.example.haveuser.utils.FileUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class UserDatailFragment extends Fragment {

    private int mUserId;

    private UserDetailViewModel userDetailViewModel;

    public UserDatailFragment(int userId) {
        mUserId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_datail, container, false);

        userDetailViewModel =
                new UserDetailViewModel(getActivity().getApplication());

        FloatingActionButton returnButton = view.findViewById(R.id.returnButton);
        ImageView imageViewDetail = view.findViewById(R.id.imageViewDetail);
        TextView birthDateDetail = view.findViewById(R.id.birthDateDetail);
        TextView cpfCnpjDetail = view.findViewById(R.id.cpfCnpjDetail);
        TextView emailDetail = view.findViewById(R.id.emailDetail);
        TextView sexDetail = view.findViewById(R.id.sexDetail);
        TextView typeDetail = view.findViewById(R.id.typeDetail);
        TextView nameDetail = view.findViewById(R.id.nameDetail);
        TextView userNameDetial = view.findViewById(R.id.userNameDetail);

        userDetailViewModel.findedUser.observe(getViewLifecycleOwner(), user -> {

            if (Objects.isNull(user)) return;

            Log.i("test", "&&&&&&&&&&&&&&&&&&&&&");
            Log.i("test", user.getUserName());

            if (!user.getFoto().isEmpty()) {
                imageViewDetail.setImageBitmap(FileUtil.encodedBase64ToBitmap(user.getFoto()));
            }

            nameDetail.setText(user.getNome());
            emailDetail.setText(user.getEmail());
            userNameDetial.setText(user.getUserName());
            cpfCnpjDetail.setText(user.getCpfCnpj());
            typeDetail.setText(user.getTipo().toString());
            sexDetail.setText(user.getSexo().toString());
            birthDateDetail.setText(user.getDataNascimento());

        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userDetailViewModel.getUser(mUserId);
    }
}