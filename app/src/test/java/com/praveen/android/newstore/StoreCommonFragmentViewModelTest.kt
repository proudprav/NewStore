package com.praveen.android.newstore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.example.StoreResponse
import com.google.gson.Gson
import com.praveen.android.newstore.repository.RetrofitRepository
import com.praveen.android.newstore.ui.viewmodel.StoreCommonFragmentViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoreCommonFragmentViewModelTest {

    @Mock
    lateinit var retrofitRepository: RetrofitRepository

    @Mock
    lateinit var viewModel: StoreCommonFragmentViewModel
    private val mockLiveData = MutableLiveData<StoreResponse>()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = StoreCommonFragmentViewModel(
            retrofitRepository
        )
        mockData()
    }

    @Test
    fun getListOfArticleNullTest() {
        Mockito.`when`(retrofitRepository.fetchStoreInformation()).thenReturn(mockLiveData)
        Assert.assertNotNull(viewModel.getStoreInfo().value)
    }

    private fun mockData() {
        val gson = Gson()
        val weatherResponse = gson.fromJson(sample, StoreResponse::class.java)
        mockLiveData.value = weatherResponse

    }

    val sample =
        "{\n" +
                "  \"StoreInfo\": \"NewKart\",\n" +
                "  \"Description\": \"The best Kart in the world\"\n" +
                "}\n" +
                "\n" +
                "  "

}