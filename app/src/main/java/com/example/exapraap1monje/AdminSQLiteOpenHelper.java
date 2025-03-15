package com.example.exapraap1monje;
//librerias de conexion a BD SQLite
import  android.database.sqlite.SQLiteDatabase;//apertura base
import  android.database.sqlite.SQLiteDatabase.CursorFactory;//instrucciones SQL
import  android.database.sqlite.SQLiteOpenHelper;//ayuda con el control y administración de los datos
import  android.content.Context;//Produccion

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {//inicia clase AdminSQLiteOpenHelper
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea tabla en base de datos administracion
        db.execSQL("create table articulo (cod integer primary key, descripcion text, ubicacion text, existencia integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists articulo");
        db.execSQL("create table articulo (cod integer primary key, descripcion text, ubicacion text, existencia integer )");
    }//inicia clase

}//Termina clase AdminSQLiteOpenHelper