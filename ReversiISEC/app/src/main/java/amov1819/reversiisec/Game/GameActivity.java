package amov1819.reversiisec.Game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import amov1819.reversiisec.Profiles.User;
import amov1819.reversiisec.R;
import amov1819.reversiisec.Utils.Backup;

public class GameActivity extends AppCompatActivity {

    User userSelected;
    Board gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userSelected = Backup.loadSelectedProfile();
        gameBoard = new Board(this);
        gameBoard.displayBoard();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Leave the Game")
                    .setMessage("You are going to loose! Are you sure?")
                    .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
