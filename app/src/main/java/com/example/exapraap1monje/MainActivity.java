package com.example.exapraap1monje;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {//Inicia clase Main
    private Button btnMenu;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Inicia metodo oncreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Integración de XML a JAVA
        btnMenu = findViewById(R.id.btnMenu);
        etPassword = findViewById(R.id.etPassword);


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirMenu = new Intent(getApplicationContext(), MenuEmpleados.class);
                startActivity(abrirMenu);
            }
        });

    }//Termina metodo oncreate

}//Termina clase Main