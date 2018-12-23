package amov1819.reversiisec.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import amov1819.reversiisec.Profiles.User;
import amov1819.reversiisec.R;

public class Backup {

    private static String fileName = "Users.txt";
    private static File file;
    private static File folder;

    public static void makeFolder(){
        String folderName = "Reversi";
        folder = new File(Environment.getExternalStorageDirectory() + File.separator + folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static String getFolderPath() {
        if(folder == null) makeFolder();
        return folder.getPath();
    }

    public static void saveProfiles(List<User> profiles) {

        String filePathString = folder + File.separator + fileName;
        file = new File(filePathString);

        FileOutputStream fos;
        ObjectOutputStream os;

        try {
            fos = new FileOutputStream(file);
            os = new ObjectOutputStream(fos);
            for (User p : profiles) {
                os.writeObject(p);
            }
            os.close();
            fos.close();

            Log.d("File", "All profiles was saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadProfiles() {
        List<User> profiles = null;
        User user;

        String filePathString = folder + File.separator + fileName;
        file = new File(filePathString);


        try {
            FileInputStream fis;
            fis = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fis);

            profiles = new ArrayList<>();
            while((user = (User)is.readObject()) != null) {
                profiles.add(user);
                Log.d("LOADING", user.getName());
            }

            is.close();
            fis.close();
        } catch(EOFException ignored) {
        } catch (Exception e) {
            profiles = new ArrayList<>();
        }

        return profiles;
    }

    public static User loadSelectedProfile() {
        User profile;
        String filePathString = folder + File.separator + fileName;
        file = new File(filePathString);

        try {
            FileInputStream fis;
            fis = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fis);

            while((profile = (User)is.readObject()) != null) {
                if(profile.isSelected()){
                    return profile;
                }
            }
            is.close();
            fis.close();
        } catch(Exception e) {
            Log.d("ERROR", "Error loading selected profile. Error: " + e);
            return null;
        }
        return null;
    }
}
