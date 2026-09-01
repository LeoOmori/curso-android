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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    const val RESULT = "result/{rightRounds}/{totalRounds}"

    fun result(rightRounds: Int, totalRounds: Int): String {
        return "result/$rightRounds/$totalRounds"
    }
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
                onShowResult = { rightRounds, totalRounds ->
                    navController.navigate(AppRoutes.result(rightRounds, totalRounds))
                }
            )
        }

        composable(
            route = AppRoutes.RESULT,
            arguments = listOf(
                navArgument("rightRounds") { type = NavType.IntType },
                navArgument("totalRounds") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val rightRounds = backStackEntry.arguments?.getInt("rightRounds") ?: 0
            val totalRounds = backStackEntry.arguments?.getInt("totalRounds") ?: 5

            ResultScreen(
                rightRounds = rightRounds,
                totalRounds = totalRounds,
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
