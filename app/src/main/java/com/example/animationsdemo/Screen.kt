package com.example.animationsdemo

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Screen() {


    Column {
        val isVisible = remember { mutableStateOf(false) }
        val isVisible2 = remember { mutableStateOf(false) }
        val isVisible3 = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Cyan),
        ) {
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInHorizontally(initialOffsetX = { it }),
                exit = slideOutHorizontally(targetOffsetX = { it })
            ) {
                Text(text = "Something", fontSize = 18.sp, modifier = Modifier.padding(end = 5.dp))
            }
            AnimatedVisibility(
                visible = isVisible2.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text("Something else", fontSize = 18.sp, modifier = Modifier.padding(end = 5.dp))
            }
            AnimatedVisibility(
                visible = isVisible3.value,
                enter = expandIn(expandFrom = Alignment.Center),
                exit = shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                Text("Something more", fontSize = 18.sp, modifier = Modifier.padding(end = 5.dp))
            }
        }
        Row {
            Button(onClick = { isVisible.value = !isVisible.value }) {
                Text("Slide stuff", fontSize = 15.sp)
            }
            Button(onClick = { isVisible2.value = !isVisible2.value }) {
                Text("Fade stuff", fontSize = 15.sp)
            }
            Button(onClick = { isVisible3.value = !isVisible3.value }) {
                Text("Expand stuff", fontSize = 15.sp)
            }
        }
        Divider(Modifier.padding(vertical = 3.dp))


        var isBig by remember { mutableStateOf(false) }
        var isRound by remember { mutableStateOf(false) }
        var isColored by remember { mutableStateOf(false) }
        val size by animateDpAsState(
            targetValue = if (isBig) 150.dp else 50.dp,
            animationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)
        )
        val cornerRad by animateIntAsState(
            targetValue = if (isRound) 50 else 0
        )
        val color by animateColorAsState(
            targetValue = if (isColored) Color.Green else Color.Magenta,
            animationSpec = tween(500)
        )


        val shape = RoundedCornerShape(cornerRad)

        Box(
            Modifier
                .size(size)
                .clip(shape)
                .background(color)
                .border(width = 3.dp, color = Color.Black, shape = shape)
        )
        Button(onClick = { isBig = !isBig; isRound = !isRound; isColored = !isColored }) {
            Text("BigBoy")
        }
        Divider(Modifier.padding(vertical = 3.dp))


        var isBig2 by remember { mutableStateOf(false) }
        val transition = updateTransition(
            targetState = isBig2,
            label = null)
        val size2 by transition.animateDp(
            transitionSpec = { spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow) },
            label = "ThingSize",
            targetValueByState = {
                if (it) 150.dp else 50.dp
            })
        val cornerRad2 by transition.animateInt(
            label = "ThingCornerRadius",
            targetValueByState = {
                if (it) 50 else 0
            })
        val color2 by transition.animateColor(
            transitionSpec = { tween(500) },
            label = "ThingColor",
            targetValueByState = {
                if (it) Color.Green else Color.Magenta
            })
        val shape2 = RoundedCornerShape(cornerRad2)

        Box(
            Modifier
                .size(size2)
                .clip(shape2)
                .background(color2)
                .border(width = 3.dp, color = Color.Black, shape = shape2)
        )
        Button(onClick = { isBig2 = !isBig2 }) {
            Text("Smarter BigBoy")
        }
        Divider(Modifier.padding(vertical = 3.dp))


        val transition2 = rememberInfiniteTransition()
        val color3 by transition2.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
            )
        )

        Box(
            Modifier
                .size(150.dp)
                .background(color3))
    }
}