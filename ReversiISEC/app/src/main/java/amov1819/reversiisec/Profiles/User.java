package amov1819.reversiisec.Profiles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String picturePath;
    private String name;
    private boolean selected;
    private List<History> history;

    public User(String picturePath, String name) {
        this.picturePath = picturePath;
        this.name = name;
        selected = false;
        history = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Bitmap getPicture(int width, int height){
        // Get the dimensions of the View
        int targetW = width;
        int targetH = height;
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, bmOptions); // existem outros. Ex: decodeStream
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        return BitmapFactory.decodeFile(picturePath, bmOptions);
    }

    public List<History> getHistory() {
        return history;
    }

    public void addHistory(History h) {
        if(history.size() == 10)
            history.remove(9);
        history.add(0, h);
    }
}