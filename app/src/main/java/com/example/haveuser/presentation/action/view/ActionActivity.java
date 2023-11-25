package com.example.haveuser.presentation.action.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.haveuser.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        Fragment addUserFragment = new AddUserFragment();
        Fragment getUserListFragment = new UserListFragment(this::pushFragmentAdministrator);

        getSupportFragmentManager().beginTransaction().replace(R.id.action_frame, getUserListFragment, getUserListFragment.getTag()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int selectedMenuItemId = item.getItemId();
            int bottomUserListMenu = R.id.user_list;
            int bottomUserAddMenu = R.id.user_add;
            /*
                Não foi possível fazer uso do switch-case
                por conta do valor constante
                necessário para a diretiva switch.
            */

            try {
                if (selectedMenuItemId == bottomUserListMenu) {
                    pushFragmentAdministrator(getUserListFragment, true).commit();
                    return true;
                }

                if (selectedMenuItemId == bottomUserAddMenu) {
                    pushFragmentAdministrator(addUserFragment, true).commit();
                    return true;
                }
            } catch (RuntimeException e) {
                Log.e("teste", e.getMessage());
            }

            return false;
        });
    }
}