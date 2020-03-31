package com.shonen.ukr.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SingUP extends AppCompatActivity {

    private EditText edtName, edtPunchPower, edtPunchSpeed, edtKickPower, edtKickSpeed;
    private Button saveBtn, getAllDatabtn;
    private TextView getDadaTxt, dataInfo;
    private String allDataKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        saveBtn = findViewById(R.id.saveBtn);
        getAllDatabtn = findViewById(R.id.getAllDatabtn);
        getDadaTxt = findViewById(R.id.getDataTxt);
        dataInfo = findViewById(R.id.dataInfo);
        allDataKickBoxers = "";

        getAllDatabtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> allQuary = ParseQuery.getQuery("KickBoxer");
                allQuary.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                FancyToast.makeText(SingUP.this, "Success", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                for (ParseObject kickBoxers : objects) {
                                    allDataKickBoxers = allDataKickBoxers + kickBoxers.get("name") + " " + "Punch Power: " + kickBoxers.get("punchPower") + " "
                                            + "Kick Power: " + kickBoxers.get("kickPower") + "\n";
                                }
                                dataInfo.setText(allDataKickBoxers);
                                dataInfo.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });

        getDadaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("ySO8EfdBXY", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            getDadaTxt.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    ParseObject kickBoxer = new ParseObject("KickBoxer");
                    kickBoxer.put("name", edtName.getText().toString());
                    kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
                    kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
                    kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
                    kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
                    kickBoxer.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SingUP.this, "kickboxer object was saved", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                            } else {
                                FancyToast.makeText(SingUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    FancyToast.makeText(SingUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                }
            }
        });
    }
}
