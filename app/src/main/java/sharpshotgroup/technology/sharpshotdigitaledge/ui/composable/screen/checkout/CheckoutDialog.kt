package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.checkout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import sharpshotgroup.technology.sharpshotdigitaledge.data.entity.BookingEntity

@Composable
fun CheckoutDialog(
    booking: BookingEntity,
    selectedDate: String,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onConfirm,
        title = { Text("Consultation confirmed") },
        text = {
            Text("Session #${booking.bookingNumber} is reserved for $selectedDate. Your consultant will be waiting in the online conference or at the agreed office address at the appointed time.")
        },
        confirmButton = { TextButton(onClick = onConfirm) { Text("View bookings") } },
    )
}
