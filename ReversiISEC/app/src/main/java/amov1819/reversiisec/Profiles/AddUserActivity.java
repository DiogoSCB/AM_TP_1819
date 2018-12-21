package amov1819.reversiisec.Profiles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import amov1819.reversiisec.R;
import amov1819.reversiisec.Utils.TakePictureActivity;

public class AddUserActivity extends AppCompatActivity {

    ImageView picture;
    String filePath;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle(R.string.addUser);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        picture = findViewById(R.id.ivTakePicture);
        editText = findViewById(R.id.etName);
    }

    public void onTakePicture(View view){
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if(data.hasExtra("filePath")) {
                Log.d("onTakePhoto", (String) data.getSerializableExtra("filePath"));
                filePath = (String) data.getSerializableExtra("filePath");
                java.io.File imgFile = new  java.io.File(filePath);
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    picture.setImageBitmap(myBitmap);
                }
            }
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
                Toast.makeText(this, "Name Required", Toast.LENGTH_SHORT).show();
                editText.findFocus();
                return false;
            }
            User user = new User(filePath, name);
            Intent intent = new Intent();
            intent.putExtra("User", user);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
