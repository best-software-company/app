package pjIII.simova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class RegisterHouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_house);
        Serializable user = getIntent().getSerializableExtra("User");
        if (user != null){
            System.out.println("AQUII   " + user.toString());
        }
        finish();

    }
}
