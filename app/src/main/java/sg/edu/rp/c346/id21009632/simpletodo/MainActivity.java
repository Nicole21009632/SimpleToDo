package sg.edu.rp.c346.id21009632.simpletodo;

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

    Spinner spinnerTask;
    EditText userInputText;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerTask = findViewById(R.id.spinnerTask);
        userInputText = findViewById(R.id.editTextUserInput);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDeleteTask);
        btnClear = findViewById(R.id.buttonClearTask);
        lvTasks = findViewById(R.id.taskList);

        ArrayList<String> taskArray;
        taskArray = new ArrayList<>();

        ArrayAdapter listOfTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskArray);

        lvTasks.setAdapter(listOfTasks);

        spinnerTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        userInputText.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        userInputText.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskArray.add(userInputText.getText().toString());
                listOfTasks.notifyDataSetChanged();
                userInputText.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = Integer.parseInt(userInputText.getText().toString());

                if (taskArray.size() != 0) {
                    if (pos < taskArray.size()) {
                        taskArray.remove(pos);
                        listOfTasks.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
                userInputText.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskArray.clear();
                listOfTasks.notifyDataSetChanged();
            }
        });
    }
}