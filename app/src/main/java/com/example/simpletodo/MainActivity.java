package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  List<String> task_list;

  Button btn_add;
  EditText et_item;
  RecyclerView rv_item_list;
  ItemsAdapter itemsAdapter_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Defining view objects
    btn_add = findViewById(R.id.btn_add);
    et_item = findViewById(R.id.et_item);
    rv_item_list = findViewById(R.id.rv_item_list);

    // Load items from persistence
    loadTasks();
    ItemsAdapter.OnLongClickListener long_click_listener = new ItemsAdapter.OnLongClickListener() {

      /**
       * @brief Delete item from model and notify adapter
       * @param position position of the clicked Viewholder in recyclerview
       */
      @Override
      public void onItemLongClicked(int position) {
        task_list.remove(position); // Delete item from model
        itemsAdapter_.notifyItemRemoved(position); // notify adapter of removed position
        Toast.makeText(getApplicationContext(),
                       "Item was removed.",
                       Toast.LENGTH_SHORT).show();
        saveTasks();
      }
    };

    itemsAdapter_ = new ItemsAdapter(task_list, long_click_listener);
    rv_item_list.setAdapter(itemsAdapter_);
    rv_item_list.setLayoutManager(new LinearLayoutManager(this));

    btn_add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String todoItem = et_item.getText().toString(); // Get text from user

        // Add item to model
        task_list.add(todoItem);

        // Notify adapter that item is added
        itemsAdapter_.notifyItemInserted(task_list.size() - 1);

        // Clear ed_item field
        et_item.setText("");

        // Toast Give user response that item has been added
        Toast.makeText(getApplicationContext(),
                       "Item was added!",
                       Toast.LENGTH_SHORT).show();
        saveTasks();
      }
    });
  }

  // Persistence methods

  /**
   * @return persistence data file
   */
  private File getDatafile() {
    return new File(getFilesDir(), "data.txt");
  }

  /**
   * @brief load items from persistence into application run
   */
  private void loadTasks() {
    try {
      task_list = new ArrayList<>(FileUtils.readLines(getDatafile(), Charset.defaultCharset()));
    } catch (IOException e) {
      Log.e("MainActivity", "Error reading task_list from persistence", e);
      task_list = new ArrayList<>();
    }
  }

  /**
   * @brief save item into persistence data
   */
  private void saveTasks() {
    try {
      FileUtils.writeLines(getDatafile(), task_list);
    } catch (IOException e) {
      Log.e("MainActivity", "Error writing task list to persistence", e);
    }
  }
}
















