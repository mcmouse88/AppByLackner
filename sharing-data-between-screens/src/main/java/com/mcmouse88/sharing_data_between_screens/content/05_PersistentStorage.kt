package com.mcmouse88.sharing_data_between_screens.content

import android.content.SharedPreferences
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.edit
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Composable
fun PersistentStorageSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<PersistentViewModel1>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            ScreenPersistentOne(
                onNavigateToScreenTwo = {
                    viewModel.saveSession()
                    navController.navigate("screen2")
                }
            )
        }

        composable("screen2") {
            val viewModel = hiltViewModel<PersistentViewModel2>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            ScreenPersistentTwo()
        }
    }
}

@Composable
fun ScreenPersistentOne(
    onNavigateToScreenTwo: () -> Unit
) {
    Button(onClick = { onNavigateToScreenTwo.invoke() }) {
        Text(text = "Go to the next screen")
    }
}

@Composable
fun ScreenPersistentTwo() {
    Text(text = "Hello World!")
}

data class Session(
    val user: User,
    val token: String,
    val expireAt: Long
)

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
)

interface SessionCache {
    fun saveSession(session: Session)
    fun getActiveSession(): Session?
    fun clearSession()
}

@Singleton
class SessionCacheImpl @Inject constructor(
    private val preferences: SharedPreferences
) : SessionCache {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val adapter = moshi.adapter(Session::class.java)

    override fun saveSession(session: Session) {
        preferences.edit {
            putString("session", adapter.toJson(session))
        }
    }

    override fun getActiveSession(): Session? {
        val json = preferences.getString("session", null) ?: return null
        return adapter.fromJson(json)
    }

    override fun clearSession() {
        preferences.edit {
            remove("session")
        }
    }
}

@HiltViewModel
class PersistentViewModel1 @Inject constructor(
    private val sessionCache: SessionCache
) : ViewModel() {
    val session get() = sessionCache.getActiveSession()

    fun saveSession() {
        sessionCache.saveSession(
            session = Session(
                user = User(
                    firstName = "John",
                    lastName = "Doe",
                    email = "johndoe@gmail.com"
                ),
                token = "token",
                expireAt = 1234567890
            )
        )
    }
}

@HiltViewModel
class PersistentViewModel2 @Inject constructor(
    private val sessionCache: SessionCache
) : ViewModel() {
    val session get() = sessionCache.getActiveSession()
}