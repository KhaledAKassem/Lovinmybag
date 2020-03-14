package com.beetleware.lovinmybag.common.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImageManger {

    private static final String CAPTURE_IMAGE_FILE_PROVIDER = "com.rabt.saudiclubs.provider";
    private static String IMAGE_NMAE = "";

    public static String getBase64Bitmap(Bitmap inImage) {//convert bitmap to base64
        Bitmap immagex = inImage;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.NO_WRAP);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

    public static Uri save_image_in_provider(Context context) {//to get full image not pixeled
        File imagePath = new File(context.getFilesDir(), "images");
        if (!imagePath.exists()) imagePath.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File image = new File(imagePath, timeStamp + ".jpg");
        IMAGE_NMAE = timeStamp + ".jpg";
        Uri imageUri = FileProvider.getUriForFile(context, CAPTURE_IMAGE_FILE_PROVIDER, image);
        return imageUri;
    }

    public static File get_saved_image(Context context) {
        File path = new File(context.getFilesDir(), "images");
        if (!path.exists()) path.mkdirs();
        File imageFile = new File(path, IMAGE_NMAE);
        return imageFile;
    }

    public static void Delete_Images(Context context) {//delet all files
        File path = new File(context.getFilesDir(), "images");
        if (!path.exists()) path.mkdirs();
        for (int i = 0; i < path.listFiles().length; i++) {
            File file = path.listFiles()[i];
            file.delete();
        }

    }


    public static void Delete_Image(Context context) {//delet all files
        File path = new File(context.getFilesDir(), "images");
        File imageFile = new File(path, IMAGE_NMAE);
        imageFile.delete();

    }

}
