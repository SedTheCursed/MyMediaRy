package com.brosedda.mymediary.tutorials.unscramble.ui.test

import com.brosedda.mymediary.tutorials.unscramble.data.MAX_NO_OF_WORDS
import com.brosedda.mymediary.tutorials.unscramble.data.SCORE_INCREASE
import com.brosedda.mymediary.tutorials.unscramble.data.getUnscrambledWord
import com.brosedda.mymediary.tutorials.unscramble.ui.viewModel.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    //thingTested_TriggerOfTest_ResultOfTest
    fun gameViewModel_CorrectWordGuessed_ScoredUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value

        // Assert that checkUserGuess() method updates isGuessedWordWrong correctly.
        assertFalse(currentGameUiState.isGuessedWordWrong)
        // Assert that score is updated correctly
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectGuess = "Cacatoès"

        viewModel.updateUserGuess(incorrectGuess)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        //Assert that score is unchanged
        assertEquals(0, currentGameUiState.score)
        // Assert that checkUserGuess() method updates isGuessedWordWrong correctly.
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }
    
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        assertEquals(1, gameUiState.currentWordCount)
        assertEquals(0, gameUiState.score)
        assertNotEquals(unscrambledWord, gameUiState.currentScrambledWord)
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentUiGameState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentUiGameState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentUiGameState = viewModel.uiState.value
        val lastWordsCount = currentUiGameState.currentWordCount
        viewModel.skipWord()
        currentUiGameState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentUiGameState.score)
        assertEquals(lastWordsCount + 1, currentUiGameState.currentWordCount)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var currentGameUiState = viewModel.uiState.value
        var correctPlayedWord: String
        var expectedScore = 0

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            correctPlayedWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            viewModel.updateUserGuess(correctPlayedWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value

            assertEquals(expectedScore, currentGameUiState.score)
        }

        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertTrue(currentGameUiState.isGameOver)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}