package sharpshotgroup.technology.sharpshotdigitaledge.di

import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.BookingViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.CheckoutViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.KGMXDOnboardingVM
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.ServiceDetailsViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.ServiceViewModel
import sharpshotgroup.technology.sharpshotdigitaledge.ui.viewmodel.KGMXDSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        KGMXDSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        KGMXDOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}