package com.example.challenge2_binar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simple_chart_table" )
data class SimpleChart (
    @PrimaryKey(autoGenerate = true)
    var itemId : Long? = null,

    @ColumnInfo(name = "item_name")
    var itemName: String? = null,

    @ColumnInfo(name = "item_quantity")
    var itemQuantity: Int = -1,

    @ColumnInfo(name = "item_image")
    var itemImage: Int,

    @ColumnInfo(name = "item_price")
    var itemPrice: Int,

    @ColumnInfo(name = "total_price")
    var totalPrice: Int,

    ){
    companion object{
        const val  TABLE_NAME = "simple_chart_table"
    }
}