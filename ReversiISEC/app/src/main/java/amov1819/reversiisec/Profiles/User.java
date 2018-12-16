package amov1819.reversiisec.Profiles;

import android.widget.ImageView;

public class User {
    ImageView picture;
    String name;
    boolean selected;

    public User(ImageView picture, String name) {
        this.picture = picture;
        this.name = name;
        selected = true;
    }

    public void changeName(String s){
        name = s;
    }

    public void selectUser(boolean b){
        selected = b;
    }
}
