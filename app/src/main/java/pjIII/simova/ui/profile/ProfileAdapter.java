package pjIII.simova.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pjIII.simova.ProfileEditActivity;
import pjIII.simova.R;
import pjIII.simova.pojo.Usuario;
import pjIII.simova.ui.ViewHolder;

public class ProfileAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Usuario> users;
    private Context context;

    public ProfileAdapter(List<Usuario> users, Context context) {

        this.users = users;
        this.context = context;
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
            final Usuario usuario = users.get(position);
            /*
            InputStream stream = usuario.getFoto().getBinaryStream();
            Bitmap bmp = BitmapFactory.decodeStream(stream);
            holder.image.setImageBitmap(bmp);
             */
            holder.image.setImageResource(R.drawable.house);
            holder.name.setText(usuario.getIdUsuario());
            holder.description.setText(usuario.getNome() +" "+ usuario.getData());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Passa para o usuario para a activity de registro

                    Intent intent = new Intent(context, ProfileEditActivity.class);
                    intent.putExtra("User",usuario);
                    context.startActivity(intent);



                    //Recupera o usuario na activity de registro
                    /*
                    seta os campos com as info do usuario

                    atualiza o usuario no server
                    se deu ok, pra voltar pra tela de perfil:
                    finish();
                    */
                }
            });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
