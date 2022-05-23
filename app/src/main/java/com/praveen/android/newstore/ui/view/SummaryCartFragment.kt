package com.praveen.android.newstore.ui.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Product
import com.praveen.android.newstore.R
import com.praveen.android.newstore.model.SummaryCart
import com.praveen.android.newstore.ui.viewmodel.StoreCommonFragmentViewModel
import com.praveen.android.newstore.utils.ViewModelFactory
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryCartFragment() : Fragment() {
    private lateinit var viewModel: StoreCommonFragmentViewModel
    val cartSummaryRecyclerViewAdapter = CartSummaryRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StoreCommonFragmentViewModel::class.java)
        rv_Summary.setHasFixedSize(true)
        rv_Summary.layoutManager = LinearLayoutManager(activity)
        rv_Summary.adapter = cartSummaryRecyclerViewAdapter
        renderProductSummary(viewModel.getSummaryProduct())

        btn_order.setOnClickListener {

            if (tv_address.text.isNotEmpty()) {
                SummaryCart.address = tv_address.text.toString()
                viewModel.postOrder().observe(viewLifecycleOwner,
                    {
                        AlertDialog.Builder(activity)
                            .setMessage("Order Successful, Just Sitback and Relax")
                            .setCancelable(false)
                            .setPositiveButton("Done", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    SummaryCart.clearCart()
                                    startActivity(Intent(activity, activity?.javaClass))
                                    activity?.finish()
                                }
                            }).show()
                    })

            } else {
                Toast.makeText(activity, "Please provide Address", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun renderProductSummary(summaryProduct: ArrayList<Product>) {
        cartSummaryRecyclerViewAdapter.setItems(summaryProduct)
    }

}