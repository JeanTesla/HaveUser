package com.example.haveuser.presentation.action.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.haveuser.R;

public class ActionActivity extends AppCompatActivity {

    private FragmentTransaction pushFragmentAdministrator(Fragment fragment, boolean replace) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (replace) {
            return transaction.replace(R.id.action_frame, fragment, fragment.getTag());
        }

        return transaction.add(R.id.action_frame, fragment, fragment.getTag());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Fragment getUserListFragment = new UserListFragment(this::pushFragmentAdministrator);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.action_frame,
                getUserListFragment,
                getUserListFragment.getTag()
        ).commit();

    }
}