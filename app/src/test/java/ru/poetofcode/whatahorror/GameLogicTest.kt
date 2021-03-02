package ru.poetofcode.whatahorror

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.IView
import ru.poetofcode.whatahorror.usecase.MovieProvider
import javax.inject.Inject

class GameLogicTest {

    var gameLogic: GameLogic? = null

    // Why do you need @set? - https://stackoverflow.com/a/52342219
    @set:Inject
    var view: IView? = null

    @set:Inject
    var movieProvider: MovieProvider? = null

    @set:Inject
    var randHelper: RandomHelper? = null


    @Before
    fun setUp() {
        val appComponent = DaggerAppComponent.builder()
            .dataModule(DataModule())
            .build()

        appComponent.injectGameLogicTest(this)
        gameLogic = GameLogic(view!!, movieProvider!!, randHelper!!)
    }

    @Test
    fun `Check that ask() then reply() working as expected - first time ask`() {
        Assert.assertNotNull(gameLogic)

        gameLogic?.ask()

        Mockito.verify(view)?.showQuestion(
            "Из какого фильма этот монстр?",
            "http://test-server.com/image.png",
            listOf("no", "yes", "no-too")
        )

        gameLogic?.reply(1)

        Mockito.verify(view)?.markVariantAsRight(1)
        Mockito.verify(view)?.showResult("Вы угадали!")
        Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    fun `Check that ask() then reply() working as expected - second time ask`() {
        Assert.assertNotNull(gameLogic)

        gameLogic?.ask()
        gameLogic?.ask()

        Mockito.verify(view)?.showQuestion(
            "Из какого фильма этот монстр?",
            "http://test-server.com/image-2.png",
            listOf("yes", "no")
        )

        gameLogic?.reply(1)

        Mockito.verify(view)?.markVariantAsRight(0)
        Mockito.verify(view)?.markVariantAsWrong(1)
        Mockito.verify(view)?.showResult("Ответ неверный :(")
        Mockito.verifyNoMoreInteractions(view)
    }

    @After
    fun tearDown() {
        gameLogic = null
    }

}