package org.utl.apiidgs901

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PersonajeScreen(personajeViewModel: PersonajeViewModel) {
    val context = LocalContext.current
    val state = personajeViewModel.state

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    Tarjeta(state.personajes)
}

@Composable
fun Tarjeta(personajes: List<Personaje>){
    LazyColumn {

        items(personajes) { personaje ->
            MyPersonajes(personaje)
        }
    }
}


@Composable
fun MyPersonajes(personaje: Personaje){
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation =  CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Blue)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ImagenPersonaje(personaje.name,personaje.image)
            Personajes(personaje)
        }
    }
}

@Composable
fun Personaje(name: String, color: Color, style: TextStyle, lines: Int=Int.MAX_VALUE) {
    Text(text = name, color = color, style = style, maxLines = lines)
}

@Composable
fun Personajes(personaje: Personaje) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 10.dp, top = 8.dp, bottom = 12.dp)
            .clickable {
                expanded = !expanded
            }
    ) {
        Personaje(personaje.name,
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.typography.bodyMedium)

        Personaje(personaje.description,
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.typography.bodyLarge,
            if (expanded) Int.MAX_VALUE else 3)
    }
}

@Composable
fun imagenHeroe(imageName: String) {
    val context = LocalContext.current
    println("Image Name:"+imageName)
    val imageResId = remember(imageName) {
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
    Image(
        painter = painterResource(id=imageResId),
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun ImagenPersonaje(nombre: String,imagenUrl: String){
    AsyncImage(
        model = imagenUrl,
        contentDescription = nombre,
        modifier = Modifier
            .padding(10.dp)
            .size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun InfoPersonaje(personaje: Personaje){
    Column{
        TextoGenerico(personaje.name)
        TextoGenerico(personaje.description)
    }
}
@Composable
fun TextoGenerico(texto: String){
    Text(text = texto)
}