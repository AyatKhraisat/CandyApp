package com.ayat.candyapp.login

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.dialogs.ProgressDialog
import com.ayat.candyapp.signup.SignupActivity
import javax.inject.Inject

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var loginViewModel: LoginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        with(binding) {
            binding.viewModel = loginViewModel
            binding.lifecycleOwner = this@LoginActivity
        }


        var dialog = ProgressDialog.progressDialog(this);

        loginViewModel.showError.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                showErrorDialog(it)
            }

        })

        loginViewModel.openSignUpActivity.observe(this,
            Observer { startActivity(Intent(LoginActivity@this,SignupActivity::class.java)) })

        loginViewModel.showLoading.observe(this, Observer { dialog.show() })
        loginViewModel.hideLoading.observe(this, Observer { dialog.hide() })

    }

    fun showErrorDialog(message: String) {
        var alertDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton(
                "Ok", { dialog, which -> dialog.dismiss() })
        .create();

        alertDialog.show();
    }

    override fun inject() {
        uiControllerComponent.inject(this)

    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

}
