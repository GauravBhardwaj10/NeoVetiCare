package com.demo.neoveticare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dialogboxofreport extends AppCompatActivity {
    public Button bt4;
    String selectedGender="one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogboxofreport);
        bt4=findViewById(R.id.button41);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showOptionsDialog();
            }
            private void showOptionsDialog() {
                final String[] genders={"One","Two","three"};
                AlertDialog.Builder builder=new AlertDialog.Builder(Dialogboxofreport.this);
                builder.setTitle("Choose");
                builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        selectedGender=genders[which];
                        Toast.makeText(Dialogboxofreport.this,"you Choose:" +selectedGender,Toast.LENGTH_LONG).show();

                    }
                });
                builder.setPositiveButton("Report", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        go();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        });
    }
    public void go()
    {
        Toast.makeText(Dialogboxofreport.this,"Reporting Done" ,Toast.LENGTH_LONG).show();
    }
}