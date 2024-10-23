package com.brossedda.mymediary

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brosedda.mymediary.tutorials.inventory.data.InventoryDatabase
import com.brosedda.mymediary.tutorials.inventory.data.Item
import com.brosedda.mymediary.tutorials.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    private var item1 = Item(1, "Apples", 10.0, 20)
    private var item2 = Item(2, "Bananas", 15.0, 97)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    fun closeDB() {
        inventoryDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(item1, allItems[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updateItemsInDB() = runBlocking {
        val new1 = Item(1, "Apples", 15.0, 25)
        val new2 = Item(2, "Bananas", 5.0, 50)

        addTwoItemsToDb()
        itemDao.update(new1)
        itemDao.update(new2)
        val allItems = itemDao.getAllItems().first()
        assertEquals(new1, allItems[0])
        assertEquals(new2, allItems[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deleteAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)
        val allItems = itemDao.getAllItems().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItem(1)
        assertEquals(item1, item.first())
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }
}