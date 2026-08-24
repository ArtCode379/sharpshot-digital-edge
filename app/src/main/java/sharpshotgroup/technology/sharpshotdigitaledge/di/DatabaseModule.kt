package sharpshotgroup.technology.sharpshotdigitaledge.di

import androidx.room.Room
import sharpshotgroup.technology.sharpshotdigitaledge.data.database.KGMXDDatabase
import org.koin.dsl.module

private const val DB_NAME = "kgmxd_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = KGMXDDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<KGMXDDatabase>().bookingDao()}

}