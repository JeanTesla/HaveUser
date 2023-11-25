package com.example.haveuser.presentation.action.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class UserListFragment extends Fragment {

    private List<UserEntity> userEntityList = new ArrayList<>();

    private final FragmentTransactionCallback fragmentTransactionCallback;

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

        UserListViewModel userListViewModel = new ViewModelProvider(this).get(UserListViewModel.class);

        AddUserFragment addUserFragment = new AddUserFragment();

        FloatingActionButton addUserButton = rootView.findViewById(R.id.addUserButton);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        UserListViewAdapter userListViewAdapter = new UserListViewAdapter(this::openUserDetailCallback);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userListViewAdapter);

        userListViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            Log.i("teste", "Retorno do observable");
            userListViewAdapter.insertAllUsers(users);
        });

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransactionCallback.run(addUserFragment, true)
                        .addToBackStack("addUserFragment")
                        .commit();
            }
        });

        return rootView;
    }

    private void openUserDetailCallback(int index) {
        Log.i("teste", userEntityList.get(index).getNome());
        fragmentTransactionCallback.run(new UserDatailFragment(), true)
                .addToBackStack("userDetailFragment")
                .commit();
    }
}

//        new Thread(new Runnable() {
//            public void run() {
//                AppDatabase db = Room.databaseBuilder(
//                        getActivity().getApplicationContext(),
//                        AppDatabase.class,
//                        "default-db").build();
//
//                List<UserEntity> userEntityList = db.userDAO().getAll();
//
//                for (int i = 0; i < userEntityList.size(); i++) {
//                    Log.i("teste", userEntityList.get(i).getNome());
//                }
//            }
//        }).start();