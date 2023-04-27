package com.main.marriage_list.repository.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.model.user.UserBaseModel
import com.main.marriage_list.model.user.UserModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlin.reflect.KClass

class LoginRepositoryImpl @Inject constructor(
) : LoginRepository {

    private val TAG = "TAG_BASE_REPOSITORY"

    override fun createUser(userBaseModel: UserBaseModel): Single<String> {
        var isUserSavedToDB: Boolean? = false
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