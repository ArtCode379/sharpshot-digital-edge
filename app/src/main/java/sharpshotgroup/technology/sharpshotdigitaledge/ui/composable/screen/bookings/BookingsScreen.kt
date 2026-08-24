package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.shared.KGMXDContentWrapper
import sharpshotgroup.technology.sharpshotdigitaledge.ui.state.BookingUiState
import sharpshotgroup.technology.sharpshotdigitaledge.ui.state.DataUiState
import sharpshotgroup.technology.sharpshotdigitaledge.ui.theme.Success
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.BookingViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val state by viewModel.bookingsState.collectAsState()
    var pendingCancellation by remember { mutableStateOf<String?>(null) }

    KGMXDContentWrapper(
        dataState = state,
        dataPopulated = {
            BookingList((state as DataUiState.Populated).data, modifier) { pendingCancellation = it }
        },
        dataEmpty = {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("No bookings yet", style = MaterialTheme.typography.titleLarge)
                Text("Choose a service to schedule your first consultation.")
                Text("Browse services from the Home tab", color = MaterialTheme.colorScheme.primary)
            }
        },
    )

    pendingCancellation?.let { bookingNumber ->
        AlertDialog(
            onDismissRequest = { pendingCancellation = null },
            title = { Text("Cancel this booking?") },
            text = { Text("This consultation will be removed from your upcoming sessions.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(bookingNumber)
                        pendingCancellation = null
                    },
                ) { Text("Confirm", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = { TextButton(onClick = { pendingCancellation = null }) { Text("Keep booking") } },
        )
    }
}

@Composable
private fun BookingList(bookings: List<BookingUiState>, modifier: Modifier, onCancel: (String) -> Unit) {
    LazyColumn(modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Your consultations", style = MaterialTheme.typography.headlineMedium) }
        items(bookings, key = { it.bookingNumber }) { booking ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(booking.serviceName, style = MaterialTheme.typography.titleMedium)
                        Surface(color = Success.copy(alpha = 0.12f), shape = RoundedCornerShape(20.dp)) {
                            Text("Confirmed", color = Success, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                        }
                    }
                    Text(booking.timestamp)
                    Text("Session #${booking.bookingNumber}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    TextButton(onClick = { onCancel(booking.bookingNumber) }) {
                        Text("Cancel", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}
