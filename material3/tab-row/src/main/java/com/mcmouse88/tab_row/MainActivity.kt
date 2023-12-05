package com.mcmouse88.tab_row

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.mcmouse88.tab_row.ui.theme.Material3TabRowTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = getString(R.string.home),
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home,
            ),
            TabItem(
                title = getString(R.string.browse),
                unselectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart,
            ),
            TabItem(
                title = getString(R.string.account),
                unselectedIcon = Icons.Outlined.AccountCircle,
                selectedIcon = Icons.Filled.AccountCircle,
            )
        )
        setContent {
            Material3TabRowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectTabIndex by remember {
                        mutableIntStateOf(0)
                    }

                    val pagerState = rememberPagerState {
                        tabItems.size
                    }
                    
                    LaunchedEffect(
                        key1 = selectTabIndex,
                        block = {
                            pagerState.animateScrollToPage(selectTabIndex)
                        }
                    )
                    
                    LaunchedEffect(
                        key1 = pagerState.currentPage,
                        key2 = pagerState.isScrollInProgress,
                        block = {
                            if (!pagerState.isScrollInProgress) {
                                selectTabIndex = pagerState.currentPage
                            }
                        }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        TabRow(selectedTabIndex = selectTabIndex) {
                            tabItems.forEachIndexed { index, tabItem ->
                                Tab(
                                    selected = index == selectTabIndex,
                                    onClick = {
                                        selectTabIndex = index
                                    },
                                    text = {
                                        Text(text = tabItem.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectTabIndex) {
                                                tabItem.selectedIcon
                                            } else {
                                                tabItem.unselectedIcon
                                            },
                                            contentDescription = tabItem.title
                                        )
                                    }
                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) { index ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = tabItems[index].title)
                            }
                        }
                    }
                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)
