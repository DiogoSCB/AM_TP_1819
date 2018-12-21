package amov1819.reversiisec.Menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import amov1819.reversiisec.Game.GameActivity;
import amov1819.reversiisec.R;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        setTitle(R.string.multiplayer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/Bungee-Regular.ttf");
        ((TextView)findViewById(R.id.tvLocal)).setTypeface(font);
        ((TextView)findViewById(R.id.tvOnline)).setTypeface(font);
    }

    public void onLocal(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    public void onOnline(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
