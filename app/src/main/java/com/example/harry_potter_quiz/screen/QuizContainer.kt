package com.example.harry_potter_quiz.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harry_potter_quiz.R
import com.example.harry_potter_quiz.ui.theme.HarrypotterquizTheme


enum class House(value: String) {
    GRYFFINDOR("Grifinória"),
    SLYTHERIN ("Sonserina"),
    RAVENCLAW ("Corvinal"),
    HUFFLEPUFF("Lufa Lufa")
}

@Composable
fun QuizContainer(
    modifier: Modifier = Modifier,
    onShowResult: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF0D0F16))
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Hogwarts Quiz",
                    textAlign = TextAlign.Left,
                    color = Color(0xFFC9C948),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    )
                Text(
                    text = "Rodada 4/10",
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                modifier = Modifier.size(width = 180.dp, height = 260.dp),
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Imagem do personagem",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Harry Potter",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Qual é a sua casa?",
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(85.dp))

            for (value in House.entries) {
                CasaBotao(value = value, onShowResult)
                Spacer(modifier = Modifier.height(15.dp))
            }

        }

    }
}

@Composable
fun CasaBotao(value: House, onShowResult: () -> Unit) {
    Button(
        onClick = {onShowResult()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF171E25)
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(width = 0.5.dp, color = Color(0xFF3F475E)),
        modifier = Modifier.width(300.dp).height(50.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = when(value){
                                House.GRYFFINDOR -> Color.Red
                                House.SLYTHERIN -> Color.Green
                                House.RAVENCLAW -> Color.Blue
                                House.HUFFLEPUFF -> Color.Yellow
                            },
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    fontSize = 15.sp,
                    text = value.toString(),
                )

            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun QuizContainerPreview() {
    HarrypotterquizTheme {
        QuizContainer()
    }
}
