package com.demo.neoveticare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class About_us extends AppCompatActivity {
PDFView pdfView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        pdfView1=findViewById(R.id.pdfview1);
        pdfView1.fromAsset("About.pdf").load();
    }
}