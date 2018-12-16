package amov1819.reversiisec.Menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import amov1819.reversiisec.Profiles.ProfileActivity;
import amov1819.reversiisec.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/Bungee-Regular.ttf");
        ((TextView)findViewById(R.id.tvNome)).setTypeface(font);
    }

    public void onPlay(View view) {
        Intent intent = new Intent(this,CreditsActivity.class);
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
