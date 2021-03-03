package ru.poetofcode.whatahorror

import org.junit.Assert
import org.junit.Test
import ru.poetofcode.whatahorror.data.LocalMovieProvider

class LocalMovieProviderTest {

    @Test
    fun `Loaded movies from file are correct`() {
        val provider = LocalMovieProvider("/test_movies.json")

        Assert.assertEquals(2, provider.count())
        Assert.assertEquals("Cobra", provider.movie(1).name)
    }

}