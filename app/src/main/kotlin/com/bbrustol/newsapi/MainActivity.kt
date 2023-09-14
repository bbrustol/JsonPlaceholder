package com.bbrustol.newsapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.IsLoading
import com.bbrustol.features.ShowError
import com.bbrustol.features.ShowGenericError
import com.bbrustol.features.home.*
import com.bbrustol.features.home.HomeViewModel.*
import com.bbrustol.features.home.HomeViewModel.UiState.*
import com.bbrustol.features.home.compose.EmptyStateScreen
import com.bbrustol.features.home.compose.UsersScreen
import com.bbrustol.features.home.model.UsersModel
import com.bbrustol.uikit.compose.scaffold.TopBar
import com.bbrustol.uikit.theme.NewsApiTheme
import com.bbrustol.uikit.utils.NavScreensType
import dagger.hilt.android.AndroidEntryPoint
import com.bbrustol.uikit.R as UIKIT_R

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Start() }
    }

    @Composable
    fun Start(viewModel: HomeViewModel = viewModel()) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        RenderState(uiState = uiState, viewModel::fetchHeadline)
    }

    @Composable
    fun RenderState(uiState: UiState, onRetryAction: () -> Unit) {
        when (uiState) {
            Idle -> { /*nothing to do*/
            }

            Loading -> IsLoading()
            is Failure -> ShowFailure(uiState, onRetryAction)
            is Catch -> ShowGenericError(
                uiState.message ?: stringResource(UIKIT_R.string.catch_generic_message),
                onRetryAction
            )

            is Success -> SetupView(uiState.usersModel)
        }
    }

    @Composable
    private fun ShowFailure(uiState: Failure, onRetryAction: () -> Unit) {
        ShowError(uiState.code, uiState.message, onRetryAction)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun SetupView(usersModel: List<UsersModel>) {
        NewsApiTheme {
            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    TopBar(
                        usersModel[0].name
                    )
                }
            ) {
                NavSystem(usersModel, navController)
            }
        }
    }

    @Composable
    fun NavSystem(usersModel: List<UsersModel>, navController: NavHostController) {
        Column {
            Spacer(modifier = Modifier.height(56.dp))

            NavHost(
                navController = navController,
                startDestination = NavScreensType.HEADLINE.name
            ) {
                composable(NavScreensType.HEADLINE.name) {
                    if (usersModel.isNotEmpty()) UsersScreen(usersModel, navController)
                    else EmptyStateScreen()
                }

//                val position = "position"
//                composable(
//                    route = NavScreensType.HEADLINE_DETAILS.name + "/{$position}",
//                    arguments = listOf(navArgument(position) {
//                        type = NavType.IntType
//                    })
//                ) { navBackStackEntry ->
//                    DetailsScreen(usersModel[navBackStackEntry.arguments!!.getInt(position)])
//
//                }
            }
        }
    }
}
