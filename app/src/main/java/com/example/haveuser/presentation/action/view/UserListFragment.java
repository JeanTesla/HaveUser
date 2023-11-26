package com.example.haveuser.presentation.action.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haveuser.R;
import com.example.haveuser.domain.callables.FragmentTransactionCallback;
import com.example.haveuser.domain.entities.UserEntity;
import com.example.haveuser.presentation.action.viewmodel.UserListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserListFragment extends Fragment {

    private final FragmentTransactionCallback fragmentTransactionCallback;

    private UserListViewModel userListViewModel;

    public UserListFragment(FragmentTransactionCallback transactionCallback) {
        fragmentTransactionCallback = transactionCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_list, container, false);

        userListViewModel =  new UserListViewModel(this.getActivity().getApplication());

        AddUserFragment addUserFragment = new AddUserFragment();

        FloatingActionButton addUserButton = rootView.findViewById(R.id.addUserButton);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        UserListViewAdapter userListViewAdapter = new UserListViewAdapter(this::openUserDetailCallback);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userListViewAdapter);

        userListViewModel.userList.observe(getViewLifecycleOwner(), users -> {
            if(Objects.isNull(users)) return;
            Log.i("test", "Retorno do observable");
            Log.i("test", users.get(0).getNome());
            userListViewAdapter.insertAllUsers(users);
            userListViewAdapter.notifyDataSetChanged();
        });

        addUserButton.setOnClickListener(v ->
                fragmentTransactionCallback.run(addUserFragment, true)
                .addToBackStack("addUserFragment")
                .commit());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userListViewModel.getUsers();
    }

    private void openUserDetailCallback(int userId) {
        fragmentTransactionCallback.run(new UserDatailFragment(userId), true)
                .addToBackStack("userDetailFragment")
                .commit();
    }
}