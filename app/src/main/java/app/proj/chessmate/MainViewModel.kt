package app.proj.chessmate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _score = mutableStateOf(0)

    val score : MutableState<Int>
        get() = _score

    fun increaseScore(){
        _score.value ++
    }

}