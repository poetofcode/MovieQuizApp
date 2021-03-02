package ru.poetofcode.whatahorror

import dagger.Module
import dagger.Provides
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.IView
import ru.poetofcode.whatahorror.usecase.MovieProvider
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideRandomHelper(): RandomHelper {
        return FakeRandomHelper()
    }

    @Provides
    fun provideView(): IView {
        return FakeView()
    }

    @Provides
    @Singleton
    fun provideMovieProvider(): MovieProvider {
        return FakeMovieProvider()
    }

}