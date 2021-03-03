package ru.poetofcode.whatahorror

import org.junit.Assert
import org.junit.Test
import ru.poetofcode.whatahorror.data.LocalMovieProvider

class LocalMovieProviderTest {

    @Test
    fun `Loaded movies from file are correct`() {
        // How to read a test-only file in Android unit test:
        // https://stackoverflow.com/a/29220857

        val fileStream = this.javaClass.getResourceAsStream("/movies.json")
        Assert.assertNotNull(fileStream)

        val provider = LocalMovieProvider(fileStream!!)

        Assert.assertEquals(2, provider.count())
        Assert.assertEquals("Cobra", provider.movie(1).name)
    }

}