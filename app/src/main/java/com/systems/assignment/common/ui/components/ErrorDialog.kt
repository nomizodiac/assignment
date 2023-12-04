package com.systems.assignment.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.systems.assignment.common.ui.theme.Shapes
import com.systems.assignment.common.ui.theme.TextColor
import com.systems.assignment.R

@Composable
fun ErrorDialog(
    title: String = "",
    message: String,
    onDismiss: () -> Unit = {},
    onConfirmButton: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }
    if(showDialog) {
        Dialog(onDismissRequest = {
            showDialog = false
            onDismiss.invoke()
        }) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White)
            ) {
                val (textTitle, textMessage, buttonConfirm) = createRefs()
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        color = TextColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .fillMaxWidth()
                            .constrainAs(textTitle) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(textMessage.top)
                            })
                }
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = TextColor,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(
                            top = if (title.isEmpty()) 25.dp else 10.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                        .wrapContentSize(align = Alignment.Center)
                        .fillMaxWidth()
                        .constrainAs(textMessage) {
                            if (title.isEmpty()) {
                                top.linkTo(parent.top)
                            } else {
                                top.linkTo(textTitle.bottom)
                            }
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(buttonConfirm.top, margin = 25.dp)
                        })
                Button(
                    onClick = {
                        showDialog = false
                        onConfirmButton()
                    },
                    colors = ButtonDefaults.buttonColors(contentColor = Color.Yellow),
                    shape = Shapes.large,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(55.dp)
                        .constrainAs(buttonConfirm) {
                            top.linkTo(textMessage.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    content = {
                        Text(
                            text = stringResource(id = R.string.ok).uppercase(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = White,
                        )
                    }
                )
            }
        }
    }
}