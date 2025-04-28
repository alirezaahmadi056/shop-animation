package info.alirezaahmadi.animatedshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import info.alirezaahmadi.animatedshop.navigation.NavGraph
import info.alirezaahmadi.animatedshop.ui.theme.AnimatedShopTheme
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel :MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimatedShopTheme {
                CompositionLocalProvider( LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color(0xffFCF3EC)
                    ) { innerPadding ->
                        NavGraph(
                            mainViewModel = mainViewModel,
                            modifier = Modifier.fillMaxSize()
                                .padding(innerPadding)
                        )
                    }
                }

            }
        }
    }
}