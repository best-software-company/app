package pjIII.simova.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pjIII.simova.R;
import pjIII.simova.Service;
import pjIII.simova.pojo.Casa;
import pjIII.simova.pojo.Usuario;
import pjIII.simova.ui.profile.ProfileAdapter;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        RecyclerView profilesView = (RecyclerView) root.findViewById(R.id.profile_list);


        List<Casa> casas = new ArrayList<>();
        casas.add(new Casa());
        casas.add(new Casa());
        casas.add(new Casa());
        HomeAdapter homeAdapter = new HomeAdapter(casas);
        Log.i("homeAdapter",Boolean.toString(homeAdapter == null));

        profilesView.setAdapter(homeAdapter);
        profilesView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        final String id = "a";

        AsyncTask<Void, Void, Casa[]> task = new AsyncTask<Void, Void, Casa[]>() {
            @Override
            protected Casa[] doInBackground(Void... voids) {
                Service service1 = new Service();
                Casa[] houses = service1.getHouses(id);
                return houses;
            }
            @Override
            protected void onPostExecute(Casa[] result){
                super.onPostExecute(result);
                if (result != null){
                    Log.i("RESUlT",Integer.toString(result.length));
                }else {
                    Log.i("RESUlT","false");
                }
            }
        };

        task.execute();

        final SearchView searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String input = searchView.getQuery().toString();
                AsyncTask<Void, Void, Casa[]> task = new AsyncTask<Void, Void, Casa[]>() {
                    @Override
                    protected Casa[] doInBackground(Void... voids) {
                        Service service1 = new Service();
                        Casa[] houses = service1.getHouses(input);
                        return houses;
                    }
                    @Override
                    protected void onPostExecute(Casa[] result){
                        super.onPostExecute(result);
                        if (result != null){
                            Log.i("RESUlT",Integer.toString(result.length));
                        }else {
                            Log.i("RESUlT","false");
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
}