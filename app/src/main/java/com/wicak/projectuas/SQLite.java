package com.wicak.projectuas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bioskop.db";
    private static final int DATABASE_VERSION = 1;

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableFilm = "CREATE TABLE IF NOT EXISTS film (" +
                "                                      id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                                      judul_film VARCHAR(255) NOT NULL, " +
                "                                      waktu_tayang VARCHAR(5) NOT NULL, " +
                "                                      harga FLOAT NOT NULL, " +
                "                                      created_by VARCHAR(255) NOT NULL " +
                "                                      );";
        db.execSQL(createTableFilm);

        String createTableTiket = "CREATE TABLE IF NOT EXISTS tiket (" +
                "                                      id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                                      id_film INT NOT NULL, " +
                "                                      nomor_seat VARCHAR(2) NOT NULL, " +
                "                                      status BOOLEAN NOT NULL, " +
                "                                      created_by VARCHAR(255) NOT NULL, " +
                "                                      FOREIGN KEY (id_film)" +
                "                                          REFERENCES film (id)" +
                "                                      );";
        db.execSQL(createTableTiket);

        String insertFilmData = "INSERT INTO film (id, judul_film, waktu_tayang, harga, created_by)" +
                "                                   VALUES(1, 'Avengers: Civil War', '18:00', 50000, '18101080'),"+
                "                                         (2, 'Avengers: End Game', '18:00', 50000, '18101080');";
        db.execSQL(insertFilmData);

        String insertTiketData = "INSERT INTO tiket (id, id_film, nomor_seat, status, created_by)" +
                "                                     VALUES(1, 1, 'A3', 1, '18101080'),"+
                "                                           (2, 1, 'A4', 1, '18101080'),"+
                "                                           (3, 1, 'A7', 1, '18101080'),"+
                "                                           (4, 1, 'A8', 1, '18101080'),"+
                "                                           (5, 1, 'B4', 1, '18101080'),"+
                "                                           (6, 2, 'A4', 1, '18101080'),"+
                "                                           (7, 2, 'A7', 1, '18101080'),"+
                "                                           (8, 2, 'A8', 1, '18101080'),"+
                "                                           (9, 2, 'B4', 1, '18101080');";
        db.execSQL(insertTiketData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
