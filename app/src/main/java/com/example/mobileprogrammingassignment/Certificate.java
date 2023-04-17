package com.example.mobileprogrammingassignment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.printservice.PrintDocument;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Certificate extends AppCompatActivity {

    EditText nameInput;
    Button btnDown;
    ImageView Cer;

    public static final int REQUEST_STORAGE=101;
    String storagePermission[];

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        Cer=findViewById(R.id.imageView2);//certificate Image
        nameInput=findViewById(R.id.inputName);
        btnDown = findViewById(R.id.btnDownload);

        storagePermission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_STORAGE);
            }
        }

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameInput.getText().toString().length()==0){
                    Toast.makeText(Certificate.this, "Please Input Your Name!!", Toast.LENGTH_SHORT).show();
                }else {
                    if (checkPermissions()) {
                        createPDF();
                    } else {
                        requestPermissions();
                    }
                }
            }
        });
    }

    private boolean checkPermissions() {
        int result;
        for (String permission : storagePermission) {
            result = ContextCompat.checkSelfPermission(Certificate.this, permission);
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(Certificate.this, storagePermission, REQUEST_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createPDF();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createPDF(){

        //Create a new PDF document
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(2480, 3000, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        //Create a new Canvas object
        Canvas canvas = page.getCanvas();

        //Draw the certificate image
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.certificate);
        bitmap = Bitmap.createScaledBitmap(bitmap, 2800, 2000, false);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);

        //Draw the name text
        String name = nameInput.getText().toString();
        paint.setTextSize(80);
        canvas.drawText(name, 1240, 1000, paint);

        //Finish the page
        document.finishPage(page);

        //Save the PDF document
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "certificate.pdf");
            document.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating PDF", Toast.LENGTH_SHORT).show();
        }

        //Close the document
        document.close();
    }
}

