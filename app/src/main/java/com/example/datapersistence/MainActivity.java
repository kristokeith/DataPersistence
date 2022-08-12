package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME="namafile.txt";
    Button create, read, update, delete;
    TextView tRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = findViewById(R.id.btCreate);
        read = findViewById(R.id.btRead);
        update = findViewById(R.id.btUpdate);
        delete = findViewById(R.id.btDelete);
        tRead = findViewById(R.id.txtRead);

        create.setOnClickListener(this);
        read.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    void createFile() {
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            tRead.setText(text.toString());
        }
    }

    void updateFile(){
        String update = "Update Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(update.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }


    @Override
    public void onClick(View view) {
        menu(view.getId());
    }

    public void menu(int id){
        switch (id){
            case R.id.btCreate:
                createFile();
                break;
            case R.id.btRead:
                readFile();
                break;
            case R.id.btUpdate:
                updateFile();
                break;
            case R.id.btDelete:
                deleteFile();
                break;
        }
    }
}