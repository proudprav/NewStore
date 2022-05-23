package com.praveen.android.newstore.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Product
import com.example.example.ProductResponse
import com.example.example.StoreResponse
import com.praveen.android.newstore.R
import com.praveen.android.newstore.model.SummaryCart
import com.praveen.android.newstore.ui.viewmodel.StoreCommonFragmentViewModel
import com.praveen.android.newstore.utils.FragmentNavigationInterface
import com.praveen.android.newstore.utils.ViewModelFactory
import kotlinx.android.synthetic.main.store_fragment.*

class ProductListFragement(val fragmentNavigation: FragmentNavigationInterface) : Fragment(),
    ProductItemListRecyclerViewAdapter.ClickListener {
    private lateinit var viewModel: StoreCommonFragmentViewModel
    val myItemRecyclerViewAdapter = ProductItemListRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.store_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StoreCommonFragmentViewModel::class.java)
        setupRecyclerView()
        btn_gotocart.setOnClickListener {
            fragmentNavigation.replaceFragment(SummaryCartFragment())
        }
        viewModel.getStoreInfo().observe(viewLifecycleOwner,
            { renderStoreUI(it) })
        viewModel.getProductItem().observe(viewLifecycleOwner,
            { renderProductUI(it) })
    }

    private fun setupRecyclerView() {
        rv_products.setHasFixedSize(true)
        rv_products.layoutManager = LinearLayoutManager(activity)
        rv_products.adapter = myItemRecyclerViewAdapter
    }

    private fun renderProductUI(it: ProductResponse?) {
        myItemRecyclerViewAdapter.setItems(it?.product!!)

    }

    override fun onButtonClickListener(product: Product) {
        SummaryCart.addProduct(product)
        btn_gotocart.visibility = View.VISIBLE
    }

    private fun renderStoreUI(storeResponse: StoreResponse?) {
        storeResponse?.let {
            tv_storename.text = it.StoreInfo
            tv_description.text = it.Description
        }
    }

}