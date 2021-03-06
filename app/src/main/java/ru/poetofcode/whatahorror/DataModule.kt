package ru.poetofcode.whatahorror

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import ru.poetofcode.whatahorror.data.LocalMovieProvider
import ru.poetofcode.whatahorror.helper.RandomHelper
import ru.poetofcode.whatahorror.usecase.GameLogic
import ru.poetofcode.whatahorror.usecase.MovieProvider
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Provides
    fun provideMovieProvider(@NonNull gson: Gson): LocalMovieProvider {
        return LocalMovieProvider(context.assets.open("movies.json"), gson)
    }

    @Singleton
    @Provides
    fun provideRandHelper(): RandomHelper {
        return RandomHelper()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideGameLogic(
        @NotNull movieProvider: LocalMovieProvider,
        @NotNull randomHelper: RandomHelper
    ): GameLogic {

        return GameLogic(movieProvider, randomHelper)
    }

}