package sharpshotgroup.technology.sharpshotdigitaledge.data.repository

import sharpshotgroup.technology.sharpshotdigitaledge.data.datastore.KGMXDOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class KGMXDOnboardingRepo(
    private val kgmxdOnboardingStoreManager: KGMXDOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return kgmxdOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            kgmxdOnboardingStoreManager.setOnboardedState(state)
        }
    }
}