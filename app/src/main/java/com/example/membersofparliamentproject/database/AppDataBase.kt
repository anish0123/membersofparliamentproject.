package com.example.membersofparliamentproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * This is the database class.
 */

@Database(
    entities = [ParliamentMembers::class, ParliamentMembersExtra::class, ParliamentMembersLikeAndComment::class, ParliamentMembersLike::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun parliamentMembersDao(): ParliamentMembersDao
    abstract fun parliamentMembersExtraDao(): ParliamentMembersExtraDao
    abstract fun parliamentMembersLikeAndCommentDao(): ParliamentMembersLikeAndCommentDao
    abstract fun parliamentMembersLikeDao(): ParliamentMembersLikeDao

    companion object {

        /**
         * Creating an instance of the database
         */
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}