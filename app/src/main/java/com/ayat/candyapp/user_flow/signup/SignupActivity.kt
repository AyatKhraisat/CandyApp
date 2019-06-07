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
import com.ayat.candyapp.databinding.ActivitySignupBinding
import com.ayat.candyapp.dialogs.ProgressDialog
import com.ayat.candyapp.user_flow.login.LoginViewModel
import javax.inject.Inject

class SignupActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var binding: com.ayat.candyapp.databinding.ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var signUpViewModel: SignUpViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SignUpViewModel::class.java)
        binding = DataBindingUtil.setContentView(this@SignupActivity, R.layout.activity_login)
        with(binding) {
            binding.viewModel = signUpViewModel
            binding.lifecycleOwner = this@SignupActivity
        }


        var dialog = ProgressDialog.progressDialog(this);

        signUpViewModel.showError.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                showErrorDialog(it)
            }

        })


        signUpViewModel.showLoading.observe(this, Observer { dialog.show() })
        signUpViewModel.hideLoading.observe(this, Observer { dialog.hide() })

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


}
