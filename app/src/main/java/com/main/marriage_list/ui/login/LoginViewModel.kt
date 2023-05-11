package com.main.marriage_list.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.common.ErrorCodes
import com.main.marriage_list.common.ErrorEnum
import com.main.marriage_list.common.ErrorType
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.model.product.ProductModel
import com.main.marriage_list.model.user.UserBaseModel
import com.main.marriage_list.model.user.UserModel
import com.main.marriage_list.repository.login.LoginRepository
import com.main.marriage_list.ui.base.BaseEvents
import com.main.marriage_list.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) :
    BaseViewModel<BaseEvents>() {

    private val _createUserLiveData = MutableLiveData<String>()
    val createUserLiveData: LiveData<String>
        get() = _createUserLiveData

    private val _signInLiveData = MutableLiveData<String>()
    val signInLiveData: LiveData<String>
        get() = _signInLiveData

    private val _currentUserLiveData = MutableLiveData<UserModel>()
    val currentUserLiveData: LiveData<UserModel>
        get() = _currentUserLiveData

    private val _saveUserLiveData = MutableLiveData<Boolean>()
    val saveUserLiveData: LiveData<Boolean>
        get() = _saveUserLiveData


    private fun createNewUser(userBaseModel: UserBaseModel) {
        disposables.add(repository.createUser(userBaseModel = userBaseModel)
            .observeOn(mainThread())
            .subscribe(
                {
                    _createUserLiveData.postValue(it)
                },
                {
                    errorType.value = ErrorType(ErrorEnum.AUTHENTICATING, it)
                }
            ))
    }

    fun signIn(userEmail: String, userPassword: String) {
        val auth: FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                _signInLiveData.postValue(auth.currentUser?.uid)
            } else {
                _signInLiveData.postValue(it.exception?.message)
            }
        }
    }

    fun onRegisterClicked(userBaseModel: UserBaseModel?) {
        if (userBaseModel != null) {
            createNewUser(userBaseModel)
        }
    }

    fun getUserInfo(uId: String?) {
        val fireStore = Firebase.firestore
        val userModel = UserModel()
        fireStore.collection(Constants.USERS_DATABASE).document(uId!!).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    userModel.userBaseModel.userId = document.data?.get("userId").toString()
                    userModel.userBaseModel.userName = document.data?.get("userName").toString()
                    userModel.boughtList =
                        document.data?.get("boughtList") as ArrayList<ProductModel>? /* = java.util.ArrayList<com.main.marriage_list.model.product.ProductModel> */
                    userModel.wantsToBuyList =
                        document.data?.get("wantsToBuyList") as ArrayList<ProductModel>? /* = java.util.ArrayList<com.main.marriage_list.model.product.ProductModel> */
                    _currentUserLiveData.postValue(userModel)
                }

            }.addOnFailureListener {
                Log.d("TAG", "Login failed. $it")
            }
    }

    fun saveUserToDB(userBaseModel: UserBaseModel, uId: String?) {
        val fireStore = Firebase.firestore
        val user = hashMapOf(
            "userName" to userBaseModel.userName,
            "userPassword" to userBaseModel.userPassword,
            "userId" to uId,
            "wantsToBuyList" to arrayListOf<ProductModel>(),
            "boughtList" to arrayListOf<ProductModel>()
        )
        fireStore.collection(Constants.USERS_DATABASE).document(uId.toString())
            .set(user)
            .addOnSuccessListener {
                _saveUserLiveData.postValue(true)
            }
            .addOnFailureListener {
                _saveUserLiveData.postValue(false)
            }
    }
}