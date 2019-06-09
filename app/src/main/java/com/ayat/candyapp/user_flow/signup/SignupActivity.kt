package com.ayat.candyapp.user_flow.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.databinding.ActivitySignUpBinding
import com.ayat.candyapp.dialogs.ProgressDialog
import com.ayat.candyapp.user_flow.login.LoginViewModel
import javax.inject.Inject

class SignupActivity : BaseActivity<SignUpViewModel,ActivitySignUpBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel


        var dialog = ProgressDialog.progressDialog(this);

    }


    override fun inject() {
        uiControllerComponent.inject(this)

    }
    override fun getLayout(): Int =R.layout.activity_sign_up

    override fun getViewModelClass(): Class<SignUpViewModel> =SignUpViewModel::class.java


}
