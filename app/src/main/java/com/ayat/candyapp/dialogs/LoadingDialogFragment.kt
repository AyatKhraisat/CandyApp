package com.ayat.candyapp.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.ayat.candyapp.R
import com.ayat.candyapp.utils.withArgs

/**
 * Created by Ayat khraisat  on 2019-06-03
 * ayatzkhraisat@gmail.com
 * Project Name: CandyApp
 *
 */
class LoadingDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val binding = DataBindingUtil.inflate<com.ayat.candyapp.databinding.ProgressDialogBinding>(
            LayoutInflater.from(activity),
            R.layout.progress_dialog,
            null,
            false
        )

        arguments?.let {binding.tvProgressText.setText(arguments!!.getString(TITLE_ARGS)) }

        val builder = AlertDialog.Builder(activity!!, R.style.TransparentProgressAlertDialog)
        builder.setView(binding.root)
        builder.setCancelable(false)

        return builder.create()


    }

    companion object {

        private val TITLE_ARGS = "title"

        fun newInstance(dogId: String) = LoadingDialogFragment().withArgs {
            putString(TITLE_ARGS, dogId)
        }
    }


}
