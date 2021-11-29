package com.example.chat.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chat.R;
import com.example.chat.activities.EditProfileActivity;
import com.example.chat.adapters.MyPostAdapter;
import com.example.chat.models.Post;
import com.example.chat.providers.AuthProvider;
import com.example.chat.providers.PostProvider;
import com.example.chat.providers.UsersProvider;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    LinearLayout mLinearLayoutEditProfile;
    View mView;
    TextView mTextViewUsername;
    TextView mTextViewGithub;
    TextView mTextViewEmail;
    TextView mTextViewPostNumber;
    TextView mTextViewPostExist;
    ImageView mImageViewCover;
    CircleImageView mCircleImageProfile;
    RecyclerView mRecyclerView;

    UsersProvider mUsersProvider;
    AuthProvider mAutProvider;
    PostProvider mPostProvider;

    MyPostAdapter mAdapter;

    ListenerRegistration mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        mLinearLayoutEditProfile = mView.findViewById(R.id.linearLayoutEditProfile);
        mTextViewEmail = mView.findViewById(R.id.textViewEmail);
        mTextViewUsername = mView.findViewById(R.id.textViewUsername);
        mTextViewGithub = mView.findViewById(R.id.textViewGithub);
        mTextViewPostNumber = mView.findViewById(R.id.textViewPostNumber);
        mTextViewPostExist = mView.findViewById(R.id.textViewPostExist);
        mImageViewCover = mView.findViewById(R.id.imageViewCover);
        mCircleImageProfile = mView.findViewById(R.id.circleImageProfile);
        mRecyclerView = mView.findViewById(R.id.recyclerViewMyPost);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mLinearLayoutEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });

        mUsersProvider = new UsersProvider();
        mAutProvider = new AuthProvider();
        mPostProvider = new PostProvider();

        getUser();
        getPostNumber();
        checkIfExistePost();
        return mView;
    }

    private void checkIfExistePost() {
        mListener = mPostProvider.getPostByUser(mAutProvider.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                if (queryDocumentSnapshots != null) {
                    int numberPost = queryDocumentSnapshots.size();
                    if (numberPost > 0) {
                        mTextViewPostExist.setText("Publicaciones");
                        mTextViewPostExist.setTextColor(Color.BLUE);
                    }
                    else {
                        mTextViewPostExist.setText("No hay publicaciones");
                        mTextViewPostExist.setTextColor(Color.GRAY);
                    }
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mPostProvider.getPostByUser(mAutProvider.getUid());
        FirestoreRecyclerOptions<Post> options =
                new FirestoreRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();
        mAdapter = new MyPostAdapter(options, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.remove();
        }
    }

    private void goToEditProfile() {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    private void getPostNumber() {
        mPostProvider.getPostByUser(mAutProvider.getUid()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int numberPost = queryDocumentSnapshots.size();
                mTextViewPostNumber.setText(String.valueOf(numberPost));
            }
        });
    }

    private void getUser() {
        mUsersProvider.getUser(mAutProvider.getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    if (documentSnapshot.contains("email")) {
                        String email = documentSnapshot.getString("email");
                        mTextViewEmail.setText(email);
                    }
                    if (documentSnapshot.contains("github")) {
                        String github = documentSnapshot.getString("github");
                        mTextViewGithub.setText(github);
                    }
                    if (documentSnapshot.contains("username")) {
                        String username = documentSnapshot.getString("username");
                        mTextViewUsername.setText(username);
                    }
                    if (documentSnapshot.contains("image_profile")) {
                        String imageProfile = documentSnapshot.getString("image_profile");
                        if (imageProfile != null) {
                            if (!imageProfile.isEmpty()) {
                                Picasso.with(getContext()).load(imageProfile).into(mCircleImageProfile);
                            }
                        }
                    }
                    if (documentSnapshot.contains("image_cover")) {
                        String imageCover = documentSnapshot.getString("image_cover");
                        if (imageCover != null) {
                            if (!imageCover.isEmpty()) {
                                Picasso.with(getContext()).load(imageCover).into(mImageViewCover);
                            }
                        }
                    }
                }
            }
        });
    }
}