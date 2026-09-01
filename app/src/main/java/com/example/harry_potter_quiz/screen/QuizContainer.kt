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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import com.example.harry_potter_quiz.R
import com.example.harry_potter_quiz.helper.RetroFitInstance
import com.example.harry_potter_quiz.model.Personagem
import com.example.harry_potter_quiz.ui.theme.HarrypotterquizTheme

enum class House(val displayName: String) {
    GRYFFINDOR("Grifinória"),
    SLYTHERIN ("Sonserina"),
    RAVENCLAW ("Corvinal"),
    HUFFLEPUFF("Lufa Lufa")
}

@Composable
fun QuizContainer(
    modifier: Modifier = Modifier,
    onShowResult: (rightRounds: Int, totalRounds: Int) -> Unit = { _, _ -> }
) {
    val totalRounds = 5
    var rightRounds by remember { mutableStateOf(0) }
    var currentRound by remember { mutableStateOf(1) }
    var character by remember { mutableStateOf<Personagem?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(currentRound) {
        isLoading = true
        character = try {
            RetroFitInstance.api.getCharacter(index = (0..24).random())
        } catch (e: Exception) {
            println(e)
            null
        }
        isLoading = false
    }

    fun nextRound(value: House) {
        val updatedRightRounds = if (character?.getHouse() == value) {
            rightRounds + 1
        } else {
            rightRounds
        }

        rightRounds = updatedRightRounds

        if (currentRound < totalRounds) {
            currentRound++
        } else {
            onShowResult(updatedRightRounds, totalRounds)
        }
    }

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
                    text = "Rodada $currentRound/$totalRounds",
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            if (character?.image?.isNotBlank() == true) {
                AsyncImage(
                    modifier = Modifier.size(width = 180.dp, height = 260.dp),
                    model = character?.image,
                    contentDescription = "Imagem do personagem",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier.size(width = 180.dp, height = 260.dp),
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Imagem do personagem",
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = character?.fullName ?: "Carregando...",
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
                CasaBotao(
                    value = value,
                    enabled = !isLoading && character != null,
                    onClick = { nextRound(value) }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

        }

    }
}

@Composable
fun CasaBotao(value: House, enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
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
                    text = value.displayName,
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
