package com.mcmouse88.note_app.feature_note.presentation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.core.app.ApplicationProvider
import com.mcmouse88.note_app.MainActivity
import com.mcmouse88.note_app.di.AppModule
import com.mcmouse88.note_app.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.mcmouse88.note_app.feature_note.presentation.notes.NotesScreen
import com.mcmouse88.note_app.feature_note.presentation.util.Screens
import com.mcmouse88.note_app.ui.theme.NoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import com.mcmouse88.note_app.R
import com.mcmouse88.note_app.core.utils.TestTags
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            NoteAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.NotesScreen.route
                ) {
                    composable(route = Screens.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }

                    composable(
                        route = Screens.AddEditNotesScreen.route +
                        "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(
                            navController = navController,
                            noteColor = color
                        )
                    }
                }
            }
        }
    }

    @Test
    fun saveNewNote_editAfterwards() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Click on FAB to get to add note screen
        composeRule.onNodeWithContentDescription(context.getString(R.string.add_note)).performClick()

        // Enter text in title and content text field
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("test_title")

        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("test_content")


        // Save the new note
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.save_note))
            .performClick()

        // Make sure there is a note in the list with our title and content
        composeRule.onNodeWithText("test_title").assertIsDisplayed()

        // Click on note to edit it
        composeRule.onNodeWithText("test_title").performClick()

        // make sure title and content text field contain note title and content
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .assertTextEquals("test_title")

        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .assertTextEquals("test_content")

        // Add the text "_2" to the title text field
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("2_")

        // Update the note
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.save_note))
            .performClick()

        // Make sure the update was applied to the list
        composeRule.onNodeWithText("2_test_title").assertIsDisplayed()
    }

    @Test
    fun saveNewNotes_orderByTitleDescending() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        for (i in 1..3) {
            // Click on FAB to get to add note screen
            composeRule.onNodeWithContentDescription(context.getString(R.string.add_note)).performClick()

            // Enter text in title and content text field
            composeRule
                .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
                .performTextInput(i.toString())

            composeRule
                .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
                .performTextInput(i.toString())


            // Save the new note
            composeRule
                .onNodeWithContentDescription(context.getString(R.string.save_note))
                .performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription(context.getString(R.string.sort))
            .performClick()

        composeRule
            .onNodeWithContentDescription(context.getString(R.string.title))
            .performClick()

        composeRule
            .onNodeWithContentDescription(context.getString(R.string.descending))
            .performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0]
            .assertTextContains("3")

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1]
            .assertTextContains("2")

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2]
            .assertTextContains("1")
    }
}