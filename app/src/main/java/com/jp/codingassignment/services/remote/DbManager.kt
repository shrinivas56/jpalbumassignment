package com.jp.codingassignment.services.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jp.codingassignment.services.db.dao.AlbumDao
import com.jp.codingassignment.services.db.entity.Album

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
@Database(entities = [Album::class], version = 1, exportSchema = false)
internal abstract class DbManager: RoomDatabase(), DbInterface {

    abstract fun albumDao(): AlbumDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        private const val NAME = "AppDb"

        @Volatile
        private var INSTANCE: DbManager? = null

        fun getDatabase(context: Context): DbManager {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbManager::class.java,
                    NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

    override suspend fun saveAlbum(albums: List<Album>) {
        for(album:Album in albums){
            albumDao().insertAlbum(album)
        }
    }

    override suspend fun getAlbumFromDb(): List<Album>? {
        return albumDao().getAlbums()
    }
}