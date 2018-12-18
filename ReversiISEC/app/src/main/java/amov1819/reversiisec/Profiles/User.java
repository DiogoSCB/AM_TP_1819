package amov1819.reversiisec.Profiles;

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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

/*
public class Profile implements Serializable{

    private String objectType;
    private String name;
    private boolean selected;
    private ArrayList<History> historyList;
    private String filePathPhoto;


    public Profile(String name) {
        this.objectType = Constants.CLASS_PROFILE; //className
        this.historyList = new ArrayList<>();

        this.name = name;
    }
    public ArrayList<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(ArrayList<History> historyList) {
        this.historyList = historyList;
    }

    public boolean hasHistory() {
        return historyList.size() != 0;
    }
    public History getHistory(int i) {
        return historyList.get(i);
    }

    public String [] getTitles() {
        String [] strs = new String[historyList.size()];

        for(int i = 0; i < historyList.size(); i++) {
            History h = historyList.get(i);
            strs[i] = h.getProfileTeamA() + " - " + h.getProfileTeamB();
        }
        return strs;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Profile))
            return false;

        Profile p = (Profile) obj;

        if(!name.equals(p.name) || historyList.size() != p.historyList.size())
            return false;

        for (int i = 0; i < historyList.size(); i++) {
            if(!historyList.get(i).equals(p.historyList.get(i)))
                return false;
        }

        return true;
    }

    public String getFilePathPhoto() {
        return filePathPhoto;
    }

    public void setFilePathPhoto(String filePathPhoto) {
        this.filePathPhoto = filePathPhoto;
    }

    private Uri Uri() {
        return Uri.parse("file://" + filePathPhoto);
    }

    @SuppressWarnings("deprecation")
    public Bitmap getImage(Context context, int targetW, int targetH) throws FileNotFoundException {

        Log.d(TAG, "getImage: Uri().getPath() = " + Uri().getPath());


        InputStream imageStream = context.getContentResolver().openInputStream(Uri());

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Uri().getPath(), bmOptions);
        Bitmap img = BitmapFactory.decodeStream(imageStream, null, bmOptions);


        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        Log.d(TAG, "getImage: targetW = " + targetW);
        Log.d(TAG, "getImage: targetH = " + targetH);
        Log.d(TAG, "getImage: photoW = " + photoW);
        Log.d(TAG, "getImage: photoH = " + photoH);

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        imageStream = context.getContentResolver().openInputStream(Uri());

        return BitmapFactory.decodeStream(imageStream, null, bmOptions);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "objectType='" + objectType + '\'' +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                ", filePathPhoto='" + filePathPhoto + '\'' +
                '}';
    }
}*/