package ru.poetofcode.whatahorror.service

import dagger.Component
import ru.poetofcode.whatahorror.GameLogicTest
import javax.inject.Singleton

@Singleton
@Component(modules = [TestDataModule::class])
interface TestAppComponent {

    fun injectGameLogicTest(gameLogicTest: GameLogicTest)

}