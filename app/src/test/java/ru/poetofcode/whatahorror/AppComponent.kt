package ru.poetofcode.whatahorror

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun injectGameLogicTest(gameLogicTest: GameLogicTest)

}