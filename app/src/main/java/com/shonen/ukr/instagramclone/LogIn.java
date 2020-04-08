package com.shonen.ukr.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLogInEmail, edtLogInPassword;
    private Button btnLogIn, btnSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edtLogInEmail = findViewById(R.id.edtLogInEmail);
        edtLogInPassword = findViewById(R.id.edtLogInPassword);

        btnSingUp = findViewById(R.id.btnSinUpFromLO);
        btnLogIn = findViewById(R.id.btnLogInInActivity);
        edtLogInPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLogIn);
                }
                return false;
            }
        });
        btnLogIn.setOnClickListener(this);
        btnSingUp.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }


    }

    public void roodLayoutTaped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogInInActivity:
                if (edtLogInEmail.getText().toString().equals("") || edtLogInPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LogIn.this, "Please enter the your e-mail and password ", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();
                } else {
                    try {
                        ParseUser.logInInBackground(edtLogInEmail.getText().toString(), edtLogInPassword.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null && e == null) {
                                    FancyToast.makeText(LogIn.this, user.getUsername() + " was log in", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                    transitionToSocialMediaActivity();
                                } else {
                                    FancyToast.makeText(LogIn.this, "Incorrect username or password, please try again", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                }

                            }
                        });
                    } catch (Exception e) {
                        FancyToast.makeText(LogIn.this, "Login or password is wrong, please try again" + e.toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                }
                break;
            case R.id.btnSinUpFromLO:
                Intent intent = new Intent(LogIn.this, SingUP.class);
                startActivity(intent);
        }

    }
    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(LogIn.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
