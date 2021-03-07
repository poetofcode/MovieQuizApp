package ru.poetofcode.whatahorror

import org.junit.*
import org.mockito.Mockito
import org.mockito.Mockito.times
import ru.poetofcode.whatahorror.service.DaggerTestAppComponent
import ru.poetofcode.whatahorror.service.FakeRandomHelper
import ru.poetofcode.whatahorror.service.TestDataModule
import ru.poetofcode.whatahorror.usecase.AnswerResult
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.Question
import javax.inject.Inject

class GameLogicTest {

    // Why do you need @set? - https://stackoverflow.com/a/52342219
    @set:Inject
    var gameLogic: GameLogic? = null

    @set:Inject
    var randHelper: FakeRandomHelper? = null

    @Before
    fun setUp() {
        val appComponent = DaggerTestAppComponent.builder()
            .testDataModule(TestDataModule())
            .build()

        appComponent.injectGameLogicTest(this)
    }

    @Test
    fun `Check that ask() then reply() working as expected`() {
        Assert.assertNotNull(gameLogic)
        val view = gameLogic!!.gameView

        gameLogic?.ask()

        Mockito.verify(gameLogic!!.gameView)?.showQuestion(Question(
            "Из какого фильма этот монстр?",
            listOf("http://test-server.com/image-1.png"),
            listOf("film-1", "film-2", "film-3", "film-4"),
            "film-1",
            AnswerResult.RESULT_NONE
        ))

        gameLogic?.reply("film-1")

        Mockito.verify(view)?.markVariantAsRight("film-1")
        Mockito.verify(view)?.showResult(1, 8)
        // Mockito.verifyNoMoreInteractions(view)
    }

    @Ignore
    @Test
    fun `Check that ask() then reply() working as expected - second time ask`() {
        Assert.assertNotNull(gameLogic)
        val view = gameLogic!!.gameView

        gameLogic!!.ask()
        gameLogic!!.reply("wrong-ans")

        randHelper?.nextStep()     // Pushing first index
        gameLogic?.ask()

        Mockito.verify(view)?.showQuestion(Question(
            "Из какого фильма этот монстр?",
            listOf("http://test-server.com/image-5.png"),
            listOf("film-5", "film-6", "film-7", "film-8"),
            "film-5",
            AnswerResult.RESULT_NONE
        ))

        gameLogic?.reply("film-8")

        Mockito.verify(view)?.markVariantAsRight("film-5")
        Mockito.verify(view)?.markVariantAsWrong("film-8")
        Mockito.verify(view, times(2))?.showResult(2, 8)
        // Mockito.verifyNoMoreInteractions(view)
    }

    @After
    fun tearDown() {
        gameLogic = null
    }

}