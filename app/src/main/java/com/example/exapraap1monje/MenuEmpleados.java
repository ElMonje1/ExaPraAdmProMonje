package com.example.exapraap1monje;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MenuEmpleados extends AppCompatActivity {
    private EditText numero, nombre, puesto, dias, sueldo;
    private Button alta, consulta, elimina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_empleados);

        numero = findViewById(R.id.etnNumero);
        nombre = findViewById(R.id.etNombre);
        puesto = findViewById(R.id.etPuesto);
        dias = findViewById(R.id.etnDias);
        sueldo = findViewById(R.id.etSueldo);



    }

    //Metodo para insertar registro tabla articulo
    public void consultaProductos(View view){//Inicia metodo
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos se reescribible

        //se asigna una variable para busqueda y consulta por campo distintivo
        String codigoConsulta = numero.getText().toString();
        //Cursor recorre los campos d euna tabla hasta encontralo por campo distintivo
        Cursor fila = bd.rawQuery("SELECT nombre,puesto,dias, sueldo from articulo where cod="+codigoConsulta,null);

        if(fila.moveToFirst()){//si condicion es verdadera, es decir, encontro un campo y sus datos
            nombre.setText(fila.getString(0));
            puesto.setText(fila.getString(1));
            dias.setText(fila.getString(2));
            sueldo.setText(fila.getString(3));
            Toast.makeText(this,"Registro encontrado de forma EXITOSA",Toast.LENGTH_LONG).show();
        }else{//condicion falsa si no encontro un registro
            Toast.makeText(this,"No existe Articulo con ese Codigo\nVerifica",Toast.LENGTH_LONG).show();
            bd.close();
        }
    }//Termina metodo

    //Metodo para borrar registro de la tabla articulo
    public void borrarProductos(View view){//Inicia metodo
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos  reescribible

        //se asigna variable para busqueda por campo distitivo caodigo producto
        String codigoBaja = numero.getText().toString();
        //Se genera instrtuccion SQL para que se elimine el registro de producto
        int c = bd.delete("articulo","cod="+codigoBaja,null);
        if(c==1){
            Toast.makeText(this,"Registro eliminado de BD exitoso\nVerifica Consulta",Toast.LENGTH_LONG).show();
            //Limpia cajas de texto
            this.numero.setText("");
            this.nombre.setText("");
            this.puesto.setText("");
            this.dias.setText("");
        }else{
            Toast.makeText(this,"Error\nNo existe Articulo con ese codigo",Toast.LENGTH_LONG).show();
        }

    }//Termina metodo

    //Metodo para editar registro de la tabla articulo
    public void editarProductos(View view){//Inicia metodo
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos  reescribible

        //se declaran variables que vienen desde formulario sus datos
        String Codigo = numero.getText().toString();
        String Descripcion = nombre.getText().toString();
        String Ubicacion = puesto.getText().toString();
        String Existencia = dias.getText().toString();

        //se genera un contenedor para almacenar los valores anteriores
        ContentValues registro = new ContentValues();
        registro.put("cod",Codigo);
        registro.put("descripcion",Descripcion);
        registro.put("ubicacion",Ubicacion);
        registro.put("existencia",Existencia);

        //Se crea la variable que contine la instruccion SQL encargada de modificar y almacenar valor 1 si edito
        int cant = bd.update("articulo",registro,"cod="+Codigo,null);
        bd.close();
        if(cant==1) {//condicion si realizo modificacion
            Toast.makeText(this,"Registro actualizado de forma correcta",Toast.LENGTH_LONG).show();
            //Limpia cajas de texto
            this.numero.setText("");
            this.nombre.setText("");
            this.puesto.setText("");
            this.dias.setText("");
        }else {//contrario a no modificacion
            Toast.makeText(this,"Error\nNo se modifico registro",Toast.LENGTH_LONG).show();
        }

    }//Termina metodo

}