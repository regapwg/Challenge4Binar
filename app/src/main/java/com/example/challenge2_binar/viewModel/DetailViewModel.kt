package com.example.challenge2_binar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge2_binar.produk.MenuList

class DetailViewModel : ViewModel(){
    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    private val _totalPrice = MutableLiveData<Int>()
    val totalPrice: LiveData<Int> = _totalPrice

    private val _selectedItem = MutableLiveData<MenuList>()


    fun initSelectedItem(item: MenuList) {
        _selectedItem.value = item
        _totalPrice.value = item.hargaMenu
    }

    fun incrementCount(){
        _counter.value = _counter.value?.plus(1)
    }

    fun decrementCount(){
        _counter.value?.let {
            if (it > 0){
                _counter.value = _counter.value?.minus(1)
            }
        }
    }

    fun updateTotalPrice() {
        val currentAmount = _counter.value ?: 1
        val selectedItem = _selectedItem.value
        if (selectedItem != null) {
            val totalPrice = selectedItem.hargaMenu * currentAmount
            _totalPrice.value = totalPrice
        }
    }

}