package com.main.marriage_list.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.main.marriage_list.R
import com.main.marriage_list.databinding.FragmentRegisterBinding
import com.main.marriage_list.helper.Constants
import com.main.marriage_list.helper.component.ResultBottomSheetFragment
import com.main.marriage_list.model.user.UserBaseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: LoginViewModel by viewModels()
    private val userModel = UserBaseModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.model = userModel


        viewModel.createUserLiveData.observe(viewLifecycleOwner) {
            saveUserToDB(it)
        }

        return binding.root
    }

    private fun saveUserToDB(uId: String) {
        viewModel.showProgressDialog(this@RegisterFragment, "tag")
        viewModel.saveUserToDB(binding.model ?: userModel, uId)
        viewModel.saveUserLiveData.observe(viewLifecycleOwner) {
            if (it)
                setResultSheet(isSuccess = true)
            else
                setResultSheet(isSuccess = false)
        }
    }

    private fun setResultSheet(isSuccess: Boolean) {
        viewModel.dismissProgressDialog(this@RegisterFragment, "tag")
        viewModel.setResultSheet(
            fragment = this,
            isSuccess = isSuccess,
            title = if (isSuccess) getString(R.string.success) else getString(R.string.fail),
            description = if (isSuccess) getString(R.string.register_success) else getString(R.string.register_fail),
            buttonText = getString(R.string.ok),
            buttonClickListener = object : ResultBottomSheetFragment.ResultBottomSheetClickListener{
                override fun onContinueButtonClick(isSuccess: Boolean) {
                    if (isSuccess) {
                        Constants.userEmail = binding.model?.userEmail
                        Constants.userPassword = binding.model?.userPassword
                        findNavController().navigateUp()
                    }

                }
            }
        )
    }
}