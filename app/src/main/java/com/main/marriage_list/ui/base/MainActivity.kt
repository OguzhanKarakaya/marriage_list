package com.main.marriage_list.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.main.marriage_list.R
import com.main.marriage_list.databinding.ActivityMainBinding
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.helper.SharedPrefHelper
import com.main.marriage_list.model.user.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        findNavController(R.id.flowFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        observeUser()
    }

    private fun observeUser() {
        val currentUser = SharedPrefHelper.get<UserModel>(Constants.USER_MODEL_PREF)
        if (currentUser != null) {
            Constants.currentUser = currentUser
            navController.navigate(R.id.actionHomePage)
        } else
            navController.navigate(R.id.actionLogin)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Constants.userEmail = ""
        Constants.userPassword = ""
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.userEmail = ""
        Constants.userPassword = ""
    }

    override fun onStop() {
        super.onStop()
        Constants.userEmail = ""
        Constants.userPassword = ""
    }
}