package ru.poetofcode.whatahorror

import dagger.Component
import ru.poetofcode.whatahorror.presentation.GameFragment
import ru.poetofcode.whatahorror.presentation.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun injectMainActivity(activity: MainActivity)

    fun injectGameFragment(fragment: GameFragment)

}