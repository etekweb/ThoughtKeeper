package net.etekweb.thoughtkeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelperObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        listView = findViewById(R.id.listView);
        databaseHelperObj = new DatabaseHelper(this);
        populateListView();
    }

    public void populateListView(){
        Cursor data = databaseHelperObj.getData();
        ArrayList<String> dataArr = new ArrayList<>();
        while(data.moveToNext()){
            dataArr.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                dataArr);
        listView.setAdapter(adapter);
    }

    public void pressDeleteButton(View view) {
        final Intent intent = new Intent(this, NotesListActivity.class);
        AlertDialog warning = new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to remove all thoughts collected from today?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deleteData = databaseHelperObj.deleteData();
                        if (deleteData) toast("Success!");
                        else toast("Failure!");
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
