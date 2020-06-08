package sg.edu.rp.c346.id18011651.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    EditText etNote;
    Button btnAdd;
    Button btnClear;
    ListView lvNotes;
    Spinner spn;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNote=findViewById(R.id.editTextNote);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        lvNotes=findViewById(R.id.listViewNote);
        spn=findViewById(R.id.spinner);
        btnDelete=findViewById(R.id.buttonDelete);
        final ArrayList<String>alNotes=new ArrayList<>();
        final ArrayAdapter aaNotes=new ArrayAdapter(this, android.R.layout.simple_list_item_1,alNotes);
        lvNotes.setAdapter(aaNotes);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNote.setText("");
                alNotes.clear();
                aaNotes.notifyDataSetChanged();
            }
        });
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String notes = etNote.getText().toString();
                                if (notes.matches("")) {
                                    Toast.makeText(MainActivity.this, "You did not enter a task", Toast.LENGTH_SHORT).show();
                                }else{
                                    alNotes.add(notes);
                                }
                                aaNotes.notifyDataSetChanged();
                                etNote.setText("");
                            }
                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etNote.setText("");
                                alNotes.clear();
                                aaNotes.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etNote.setHint("Type in the index of the task to be removed");
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etNote.setText("");
                                alNotes.clear();
                                aaNotes.notifyDataSetChanged();
                                etNote.setText("");
                            }
                        });
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(alNotes.size()!=0){
                                    if(!etNote.getText().toString().trim().matches("")){
                                        if(etNote.getText().toString().trim().matches("^[0-9]*$")){
                                            int pos=Integer.parseInt(etNote.getText().toString());
                                            if (pos > (alNotes.size() - 1)) {
                                                Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                                            }else{
                                                alNotes.remove(pos);
                                            }
                                        }else{
                                            Toast.makeText(MainActivity.this, "Please enter an integer instead", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "Please enter an integer", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                                }
                                if(!etNote.getText().toString().trim().matches("")){
                                    if(etNote.getText().toString().trim().matches("^[0-9]*$")){
                                        int pos=Integer.parseInt(etNote.getText().toString());
                                        if (pos > (alNotes.size() - 1)) {
                                            Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                                        }else{
                                            alNotes.remove(pos);
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "Please enter an integer instead", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this, "Please enter an integer", Toast.LENGTH_SHORT).show();
                                }
                                aaNotes.notifyDataSetChanged();
                                etNote.setText("");
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
