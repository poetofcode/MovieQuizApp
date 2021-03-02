package ru.poetofcode.whatahorror

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
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
        return Mockito.mock(IView::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieProvider(): MovieProvider {
        return FakeMovieProvider()
    }

}