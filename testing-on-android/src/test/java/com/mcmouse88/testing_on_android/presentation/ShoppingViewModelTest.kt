package com.mcmouse88.testing_on_android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mcmouse88.testing_on_android.others.Constants
import com.mcmouse88.testing_on_android.others.Status
import com.mcmouse88.testing_on_android.repository.FakeShoppingRepositoryAndroidTest
import com.mcmouse88.testing_on_android.utils.MainCoroutineRule
import com.mcmouse88.testing_on_android.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setUp() {
        viewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
    }

    @Test
    fun `insert shopping item with empty field returns error`() {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name returns error`() {
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price returns error`() {
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem("name", "5", string)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount returns error`() {
        viewModel.insertShoppingItem("name", "5999999999999999999999", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input returns success`() {
        viewModel.insertShoppingItem("name", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `set current image add to currentImageLiveData`() {
        viewModel.setCurrentImageUrl("image")
        val value = viewModel.currentImageUrl.getOrAwaitValue()
        Truth.assertThat(value).isEqualTo("image")
    }
}