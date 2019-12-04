package pjIII.simova;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

//RegistroActivity.java
import androidx.appcompat.app.AppCompatActivity;

import pjIII.simova.pojo.Usuario;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mSenha;
    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mNascimento;
    private String mGender;
    private Button mRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        final RadioButton mFeminino;
        final RadioButton mMasculino;
        final Usuario newUser = new Usuario();

        mUser = (EditText) findViewById(R.id.editUser);
        mSenha = (EditText) findViewById(R.id.editPassword);
        mName = (EditText) findViewById(R.id.editName);
        mPhone = (EditText) findViewById(R.id.editTelefone);
        mEmail = (EditText) findViewById(R.id.editEmail);
        mNascimento = (EditText) findViewById(R.id.editData);
        mFeminino = (RadioButton) findViewById(R.id.checkBoxF);
        mMasculino = (RadioButton) findViewById(R.id.checkBoxM);
        mRegistro = (Button) findViewById(R.id.buttonRegistrar);

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

        mRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser.setIdUsuario(mUser.getText().toString());
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
                execute.execute();

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
