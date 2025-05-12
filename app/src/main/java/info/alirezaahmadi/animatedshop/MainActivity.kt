package info.alirezaahmadi.animatedshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import info.alirezaahmadi.animatedshop.navigation.BottomNavigation
import info.alirezaahmadi.animatedshop.navigation.NavGraph
import info.alirezaahmadi.animatedshop.ui.component.AppTopBar
import info.alirezaahmadi.animatedshop.ui.component.DrawerContent
import info.alirezaahmadi.animatedshop.ui.component.MainDrawer
import info.alirezaahmadi.animatedshop.ui.theme.AnimatedShopTheme
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            AnimatedShopTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val coroutineScope = rememberCoroutineScope()
                    val openDrawer ={ coroutineScope.launch { drawerState.open() } }
                    val closeDrawer ={ coroutineScope.launch { drawerState.close() } }
                    BackHandler(enabled = drawerState.isOpen) { closeDrawer.invoke() }
                    MainDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            DrawerContent(
                                closeDrawer = {closeDrawer.invoke()},
                                navHostController = navHostController,
                                mainViewModel = mainViewModel
                            )
                        }
                    ) {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize(),
                            containerColor = Color(0xffFCF3EC),
                            bottomBar = { BottomNavigation(navHostController = navHostController) },
                            topBar = {
                                AppTopBar(
                                    navHostController = navHostController,
                                    mainViewModel = mainViewModel,
                                    openDrawer = { openDrawer.invoke()}
                                )
                            }
                        ) { innerPadding ->
                            NavGraph(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            )
                        }
                    }

                }

            }
        }
    }
}