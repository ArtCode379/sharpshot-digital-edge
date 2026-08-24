package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.data.model.ServiceModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.shared.KGMXDContentWrapper
import sharpshotgroup.technology.sharpshotdigitaledge.ui.state.DataUiState
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.ServiceDetailsViewModel

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val state by viewModel.serviceState.collectAsState()
    LaunchedEffect(serviceId) { viewModel.observeServiceById(serviceId) }
    KGMXDContentWrapper(
        dataState = state,
        dataPopulated = { Details((state as DataUiState.Populated).data, modifier, onNavigateToCheckout) },
        dataEmpty = { Text("Service details are unavailable") },
    )
}

@Composable
private fun Details(service: ServiceModel, modifier: Modifier, onBook: (Int) -> Unit) {
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        AsyncImage(
            model = service.imageUrl,
            contentDescription = service.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(280.dp),
        )
        Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(20.dp)) {
                Text(service.category, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
            }
            Text(service.name, style = MaterialTheme.typography.titleLarge)
            Text("From £${service.price.toInt()} · ${service.durationMinutes} min", color = MaterialTheme.colorScheme.primary)
            Text(service.description)
            Text("What’s included", style = MaterialTheme.typography.titleMedium)
            service.features.forEach { feature ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.CheckCircle, null, tint = MaterialTheme.colorScheme.secondary)
                    Text(feature)
                }
            }
            Text("Available times", style = MaterialTheme.typography.titleMedium)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(service.availableTime.orEmpty()) { slot ->
                    Card { Text(slot.toString(), modifier = Modifier.padding(12.dp)) }
                }
            }
            Button(onClick = { onBook(service.id) }, modifier = Modifier.fillMaxWidth()) {
                Text("Book Consultation")
            }
        }
    }
}
