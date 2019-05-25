package com.example.actour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actour.model.LoginResultModel;
import com.example.actour.model.LoginSession;
import com.example.actour.model.UserForLogin;
import com.example.actour.trans.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton  = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText loginId = findViewById(R.id.loginId);
                EditText loginPassword = findViewById(R.id.loginPassword);

                UserForLogin userForLogin = new UserForLogin(loginId.getText().toString(),loginPassword.getText().toString());
                Retrofit client = new Retrofit.Builder()
                        .baseUrl(ApiService.API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                ApiService apiService =client.create(ApiService.class);
                Call<LoginResultModel> call = apiService.login(userForLogin);
                call.enqueue(new Callback<LoginResultModel>() {
                    @Override
                        public void onResponse(Call<LoginResultModel> call, retrofit2.Response<LoginResultModel> response) {
                            LoginResultModel result = response.body();
                            if(result.isResult()){
                                LoginSession.setLoginId(result.getSystemId());
                                Toast.makeText(LoginActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                                Intent main2Intent = new Intent(LoginActivity.this,Main2Activity.class);
                                LoginActivity.this.startActivity(main2Intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(LoginActivity.this,"서버에러",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
        });

        Button goToRegister = findViewById(R.id.loginGoToRegisterButton);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
