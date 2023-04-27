package com.main.marriage_list.helper

import com.main.marriage_list.model.user.UserModel

object Constants {
    var userId: String? = ""
    var userEmail: String? = ""
    var userPassword: String? = ""
    var currentUser: UserModel? = UserModel()

    const val USERS_DATABASE = "USERS"
    const val USER_MODEL_PREF = "USER_MODEL"
}