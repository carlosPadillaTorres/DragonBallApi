package org.utl.apiidgs901

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.utl.dragonballapi.ApiService
import org.utl.dragonballapi.RespuestaPersonaje


class PersonajeViewModel: ViewModel() {
    var state by mutableStateOf(PersonajeState())
    var response: List<Personaje> by mutableStateOf(listOf())
        private set
    init{
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            val apiService = ApiService.getInstance()
            val personajeList: RespuestaPersonaje = apiService.getPersonajes()
            println("Personajes: $personajeList")

            response = personajeList.items!!

            state = state.copy(
                isLoading = false,
                personajes = response
            )
        }
    }
}