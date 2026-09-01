package com.example.harry_potter_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harry_potter_quiz.screen.QuizContainer
import com.example.harry_potter_quiz.screen.ResultScreen
import com.example.harry_potter_quiz.ui.theme.HarrypotterquizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterQuizApp()
        }
    }
}

@Composable
fun HarryPotterQuizApp() {
    val navController = rememberNavController()

    HarrypotterquizTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

}

object AppRoutes {
    const val QUIZ = "quiz"
    const val RESULT = "result"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.QUIZ,
        modifier = modifier
    ) {
        composable(AppRoutes.QUIZ) {
            QuizContainer(
                onShowResult = {
                    navController.navigate(AppRoutes.RESULT)
                }
            )
        }

        composable(AppRoutes.RESULT) {
            ResultScreen(
                onRestartQuiz = {
                    navController.navigate(AppRoutes.QUIZ) {
                        popUpTo(AppRoutes.QUIZ) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HarryPotterQuizAppPreview() {
    HarryPotterQuizApp()
}
