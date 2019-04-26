package com.ayat.candyapp.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ayat.candyapp.R
import com.ayat.candyapp.ViewModelProviderFactory
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.di.Qualifiers

import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var loginViewModel: LoginViewModel? = null

    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        binding!!.loginViewModel = loginViewModel
        binding!!.lifecycleOwner = this

        //        loginViewModel.getOpenMainActivityEvent().observe(
        //                this,
        //                aVoid -> NavActivity.launchActivity(LoginActivity.this));

        loginViewModel!!.showErrorMessage.observe(this, Observer {
            Toast.makeText(
                this@LoginActivity,
                R.string.username_or_password_not_valid, Toast.LENGTH_LONG
            ).show()
        })
    }

    override fun inject() {
        uiControllerComponent.inject(this)

    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

    companion object {

        fun launchActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}
