package com.main.marriage_list.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.main.marriage_list.databinding.FragmentAddItemsBinding
import com.main.marriage_list.model.product.ProductChildTest
import com.main.marriage_list.model.product.ProductDetailModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddNewItems : Fragment() {

    private lateinit var binding: FragmentAddItemsBinding
    var productDetailModel: ProductDetailModel? = ProductDetailModel()
    private var productDetailList: ArrayList<ProductDetailModel>? = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemsBinding.inflate(inflater, container, false)
        binding.detailModel = productDetailModel


        binding.btnSaveList.setOnClickListener { saveItemsToList() }
        binding.btnSaveDB.setOnClickListener { saveItemsToDB() }
        binding.btnGetDatas.setOnClickListener { getItems() }

        return binding.root
    }

    private fun saveItemsToList() {
        productDetailModel = ProductDetailModel()
        productDetailModel = binding.detailModel
        productDetailList?.add(productDetailModel ?: ProductDetailModel())
    }

    private fun saveItemsToDB() {
        val reference: DatabaseReference = Firebase.database.reference
        reference.child("Çeyiz").child(binding.etMainType.text.toString())
            .setValue(productDetailList)
            .addOnSuccessListener {
                Log.i("SAVE SUCCESS", "saveItemsToDB: ")
            }
            .addOnFailureListener {
                Log.i("SAVE FAILED", "$it")
            }
    }

    private fun getItems() {
        val reference: DatabaseReference = Firebase.database.reference
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.child("Çeyiz").children
                val list : MutableList<Any> = mutableListOf()
                val map : MutableMap<String, MutableList<Any>> = mutableMapOf()

                child.forEach {
                    it.getValue(Any::class.java)?.let { it1 -> list.add(it1) }
                    map[it.key.toString()] = list
                }

                Log.i("TAG", "onDataChange: ")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", error.message)
            }

        })
    }
}