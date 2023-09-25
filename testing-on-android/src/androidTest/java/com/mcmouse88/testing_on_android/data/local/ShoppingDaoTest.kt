package com.mcmouse88.testing_on_android.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.mcmouse88.testing_on_android.di.ShoppingItemDbTest
import com.mcmouse88.testing_on_android.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @[Inject ShoppingItemDbTest]
    lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.getShoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest {
        val item = ShoppingItem(
            id = 1,
            name = "name",
            amount = 1,
            price = 1f,
            imageUrl = "url"
        )

        dao.insertShoppingItem(item)

        val shoppingItems = dao.observeAllShoppingItem().getOrAwaitValue()

        Truth.assertThat(shoppingItems).contains(item)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val item = ShoppingItem(
            id = 1,
            name = "name",
            amount = 1,
            price = 1f,
            imageUrl = "url"
        )

        dao.insertShoppingItem(item)
        dao.deleteShoppingItem(item)

        val shoppingItems = dao.observeAllShoppingItem().getOrAwaitValue()

        Truth.assertThat(shoppingItems).doesNotContain(shoppingItems)
    }

    @Test
    fun observeTotalPriceSum() = runTest {
        val item1 = ShoppingItem(
            id = 1,
            name = "name",
            amount = 2,
            price = 10f,
            imageUrl = "url"
        )

        val item2 = ShoppingItem(
            id = 2,
            name = "name",
            amount = 4,
            price = 5.5f,
            imageUrl = "url"
        )

        val item3 = ShoppingItem(
            id = 3,
            name = "name",
            amount = 0,
            price = 100f,
            imageUrl = "url"
        )

        dao.insertShoppingItem(item1)
        dao.insertShoppingItem(item2)
        dao.insertShoppingItem(item3)

        val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()

        Truth.assertThat(totalPriceSum).isEqualTo(2 * 10f + 4 * 5.5f)
    }
}