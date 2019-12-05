package pjIII.simova.ui.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pjIII.simova.R;
import pjIII.simova.Service;
import pjIII.simova.pojo.Usuario;

public class ProfileFragment extends Fragment {

    View root;
    List<Usuario> usuarios;
    RecyclerView profilesView;
    ProfileAdapter profileAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);
        usuarios = new ArrayList<>();
        profilesView = (RecyclerView) root.findViewById(R.id.profile_list);
        profileAdapter = new ProfileAdapter(usuarios,this.getContext());
        profilesView.setAdapter(profileAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        profilesView.setLayoutManager(linearLayoutManager);

        AsyncTask<Void, Void, Usuario> task = new AsyncTask<Void, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(Void... voids) {
                Service service1 = new Service();
                Usuario user = service1.getUser(null);
                return user;
            }
            protected void onPostExecute(Usuario result){
                usuarios.add(result);
                profileAdapter.notifyDataSetChanged();

            }

        };

        task.execute();

        final SearchView searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String input = searchView.getQuery().toString();
                AsyncTask<Void, Void, Usuario[]> task = new AsyncTask<Void, Void, Usuario[]>() {
                    @Override
                    protected Usuario[] doInBackground(Void... voids) {
                        Service service1 = new Service();
                        Usuario user = service1.getUser(input);
                        Usuario[] users = new Usuario[1];
                        if(user != null){
                            users[0] = user;
                        }else{
                            users = service1.getUsers(input);
                        }
                        return users;
                    }
                    @Override
                    protected void onPostExecute(Usuario[] result){
                        super.onPostExecute(result);
                            profilesView.removeAllViews();
                            usuarios.clear();
                            for (Usuario u : result){
                                if(!u.getIdUsuario().equals(Service.id))
                                usuarios.add(u);
                                System.out.println(u);
                            }
                    }
                };

                task.execute();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if((usuarios.size() == 1) && (usuarios.get(0).getIdUsuario().equals(Service.id))){
            AsyncTask<Void, Void, Usuario> task = new AsyncTask<Void, Void, Usuario>() {
                @Override
                protected Usuario doInBackground(Void... voids) {
                    Service service1 = new Service();
                    Usuario user = service1.getUser(null);
                    return user;
                }
                protected void onPostExecute(Usuario result){
                    usuarios.clear();
                    usuarios.add(result);
                    System.out.println(result.toString());
                    profileAdapter.notifyDataSetChanged();

                }
            };

            task.execute();

        }

    }
}