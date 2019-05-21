package com.example.actour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class myProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myproduct);
        Toolbar tb = findViewById(R.id.Maintoolbar);
        setSupportActionBar(tb);

        Button goToMyCreateProductButton = findViewById(R.id.myProduct_CreateMoreProduct);
        goToMyCreateProductButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent main2 = new Intent(myProduct.this,CreateProduct.class);
                myProduct.this.startActivity(main2);
            }
        }));
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
                Intent profilesIntent = new Intent(myProduct.this,Profiles.class);
                myProduct.this.startActivity(profilesIntent);
                return true;

            default:

                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
