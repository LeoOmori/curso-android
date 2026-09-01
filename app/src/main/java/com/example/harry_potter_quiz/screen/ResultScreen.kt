package com.example.harry_potter_quiz.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harry_potter_quiz.ui.theme.HarrypotterquizTheme

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    rightRounds: Int = 0,
    totalRounds: Int = 5,
    onRestartQuiz: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF0D0F16))
            .padding(20.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Hogwarts Quiz",
            color = Color(0xFFCCCC4A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Resultado",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "$rightRounds/$totalRounds",
                color = Color(0xFFCCCC4A),
                fontSize = 68.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(68.dp),
            onClick = onRestartQuiz,
            colors = ButtonDefaults.buttonColors(
                containerColor =
                    Color(0xFFCCCC4A)
            ),
        ) {
            Text(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Jogar novamente",
            )
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ResultScreenPreview() {
    HarrypotterquizTheme {
        ResultScreen()
    }
}
