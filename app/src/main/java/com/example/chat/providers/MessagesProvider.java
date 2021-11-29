package com.example.chat.providers;

import com.example.chat.models.Message;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MessagesProvider {

    CollectionReference mCollection;

    public MessagesProvider() {
        mCollection = FirebaseFirestore.getInstance().collection("Messages");
    }

    public Task<Void> create(Message message) {
        DocumentReference document = mCollection.document();
        message.setId(document.getId());
        return document.set(message);
    }

    public Query getMessageByChat(String idChat) {
        return mCollection.whereEqualTo("idChat", idChat).orderBy("timestamp", Query.Direction.ASCENDING);
    }

    public Query getMessagesByChatAndSender(String idChat, String idSender) {
        return mCollection.whereEqualTo("idChat", idChat).whereEqualTo("idSender", idSender).whereEqualTo("viewed", false);
    }

    public Query getLastMessage(String idChat) {
        return mCollection.whereEqualTo("idChat", idChat).orderBy("timestamp", Query.Direction.DESCENDING).limit(1);
    }

    public Task<Void> updateViewed(String idDocument, boolean state) {
        Map<String, Object> map = new HashMap<>();
        map.put("viewed", state);
        return mCollection.document(idDocument).update(map);
    }

}