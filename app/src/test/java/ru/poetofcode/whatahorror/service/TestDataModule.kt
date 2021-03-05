package ru.poetofcode.whatahorror.service

import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import org.mockito.Mockito
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.GameView
import javax.inject.Singleton

@Module
class TestDataModule {

    @Provides
    @Singleton
    fun provideRandomHelper(): FakeRandomHelper {
        return FakeRandomHelper()
    }

    @Provides
    fun provideView(): GameView {
        return Mockito.mock(GameView::class.java)
    }

    @Provides
    fun provideMovieProvider(): FakeMovieProvider {
        return FakeMovieProvider()
    }

    @Provides
    @Singleton
    fun provideGameLogic(
        @NotNull movieProvider: FakeMovieProvider,
        @NotNull randomHelper: FakeRandomHelper,
        @NotNull gameView: GameView): GameLogic
    {
        return GameLogic(movieProvider, randomHelper).apply {
            this.gameView = gameView
        }
    }

}