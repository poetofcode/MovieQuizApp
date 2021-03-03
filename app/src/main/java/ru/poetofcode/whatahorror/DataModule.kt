package ru.poetofcode.whatahorror

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ru.poetofcode.whatahorror.data.LocalMovieProvider
import ru.poetofcode.whatahorror.helper.RandomHelper
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Singleton
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

}