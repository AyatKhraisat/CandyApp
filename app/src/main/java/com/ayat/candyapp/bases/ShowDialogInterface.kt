package com.ayat.candyapp.bases

import android.content.DialogInterface
import android.widget.ListAdapter
import androidx.fragment.app.DialogFragment

/**
 * Created by Ayat khraisat  on 2019-06-04
 * akhraisat@blessedtreeit.com
 *
 *
 * Project Name: GateToPay
 *
 *
 * Blessed Tree IT
 */
interface ShowDialogInterface {

    fun showLoadingDialog()
    fun showDialog(dialogFragment: DialogFragment, tag: String)
    fun showListDialog(adapter: ListAdapter,itemClicked:() ->Unit, title: String)
    fun showErrorDialog(error: String)
    fun hideLoadingDialog()
}
