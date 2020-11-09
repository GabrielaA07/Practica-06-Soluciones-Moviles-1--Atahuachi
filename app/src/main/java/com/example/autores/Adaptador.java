package com.example.autores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.LibroViewHolder> implements
        View.OnClickListener {
    private View.OnClickListener listener;

    List<Libro> libros;
    Context context;

    Adaptador(List<Libro> personas, Context context){
        this.libros = personas;
        this.context = context;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public Adaptador.LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_main, parent,false);
        LibroViewHolder lvh = new LibroViewHolder(view);
        view.setOnClickListener(this);

        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.LibroViewHolder holder, int position) {
        holder.titulo.setText(libros.get(position).Title);
        holder.autor.setText(libros.get(position).Author);
        holder.anio.setText(libros.get(position).Year);
        holder.descripcion.setText(libros.get(position).Description);
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
    public static class LibroViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView titulo;
        TextView autor;
        TextView anio;
        TextView descripcion;
        ImageView fotoLibro;

        LibroViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvLibro);
            titulo = (TextView)itemView.findViewById(R.id.Title);
            autor = (TextView)itemView.findViewById(R.id.Author);
            anio = (TextView)itemView.findViewById(R.id.Year);
            descripcion = (TextView)itemView.findViewById(R.id.Description);
        }

    }
}
