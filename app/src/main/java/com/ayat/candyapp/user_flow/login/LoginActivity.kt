package com.ayat.candyapp.user_flow.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.user_flow.home.HomeActivity
import com.ayat.candyapp.user_flow.signup.SignupActivity

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

    }

    override fun observeLiveData() {
        super.observeLiveData()

        viewModel.openSignUpActivity.observe(this,
            Observer { startActivity(Intent(this, SignupActivity::class.java)) })
        viewModel._openMainActivityEvent.observe(this,
            Observer {
                val auth = it.getContentIfNotHandled()
                auth?.let { HomeActivity.launchActivity(this, auth) }
            })

    }

    override fun inject() {
        uiControllerComponent.inject(this)

    }


    override fun getLayout(): Int = R.layout.activity_login;


    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java


    override fun onBackPressed() {
        this.finishAffinity()
    }

}
