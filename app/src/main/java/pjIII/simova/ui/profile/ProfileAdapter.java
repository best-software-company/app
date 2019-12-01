package pjIII.simova.ui.profile;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pjIII.simova.R;
import pjIII.simova.pojo.Usuario;
import pjIII.simova.ui.ViewHolder;

public class ProfileAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Usuario> users;

    public ProfileAdapter(List<Usuario> users) {
        this.users = users;
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
        return users.size();
    }
}
