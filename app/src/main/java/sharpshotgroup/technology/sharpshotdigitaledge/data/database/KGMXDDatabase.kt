package sharpshotgroup.technology.sharpshotdigitaledge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sharpshotgroup.technology.sharpshotdigitaledge.data.dao.BookingDao
import sharpshotgroup.technology.sharpshotdigitaledge.data.database.converter.Converters
import sharpshotgroup.technology.sharpshotdigitaledge.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KGMXDDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

