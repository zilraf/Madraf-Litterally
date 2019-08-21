package com.example.madraf.litterally;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madraf.litterally.Model.Products;
import com.example.madraf.litterally.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Recycle extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbarcollaps;

    CollapsingToolbarLayout coltb;
    String getThemeku;
    String themeku = "";
    String SHARED_PREFS = "codeTheme";

    Button btnconvert, btntopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        toolbarcollaps = findViewById(R.id.toolbarcollaps);
        setSupportActionBar(toolbarcollaps);
        setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                    Intent intent = new Intent(Recycle.this, CartActivity.class);
                    startActivity(intent);
            }
        });

        coltb = findViewById(R.id.coltb);
        btnconvert = findViewById(R.id.btnconvert);
        btntopup = findViewById(R.id.btntopup);
        //btncraft = findViewById(R.id.btncraft);
        changeOurTheme();

        /*btncraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Recycle.this, Craft.class);
                startActivity(a);
            }
        });*/

    }

    public void changeOurTheme() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        getThemeku = sharedPreferences.getString(themeku, "");

        if (getThemeku.equals("blue")) {
            btntopup.setTextColor(Color.parseColor("#3498db"));
            btnconvert.setTextColor(Color.parseColor("#3498db"));
            //btncraft.setTextColor(Color.parseColor("#3498db"));
            coltb.setBackgroundColor(Color.parseColor("#3498db"));
        } else if (getThemeku.equals("green")) {
            btntopup.setTextColor(Color.parseColor("#1bac9c"));
            btnconvert.setTextColor(Color.parseColor("#1bac9c"));
            //btncraft.setTextColor(Color.parseColor("#1bac9c"));
            coltb.setBackgroundColor(Color.parseColor("#1bac9c"));
        } else if (getThemeku.equals("red")) {
            btntopup.setTextColor(Color.parseColor("#FF8D7E"));
            btnconvert.setTextColor(Color.parseColor("#FF8D7E"));
            //btncraft.setTextColor(Color.parseColor("#FF8D7E"));
            coltb.setBackgroundColor(Color.parseColor("#FF8D7E"));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {
                holder.txtproductName.setText(model.getPname());
                holder.txtproductDescription.setText(model.getDescription());
                holder.txtproductPrice.setText("Price: Rp." + model.getPrice() + ",-");
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent intent = new Intent(Recycle.this, ProductDetailsActivity.class);
                            intent.putExtra("pid", model.getPid());
                            startActivity(intent);
                        }
                    });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
