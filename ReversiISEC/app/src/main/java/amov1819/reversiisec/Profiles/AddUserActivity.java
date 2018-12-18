package amov1819.reversiisec.Profiles;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import amov1819.reversiisec.R;

public class AddUserActivity extends AppCompatActivity {

    ImageView picture;
    String filePath;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle(R.string.addUser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picture = findViewById(R.id.ivTakePicture);
    }

    public void onTakePicture(View view){
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            filePath = (String)data.getSerializableExtra("filePath");
            Log.d("PATH", filePath);
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            picture.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adduser, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        if(item.getItemId() == R.id.menuAddUser){
            String name = editText.getText().toString();
            if(name.trim().isEmpty()){
                editText.findFocus();
                return false;
            }
            User user = new User(filePath, name);
            Intent intent = new Intent();
            intent.putExtra("User", user);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
