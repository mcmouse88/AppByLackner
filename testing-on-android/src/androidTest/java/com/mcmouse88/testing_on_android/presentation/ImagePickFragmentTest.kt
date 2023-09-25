package com.mcmouse88.testing_on_android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.adapters.ImageAdapter
import com.mcmouse88.testing_on_android.getOrAwaitValue
import com.mcmouse88.testing_on_android.launchFragmentInHiltContainer
import com.mcmouse88.testing_on_android.repository.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
class ImagePickFragmentTest {

    @get:Rule
    val taskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickImage_navigateUpAndSetImageUrl() {
        val navController = Mockito.mock(NavController::class.java)
        val imageUrl = "TEST"
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<ImagePickFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            imageAdapter.images = listOf(imageUrl)
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Mockito.verify(navController).navigateUp()
        Truth.assertThat(testViewModel.currentImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)
    }
}