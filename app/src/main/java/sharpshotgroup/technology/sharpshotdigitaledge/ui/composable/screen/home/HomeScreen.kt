package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.ServiceViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val state by viewModel.servicesState.collectAsState()
    KGMXDContentWrapper(
        dataState = state,
        dataPopulated = {
            HomeContent((state as DataUiState.Populated).data, modifier, onNavigateToServiceDetails)
        },
        dataEmpty = { Text("No services available") },
    )
}

@Composable
private fun HomeContent(services: List<ServiceModel>, modifier: Modifier, onSelect: (Int) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 14.dp)) {
                Text("Sharpshot Digital Edge", style = MaterialTheme.typography.headlineMedium)
                Text("Sharper decisions. Stronger digital systems.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("NEXT AVAILABLE", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                    Text("Tomorrow · 09:30", color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.titleLarge)
                    Text("Start with a focused 60-minute discovery call.", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
        item {
            Text("Consulting areas", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 20.dp))
            LazyRow(
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(services.map { it.category }.distinct()) { category ->
                    Card {
                        Column(Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Outlined.Category, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(30.dp))
                            Text(category, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
        item {
            Text("Services & solutions", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 20.dp))
        }
        items(services, key = { it.id }) { service ->
            ServiceCard(service, onSelect)
        }
        item {
            Text("Selected work", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 20.dp))
            Text("Cloud migration roadmap · Security uplift · Operations automation", modifier = Modifier.padding(horizontal = 20.dp))
        }
        item {
            Text("Knowledge base", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 20.dp))
            Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("How to build an IT strategy that survives change")
                Text("Five signals your cloud costs need attention")
                Text("Cyber resilience: from checklist to operating habit")
            }
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceModel, onSelect: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clickable { onSelect(service.id) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(112.dp, 132.dp),
            )
            Column(Modifier.padding(14.dp).weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(service.name, style = MaterialTheme.typography.titleMedium)
                Text(service.description, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("From £${service.price.toInt()}", color = MaterialTheme.colorScheme.primary)
                    Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(20.dp)) {
                        Text("Book Now", modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                    }
                }
            }
        }
    }
}
