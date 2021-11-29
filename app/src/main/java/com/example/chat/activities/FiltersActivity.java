package com.example.chat.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chat.R;
import com.example.chat.adapters.PostsAdapter;
import com.example.chat.models.Post;
import com.example.chat.providers.AuthProvider;
import com.example.chat.providers.PostProvider;
import com.example.chat.utils.ViewedMessageHelper;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class FiltersActivity extends AppCompatActivity {

    String mExtraCategory;

    AuthProvider mAuthProvider;
    RecyclerView mRecyclerView;
    PostProvider mPostProvider;
    PostsAdapter mPostsAdapter;

    Toolbar mToolbar;
    TextView mTextViewNumberFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        mRecyclerView = findViewById(R.id.recyclerFilter);
        mTextViewNumberFilters = findViewById(R.id.textViewNumberFilter);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Filtros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new GridLayoutManager(FiltersActivity.this, 2 ));

        mExtraCategory = getIntent().getStringExtra("category");

        mAuthProvider = new AuthProvider();
        mPostProvider = new PostProvider();

    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mPostProvider.getPostByCategoryAndTimestamp(mExtraCategory);
        FirestoreRecyclerOptions<Post> options =
                new FirestoreRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();
        mPostsAdapter = new PostsAdapter(options, FiltersActivity.this, mTextViewNumberFilters);
        mRecyclerView.setAdapter(mPostsAdapter);
        mPostsAdapter.startListening();
        ViewedMessageHelper.updateOnline(true, FiltersActivity.this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPostsAdapter.stopListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ViewedMessageHelper.updateOnline(false, FiltersActivity.this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}