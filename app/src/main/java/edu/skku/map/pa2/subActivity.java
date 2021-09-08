package edu.skku.map.pa2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class subActivity extends AppCompatActivity {

    GridView gridView;
    Button button1, button2;
    EditText text;
    String query;
    String imageStr;
    ImageView imageView;
    TextView h1;TextView h2;TextView h3;TextView h4;TextView h5;
    TextView h6;TextView h7;TextView h8;TextView h9;TextView h10;
    TextView h11;TextView h12;TextView h13;TextView h14;TextView h15;
    TextView h16;TextView h17;TextView h18;TextView h19;TextView h20;
    TextView w1;TextView w2;TextView w3;TextView w4;TextView w5;
    TextView w6;TextView w7;TextView w8;TextView w9;TextView w10;
    TextView w11;TextView w12;TextView w13;TextView w14;TextView w15;
    TextView w16;TextView w17;TextView w18;TextView w19;TextView w20;
    static public ConstraintLayout layout;
    static public Bitmap newImage;
    static public Bitmap white;
    static public Bitmap black;
    static public int[] imgNum =new int[400];
    static public int[] playNum=new int[400];
    static public int[][] hNum=new int[20][20];
    static public ArrayList<Bitmap> imgArr = new ArrayList<Bitmap>(); //정답
    static public ArrayList<Bitmap> playArr = new ArrayList<Bitmap>(); //맞추는배열
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        gridView=(GridView)findViewById(R.id.board);
        layout = (ConstraintLayout)findViewById(R.id.layout);
        imageView = (ImageView)findViewById(R.id.imageView);
        button1 = (Button) findViewById(R.id.search);
        button2 = (Button) findViewById(R.id.gallery);
        text = (EditText) findViewById(R.id.query);
        h1=(TextView) findViewById(R.id.h1);
        h2=(TextView) findViewById(R.id.h2);
        h3=(TextView) findViewById(R.id.h3);
        h4=(TextView) findViewById(R.id.h4);
        h5=(TextView) findViewById(R.id.h5);
        h6=(TextView) findViewById(R.id.h6);
        h7=(TextView) findViewById(R.id.h7);
        h8=(TextView) findViewById(R.id.h8);
        h9=(TextView) findViewById(R.id.h9);
        h10=(TextView) findViewById(R.id.h10);
        h11=(TextView) findViewById(R.id.h11);
        h12=(TextView) findViewById(R.id.h12);
        h13=(TextView) findViewById(R.id.h13);
        h14=(TextView) findViewById(R.id.h14);
        h15=(TextView) findViewById(R.id.h15);
        h16=(TextView) findViewById(R.id.h16);
        h17=(TextView) findViewById(R.id.h17);
        h18=(TextView) findViewById(R.id.h18);
        h19=(TextView) findViewById(R.id.h19);
        h20=(TextView) findViewById(R.id.h20);
        w1=(TextView) findViewById(R.id.w1);
        w2=(TextView) findViewById(R.id.w2);
        w3=(TextView) findViewById(R.id.w3);
        w4=(TextView) findViewById(R.id.w4);
        w5=(TextView) findViewById(R.id.w5);
        w6=(TextView) findViewById(R.id.w6);
        w7=(TextView) findViewById(R.id.w7);
        w8=(TextView) findViewById(R.id.w8);
        w9=(TextView) findViewById(R.id.w9);
        w10=(TextView) findViewById(R.id.w10);
        w11=(TextView) findViewById(R.id.w11);
        w12=(TextView) findViewById(R.id.w12);
        w13=(TextView) findViewById(R.id.w13);
        w14=(TextView) findViewById(R.id.w14);
        w15=(TextView) findViewById(R.id.w15);
        w16=(TextView) findViewById(R.id.w16);
        w17=(TextView) findViewById(R.id.w17);
        w18=(TextView) findViewById(R.id.w18);
        w19=(TextView) findViewById(R.id.w19);
        w20=(TextView) findViewById(R.id.w20);
        text.bringToFront();
        //초기화
        white = Bitmap.createBitmap(20,40,Bitmap.Config.ARGB_8888);
        white.eraseColor(Color.WHITE);
        black = Bitmap.createBitmap(20,40,Bitmap.Config.ARGB_8888);
        black.eraseColor(Color.BLACK);
        for(int i=0;i<400;i++){
            playArr.add(white);
        }

        //search
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();

                //검색어 입력
                query = text.getText().toString();
                try{
                    query = URLEncoder.encode(query,"UTF-8");
                } catch(UnsupportedEncodingException e){
                    throw new RuntimeException("encoding failure",e);
                }

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://openapi.naver.com/v1/search/image").newBuilder();
                urlBuilder.addQueryParameter("query",query);
                urlBuilder.addQueryParameter("display","1");
                urlBuilder.addQueryParameter("start","1");

                String url = urlBuilder.build().toString();

                //아이디와 비밀번호
                String clientId = "mtNe1s6CaCxgLHjc4Ya9";
                String clientSecret = "zqBLcb_WOV";

                //request
                Request req = new Request.Builder().url(url)
                        .addHeader("X-Naver-Client-Id", clientId)
                        .addHeader("X-Naver-Client-Secret", clientSecret)
                        .build();

                client.newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Gson gson = new GsonBuilder().create();
                        final String myResponse = response.body().string();
                        final DataModel newData = gson.fromJson(myResponse, DataModel.class);
                        subActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                try{
                                    //URL to Image
                                    imageStr = newData.items[0].getLink();
                                    UrlToImage urlToImage = new UrlToImage(imageStr);
                                    urlToImage.execute();
                                    setup(newImage);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });

        //갤러리
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                //갤러리에서 결과값 받아오기
                startActivityForResult(intent, 0);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(playNum[position]!=imgNum[position]&&playNum[position]!=1){
                    playArr.set(position, black);
                    playNum[position]=1;
                    gridView.setAdapter(new ImageAdapter(subActivity.this));
                }else if (playNum[position]!=1){
                    //초기화
                    reset();
                    gridView.setAdapter(new ImageAdapter(subActivity.this));
                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
                }

                //게임 끝났을 때
                if(Arrays.equals(playNum,imgNum)){
                    Toast.makeText(getApplicationContext(),"FINISH!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setup(Bitmap bm){
        Bitmap bitmap1,bitmap2,bitmap3;
        int size, a=0,r,g,b,pixel,color=0;

        //초기화
        for(int i=0;i<400;i++)
            imgNum[i]=0;
        reset();
        size = 280;
        bitmap1=Bitmap.createScaledBitmap(bm,size,size,false);
        imageView.setImageBitmap(bitmap1);

        //흑백 변환
        bitmap2=Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_4444);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                pixel = bitmap1.getPixel(x, y);
                a = Color.alpha(pixel);
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);
                //회색값을 뽑아냄
                //GRAYSCALE 행렬
                color = (int) (0.2989 * r + 0.5870 * g + 0.1140 * b);
                //흑백전환
                if (color > 128)
                    color = 255;
                else
                    color = 0;
                bitmap2.setPixel(x, y, Color.argb(a,color,color,color));
            }
        }
        //이미지 분할
        imgArr.clear();
        for(int i=0; i<20;i++){
            for(int j=0;j<20;j++) {
                bitmap3 = Bitmap.createBitmap(bitmap2, bitmap2.getWidth()*j/20, bitmap2.getHeight()*i/20, bitmap2.getWidth()/20, bitmap2.getHeight()/20);
                imgArr.add(bitmap3);
            }
        }

        //흑백 결정
        Bitmap temp;
        int sum;
        for (int num = 0; num < 400; num++) {
            temp = imgArr.get(num);
            sum=0;
            for (int x = 0; x < temp.getWidth(); x++) {
                for (int y = 0; y < temp.getHeight(); y++) {
                    pixel = temp.getPixel(x, y);
                    a = Color.alpha(pixel);
                    r = Color.red(pixel);
                    g = Color.green(pixel);
                    b = Color.blue(pixel);
                    //GRAYSCALE 행렬
                    color = (int) (0.2989 * r + 0.5870 * g + 0.1140 * b);
                    sum +=color;
                }
            }
            //흑백전환
            if (sum/(temp.getWidth()*temp.getHeight())> 128) {
                color = 255;
                imgNum[num]=0;
            }
            else {
                color = 0;
                imgNum[num]=1;
            }
            for (int x = 0; x < temp.getWidth(); x++) {
                for (int y = 0; y < temp.getHeight(); y++) {
                    temp.setPixel(x, y, Color.argb(a, color, color, color));
                }
            }
            imgArr.set(num,temp); //정답
        }

        gridView.setAdapter(new ImageAdapter(this));

        //숫자출력하기
        check();
        wcheck();
        w1.setText(str[0]);w2.setText(str[1]);w3.setText(str[2]);w4.setText(str[3]);w5.setText(str[4]);
        w6.setText(str[5]);w7.setText(str[6]);w8.setText(str[7]);w9.setText(str[8]);w10.setText(str[9]);
        w11.setText(str[10]);w12.setText(str[11]); w13.setText(str[12]);w14.setText(str[13]);w15.setText(str[14]);
        w16.setText(str[15]);w17.setText(str[16]);w18.setText(str[17]);w19.setText(str[18]);w20.setText(str[19]);
        h1.setText(str2[0]);h2.setText(str2[1]);h3.setText(str2[2]);h4.setText(str2[3]);h5.setText(str2[4]);
        h6.setText(str2[5]);h7.setText(str2[6]);h8.setText(str2[7]);h9.setText(str2[8]);h10.setText(str2[9]);
        h11.setText(str2[10]);h12.setText(str2[11]);h13.setText(str2[12]);h14.setText(str2[13]);h15.setText(str2[14]);
        h16.setText(str2[15]);h17.setText(str2[16]);h18.setText(str2[17]);h19.setText(str2[18]);h20.setText(str2[19]);
    }

    public void reset(){
        for(int i=0;i<400;i++){
            playArr.set(i,white);
            playNum[i]=0;
        }
    }

    String [] str;
    String [] str2;
    public void check() {
        str=new String[20];
        for (int i=0;i<20;i++){
            str[i]="";
        }
        int previous, len;
        for (int j = 0; j < 20; j++) {
            len = 0;
            previous = 0;
            for (int i = j; i < 400; i = i + 20) {
                if (imgNum[i] == 1) {
                    previous = 1;
                    len++;
                    if(i+20>=400){
                        str[j]+="\n"+Integer.toString(len);
                    }
                } else {
                    previous = 0;
                    if (len > 0){
                        str[j]+="\n"+Integer.toString(len);
                    }
                    len=0;
                }
            }
        }
    }

    public void wcheck() {
        str2=new String[20];
        for (int i=0;i<20;i++){
            str2[i]="";
        }
        int previous = 0, len = 0;
        for (int j = 0; j < 20; j++) {
            len = 0;
            previous = 0;
            for (int i = j*20; i < j*20+20; i++) {
                if (imgNum[i] == 1) {
                    previous = 1;
                    len++;
                    if(i+1==400||i+1==j*20+20){
                        str2[j]+=Integer.toString(len)+" ";
                    }
                } else {
                    previous = 0;
                    if (len > 0){
                        str2[j]+=Integer.toString(len)+" ";
                    }
                    len=0;
                }
            }
        }
    }

    //갤러리에 사용
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //가져왔을 경우
            if (resultCode == RESULT_OK && requestCode==0) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    newImage = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    setup(newImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ImageAdapter extends BaseAdapter {

    private Context context;
    private int len;
    public ImageAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount(){
        return subActivity.playArr.size();
    }

    @Override
    public Object getItem(int position) {
        return subActivity.playArr.get(position);//아이템 호출 시 사용
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView==null){
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else{
            imageView=(ImageView)convertView;
        }
        imageView.setImageBitmap(subActivity.playArr.get(position));

        return imageView;
    }
}
