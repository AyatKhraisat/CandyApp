package com.ayat.candyapp.bases

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ListAdapter
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ayat.candyapp.App
import com.ayat.candyapp.R
import com.ayat.candyapp.di.DaggerUiControllerComponent
import com.ayat.candyapp.di.UiControllerComponent
import com.ayat.candyapp.di.UiControllerModule
import com.ayat.candyapp.dialogs.LoadingDialogFragment
import com.ayat.candyapp.utils.Event
import io.reactivex.annotations.Nullable
import javax.inject.Inject

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
abstract class BaseActivity<VM : BaseViewModel, V : ViewDataBinding> :
    AppCompatActivity(), ShowDialogInterface {

    companion object {
        private val LOADING_DIALOG_TAG = "loading_dialog"

    }


    private var loadingDialog: LoadingDialogFragment? = null


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var viewModel: VM
    lateinit var binding: V

    val uiControllerComponent: UiControllerComponent by lazy {
        DaggerUiControllerComponent.builder()
            .uiControllerModule(UiControllerModule(this))
            .applicationComponent((application as App).applicationComponent).build()

    }

    open  fun showFragment(@IdRes container: Int, fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)

        if (addToBackStack) {
            ft.addToBackStack(null)
        }

        ft.commit()
    }

    override fun showLoadingDialog() {

        showProgressDialog(getString(R.string.loading))

    }

    private fun showProgressDialog(text: String) {
        loadingDialog = LoadingDialogFragment.newInstance(text)

        loadingDialog!!.show(supportFragmentManager, LOADING_DIALOG_TAG)
    }

    override fun hideLoadingDialog() {
        loadingDialog?.let {
            it.dismiss()
            loadingDialog = null
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        binding=  DataBindingUtil.setContentView<V>(this@BaseActivity, getLayout())
        binding.lifecycleOwner = this@BaseActivity


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())

        observeLiveData()
    }

    @CallSuper
   open protected fun observeLiveData() {
        viewModel.showErrorEventMutableLiveData.observe(this, Observer<Event<String>> { stringEvent ->
            val errorMessage = stringEvent.getContentIfNotHandled()
            if (errorMessage != null)
                showErrorDialog(errorMessage)
        })
        viewModel.showLoadingDialogSingleLiveEvent.observe(this, Observer<Event<Any>> { objectEvent ->
            if (objectEvent.getContentIfNotHandled() != null)
                showLoadingDialog()
        })
        viewModel.hideDialogEventMutableLiveData.observe(this, Observer<Event<Any>> { objectEvent ->
            if (objectEvent.getContentIfNotHandled() != null)
                hideLoadingDialog()
        })
    }


      override fun showListDialog(adapter: ListAdapter, itemClicked: () -> Unit, title: String) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(title)
            setAdapter(adapter) { dialog: DialogInterface?, which: Int ->
                itemClicked()
            }
        }
        val alert = builder.create()
        alert.show()

    }

     override fun showDialog(dialogFragment: DialogFragment, tag: String) {
        dialogFragment.show(supportFragmentManager, tag)
    }

      override fun showErrorDialog(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(error)
        builder.setIcon(R.drawable.ic_sad)
        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
    protected abstract fun getViewModelClass(): Class<VM>


    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun inject()

}
