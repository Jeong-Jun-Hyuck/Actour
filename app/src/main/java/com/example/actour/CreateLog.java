package com.example.actour;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actour.model.LoginSession;
import com.example.actour.model.ProductCourse;
import com.example.actour.model.ProductModel;
import com.example.actour.model.ProductSet;
import com.example.actour.model.Result;
import com.example.actour.trans.NetworkClient;
import com.example.actour.trans.UploadAPIs;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CreateLog extends AppCompatActivity {

    private TextView logTitle;
    private Spinner nationSelector;
    private int productId=0;
    ProductSet productSet;
    ImageView iv;
    int courseCount=0;
    ArrayList<File> imageFiles = new ArrayList<>();
    ArrayList<ImageView> imageViewsList = new ArrayList<>();
    Bitmap bitmap;
    boolean picCheck=false;
    int mYear, mMonth, mDay, mHour, mMinute;
    File testFile ;
    ArrayList<ProductCourse> courseList = new ArrayList<>();
    EditText dayEditText;
    EditText priceText;
    final String[] nations={"나라","﻿가나","가봉","가이아나","감비아","과테말라","그레나다","그리스","기니","기니비사우","나고르노카라바흐 공화국",
            "나미비아","나우루","나이지리아","남수단","남아프리카 공화국","남오세티야","네덜란드","네팔","노르웨이","뉴질랜드","니제르","니카라과","대한민국","덴마크","도미니카",
            "도미니카 공화국","독일","동티모르","라오스","라이베리아","라트비아","러시아","레바논","레소토","루마니아","룩셈부르크","르완다","리비아","리투아니아","리히텐슈타인",
            "마다가스카르","마셜 제도","마케도니아 공화국","말라위","말레이시아","말리","멕시코","모나코","모로코","모리셔스","모리타니","모잠비크","몬테네그로","몰도바","몰디브","몰타",
            "몽골","미국","미얀마","미크로네시아 연방","바누아투","바레인","바베이도스","바티칸 시국","바하마","방글라데시","베냉","베네수엘라","베트남","벨기에","벨라루스","벨리즈",
            "보스니아 헤르체고비나","보츠와나","볼리비아","부룬디","부르키나파소","부탄","북키프로스","불가리아","브라질","브루나이","사모아","사우디아라비아","사하라 아랍 민주 공화국",
            "산마리노","상투메 프린시페","세네갈","세르비아","세이셸","세인트루시아","세인트빈센트 그레나딘","세인트키츠 네비스","소말리아","소말릴란드","솔로몬 제도","수단","수리남",
            "스리랑카","스와질란드","스웨덴","스위스","스페인","슬로바키아","슬로베니아","시리아","시에라리온","싱가포르","아랍에미리트","아르메니아","아르헨티나","아이슬란드","아이티",
            "아일랜드","아제르바이잔","아프가니스탄","안도라","알바니아","알제리","압하스","앙골라","앤티가 바부다","에리트레아","에스토니아","에콰도르","에티오피아","엘살바도르","영국",
            "예멘","오만","오스트레일리아","오스트리아","온두라스","요르단","우간다","우루과이","우즈베키스탄","우크라이나","이라크","이란","이스라엘","이집트","이탈리아","인도","인도네시아",
            "일본","자메이카","잠비아","적도 기니","조선민주주의인민공화국","조지아","중앙아프리카 공화국","중화민국","중화인민공화국","지부티","짐바브웨","차드","체코","칠레","카메룬",
            "카보베르데","카자흐스탄","카타르","캄보디아","캐나다","케냐","코모로","코소보 공화국","코스타리카","코트디부아르","콜롬비아","콩고 공화국","콩고 민주 공화국","쿠바","쿠웨이트",
            "크로아티아","키르기스스탄","키리바시","키프로스","타지키스탄","탄자니아","태국","터키","토고","통가","투르크메니스탄","투발루","튀니지","트란스니스트리아","트리니다드 토바고",
            "파나마","파라과이","파키스탄", "파푸아뉴기니","팔라우","팔레스타인","페루","포르투갈","폴란드","프랑스","피지","핀란드","필리핀","헝가리"};


    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤

                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createlog);
        Toolbar tb = findViewById(R.id.Maintoolbar);
        setSupportActionBar(tb);
        imageViewsList.add((ImageView)findViewById(R.id.createProduct_pic1));
        Button confirmButton = findViewById(R.id.createlog_confirmButton);
        logTitle = findViewById(R.id.create_logTitle);
        //통신
//        Retrofit retrofit = NetworkClient.getRetrofitClient(CreateLog.this);
//        UploadAPIs uploadAPI = retrofit.create(UploadAPIs.class);
//        final Call<Integer> makeProductId = uploadAPI.makeProductID();
//        makeProductId.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {
//                productId=response.body().intValue();
//                System.out.println("now id "+productId);
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                System.out.println("fail");
//            }
//        });

        nationSelector = findViewById(R.id.createProductnationselector);
        ArrayAdapter adapter =  new ArrayAdapter(this,R.layout.spin,nations);
        adapter.setDropDownViewResource(R.layout.spin);
        nationSelector.setAdapter(adapter);
        nationSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override   // position 으로 몇번째 것이 선택됬는지 값을 넘겨준다
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
               //클릭시 함수
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //안클릭
            }
        });


        dayEditText = findViewById(R.id.createlogDaySelector);
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        UpdateNow();


        dayEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new DatePickerDialog(CreateLog.this, mDateSetListener, mYear, mMonth, mDay).show();
            }
        });

