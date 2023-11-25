package com.example.haveuser.presentation.action.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haveuser.R;
import com.example.haveuser.domain.callables.OnClickItemCallback;
import com.example.haveuser.domain.entities.UserEntity;
import com.example.haveuser.domain.enums.TipoPessoaEnum;

import java.util.ArrayList;
import java.util.List;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.ViewHolder> {

    private List<UserEntity> mDataSet = new ArrayList<>();

    private final OnClickItemCallback mCallback;

    public UserListViewAdapter(OnClickItemCallback callback) {
        mCallback = callback;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView userNameText;

        private final TextView userEmailText;

        private final TextView userTypeText;

        public ViewHolder(View v, OnClickItemCallback callback) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.run(getAdapterPosition());
                }
            });

            userNameText = v.findViewById(R.id.userNameText);
            userEmailText = v.findViewById(R.id.userEmailText);
            userTypeText = v.findViewById(R.id.userTypeText);
        }

        public TextView getUserNameText() {
            return userNameText;
        }

        public TextView getUserEmailText() {
            return userEmailText;
        }

        public TextView getUserTypeText() {
            return userTypeText;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new ViewHolder(v, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserEntity currentUser = mDataSet.get(position);

        String userType = TipoPessoaEnum.FISICA.equals(currentUser.getTipo()) ? "Pessoa Física" : "Pessoa Jurídica";

        holder.getUserNameText().setText(currentUser.getNome());
        holder.getUserEmailText().setText(currentUser.getEmail());
        holder.getUserTypeText().setText(userType);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void insertAllUsers(List<UserEntity> users){
        mDataSet = users;
        //notify();
    }

    public void insertSingleUser(UserEntity user){
        mDataSet.add(user);
        notifyItemInserted(mDataSet.size());
    }

}
