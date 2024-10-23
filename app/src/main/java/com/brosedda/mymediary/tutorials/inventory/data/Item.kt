package com.brosedda.mymediary.tutorials.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val price: Double,
    val quantity: Int
)
