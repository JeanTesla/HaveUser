package com.example.haveuser.domain.callables;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public interface FragmentTransactionCallback {
    FragmentTransaction run(Fragment fragment, boolean replace);
}
