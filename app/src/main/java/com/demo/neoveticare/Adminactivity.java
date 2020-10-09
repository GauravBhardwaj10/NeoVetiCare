package com.demo.neoveticare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminactivity extends AppCompatActivity {
    public EditText ed1,ed2;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);
        ed1=findViewById(R.id.email1);
        ed2=findViewById(R.id.pass1);
        btn=findViewById(R.id.login1);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if((ed1.getText().toString().equals("A"))&&(ed2.getText().toString().equals("1"))){
                  AlertDialog.Builder builder=new AlertDialog.Builder(Adminactivity.this);
                  builder.setIcon(R.drawable.ic_baseline_security_24);
                  builder.setTitle(" Admin Login sucessfully");
                  builder.setMessage("Now, Do you want to open a page to see the List of nannies  and CareTakers as well as Manage Users:");
                  builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.cancel();
                          done();
                      }
                  });
              AlertDialog alertDialog=builder.create();
              alertDialog.show();
              }
              else
              {
                  Toast.makeText(getApplicationContext(),"NOT VALID",Toast.LENGTH_SHORT).show();
              }
          }
      });

    }
    public void done()
    {
        Intent intent= new Intent(Adminactivity.this,manageUserndlistofalladmin.class);
        startActivity(intent);
    }

}