package com.main.marriage_list.ui.homepage

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.main.marriage_list.databinding.FragmentHomePageBinding
import com.main.marriage_list.model.product.ProductModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()

    private var productList: ArrayList<ProductModel>? = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        viewModel.showProgressDialog(this, "tag")
        getProducts()


        viewModel.event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { st ->
                listenEvents(st)
            }
        }

        return binding.root
    }

    private fun getProducts() {
        viewModel.getProducts()
        viewModel.productLiveData.observe(viewLifecycleOwner) {
            Log.i("TAG", "getProducts: ")
        }
    }

    private fun listenEvents(event: HomePageEvents) {
        when (event) {
            is HomePageEvents.OpenProduct -> openProductDetail(event.data)
        }
    }

    private fun openProductDetail(productModel: ProductModel) {
        val action =
            HomePageFragmentDirections.actionHomePageFragmentToProductDetailFragment(productModel)
        findNavController().navigate(action)
    }
}