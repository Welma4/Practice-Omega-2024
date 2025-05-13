package com.example.todolistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskDescriptionView(taskDescription: String, onTaskDescriptionChange: (String) -> Unit) {

    Column(modifier = Modifier.padding(bottom = 15.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Task description",
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
        )

        TextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFFFFFF),
                unfocusedTextColor = Color(0xFF6E7591),
                focusedContainerColor = Color(0xFFFFFFFF),
                focusedTextColor = Color(0xFF6E7591),
                focusedIndicatorColor = Color(0xFF613BE7)
            ),
            value = taskDescription,
            onValueChange = { newValue ->
                if (newValue.length <= 200) {
                    onTaskDescriptionChange(newValue)
                }
            },
            textStyle = TextStyle(lineHeight = 18.sp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .height(120.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFDCE1EF),
                    shape = RoundedCornerShape(20.dp)
                ),

            )

    }
}