package com.example.todolistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(Color.White)
            .border(2.dp, Color(0xFFDCE1EF), RoundedCornerShape(36.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { navController.navigate("MainScreen") }, modifier = Modifier.background(
                Color.Transparent)) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { navController.navigate("FoldersScreen") }, modifier = Modifier.background(
                Color.Transparent)) {
                Icon(Icons.Default.List, contentDescription = "List")
            }

        }
    }
}