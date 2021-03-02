package ru.poetofcode.whatahorror

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
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
    fun `Check that ask() then reply() working as expected`() {

        // implement


        Assert.assertNotNull(gameLogic)
    }

    @After
    fun tearDown() {
        gameLogic = null
    }

}