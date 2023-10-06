package com.example.challenge2_binar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SimpleChartDao {
    @Insert
    fun insert(chart: SimpleChart)

    @Query("SELECT * FROM simple_chart_table ORDER BY itemId DESC")
    fun getAllItem(): LiveData<List<SimpleChart>>

    @Delete
    fun delete(chart: SimpleChart)

    @Query("DELETE FROM simple_chart_table WHERE itemId = :itemIdParams")
    fun deleteByItemId(itemIdParams: Long)

    @Update
    fun update(chart: SimpleChart)

    @Query("UPDATE simple_chart_table SET item_quantity = :newQuantity where itemId = :itemIdParams")
    fun  updateQuantityByItemId(newQuantity: Int, itemIdParams: Long)
}