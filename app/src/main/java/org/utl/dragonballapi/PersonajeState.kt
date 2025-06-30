package org.utl.apiidgs901

data class PersonajeState (
        val personajes:List<Personaje> = emptyList(),
        val isLoading: Boolean = false
)