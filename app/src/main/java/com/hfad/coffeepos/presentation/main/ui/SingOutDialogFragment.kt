package com.hfad.coffeepos.presentation.main.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.R

class SingOutDialogFragment : DialogFragment() {

    private val auth = Firebase.auth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выход из аккаунта")
                .setMessage("Вы действительно хотите выйти?")
                .setCancelable(true)
                .setPositiveButton("Выйти") { _, _ ->
                    auth.signOut()
                    findNavController().navigate(R.id.loginFragment)
                }
                .setNegativeButton("Назад") { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}