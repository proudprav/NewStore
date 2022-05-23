package com.praveen.android.newstore.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.praveen.android.newstore.model.SummaryCart
import com.praveen.android.newstore.repository.RetrofitRepository

class StoreCommonFragmentViewModel(private val retrofitRepository: RetrofitRepository) :
    ViewModel() {

    fun getStoreInfo() =
        retrofitRepository.fetchStoreInformation()

    fun getProductItem() = retrofitRepository.fetchProductItem()

    fun getCartTotalItems() =
        SummaryCart.totalItems()

    fun getCartTotalPrice() =
        SummaryCart.totalPrice()

    fun getSummaryProduct() =
        SummaryCart.productArray.product

    fun postOrder() = retrofitRepository.postOrder()


}