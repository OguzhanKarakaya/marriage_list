package com.main.marriage_list.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.main.marriage_list.databinding.FragmentHomePageBinding
import com.main.marriage_list.model.product.ProductDetailModel
import com.main.marriage_list.model.product.ProductModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()

    private var productDetailArrayList: ArrayList<ProductDetailModel>? = arrayListOf()
    private var productList: ArrayList<ProductModel>? = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        val productDetailModel = ProductDetailModel(
            productName = "productNameTest",
            productImage = "https://img.freepik.com/free-photo/golden-wedding-rings-white-rose-from-wedding-bouquet_8353-10467.jpg?size=626&ext=jpg"
        )

        val productDetailModel2 = ProductDetailModel(
            productName = "productNameTest2",
            productImage = "https://img.freepik.com/free-photo/golden-wedding-rings-white-rose-from-wedding-bouquet_8353-10467.jpg?size=626&ext=jpg"
        )

        productDetailArrayList?.add(productDetailModel)
        productDetailArrayList?.add(productDetailModel2)
        productDetailArrayList?.add(productDetailModel)

        val productModel = ProductModel(
            productDetailList = productDetailArrayList,
            productMainName = "main name",
            productImage = "https://img.freepik.com/free-photo/golden-wedding-rings-white-rose-from-wedding-bouquet_8353-10467.jpg?size=626&ext=jpg"
        )

        productList?.add(productModel)


        binding.productList = productList

        viewModel.event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { st ->
                listenEvents(st)
            }
        }

        return binding.root
    }

    private fun listenEvents(event: HomePageEvents) {
        when (event) {
            is HomePageEvents.OpenProduct -> openProductDetail(event.data)
        }
    }

    private fun openProductDetail(productModel: ProductModel) {
        val action = HomePageFragmentDirections.actionHomePageFragmentToProductDetailFragment(productModel)
        findNavController().navigate(action)
    }
}