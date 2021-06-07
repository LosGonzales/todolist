package sg.edu.rp.c346.id20013676.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText editText;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAddItem);
        listView = findViewById(R.id.listView);
        Button btnRemove = findViewById(R.id.buttonRemove);
        Button btnClear = findViewById(R.id.buttonClear);

        arrayList = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() > 0) {
                    arrayList.add(editText.getText().toString());
                }
                else {
                    Toast.makeText((MainActivity.this),"Do not leave the field blank",Toast.LENGTH_LONG).show();
                }
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() > 0) {
                    int pos = Integer.parseInt(editText.getText().toString());
                    if (pos+1 <= arrayList.size() && pos >= 0) {
                        arrayList.remove(pos);
                    }
                    else {
                        Toast.makeText((MainActivity.this),"Please enter valid index",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText((MainActivity.this),"Please enter valid index",Toast.LENGTH_LONG).show();
                }
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.size() > 0) {
                    arrayList.clear();
                }
                else {
                    Toast.makeText((MainActivity.this),"There are no tasks to clear",Toast.LENGTH_LONG).show();
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = arrayAdapter.getItem(position);
                Toast.makeText((MainActivity.this), colour, Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnRemove.setEnabled(false);
                        btnAdd.setEnabled(true);
                        editText.setHint("Type in a new task here");
                        editText.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnRemove.setEnabled(true);
                        btnAdd.setEnabled(false);
                        editText.setHint("Type in the index here");
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}