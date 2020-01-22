package com.example.Magpie;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Magpie.model.PasswordChange;
import com.example.Magpie.model.Response;
import com.example.Magpie.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AccountFragment extends Fragment {

    static final String TAG = AccountFragment.class.getSimpleName();

    private static AccountFragment fragment;

    private EditText oldPassword;
    private EditText newPassword;
    private Button confirmBtn;

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();

    public static AccountFragment getInstance() {
        if (fragment == null) {
            fragment = new AccountFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmBtn = getActivity().findViewById(R.id.acc_comfirm_btn);
        oldPassword = getActivity().findViewById(R.id.acc_original_pwd);
        newPassword = getActivity().findViewById(R.id.acc_change_pwd);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);

                String oldPwd = oldPassword.getText().toString();
                String newPwd = newPassword.getText().toString();
                String username = new Session(getActivity()).getCurrentUsername();

                Call<Response<Void>> call = userService.changePassword(new PasswordChange().setUsername(username).setOldPassword(oldPwd).setNewPassword(newPwd));
                call.enqueue(new Callback<Response<Void>>() {
                    @Override
                    public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                        Log.d(TAG, response.toString());
                        String status = response.body().getStatus();
                        if (status.equals("NO_CONTENT")) {
                            Toast.makeText(getActivity(), "Change password successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.body().getError() != null) {
                                Toast.makeText(getActivity(), response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Unable to change password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Void>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Change password failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
