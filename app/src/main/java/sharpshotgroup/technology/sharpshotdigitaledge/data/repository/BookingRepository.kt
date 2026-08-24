package sharpshotgroup.technology.sharpshotdigitaledge.data.repository

import sharpshotgroup.technology.sharpshotdigitaledge.data.dao.BookingDao
import sharpshotgroup.technology.sharpshotdigitaledge.data.entity.BookingEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BookingRepository(
    private val bookingDao: BookingDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    fun observeAll(): Flow<List<BookingEntity>> {
        return bookingDao.observeAll()
    }

    suspend fun save(bookingEntity: BookingEntity) {
        return withContext(coroutineDispatcher) {
            bookingDao.save(bookingEntity)
        }
    }

    suspend fun deleteByBookingNumber(bookingNumber: String) {
        withContext(coroutineDispatcher) {
            bookingDao.deleteByBookingNumber(bookingNumber)
        }
    }
}