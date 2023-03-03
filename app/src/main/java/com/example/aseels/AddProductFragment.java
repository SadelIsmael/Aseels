package com.example.aseels;
import static android.R.layout.*;

import android.R.layout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
public class AddProductFragment extends Fragment
{
    private static final String TAG = "AddProductFragment";
    private EditText etName, etInfo;
    private Spinner spCat;
    private ImageView ivPhoto;
    private EditText etPrice;
    private EditText etCompany;
    private FirebaseServices fbs;
    private Uri filePath;
    private StorageReference storageReference;
    private String refAfterSuccessfullUpload = null;
    private String downloadableURL = "";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddProductFragment() {

    }

    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            connectComponents();

        }
    }
    private void connectComponents()
    {
        etName =getView(). findViewById(R.id.etProductNameAddProduct);
        spCat = getView().findViewById(R.id.spProductCatADDPRODUCT);
        etInfo = getView().findViewById(R.id.etdInfoAddProduct);
        ivPhoto = getView().findViewById(R.id.ivPhotoAddProduct);
        etPrice=getView().findViewById(R.id.etProPriceAddProduct);
        etCompany=getView().findViewById(R.id.etProductCompanyAddPro);
        fbs = FirebaseServices.getInstance();
       // spCat.setAdapter(new ArrayAdapter<ProductCat>(this, simple_list_item_1, ProductCat.values()));
        storageReference = fbs.getStorage().getReference();
    }
    public void add(View view)
    {
        // check if any field is empty
        String productName, proInfo, proCompany, proPhoto, proPrice;
        productName = etName.getText().toString();
        proInfo = etInfo.getText().toString();
        proPrice = etPrice.getText().toString();
        proCompany = etCompany.getText().toString();
        if (ivPhoto.getDrawable() == null)
            proPhoto = "no_image";
        else proPhoto = ivPhoto.getDrawable().toString();
        if (productName.trim().isEmpty() || proInfo.trim().isEmpty()
                || proPhoto.trim().isEmpty() || proCompany.trim().isEmpty())
        {
            Toast.makeText(getContext(), "SomeFieldsAreEmpty", Toast.LENGTH_SHORT).show();
            return;
        }

    Product product = new Product(productName, proInfo, proCompany, proPhoto, proPrice);
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a new user with a first and last nam
    // Add a new document with a generated ID
        db.collection("products")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
    @Override
    public void onSuccess(DocumentReference documentReference) {
        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
    }
})
        .addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Log.w(TAG, "Error adding document", e);
    }
});
}
    public void selectPhoto(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),40);
    }
}