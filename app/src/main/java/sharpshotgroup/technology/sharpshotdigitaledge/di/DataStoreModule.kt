package sharpshotgroup.technology.sharpshotdigitaledge.di

import sharpshotgroup.technology.sharpshotdigitaledge.data.datastore.KGMXDOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { KGMXDOnboardingPrefs(androidContext()) }
}