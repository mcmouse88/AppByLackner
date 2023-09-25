package com.mcmouse88.testing_on_android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.data.local.ShoppingItem
import com.mcmouse88.testing_on_android.getOrAwaitValue
import com.mcmouse88.testing_on_android.launchFragmentInHiltContainer
import com.mcmouse88.testing_on_android.repository.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class AddShoppingItemFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val taskExecutor = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: TestShoppingFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_navigateUp() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.pressBack()

        Mockito.verify(navController).navigateUp()
    }

    @Test
    fun clickShoppingItem_navigateToToImagePickFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.iv_shopping_item)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
        )
    }

    @Test
    fun clickInsertIntoDb_shoppingItemInsertedIntoDb() {
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_shopping_item_name))
            .perform(ViewActions.replaceText("shopping item"))

        Espresso.onView(ViewMatchers.withId(R.id.et_shopping_item_amount))
            .perform(ViewActions.replaceText("5"))

        Espresso.onView(ViewMatchers.withId(R.id.et_shopping_item_price))
            .perform(ViewActions.replaceText("5.5"))

        Espresso.onView(ViewMatchers.withId(R.id.btn_add_shopping_item))
            .perform(ViewActions.click())

        Truth.assertThat(testViewModel.shoppingItems.getOrAwaitValue())
            .contains(
                ShoppingItem(
                    name = "shopping item",
                    amount = 5,
                    price = 5.5f,
                    imageUrl = ""
                )
            )
    }
}