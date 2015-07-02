package com.socialpresencenetwork.phodo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;


public class ProfileActivity extends FragmentActivity implements View.OnClickListener {

    Context ctxx=this;
    Button likes_Bt;
    Button posts_info_Bt;
    Button comments_Bt;
    Button ranks_Bt;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        images_bt=(Button)findViewById(R.id.bt_images);
            image_Iv=(ImageView)findViewById(R.id.iv_image);
            images_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });


        pager =(ViewPager)findViewById(R.id.pager);
        likes_Bt= (Button)findViewById(R.id.bt_likes);
        likes_Bt.setOnClickListener(this);
        posts_info_Bt= (Button)findViewById(R.id.bt_posts_info);
        posts_info_Bt.setOnClickListener(this);
        comments_Bt= (Button)findViewById(R.id.bt_comments_info);
        comments_Bt.setOnClickListener(this);
        ranks_Bt= (Button)findViewById(R.id.bt_rank_info);
        ranks_Bt.setOnClickListener(this);
        CustomProfilePagerAdapter adapter = new CustomProfilePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_likes:
                pager.setCurrentItem(0);
                break;
            case R.id.bt_posts_info:
                pager.setCurrentItem(1);
                break;
            case R.id.bt_comments_info:
                pager.setCurrentItem(2);
                break;
            case R.id.bt_rank_info:
                pager.setCurrentItem(3);
                break;

        }
    }

    Button images_bt;
    Context ctx=this;
    final static int cameraData = 0;
    final static int RESULT_LOAD_IMAGE = 1;
    String selectedImagePath;
    String profilePicImageUrl="";
    ImageView image_Iv;
    String localimageName="";
    String encodedImage="";


    public void showDialog()
    {


        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.profile_image_share);
        dialog.setTitle("Select Image");
        LinearLayout fromcam=(LinearLayout)dialog.findViewById(R.id.fromcameraup);
        LinearLayout fromfile=(LinearLayout)dialog.findViewById(R.id.fromfileup);
        fromcam.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempFile());
                photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                photoPickerIntent.putExtra("return-data", true);
                dialog.dismiss();
                startActivityForResult(photoPickerIntent,cameraData);
            }
        });
        fromfile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                dialog.dismiss();
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        dialog.show();

    }
    private Uri getTempFile() {

        File root = new File(Environment.getExternalStorageDirectory(),"Samples");
        if (!root.exists()) {
            root.mkdirs();
        }
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int mi = c.get(Calendar.MINUTE);
        String filename = "" + System.currentTimeMillis();
        File file = new File(root, filename + ".jpeg");
        Uri muri = Uri.fromFile(file);
        selectedImagePath = muri.getPath();
        Log.i("take picture path", selectedImagePath);
        return muri;
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
