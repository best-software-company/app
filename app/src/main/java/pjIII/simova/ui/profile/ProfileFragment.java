package pjIII.simova.ui.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import pjIII.simova.R;
import pjIII.simova.Service;
import pjIII.simova.pojo.Usuario;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        final SearchView searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String input = searchView.getQuery().toString();
                AsyncTask<Void, Void, Usuario[]> task = new AsyncTask<Void, Void, Usuario[]>() {
                    @Override
                    protected Usuario[] doInBackground(Void... voids) {
                        Service service1 = new Service();
                        Usuario[] users = service1.getUsers(input);
                        return users;
                    }
                    @Override
                    protected void onPostExecute(Usuario[] result){
                        super.onPostExecute(result);
                        if (result != null){
                            Log.i("RESUlT","true");
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