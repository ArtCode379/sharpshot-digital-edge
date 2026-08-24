package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.data.entity.BookingEntity
import sharpshotgroup.technology.sharpshotdigitaledge.ui.state.DataUiState
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.CheckoutViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun CheckoutScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToBookingsScreen: () -> Unit,
) {
    val bookingState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    var phone by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val complete = viewModel.customerFirstName.isNotBlank() && viewModel.customerLastName.isNotBlank() &&
        viewModel.customerEmail.isNotBlank() && phone.isNotBlank() && selectedDate.isNotBlank()

    if (bookingState is DataUiState.Populated) {
        CheckoutDialog((bookingState as DataUiState.Populated<BookingEntity>).data, selectedDate, onNavigateToBookingsScreen)
    }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Book a consultation", style = MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Consultation service #$serviceId", style = MaterialTheme.typography.titleMedium)
                Text("A consultant will review your request before the session.")
            }
        }
        OutlinedTextField(viewModel.customerFirstName, viewModel::updateCustomerFirstName, label = { Text("First name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(viewModel.customerLastName, viewModel::updateCustomerLastName, label = { Text("Last name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            viewModel.customerEmail,
            viewModel::updateCustomerEmail,
            label = { Text("Email") },
            isError = emailInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            phone,
            { phone = it },
            label = { Text("Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            selectedDate,
            { selectedDate = it },
            readOnly = true,
            label = { Text("Preferred date") },
            trailingIcon = { Icon(Icons.Outlined.CalendarMonth, null) },
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
        )
        OutlinedTextField(notes, { notes = it }, label = { Text("Project notes") }, minLines = 3, modifier = Modifier.fillMaxWidth())
        Button(onClick = { viewModel.placeBooking(serviceId) }, enabled = complete, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm Booking")
        }
    }

    if (showDatePicker) {
        val pickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        pickerState.selectedDateMillis?.let { millis ->
                            selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.of("UTC")).toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                        }
                        showDatePicker = false
                    },
                ) { Text("Select") }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } },
        ) { DatePicker(pickerState) }
    }
}
