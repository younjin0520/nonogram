package edu.skku.map.pa2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlToImage extends AsyncTask<Bitmap,Void,Bitmap> {

    private String imageUrl;

    public UrlToImage(String imageUrl){
        this.imageUrl=imageUrl;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... bitmaps) {
        Bitmap bitmap=null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        subActivity.newImage=bm;
    }
}

