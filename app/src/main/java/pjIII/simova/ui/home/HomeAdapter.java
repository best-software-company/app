package pjIII.simova.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pjIII.simova.HouseEditActivity;
import pjIII.simova.R;
import pjIII.simova.pojo.Casa;
import pjIII.simova.ui.ViewHolder;

public class HomeAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Casa> casas;
    private Context context;

    public HomeAdapter(List<Casa> casas) {
        this.casas = casas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
        Usuario usuario = users.get(position);
        InputStream stream = usuario.getFoto().getBinaryStream();
        Bitmap bmp = BitmapFactory.decodeStream(stream);
        holder.image.setImageBitmap(bmp);
         */

        final Casa casa = casas.get(position);
        holder.image.setImageResource(R.drawable.house);
        holder.name.setText(casa.getNome());
        holder.description.setText(casa.getDescricao());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, HouseEditActivity.class);
                intent.putExtra("Home",casa);
                context.startActivity(intent);

            }
        });


    }
    @Override
    public int getItemCount() {
        return casas.size();
    }
}
