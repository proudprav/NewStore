package com.praveen.android.newstore.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.praveen.android.newstore.application.NewStoreApplication
import com.praveen.android.newstore.di.APIComponent
import com.praveen.android.newstore.repository.RetrofitRepository
import com.praveen.android.newstore.ui.viewmodel.StoreCommonFragmentViewModel


import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val apiComponent: APIComponent = NewStoreApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(StoreCommonFragmentViewModel::class.java)) {
            return StoreCommonFragmentViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
