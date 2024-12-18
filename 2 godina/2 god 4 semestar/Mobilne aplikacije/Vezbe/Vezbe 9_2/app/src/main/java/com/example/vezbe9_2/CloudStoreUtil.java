package com.example.vezbe9_2;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CloudStoreUtil {

    public static void initDB(){
        // kreiraj novi objekat klase User
        User user1 = new User("Milica", "Milic");
        Map<String, Object> user2 = new HashMap<>();
        user2.put("firstName", "Ivana");
        user2.put("lastName", "Ivic");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //        dodaje se novi user1 u kolekciju "users"
        db.collection("users")
                .add(user1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("REZ_DB", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("REZ_DB", "Error adding document", e);
                    }
                });
        //        dodaje se novi user2 u kolekciju "users"
        db.collection("users")
                .add(user2)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("REZ_DB", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("REZ_DB", "Error adding document", e);
                    }
                });
    }

    public static void insert(){
        // kreiraj novi objekat klase User
        User user1 = new User("Mitar", "Kovacevic");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        dodaje se novi user u kolekciju "users"
        db.collection("users")
                .add(user1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("REZ_DB", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("REZ_DB", "Error adding document", e);
                    }
                });
    }

    public static void select(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("REZ_DB", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("REZ_DB", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public static void update(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // izmena dokumenta s ID-em "1wUqKBOWBI5O1Iq6rOAA" iz kolekcije "users"
        DocumentReference docRef = db.collection("users").document("1wUqKBOWBI5O1Iq6rOAA");
        docRef.update("firstName", "Dragan")
                .addOnSuccessListener(aVoid -> Log.d("REZ_DB", "User successfully changed"))
                .addOnFailureListener(e -> Log.w("REZ_DB", "Error getting documents.", e));

    }

    public static void delete(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // izbrisati user-a s ID-om "1wUqKBOWBI5O1Iq6rOAA" iz kolekcije "users"
        db.collection("users")
                .document("1wUqKBOWBI5O1Iq6rOAA")
                .delete()
                .addOnSuccessListener(aVoid -> Log.d("REZ_DB", "The user has been deleted."))
                .addOnFailureListener(e -> Log.w("REZ_DB", "Error deleting document.", e));

    }
    public static void selectById(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef =  db.collection("users").document("C7YBWoduf6jaJ5XzNkfX");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                Log.d("REZ_DB", documentSnapshot.getId() + " => " + documentSnapshot.getData());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("REZ_DB", "Error getting documents.", e);
            }
        });

    }

}
