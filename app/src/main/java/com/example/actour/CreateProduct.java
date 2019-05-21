package com.example.actour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

public class CreateProduct extends AppCompatActivity {

    ImageView iv;
    int courseCount=0;
    Bitmap bitmap;
    boolean picCheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createproduct);
        Toolbar tb = findViewById(R.id.Maintoolbar);
        setSupportActionBar(tb);

        Button addCourseButton = findViewById(R.id.addNewCourseButton);
        addCourseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(picCheck){
                LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                courseCount++;
                LinearLayout ll = findViewById(R.id.createCourseL1);
                mInflater.inflate(R.layout.courseplus,ll,true);
                ConstraintLayout child = (ConstraintLayout)ll.getChildAt(courseCount);
                LinearLayout child2= (LinearLayout)child.getChildAt(0);
                ConstraintLayout child3 = (ConstraintLayout)child2.getChildAt(0);
                ImageView iv2 = (ImageView)child3.getChildAt(2);
                picCheck=false;
                iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent_pick = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent_pick, 1);
                        iv=(ImageView)view;
                    }});
                }
                else{
                        Toast.makeText(getApplicationContext(), "코스 완성 후 추가하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });

        iv= findViewById(R.id.createProduct_pic1);
        iv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                iv=(ImageView)view;
                Intent intent_pick = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent_pick,1);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Uri image =data.getData();
        try {
            bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),image);
            iv.setImageBitmap(bitmap);
            picCheck=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                Intent profilesIntent = new Intent(CreateProduct.this, CreateProduct.class);
                CreateProduct.this.startActivity(profilesIntent);
                return true;

            default:

                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
