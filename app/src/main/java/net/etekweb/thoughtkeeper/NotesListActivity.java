package net.etekweb.thoughtkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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
}
