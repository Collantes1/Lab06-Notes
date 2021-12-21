package com.example.lab06_notes;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter <Note> adp;
    int sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1);
        ListView lst = findViewById(R.id.list_notes);
        lst.setAdapter(adp);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sel = position;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (data != null)
        {
            int pos = data.getIntExtra("my-note-index", -1);
            String title = data.getStringExtra("my-note-title");
            String content = data.getStringExtra("my-note-content");

            Note n = adp.getItem(pos);
            n.title = title;
            n.content = content;

            adp.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void on_new_click(View v)
    {
        Note n = new Note();
        n.title = "New title";
        n.content = "New content";

        adp.add(n);
        int pos = adp.getPosition(n);
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("my-note-index", pos);
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-content", n.content);

       startActivityForResult(i, 12345);
    }

    public void on_edit_click(View v)
    {

    }

    public void on_delete_click(View v)
    {

    }
}