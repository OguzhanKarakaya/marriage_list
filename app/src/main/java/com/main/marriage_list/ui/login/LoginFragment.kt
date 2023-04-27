package com.main.marriage_list.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.main.marriage_list.R
import com.main.marriage_list.common.setSafeOnClickListener
import com.main.marriage_list.databinding.FragmentLoginBinding
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.helper.SharedPrefHelper
import com.main.marriage_list.model.user.UserBaseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private var userModel: UserBaseModel? = UserBaseModel()

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnRegister.setSafeOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.btnSignIn.setSafeOnClickListener {
            with(binding) {
                if (!controlIsEmpty(etEmail.text.toString()) || !controlIsEmpty(etPassword.text.toString()))
                    setResultSheet(getString(R.string.check_info))
                else {
                    viewModel.signIn(etEmail.text.toString(), etPassword.text.toString())
                    viewModel.signInLiveData.observe(viewLifecycleOwner) {
                        if (!it.isNullOrEmpty())
                        getCurrentUser(uId = it)
                    }
                }
            }
        }

        return binding.root
    }

    private fun getCurrentUser(uId: String) {
        viewModel.getUserInfo(uId = uId)
        viewModel.currentUserLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                SharedPrefHelper.put(it, Constants.USER_MODEL_PREF)
                Constants.currentUser = it
                findNavController().navigate(R.id.mainFlowFragment)
            }
            else
                setResultSheet(getString(R.string.login_fail))
        }
    }

    override fun onResume() {
        super.onResume()
        userModel?.userEmail = Constants.userEmail
        userModel?.userPassword = Constants.userPassword
        binding.model = userModel
    }

    private fun controlIsEmpty(str: String): Boolean {
        return str.isNotEmpty()
    }

    private fun setResultSheet(description: String) {
        viewModel.setResultSheet(
            fragment = this@LoginFragment,
            isSuccess = false,
            title = getString(R.string.fail),
            description = description,
            buttonText = getString(R.string.ok),
            buttonClickListener = null
        )
    }
}