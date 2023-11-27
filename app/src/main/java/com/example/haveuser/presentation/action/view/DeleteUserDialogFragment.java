package com.example.haveuser.presentation.action.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.haveuser.domain.callables.OnClickDeleteUserCallback;


public class DeleteUserDialogFragment extends DialogFragment {

    private final int mUserId;

    private OnClickDeleteUserCallback mOnClickDeleteUserCallback;

    DeleteUserDialogFragment(OnClickDeleteUserCallback onClickDeleteUserCallback, int userId) {
        this.mUserId = userId;
        this.mOnClickDeleteUserCallback = onClickDeleteUserCallback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Você está prestes a deletar esse usuário.")
                .setPositiveButton("Estou ciente", (dialog, id) ->
                        mOnClickDeleteUserCallback.run(mUserId)
                )

                .setNegativeButton("Sair", (dialog, id) -> dialog.cancel());
        return builder.create();
    }


}