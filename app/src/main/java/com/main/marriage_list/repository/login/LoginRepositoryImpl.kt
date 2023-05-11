package com.main.marriage_list.repository.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.model.user.UserBaseModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
) : LoginRepository {

    private val TAG = "TAG_BASE_REPOSITORY"

    override fun createUser(userBaseModel: UserBaseModel): Single<String> {
        val auth: FirebaseAuth = Firebase.auth
        auth.createUserWithEmailAndPassword(
            userBaseModel.userEmail.toString(),
            userBaseModel.userPassword.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "User creation successful.")
            } else {
                Log.d(TAG, "User creation failed.")
            }
        }
        return Single.just(auth.currentUser?.uid ?: "")
    }

    override fun signInUser(userEmail: String, userPassword: String): Single<String> {
        val auth: FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i(TAG, "signInUser: ")
            } else
                Log.d(TAG, "Login failed.${it.exception.toString()}")
        }
        return Single.just(auth.currentUser?.uid ?: "")
    }
}