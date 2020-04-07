package com.shonen.ukr.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity {

    private EditText edtLogInEmail, edtLogInPassword;
    private Button btnLogIn, btnSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edtLogInEmail = findViewById(R.id.edtLogInEmail);
        edtLogInPassword = findViewById(R.id.edtLogInPassword);

        btnLogIn = findViewById(R.id.btnLogInInActivity);
        btnSingUp = findViewById(R.id.btnSinUpFromLO);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ParseUser.logInInBackground(edtLogInEmail.getText().toString(), edtLogInPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LogIn.this, user.getUsername() + " was log in", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(LogIn.this, "Incorrect username or password, please try again", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }

                        }
                    });
                } catch (Exception e) {
                    FancyToast.makeText(LogIn.this, "Login or password is wrong, please try again" + e.toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }
        });
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SingUP.class);
                startActivity(intent);
            }
        });

    }
}
