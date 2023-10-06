package com.example.challenge2_binar.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repositry(application: Application)  {

    private val mCartDao: SimpleChartDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SimpleDatabase.getInstance(application)
        mCartDao = db.simpleChartDao
    }

    fun insert(simpleChart: SimpleChart) {
        executorService.execute { mCartDao.insert(simpleChart) }
    }

    fun deleteById(chartId: Long) {
        executorService.execute { mCartDao.deleteById(chartId) }
    }

    fun update(simpleChart: SimpleChart) {
        executorService.execute { mCartDao.update(simpleChart) }
    }

    fun getAllCartItems(): LiveData<List<SimpleChart>> = mCartDao.getAllCartItems()

    fun calculateTotalPrice() : LiveData<Int> {
        return mCartDao.getAllCartItems().map { cartItems ->
            var total = 0
            for (cartItem in cartItems) {
                total += cartItem.itemPrice * cartItem.itemQuantity
            }
            total
        }
    }
}