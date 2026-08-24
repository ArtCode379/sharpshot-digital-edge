package sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sharpshotgroup.technology.sharpshotdigitaledge.R
import sharpshotgroup.technology.sharpshotdigitaledge.data.model.ServiceModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.shared.KGMXDContentWrapper
import sharpshotgroup.technology.sharpshotdigitaledge.ui.composable.shared.KGMXDEmptyView
import sharpshotgroup.technology.sharpshotdigitaledge.ui.state.DataUiState
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val serviceState by viewModel.serviceState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeServiceById(serviceId)
    }

    ServiceDetailsContent(
        serviceState = serviceState,
        modifier = modifier,
        onNavigateToCheckout = onNavigateToCheckout
    )
}

@Composable
private fun ServiceDetailsContent(
    serviceState: DataUiState<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        KGMXDContentWrapper<ServiceModel>(
            dataState = serviceState,

            dataPopulated = {
                ServicesDetailsPopulated(
                    service = (serviceState as DataUiState.Populated).data,
                    onNavigateToCheckout = onNavigateToCheckout,
                )
            },

            dataEmpty = {
                KGMXDEmptyView(
                    primaryText = stringResource(R.string.kgmxd_services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesDetailsPopulated(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {

}