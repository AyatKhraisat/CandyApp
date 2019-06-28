package com.ayat.candyapp.user_flow.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseActivity
import com.ayat.candyapp.bases.BaseListAdapter
import com.ayat.candyapp.databinding.ActivityLoginBinding
import com.ayat.candyapp.user_flow.home.models.CandyModel
import com.ayat.candyapp.user_flow.signup.SignupActivity
import com.ayat.candyapp.utils.Event

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

    lateinit var listAdapter: BaseListAdapter<CandyModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val auth = intent.getStringExtra(AUTH_ARGS)
        listAdapter = BaseListAdapter<CandyModel>(R.layout.item_candy)

        binding.rvCandy.showItemDecoration()
        binding.rvCandy.setAdapter(listAdapter)
        binding.rvCandy.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { viewModel.getCandyList(auth) })


        viewModel.getCandyList(auth)


    }

    override fun observeLiveData() {
        viewModel.showErrorEventMutableLiveData.observe(this, Observer<Event<String>> { stringEvent ->
            val errorMessage = stringEvent.getContentIfNotHandled()
            if (errorMessage != null)
                binding.rvCandy.setEmptyViewText(errorMessage)
        })
        viewModel.showLoadingDialogSingleLiveEvent.observe(this, Observer<Event<Any>> { objectEvent ->
            if (objectEvent.getContentIfNotHandled() != null)
                binding.rvCandy.startRefreshing()
        })
        viewModel.hideDialogEventMutableLiveData.observe(this, Observer<Event<Any>> { objectEvent ->
            if (objectEvent.getContentIfNotHandled() != null)
                binding.rvCandy.stopRefreshing()
        })
        viewModel.mutableLiveDataList.observe(this, Observer {
            listAdapter.addItems(it)

        })


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
