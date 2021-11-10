package com.example.myfridge;

import static com.example.myfridge.MainActivity.theFridge;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

// This is the activity for viewing our Refrigerator contents
public class FridgeActivity extends AppCompatActivity {

    private FridgeAdapter adapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_view_fridge);

        List<Item> FridgeItems = theFridge.get_Items();

        TextView emptyMessage = findViewById(R.id.EmptyTextView);

        if (!FridgeItems.isEmpty())
            emptyMessage.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.viewGroceryList);

        adapter = new FridgeAdapter(
                FridgeItems,
                FridgeActivity.this
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FridgeActivity.this));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target
            )
            {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                Item deletedCourse = FridgeItems.get(viewHolder.getAdapterPosition());

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(position);

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                //FridgeItems.remove(viewHolder.getAdapterPosition());
                theFridge.RemoveItem(deletedCourse, getApplicationContext());

                // below line is to display our snackbar with action.
                Snackbar.make(
                        recyclerView,
                        deletedCourse.Get_Name(),
                        Snackbar.LENGTH_LONG).setAction("Undo", v -> {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            theFridge.AddItem(deletedCourse, getApplicationContext());

                            // below line is to notify item is
                            // added to our adapter class.
                            adapter.notifyItemInserted(position);
                        }).show();
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView);
    }

}
