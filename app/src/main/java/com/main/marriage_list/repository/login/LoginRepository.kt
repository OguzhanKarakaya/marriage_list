package com.main.marriage_list.repository.login

import com.main.marriage_list.model.user.UserBaseModel
import com.main.marriage_list.model.user.UserModel
import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun createUser(userBaseModel: UserBaseModel): Single<String>
    fun signInUser(userEmail: String, userPassword: String): Single<String>
}