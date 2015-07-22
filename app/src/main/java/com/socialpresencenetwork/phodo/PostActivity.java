package com.socialpresencenetwork.phodo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;


public class PostActivity extends Activity {
    Button images_bt;
    Context ctx=this;
    final static int cameraData = 0;
    final static int RESULT_LOAD_IMAGE = 1;
    String selectedImagePath;
    String profilePicImageUrl="";
    ImageView image_Iv;
    String localimageName="";
    String encodedImage="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images_bt=(Button)findViewById(R.id.bt_images);
        image_Iv=(ImageView)findViewById(R.id.iv_image);
        images_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog()
    {
    }
    private Uri getTempFile() {
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ONACTIVYRESULT","Running Activity");
        switch (requestCode) {

            case RESULT_LOAD_IMAGE:
                final String srtt = "";
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA
                    };

                    Log.v("GETRESULTTT", data.getData() + "");
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    //uploadImage(picturePath+"");
                    Log.v("GOT RESULT"," FILE"+picturePath);
                    profilePicImageUrl=picturePath+"";
                    setImage();
                }
                break;

            case cameraData:

                final String srtt2 ="";

                if (requestCode == cameraData && resultCode ==RESULT_OK) {

                    if(selectedImagePath==null||selectedImagePath.equals("null"))
                    {
                        Log.i("SelecteIMagePath","isnull");
                        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID,
                                MediaStore.Images.ImageColumns.DATA,
                                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                                MediaStore.Images.ImageColumns.DATE_TAKEN,
                                MediaStore.Images.ImageColumns.MIME_TYPE
                        };
                        final Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

                        if (cursor.moveToFirst()) {

                            selectedImagePath = cursor.getString(1);
                            Log.v("GOT RESULT","CAMERA FILE1"+selectedImagePath);
                            //uploadImage(selectedImagePath);
                            profilePicImageUrl=selectedImagePath+"";
                            setImage();
                        }
                    }
                    else
                    {
                        Log.v("GOT RESULT","CAMERA FILE"+selectedImagePath);
                        //uploadImage(selectedImagePath);
                        profilePicImageUrl=selectedImagePath+"";
                        setImage();
                    }
                }
                break;
        }
    }
    public void setImage()
    {
        try{
            File imgFile = new  File(profilePicImageUrl);

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image_Iv.setImageBitmap(myBitmap);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void makebase64(){
        try{
            Bitmap bm = BitmapFactory.decodeFile(profilePicImageUrl);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            localimageName=profilePicImageUrl;
            localimageName=localimageName.substring(localimageName.lastIndexOf("/")+1, localimageName.length());
            Log.i("BASE 64",localimageName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



}