//        Button addCourseButton = findViewById(R.id.addNewCourseButton);
//        addCourseButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(picCheck){
//                LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//                courseCount++;
//                LinearLayout ll = findViewById(R.id.createCourseL1);
//                mInflater.inflate(R.layout.courseplus,ll,true);
//                ConstraintLayout child = (ConstraintLayout)ll.getChildAt(courseCount);
//                ImageView iv2 = (ImageView)child.getChildAt(2);
//                LinearLayout ll2  = (LinearLayout)child.getChildAt(4);
//                TextView txx = (TextView)ll2.getChildAt(3);
//                txx.addTextChangedListener(watcher);
//                //id 생산
//                iv2.setId(View.generateViewId());
//                imageViewsList.add(iv2);
//                picCheck=false;
//                iv2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent_pick = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                        iv=(ImageView)view;
//                        for(int i=0;i<imageViewsList.size();i++){
//                            if(imageViewsList.get(i).getId()==view.getId()){
//                                startActivityForResult(intent_pick,i);
//                            }
//                        }
//                    }});
//                }
//                else{
//                        Toast.makeText(getApplicationContext(), "코스 완성 후 추가하세요!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
        iv= findViewById(R.id.createlog_pic1);
        iv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                iv=(ImageView)view;
                Intent intent_pick = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent_pick,0);

            }
        });
//        confirmButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                LinearLayout ll = findViewById(R.id.createCourseL1);
//                for(int i =0;i<courseCount+1;i++){
//                    ConstraintLayout CL = (ConstraintLayout)ll.getChildAt(i);
//                    TextView courseName = (TextView)CL.getChildAt(1);
//                    ImageView photo = (ImageView)CL.getChildAt(2);
//                    TextView details = (TextView)CL.getChildAt(3);
//                    LinearLayout LL2 = (LinearLayout)CL.getChildAt(4);
//                    TextView spenttime = (TextView)LL2.getChildAt(1);
//                    TextView money = (TextView)LL2.getChildAt(3);
//                    TextView cautions = (TextView)CL.getChildAt(5);
//                    String str = money.getText().toString();
//                    str=str.replaceAll(",","");
//                    ProductCourse pc = new ProductCourse(courseName.getText().toString(),courseTime,courseMoney,details.getText().toString(),cautions.getText().toString(),i,productId);
//                    courseList.add(pc);
//                }
                //반복문 끝

                //통신
//                Retrofit retrofit = NetworkClient.getRetrofitClient(CreateLog.this);
//                UploadAPIs uploadAPI = retrofit.create(UploadAPIs.class);
//                for(int i =0;i<imageFiles.size();i++) {
//                    File uploadFile = imageFiles.get(i);
//                    RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), uploadFile);
//                    // Create MultipartBody.Part using file request-body,file name and part name
//                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", productId + "_pid"+i+"_idx"+uploadFile.getName(), fileReqBody);
//                    Call<Result> call = uploadAPI.uploadImage(part);
//                    call.enqueue(new Callback<Result>() {
//                        @Override
//                        public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
//                            System.out.println(response.code());
//                        }
//
//                        @Override
//                        public void onFailure(Call call, Throwable t) {
//                            System.out.println("fail");
//                        }
//                    });
//                }
//                Call<Result> call2 = uploadAPI.uploadproductSet(productSet);
//                call2.enqueue(new Callback<Result>() {
//                    @Override
//                    public void onResponse(Call<Result> call2, retrofit2.Response<Result> response) {
//
//                        System.out.println(response.code()+"upload");
//                        productSet =null;
//                        courseList.clear();
//                    }
//                    @Override
//                    public void onFailure(Call call2, Throwable t) {
//                        System.out.println("fail");
//                        productSet =null;
//                        courseList.clear();
//                    }
//                });
//
//            }
//        });

    }

    //Oncreate 끝==============================================

    public void UpdateNow(){

        dayEditText.setText(String.format("%d/%d/%d", mYear,mMonth + 1, mDay));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Uri image =data.getData();
        System.out.println(getRealPathFromURI(image));
        try {
            bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),image);
            iv.setImageBitmap(bitmap);
            testFile=new File(getRealPathFromURI(image));
            System.out.println(testFile.getName());
            System.out.println(iv.getId());
            picCheck=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(imageFiles.size()==requestCode){
            imageFiles.add(testFile);
        }
        else{
            imageFiles.set(requestCode,testFile);
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
                Intent profilesIntent = new Intent(CreateLog.this, Profiles.class);
                CreateLog.this.startActivity(profilesIntent);
                return true;

            default:

                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        CursorLoader cursorLoader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
