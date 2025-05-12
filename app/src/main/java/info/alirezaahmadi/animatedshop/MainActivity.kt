package info.alirezaahmadi.animatedshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
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
                    val drawerState = rememberDrawerState(DrawerValue.Open)
                    MainDrawer(
                        drawerState = drawerState,
                        drawerContent ={
                            DrawerContent(
                                closeDrawer = {},
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
                            topBar = {AppTopBar(navHostController=navHostController, mainViewModel = mainViewModel)}
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