package amov1819.reversiisec.Menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import amov1819.reversiisec.Game.GameActivity;
import amov1819.reversiisec.Profiles.ProfileActivity;
import amov1819.reversiisec.Profiles.User;
import amov1819.reversiisec.R;
import amov1819.reversiisec.Utils.Backup;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/Bungee-Regular.ttf");
        ((TextView)findViewById(R.id.tvNome)).setTypeface(font);
        ((Button)findViewById(R.id.btnSinglePlayer)).setTypeface(font);
        ((Button)findViewById(R.id.btnMultiPlayer)).setTypeface(font);
        ((Button)findViewById(R.id.btnProfile)).setTypeface(font);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                    PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=
                    PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            if (checkSelfPermission(Manifest.permission.CAMERA)!=
                    PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.CAMERA},1);
        }

        Backup.makeFolder();
    }

    public void onSinglePlayer(View view) {
        Intent intent;
        user = Backup.loadSelectedProfile();

        if(user == null) {
            intent = new Intent(this, ProfileActivity.class);
            Toast.makeText(this, "First need to choose/create a profile.", Toast.LENGTH_LONG).show();
        } else {
            intent = new Intent(this, GameActivity.class);
        }
        startActivity(intent);
    }
    public void onMultiPlayer(View view) {
        Intent intent;
        user = Backup.loadSelectedProfile();

        if(user == null) {
            intent = new Intent(this, ProfileActivity.class);
            Toast.makeText(this, "First need to choose/create a profile.", Toast.LENGTH_LONG).show();
        } else {
            intent = new Intent(this, MultiplayerActivity.class);
        }
        startActivity(intent);
    }
    public void onProfile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuCredits){
            Intent intent = new Intent(this,CreditsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
