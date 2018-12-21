package amov1819.reversiisec.Profiles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import amov1819.reversiisec.R;
import amov1819.reversiisec.Utils.Backup;

public class ProfileActivity extends AppCompatActivity {

    private List<User> list;
    private ListView lv;
    private Backup backup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profiles);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        backup = new Backup();
        list = backup.loadProfiles();

        if(list.isEmpty()){
            findViewById(R.id.tvNoProfiles).setVisibility(View.VISIBLE);
        }

        lv = findViewById(R.id.lvProfile);
        lv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public User getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final View layout = getLayoutInflater().inflate(R.layout.list_item,null);

            ((TextView)layout.findViewById(R.id.tvUserName)).setText(list.get(i).getName());
            ((ImageView)layout.findViewById(R.id.ivUserImg)).setImageBitmap(list.get(i).getPicture(75,75));

            if(list.get(i).isSelected()){
                layout.findViewById(R.id.ivUserSelected).setVisibility(View.VISIBLE);
            }else layout.findViewById(R.id.ivUserSelected).setVisibility(View.INVISIBLE);

            layout.findViewById(R.id.listItem).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(list.get(i).isSelected()) {
                        layout.findViewById(R.id.ivUserSelected).setVisibility(View.INVISIBLE);
                        list.get(i).setSelected(false);
                    }else {
                        updateUserSelected(i);
                    }
                }
            });

            layout.findViewById(R.id.btnUserDetails).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                      Log.d("ProfilesListActivity", "details: " + i );

                        /*Intent newIntent = new Intent(context, DetailsActivity.class);
                        newIntent.putExtra("Profile", profiles.get(i));
                        startActivity(newIntent);*/
                }
            });

            layout.findViewById(R.id.btnUserDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.d("ProfilesListActivity", "details: " + i );

                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setTitle("Delete User")
                            .setMessage("Remove " + list.get(i).getName() + "?")
                            .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(i);
                                    lv.invalidateViews();
                                    if(list.isEmpty()){
                                        findViewById(R.id.tvNoProfiles).setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            return layout;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuProfile){
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivityForResult(intent, 1);
        }
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        backup.saveProfiles(list);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if(data.hasExtra("User")) {
                list.add((User)data.getSerializableExtra("User"));
                Log.d(list.get(list.size()-1).getName(),list.get(list.size()-1).getPicturePath());
                Log.d("LIST", String.valueOf(list.size()));
                lv.invalidateViews();
                findViewById(R.id.tvNoProfiles).setVisibility(View.GONE);
            }
        }
    }

    private void updateUserSelected(int selected) {
        for(int i=0; i<list.size(); i++){
            if(selected != i){
                list.get(i).setSelected(false);
            }
        }
        list.get(selected).setSelected(true);
        Toast.makeText(this, list.get(selected).getName() + " selected", Toast.LENGTH_SHORT).show();
        finish();
    }
}
