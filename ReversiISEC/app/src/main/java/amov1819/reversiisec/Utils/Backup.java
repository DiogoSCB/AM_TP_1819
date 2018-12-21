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

    private String fileName = "Users.txt";
    private String folderName = "Reversi";
    private File file;
    private File folder;

    public Backup() {
        folder = new File(Environment.getExternalStorageDirectory() + File.separator + folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public String getFolderPath() {
        return folder.getPath();
    }

    public void saveProfiles(List<User> profiles) {

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

    public List<User> loadProfiles() {
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
        } catch(EOFException e) {            
        } catch (Exception e) {
            profiles = new ArrayList<>();
        }

        return profiles;
    }

    public User loadSelectedProfile() {
        User profile = null;

        String filePathString = folder + File.separator + fileName;
        file = new File(filePathString);

        try {
            FileInputStream fis;
            fis = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fis);

            int size = (int) is.readObject();

            for(int i = 0; i < size; i++) {
                User profileAux = (User) is.readObject();
                if(profileAux.isSelected())
                {
                    profile = profileAux;
                    break;
                }
            }
            is.close();
            fis.close();
        } catch(Exception e) {
            Log.d("getSelectedProfile", "Error loading selected profile. Error: " + e);
            return null;
        }
        return profile;
    }
}
