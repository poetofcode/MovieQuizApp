package ru.poetofcode.whatahorror

import org.junit.*
import org.mockito.Mockito
import org.mockito.Mockito.times
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.service.DaggerTestAppComponent
import ru.poetofcode.whatahorror.service.FakeRandomHelper
import ru.poetofcode.whatahorror.service.TestDataModule
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.GameView
import ru.poetofcode.whatahorror.usecase.MovieProvider
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

        Mockito.verify(gameLogic!!.gameView)?.showQuestion(
            "Из какого фильма этот монстр?",
            "http://test-server.com/image-1.png",
            listOf("film-1", "film-2", "film-3", "film-4")
        )

        gameLogic?.reply("film-1")

        Mockito.verify(view)?.markVariantAsRight("film-1")
        Mockito.verify(view)?.showResult("Вы угадали!")
        // Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    fun `Check that ask() then reply() working as expected - second time ask`() {
        Assert.assertNotNull(gameLogic)
        val view = gameLogic!!.gameView

        gameLogic!!.ask()
        gameLogic!!.reply("wrong-ans")

        randHelper?.nextStep()     // Pushing first index
        gameLogic?.ask()

        Mockito.verify(view)?.showQuestion(
            "Из какого фильма этот монстр?",
            "http://test-server.com/image-5.png",
            listOf("film-5", "film-6", "film-7", "film-8")
        )

        gameLogic?.reply("film-8")

        Mockito.verify(view)?.markVariantAsRight("film-5")
        Mockito.verify(view)?.markVariantAsWrong("film-8")
        Mockito.verify(view, times(2))?.showResult("Ответ неверный :(")
        // Mockito.verifyNoMoreInteractions(view)
    }

    @After
    fun tearDown() {
        gameLogic = null
    }

}