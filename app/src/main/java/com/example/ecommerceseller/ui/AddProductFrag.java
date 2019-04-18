package com.example.ecommerceseller.ui;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.utils.FileUtil;
import com.example.ecommerceseller.utils.SessionManager;
import com.example.ecommerceseller.viewmodel.AddProductViewModel;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductFrag extends Fragment {

    Button uploadProduct,uploadImages;
    TextInputLayout nameIL,priceIL, stockIL,descIL;
    ProgressBar uploadPB;
    Spinner categoriesSpinner;
    AddProductViewModel addProductViewModel;
    int categoryId=-1;
    int marketId=-1;
    private int PICK_IMAGE=1;
    private int STORAGE_PERMISSION_CODE=12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.add_product_frag,container,false);

        uploadProduct =v.findViewById(R.id.uploadProduct);
        uploadImages =v.findViewById(R.id.uploadMedia);
        nameIL =v.findViewById(R.id.productName_IL);
        priceIL =v.findViewById(R.id.productPrice_IL);
        stockIL =v.findViewById(R.id.productStock_IL);
        descIL =v.findViewById(R.id.productDesc_IL);
        uploadPB =v.findViewById(R.id.uploadProduct_PB);
        categoriesSpinner =v.findViewById(R.id.product_categorySpinner);

        addProductViewModel= ViewModelProviders.of(this).get(AddProductViewModel.class);
        marketId= SessionManager.getInstance(getContext()).getMarketId();

        addProductViewModel.getCategories()
                .observe(this, new Observer<ArrayList<Category>>() {
                    @Override
                    public void onChanged(@Nullable final ArrayList<Category> categories) {
                        if (categories!=null){
                            ArrayList<String> c=new ArrayList<>();
                            for (int i = 0; i < categories.size(); i++) {
                                c.add(categories.get(i).getName());
                            }
                            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(),
                                    android.R.layout.simple_spinner_item,c
                                    );
                            categoriesSpinner.setAdapter(arrayAdapter);
                            categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    categoryId=categories.get(position).getId();
                                    Log.d("SPINNERRR","position "+position+" id "+id);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    Log.d("SPINNERRR","nothing ");

                                }
                            });
                        }

                    }
                });

        uploadProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryId==-1){
                    return;
                }
                if (marketId==-1){
                    Toast.makeText(getContext(), "register a market first before uploading products !",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (validateInput()){
                    uploadProduct.setEnabled(false);
                    String name=nameIL.getEditText().getText().toString();
                    String price=priceIL.getEditText().getText().toString();
                    String stock=stockIL.getEditText().getText().toString();
                    String desc=descIL.getEditText().getText().toString();

                    addProductViewModel.uploadProduct(name,Float.valueOf(price),
                            Integer.valueOf(stock),desc,categoryId,marketId);
                    observeProducUpload();
                    observeProductUpLoading();
                    observeProductUploadError();
                    clearFields();
                }



            }
        });

        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermession();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE){
            if (data!=null){
                uploadImages(8,data.getData());
                Toast.makeText(getContext(), "picked "+data.getDataString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==STORAGE_PERMISSION_CODE){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        }
    }

    private void pickImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,PICK_IMAGE);
    }

    private void uploadImages(int productId, Uri imageUri){
        RequestBody productIdPart=RequestBody.create(MediaType.parse("text/plain"), String.valueOf(productId));
        File file=new File(FileUtil.getPath(imageUri, getContext()));

            //Log.d("UPLOOOOD","file "+file.toString());
        RequestBody imagePart=RequestBody.create
                (MediaType.parse(getContext().getContentResolver()
                        .getType(imageUri)),file);
        MultipartBody.Part image =MultipartBody.Part.createFormData("image",file.getName(),imagePart);

        addProductViewModel.uploadImage(productIdPart,image);
        observeImageUploadError();
        observeImageUpLoading();

    }

    private void requestPermession(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){

                }else{
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
                }
            }else {
                pickImage();
            }
        }else {
            pickImage();
        }
    }


    private boolean validateInput(){
        boolean pass=true;

        if (TextUtils.isEmpty(nameIL.getEditText().getText())){
            nameIL.setError("Required");
            pass=false;
        }else{
            nameIL.setError(null);

        }
        if (TextUtils.isEmpty(priceIL.getEditText().getText())){
            priceIL.setError("Required");
            pass=false;
        }else{
            priceIL.setError(null);

        }
        if (TextUtils.isEmpty(stockIL.getEditText().getText())){
            stockIL.setError("Required");
            pass=false;
        }else{
            stockIL.setError(null);

        }
        if (TextUtils.isEmpty(descIL.getEditText().getText())){
            descIL.setError("Required");
            pass=false;
        }else{
            descIL.setError(null);

        }

        return pass;
    }

    private void clearFields(){
        nameIL.getEditText().setText("");
        priceIL.getEditText().setText("");
        stockIL.getEditText().setText("");
        descIL.getEditText().setText("");
        descIL.clearFocus();
        stockIL.clearFocus();
        descIL.clearFocus();
        nameIL.clearFocus();
    }

    private void observeProducUpload(){
        addProductViewModel.getProductResponse()
                .observe(this, new Observer<ProductResponse>() {
                    @Override
                    public void onChanged(@Nullable ProductResponse productResponse) {
                        uploadProduct.setEnabled(true);
                        if (productResponse != null) {
                            Toast.makeText(getContext(), productResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void observeProductUpLoading(){
        addProductViewModel.getIsProductUploading()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean!=null&&aBoolean)
                            uploadPB.setVisibility(View.VISIBLE);
                        else
                            uploadPB.setVisibility(View.GONE);
                    }
                });
    }

    private void observeProductUploadError(){
        addProductViewModel.getProductUploadError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                uploadProduct.setEnabled(true);
                if (s!=null)
                    Toast.makeText(getContext(), "error : "+s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void observeImageUpLoading(){
        addProductViewModel.getIsImagesUpLoading()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean!=null&&aBoolean)
                            uploadPB.setVisibility(View.VISIBLE);
                        else
                            uploadPB.setVisibility(View.GONE);
                    }
                });
    }

    private void observeImageUploadError(){
        addProductViewModel.getImageUploadingError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                uploadProduct.setEnabled(true);
                if (s!=null)
                    Toast.makeText(getContext(), "error : "+s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
