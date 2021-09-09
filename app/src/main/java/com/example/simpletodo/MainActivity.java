package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  List<String> task_list;

  Button btn_add;
  EditText et_item;
  RecyclerView rv_item_list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Defining view objects
    btn_add = findViewById(R.id.btn_add);
    et_item = findViewById(R.id.et_item);
    rv_item_list = findViewById(R.id.rv_item_list);

    rv_item_list.

    // List of strings for task list
    task_list = new ArrayList<>();
    task_list.add("Clean room");
    task_list.add("Go to gym");
    task_list.add("Fly to Texas");

  }
}