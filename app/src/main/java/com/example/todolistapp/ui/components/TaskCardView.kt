package com.example.todolistapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.R
import com.example.todolistapp.model.Task
import com.example.todolistapp.navigation.MyApp
@Composable
fun TaskCard(
    task: Task,
    onTaskCardClicked: (Task) -> Unit,
) {

    Box(modifier = Modifier
        .padding(bottom = 5.dp)
        .height(150.dp)
        .fillMaxWidth(),
        ) {
        Card(
            modifier = Modifier.clickable(onClick = ({onTaskCardClicked(task)})),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(0.7.dp, Color(0xFFDCE1EF)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
        ) {
            Column(modifier = Modifier.padding(11.dp)) {
                Text(
                    text = "${task.title}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D101C)
                    ),
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "${task.description}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFF6E7591),
                        lineHeight = 18.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.height(35.dp)
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.folder),
                        contentDescription = "folder",
                        modifier = Modifier
                            .padding(end = 7.dp, top = 8.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = "${task.project}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0xFF6E7591)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.timer),
                        contentDescription = "timer",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = "${task.deadline}",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF6E7591)
                        ),
                    )

                    if (task.completed) CompletedView() else OnGoingView()

                }

            }
        }
    }
}