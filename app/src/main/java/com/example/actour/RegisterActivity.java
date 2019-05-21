package com.example.actour;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.actour.model.RegisterResultModel;
import com.example.actour.model.User;
import com.example.actour.trans.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final String[] sexSpinner={"남","녀"};
        final Spinner sexSpinner_1 = findViewById(R.id.sex_spinner);
        ArrayAdapter adapter =  new ArrayAdapter(getApplicationContext(),R.layout.spin,sexSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner_1.setAdapter(adapter);

        Button regButton = findViewById(R.id.regButtonReg);
        Button testButton = findViewById(R.id.maintest);
        testButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent main2 = new Intent(RegisterActivity.this,productSellingActivity.class);
                RegisterActivity.this.startActivity(main2);
            }
        }));

        regButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 EditText name = findViewById(R.id.regName);
                 EditText id = findViewById(R.id.regId);
                 EditText password = findViewById(R.id.regPassword1);
                 EditText passwordConfirm = findViewById(R.id.regPassword2);
                 EditText email = findViewById(R.id.regEmail);
                 EditText nation = findViewById(R.id.regNation);
                 EditText age = findViewById(R.id.reg_Age);
                 EditText nickName =  findViewById(R.id.reg_nickname);


                 if(password.getText().toString().equals(passwordConfirm.getText().toString()))
                 {
                     boolean sex;
                     if(sexSpinner_1.getSelectedItem().toString().equals("남"))
                         sex=true;
                     else
                         sex=false;
                     User postUser = new User(name.getText().toString(),id.getText().toString(),password.getText().toString(),email.getText().toString(),nation.getText().toString(),Integer.parseInt(age.getText().toString()),nickName.getText().toString(),sex);
//                 try {
//                     data.put("name", name.getText());
//                     data.put("id",id.getText());
//                     data.put("password",password.getText());
//                     data.put("email",email.getText());
//                     data.put("nation",nation.getText());
//                     data.put("sex",sex);
//                     Toast.makeText(RegisterActivity.this,data.getString("nation"),Toast.LENGTH_SHORT).show();
//                 }
//                 catch (Exception e)
//                 {
//                    Toast.makeText(RegisterActivity.this,"파싱 실패",Toast.LENGTH_SHORT).show();
//                 }


                     Retrofit client = new Retrofit.Builder()
                             .baseUrl(ApiService.API_URL)
                             .addConverterFactory(GsonConverterFactory.create())
                             .build();


                     ApiService apiService =client.create(ApiService.class);
                     Call<RegisterResultModel> call = apiService.registerUser(postUser);
                     call.enqueue(new Callback<RegisterResultModel>() {
                         @Override
                         public void onResponse(Call<RegisterResultModel> call, retrofit2.Response<RegisterResultModel> response) {
                             RegisterResultModel result = response.body();
                             System.out.println("this is "+result.getMessage());
                             if(result.getResult())
                                Toast.makeText(RegisterActivity.this,"성공",Toast.LENGTH_SHORT).show();
                             else
                             {
                                 Toast.makeText(RegisterActivity.this,result.getMessage(),Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call call, Throwable t) {
                             Toast.makeText(RegisterActivity.this,"실패"+t.getMessage(),Toast.LENGTH_SHORT).show();

                         }
                     });
                 }
                 else


                 {
                     Toast.makeText(RegisterActivity.this,"비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                 }

             }
         }
        );
    }
}
