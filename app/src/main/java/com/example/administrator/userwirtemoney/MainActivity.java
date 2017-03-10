package com.example.administrator.userwirtemoney;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Util.HttpUtilRequest;
import com.example.administrator.userwirtemoney.adapter.RecyclerViewAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public RecyclerView recyclerView;
    public ImageView title_img;

    //获取郭霖大神提供的免费api接口获得必应的图片url
    public static final String BIYINGIMG_URI = "http://guolin.tech/api/bing_pic";

    //该值表示键值对名,对应的是用户访问服务器返回的图片真实地址
    public static final String IMG_URL = "img_url";

    //该用户偏好用于保存用户自己的图片路径
    public static final String IMG_URI = "img_uri";

    public static final int TAKE_PHOTO = 1;

    public SharedPreferences sharedPreferences;

    public SharedPreferences.Editor editor;

    //用于保存图片uri地址
    public String img_url;

    //用于保存文件真实路径的uri
    public Uri image_uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.daohang);
            setTitle("账单助手");
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        title_img = (ImageView) findViewById(R.id.titleImage);
        sharedPreferences = getSharedPreferences("user_content", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        img_url = sharedPreferences.getString(IMG_URL,null);
        if(sharedPreferences.getString(IMG_URI,null)!=null){
            image_uri = Uri.parse(sharedPreferences.getString(IMG_URI,null));
        }
        if(img_url!=null){
            Glide.with(MainActivity.this).load(img_url).into(title_img);
        }else if(image_uri!=null){
            Glide.with(MainActivity.this).load(image_uri).into(title_img);
        }else{
            HttpUtilRequest.sendRequset(BIYINGIMG_URI, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(MainActivity.this).load(R.drawable.furits).into(title_img);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String imguri = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(MainActivity.this).load(imguri).into(title_img);
                            editor.putString(IMG_URL,imguri);
                            editor.apply();
                        }
                    });
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:

                break;

            case R.id.clickPhto:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请选择:");
                builder.setCancelable(false);
                builder.setNegativeButton("打开相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNeutralButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openPhoto();
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setMessage("选择相册或者是拍照来设置您的主题");

                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }


    public  void openPhoto(){
        File imageFile = new File(getExternalCacheDir(),"userImage.jpg");
        try {
            if(imageFile.exists()){
                imageFile.delete();
            }
            imageFile.createNewFile();
            if(Build.VERSION.SDK_INT>=24){
                image_uri = FileProvider.getUriForFile(MyApplication.getmContext(),
                        "com.exaple.administrator.userwirtemoney.fileProvier",imageFile);
            }else{
                image_uri = Uri.fromFile(imageFile);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
            startActivityForResult(intent,TAKE_PHOTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(sharedPreferences.getString(IMG_URL,null)!=null){
                        editor.putString(IMG_URL,null);
                        editor.putString(IMG_URI,image_uri.toString());
                        editor.apply();
                    }

                    //为什么图片显示的还是以前的那张呢?
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(image_uri));
                        title_img.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:

                break;
        }
    }
}
