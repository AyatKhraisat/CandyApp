package com.ayat.candyapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.ayat.candyapp.R
import com.ayat.candyapp.databinding.DialogAddCandyBinding
import com.ayat.candyapp.user_flow.home.models.CandyModel


open class AddCandyDialog : androidx.fragment.app.DialogFragment() {
    private lateinit var binding: DialogAddCandyBinding

    private var clickListener: CandyDialogListener? = null


    override fun onAttach(context: Context) {

        super.onAttach(context)

        try {
            clickListener = context as CandyDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement AddCandyDialog")
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.dialog_add_candy, null, false
        )
        val root = binding.root
        return getAlertDialogBuilder(root).create()
    }

    private fun getAlertDialogBuilder(root: View): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context)
        builder.setView(root)
            .setTitle("Add New Candy")
            .setPositiveButton(getString(R.string.add), { dialog, which ->
                //do nothing
            })
            .setNegativeButton(getString(R.string.dismiss), { dialog, which -> dialog.dismiss() })
        return builder
    }

    override fun onStart() {
        super.onStart()
        val alertDialog = dialog as AlertDialog
        val positiveButton = alertDialog.getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener({ this.onPositiveButtonClicked() })
    }


    private fun onPositiveButtonClicked() {
        if (validateFields()) {
            clickListener!!.onAddClicked(CandyModel(binding.etName.getText().toString(),
                binding.etDes.getText().toString()))
            dismiss()
        }
    }


    private fun validateFields(): Boolean {
        var isValid = true

        if (TextUtils.isEmpty(binding.etName.getText().toString().trim())) {
            binding.tilName.error = context!!.getString(R.string.error_required_field)
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if (TextUtils.isEmpty(binding.etDes.getText().toString().trim())) {
            binding.tilDes.error=context!!.getString(R.string.error_required_field)
            isValid = false
        } else {
            binding.tilDes.error=null
        }

        return isValid
    }


}
