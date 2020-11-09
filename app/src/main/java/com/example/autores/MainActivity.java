package com.example.autores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputBook;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookYear;
    private TextView bookDescription;
    private ImageView Image;
    //TODO: Lista dinámica donde se almacenarán todos los libros
    public static List<Libro> libros;
    //TODO: Inicializar los Datos
    private void initializaData(){
        libros = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start the Variables
        inputBook = (EditText) findViewById(R.id.inputBook);
        bookTitle= (TextView) findViewById(R.id.Title);
        bookAuthor = (TextView) findViewById(R.id.Author);
        bookYear = (TextView) findViewById(R.id.Year);
        bookDescription = (TextView) findViewById(R.id.Description);
        Image = (ImageView) findViewById(R.id.imgBook);

        initializaData();

        final RecyclerView rv = (RecyclerView)findViewById(R.id.RvBooks);
        rv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(false);
        rv.setLayoutManager(linearLayoutManager);

        Adaptador adaptador = new Adaptador(libros,this);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Detalle.class);
                intent.putExtra("titulo", libros.get(rv.getChildAdapterPosition(v)).Title);
                intent.putExtra("autor", libros.get(rv.getChildAdapterPosition(v)).Author);
                intent.putExtra("anio", libros.get(rv.getChildAdapterPosition(v)).Year);
                intent.putExtra("descripcion", libros.get(rv.getChildAdapterPosition(v)).Description);
                startActivity(intent);
            }
        });

        rv.setAdapter(adaptador);
    }

    public void searchBook(View view) {
        if (inputBook.getText().toString().equals("")) {
            libros.clear();
        } else {
            String Busqueda = inputBook.getText().toString();
            new ConseguirLibro(bookTitle, bookAuthor, bookYear, bookDescription).execute(Busqueda);
            super.onRestart();

        }
    }
}