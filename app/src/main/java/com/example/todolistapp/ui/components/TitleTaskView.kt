package com.example.todolistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleTaskView(taskTitle: String, onTaskTitleChange: (String) -> Unit) {
    var charactersLimit = 45

    Box(modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)) {
        TextField(
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D101C)
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFFFFFF),
                unfocusedTextColor = Color(0xFF0D101C),
                focusedContainerColor = Color(0xFFFFFFFF),
                focusedTextColor = Color(0xFF0D101C),
                focusedIndicatorColor = Color(0xFF613BE7)
            ),
            value = taskTitle,
            onValueChange = { newValue ->
                if (newValue.length <= charactersLimit) {
                    onTaskTitleChange(newValue)
                }
            },
            modifier = Modifier
                .background(Color.White)
                .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(0.dp))
                .fillMaxWidth()
                .height(90.dp)
        )
        Text(
            text = "${taskTitle.length} / $charactersLimit",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 7.dp),
            style = TextStyle(fontSize = 22.sp, color = Color(0xFF6E7591))
        )
    }
}