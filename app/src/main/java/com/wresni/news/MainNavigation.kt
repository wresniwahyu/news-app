package com.wresni.news

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wresni.news.screen.all.AllNewsScreen
import com.wresni.news.screen.detail.NewsDetailScreen
import com.wresni.news.screen.headline.HeadlineNewsScreen
import com.wresni.news.screen.source.SourceScreen

sealed class Screens(val route: String) {
    object AllNews : Screens("all")
    object HeadlineNews : Screens("headline")
    object Source : Screens("source")
    object Detail : Screens("detail")
}

@Composable
fun MainNavigation(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screens.AllNews.route) { AllNewsScreen(navController = navController) }
        composable(Screens.HeadlineNews.route) { HeadlineNewsScreen(navController = navController) }
        composable(Screens.Source.route) { SourceScreen() }
        composable(Screens.Detail.route) { NewsDetailScreen(navController = navController) }
    }
}