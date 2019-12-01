package pjIII.simova.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pjIII.simova.R;
import pjIII.simova.pojo.Casa;
import pjIII.simova.ui.ViewHolder;

public class HomeAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Casa> casas;

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
            holder.name.setText(usuario.getIdUsuario());
            holder.description.setText(usuario.getNome() +" "+ usuario.getData());
             */
            holder.image.setImageResource(R.drawable.house);
            holder.name.setText("NOME");
            holder.description.setText("DESC");
    }

    @Override
    public int getItemCount() {
        return casas.size();
    }
}
