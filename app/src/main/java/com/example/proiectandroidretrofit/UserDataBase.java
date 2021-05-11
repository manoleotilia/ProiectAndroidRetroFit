package com.example.proiectandroidretrofit;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class,DepozitDB.class},version = 2)
public abstract class UserDataBase extends RoomDatabase {
    private static final String DB_NAME = "userdb";
    private static UserDataBase instance;
    public abstract UserDao userDao();
    public abstract DepozitDBDao depozitDao();
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `depozite` (`valoare_depozit` REAL NOT NULL, "+"  `depozit_id` INTEGER NOT NULL PRIMARY KEY,"+"`user_id` INTEGER NOT NULL, "
                    +"`luni` INTEGER NOT NULL, "+" `moneda` TEXT , "+
                    "`suma_incasata` REAL NOT NULL, "+" `dobanda` INTEGER NOT NULL )");
        }
    };

    public synchronized static UserDataBase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, UserDataBase.class, DB_NAME).addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }


}
