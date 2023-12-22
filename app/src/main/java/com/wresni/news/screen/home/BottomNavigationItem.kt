package com.wresni.news.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.wresni.news.Screens

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "News",
                icon = Icons.Filled.Home,
                route = Screens.AllNews.route
            ),
            BottomNavigationItem(
                label = "Headlines",
                icon = Icons.Filled.Star,
                route = Screens.HeadlineNews.route
            ),
            BottomNavigationItem(
                label = "Sources",
                icon = Icons.Filled.Info,
                route = Screens.Source.route
            ),
        )
    }
}