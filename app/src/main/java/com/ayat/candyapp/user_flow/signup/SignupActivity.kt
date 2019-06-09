package com.ayat.candyapp.user_flow.signup

import android.os.Bundle
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.databinding.ActivitySignUpBinding
import com.ayat.candyapp.dialogs.ProgressDialog

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
