package com.viellandsteve.htwk

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), Communicator {
    val TAG = "MainActivity"

    val stpFragment = StpFragment()
    val stp2Fragment = Stp2Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_stp, stpFragment)
            addToBackStack(null)
            commit()
        }

    }

    override fun passDataCom(editTextInput: String) {
        val bundle = Bundle()
        bundle.putString("message", editTextInput)

        val transaction = this.supportFragmentManager.beginTransaction()
        stp2Fragment.arguments = bundle
        transaction.replace(R.id.fragment_stp, stp2Fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



    fun btnStp(view: android.view.View) {
        stp2Fragment.list_montag.clear()
        stp2Fragment.list_dienstag.clear()
        stp2Fragment.list_mittwoch.clear()
        stp2Fragment.list_donnerstag.clear()
        stp2Fragment.list_freitag.clear()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_stp, stpFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        stp2Fragment.list_montag.clear()
        stp2Fragment.list_dienstag.clear()
        stp2Fragment.list_mittwoch.clear()
        stp2Fragment.list_donnerstag.clear()
        stp2Fragment.list_freitag.clear()
    }



}