package com.example.medicinedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText  mname, mdate;
    Spinner spin;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mname = findViewById(R.id.mname);
        mdate= findViewById(R.id.mdate);
        spin=findViewById(R.id.spin);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnameTXT = mname.getText().toString();
                String mdateTXT = mdate.getText().toString();
                String spinTXT = spin.getSelectedItem().toString();

                Boolean insertdata = DB.insertuserdata(mnameTXT, mdateTXT, spinTXT);
                if (insertdata == true)
                    Toast.makeText(MainActivity.this, "data inseerted", Toast.LENGTH_LONG).show();
                else

                    Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnameTXT = mname.getText().toString();
                String mdateTXT =mdate .getText().toString();
                String spinTXT = spin.getSelectedItem().toString();

                Boolean updatedata = DB.updateuserdata(mnameTXT,mdateTXT,spinTXT);
                if(updatedata==true)
                    Toast.makeText(MainActivity.this, "data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "data not Updated", Toast.LENGTH_LONG).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnameTXT =mname.getText().toString();
                Boolean deletedata = DB.deletedata(mnameTXT);
                if(deletedata==true)
                    Toast.makeText(MainActivity.this, "data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "data not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("enter name:"+res.getString(0)+"\n");
                    buffer.append("Date :"+res.getString(1)+"\n");
                    buffer.append("Time of the day:"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}