package com.example.fitnesshub.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.fitnesshub.ui.theme.NavBarBackground
import com.example.fitnesshub.ui.theme.NavBarIndicator
import com.example.fitnesshub.ui.theme.NavBarSelectedContent
import com.example.fitnesshub.ui.theme.NavBarUnselectedContent

@Composable
fun AppBottomBar(
    currentDestination: NavDestination?,
    onItemSelected: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = NavBarBackground,
        tonalElevation = 0.dp
    ) {
        topLevelDestinations.forEach { destination ->
            val selected = currentDestination
                ?.hierarchy
                ?.any { it.route == destination.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = {
                    Text(
                        text = destination.label,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavBarSelectedContent,
                    selectedTextColor = NavBarSelectedContent,
                    indicatorColor = NavBarIndicator,
                    unselectedIconColor = NavBarUnselectedContent,
                    unselectedTextColor = NavBarUnselectedContent
                )
            )
        }
    }
}
