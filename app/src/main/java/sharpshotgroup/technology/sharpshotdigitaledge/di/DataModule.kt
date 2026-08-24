package sharpshotgroup.technology.sharpshotdigitaledge.di

import sharpshotgroup.technology.sharpshotdigitaledge.data.repository.BookingRepository
import sharpshotgroup.technology.sharpshotdigitaledge.data.repository.KGMXDOnboardingRepo
import sharpshotgroup.technology.sharpshotdigitaledge.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        KGMXDOnboardingRepo(
            kgmxdOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}