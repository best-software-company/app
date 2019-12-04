package pjIII.simova;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

//RegistroActivity.java
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import pjIII.simova.pojo.Usuario;

public class ProfileEditActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mData;
    private EditText mDescricao;
    private TextView mPontuacao;
    private String mGender;
    private Button mEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        Serializable user = getIntent().getSerializableExtra("User");
        final RadioButton mFeminino;
        final RadioButton mMasculino;
        final Usuario newUser = new Usuario();

        mName = (EditText) findViewById(R.id.editName);
        mData = (EditText) findViewById(R.id.editData);
        mDescricao = (EditText) findViewById(R.id.editDescrição);
        mPontuacao = (TextView) findViewById(R.id.pontos);
        mFeminino = (RadioButton) findViewById(R.id.radioF);
        mMasculino = (RadioButton) findViewById(R.id.radioM);
        mEditar = (Button) findViewById(R.id.buttonSalvar);

        mFeminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFeminino.isChecked()) {
                    mGender = "feminino";
                }
            }

        });
        mMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMasculino.isChecked()) {
                    mGender = "masculino";
                }
            }
        });

        mEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* newUser.setIdUsuario(mUser.getText().toString());
                newUser.setSenha(mSenha.getText().toString());
                newUser.setNome(mName.getText().toString());
                newUser.setTelefone(mPhone.getText().toString());
                newUser.setEmail(mEmail.getText().toString());
                newUser.setData(mNascimento.getText().toString());
                newUser.setGenero(mGender);
                newUser.setIdCasa(0);
                newUser.setPontos(0);
                newUser.setPerfil("");

                Service service = new Service();
                AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(service,newUser);
                execute.execute();*/

            }
        });

    }

    private class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private Service service;
        private String isValidCredentials;
        private Usuario user;
        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(Service service, Usuario user) {
            this.service = service;
            this.user = user;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                service.registerUser(user);
                //isValidCredentials = service.authUser(username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            // Register Success
            /*if (isValidCredentials == "true"){
                progressBar.setVisibility(GONE);
                Toast.makeText(getApplicationContext(), "Bem vindo!", Toast.LENGTH_LONG).show();
                goToTasksActivity();
            }else {// Login Failure
                progressBar.setVisibility(GONE);
                Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
            }*/
        }


    }
}
