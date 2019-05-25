package com.example.actour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateProductType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createproducttype);
        Toolbar tb = findViewById(R.id.Maintoolbar);
        setSupportActionBar(tb);

        Button ConfirmButton = findViewById(R.id.TypeconfirmButton);
        ConfirmButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent main2 = new Intent(CreateProductType.this,Profiles.class);
                CreateProductType.this.startActivity(main2);
            }
        }));


        String[] values = {"1","2","3","4","5"};

        Spinner adventureSelector = findViewById(R.id.adventureSelector);
        ArrayAdapter adapter =  new ArrayAdapter(this,R.layout.spin2,values);
        adapter.setDropDownViewResource(R.layout.spin);
        adventureSelector.setAdapter(adapter);

        adventureSelector = findViewById(R.id.comfortableSelector);
        adventureSelector.setAdapter(adapter);

        adventureSelector = findViewById(R.id.eatingSelector);
        adventureSelector.setAdapter(adapter);

        adventureSelector = findViewById(R.id.variousSelector);
        adventureSelector.setAdapter(adapter);

        adventureSelector = findViewById(R.id.photoSelector);
        adventureSelector.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.myMenu_button:
                Intent profilesIntent = new Intent(CreateProductType.this,Profiles.class);
                CreateProductType.this.startActivity(profilesIntent);
                return true;

            default:

                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
