package net.etekweb.thoughtkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // SDK version is 28 since you keep forgetting

    DatabaseHelper databaseHelperObj;
    EditText inputField;
    Button submitButton;
    Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelperObj = new DatabaseHelper(this);
        inputField = findViewById(R.id.note);
        submitButton = findViewById(R.id.saveButton);
        listButton = findViewById(R.id.listButton);
    }

    public void pressSaveButton(View view) {
        handleSave();
        finish();
    }

    public void handleSave(){
        String text = inputField.getText().toString();
        boolean insertData = databaseHelperObj.addData(text);
        if(insertData) toast("Success!");
        else toast("Failure!");
    }

    public void pressListButton(View view) {
        Intent intent = new Intent(this, NotesListActivity.class);
        startActivity(intent);
    }

    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
