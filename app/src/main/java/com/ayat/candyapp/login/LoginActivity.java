package com.ayat.candyapp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.ayat.candyapp.R;
import com.ayat.candyapp.bases.BaseActivity;
import com.ayat.candyapp.di.Qualifiers;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    @Inject
    @Qualifiers.LoginViewModel
    protected ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setViewmodel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.getOpenMainActivityEvent().observe(
                this,
                aVoid -> NavActivity.launchActivity(LoginActivity.this));

        loginViewModel.getShowErrorMessage().observe(this,
                aBoolean -> Toast.makeText(LoginActivity.this,
                        R.string.username_or_password_not_valid, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void inject() {
        getUiControllerComponent().inject(this);

    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

}
