package com.mcmouse88.koin_guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mcmouse88.koin_guide.ui.theme.KoinGuideTheme
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    private val hello by inject<String>(named("hello"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinGuideTheme {
                val viewModel = getViewModel<MainViewModel>()
                viewModel.doNetWorkCall(hello)
            }
        }
    }
}