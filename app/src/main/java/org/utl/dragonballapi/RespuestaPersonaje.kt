package org.utl.dragonballapi

import org.utl.apiidgs901.Personaje

data class RespuestaPersonaje (
    val items: List<Personaje>? = null,
    val meta: Meta? = null,
    val links: Links? = null
    )

@kotlinx.serialization.Serializable
data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)

@kotlinx.serialization.Serializable
data class Links(
    val first: String,
    val previous: String,
    val next: String,
    val last: String
)