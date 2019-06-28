package com.ayat.candyapp.user_flow.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.user_flow.signup.SignupActivity

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class HomeActivity : BaseActivity<HomeViewModel, com.ayat.candyapp.databinding.ActivityHomeBinding>() {

    companion object {
        private const val AUTH_ARGS = "auth"
        fun launchActivity(context: Context, auth: String) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(AUTH_ARGS, auth)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val auth = intent.getStringExtra(AUTH_ARGS)

    }

    override fun observeLiveData() {
        super.observeLiveData()


    }

    override fun inject() {
        uiControllerComponent.inject(this)

    }


    override fun getLayout(): Int = R.layout.activity_home;


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java


    override fun onBackPressed() {
        this.finishAffinity()
    }

}
