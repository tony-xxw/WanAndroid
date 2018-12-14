package com.study.kotlin.wan

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, BlogFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() =
            findNavController(R.id.homeFragment).navigateUp()
}
