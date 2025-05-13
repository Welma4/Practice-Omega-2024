package com.example.todolistapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WelcomeMenu() {
    val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = "Good morning!",
                style = TextStyle(fontSize = 12.sp, color = Color(0xFF6E7591))
            )
            Text(
                modifier = Modifier.padding(bottom = 40.dp),
                text = currentDate,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
            )
        }
        Image(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "notification",
            modifier = Modifier.size(30.dp)
        )
    }
}