package com.example.challenge2_binar.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge2_binar.database.Repository
import com.example.challenge2_binar.database.SimpleChart

class KeranjangViewModel (application: Application) : ViewModel() {

    private val repository: Repository = Repository(application)

    val allCartItems: LiveData<List<SimpleChart>> = repository.getAllItems()

    private val _cartItemLiveData = MutableLiveData<SimpleChart>()
    val cartItemLiveData: LiveData<SimpleChart> = _cartItemLiveData

    var totalPrice: LiveData<Int> = repository.totalPrice()

    fun delete(chartId: Long) { repository.deleteById(chartId) }

    fun update(simpleChart: SimpleChart) {
        repository.update(simpleChart)
        _cartItemLiveData.value = simpleChart
    }
}