package com.example.administrator.userwirtemoney.Util;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.example.administrator.userwirtemoney.Application.MyApplication;
import com.example.administrator.userwirtemoney.Myinterface.JamInterface;

/**
 * 这是用于解析用户返回的图片uri
 */

public class PhtoUriSax {

    public static String imagePath = null;

    public static void startSaxUri(JamInterface.photoInterface photoInterface, Intent data){

        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(MyApplication.getmContext(),uri)){
            //获得document类型的Id
            String doId = DocumentsContract.getDocumentId(uri);
            //如果document类型是media
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = doId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(doId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        photoInterface.finshed(imagePath);
    }

    public static void startImageUri(Intent data,JamInterface.photoInterface photoInterface){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        photoInterface.finshed(imagePath);
    }

    private static String getImagePath(Uri uri,String selection){
        String imagePath = null;
        Cursor cursor = MyApplication.getmContext().getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return imagePath;
    }


}
