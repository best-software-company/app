package pjIII.simova.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pjIII.simova.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView name, description;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.foto);
        name = itemView.findViewById(R.id.nome);
        description = itemView.findViewById(R.id.descricao);

    }
}