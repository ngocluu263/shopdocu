package com.swd2015.shopdocu.Ga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.swd2015.shopdocu.R;

public class RequestSellProductFragment extends Fragment {
    private     static final int           REQUEST_IMAGE_CAPTURE  = 1;
    private     static final int           PICK_IMAGE = 2;
    private     Bitmap                     bitmap;
    private     ImageView                  addProductImageView;
    private     Button                     btnContinue;
    private     EditText                   productNameEditText;
    private     Spinner                    categorySpinner;
    private     EditText                   productPriceEditText;
    private     EditText                   placeExchangeEditText;
    private     EditText                   productDescriptionEditText;
    private     String                     productName;
    private     int                        categoryID;
    private     int                        productPrice;
    private     String                     description;
    private     String                     placeExchange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_sell_product, container, false);

        // Initialize Edit Text and Spinner to get product information
        productNameEditText = (EditText) view.findViewById(R.id.input_product_name);
        categorySpinner = (Spinner) view.findViewById(R.id.input_product_category_spinner);
        productPriceEditText = (EditText) view.findViewById(R.id.input_product_price);
        placeExchangeEditText = (EditText) view. findViewById(R.id.input_place_exchange);
        productDescriptionEditText = (EditText) view.findViewById(R.id.input_product_description);

        // Image View - click to take photo or choose image from gallery
        addProductImageView = (ImageView) view.findViewById(R.id.add_product_photos);
        addProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (bitmap == null) {
//                    Toast.makeText(getActivity().getApplicationContext(),
//                            "Please select image", Toast.LENGTH_SHORT).show();
//                }
//                selectImageFromGallery();
                takingProductPhoto();
            }
        });

        // Initialize View Pager to change tab
        final ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.request_sell_pager);

        // Button Continue - move to next tab
        btnContinue = (Button) view.findViewById(R.id.request_sell_product_button_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get product information from view
                productName = productNameEditText.getText().toString();

                if (!productPriceEditText.getText().toString().equals("")) {
                    productPrice = Integer.parseInt(productPriceEditText.getText().toString());
                } else {
                    productPrice = 0;
                }

                categoryID = categorySpinner.getSelectedItemPosition();
                placeExchange = placeExchangeEditText.getText().toString();

                if (!productDescriptionEditText.getText().toString().equals("")){
                    description = productDescriptionEditText.getText().toString();
                } else {
                    description = getResources().getString(R.string.no_product_description);
                }

                // Validate product information and pass value to RequestSellCustomerFragment
                if (!validateInput(productName, placeExchange)) {
                    RequestSellCustomerFragment.productName = productName;
                    RequestSellCustomerFragment.categoryID = categoryID + 1;
                    RequestSellCustomerFragment.productPrice = productPrice;
                    RequestSellCustomerFragment.placeExchange = placeExchange;
                    RequestSellCustomerFragment.productDescription = description;

                    viewPager.setCurrentItem(1);
                }
            }
        });

        return view;
    }

    /**
     * Method: validateInput(String productName, String placeExchange)
     *
     * Validate Product Name and Place Exchange. If null -> show error message
     *
     * ++PhucLHSE61219_20151203
     */
    private boolean validateInput(String productName, String placeExchange) {
        boolean error = false;

        // Validate Product Name
        if (productName.equals("")) {
            productNameEditText.setError(getResources().getString(R.string.product_name_require));
            error = true;
        }

        // Validate Place Exchange
        if (placeExchange.equals("")) {
            placeExchangeEditText.setError(getResources().getString(R.string.place_exchange_require));
            error = true;
        }
        return error;
    }

    /**
     * Method: takingProductPicture()
     *
     * Request camera and take photo of product
     *
     * ++PhucLHSE61219_201511
     */
    private void takingProductPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * @Override Method: onActivityResult(int requestCode, int resultCode, Intent data)
     *
     * Do task follow requestCode when call startActivityForResult(Intent intent, int requestCode)
     *
     * PhucLHSE61219_201511
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            addProductImageView.setImageBitmap(imageBitmap);
        } else if (requestCode == PICK_IMAGE && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            System.out.println("SELECTED IMAGE " + selectedImage);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                                            filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            addProductImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            decodeFile(picturePath);
        }
    }

    /**
     * Method: selectImageFromGallery()
     *
     * Select image from gallery
     *
     * ++PhucLHSE61219_20151202
     */
    private void selectImageFromGallery(){
//        // Create intent to Open Image applications like Gallery, Google Photos
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // Start the Intent
//        startActivityForResult(galleryIntent, PICK_IMAGE);
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(),
                                                                  Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
         }
    }

    /**
     * Method: decodeFile(String filePath)
     *
     * Retrieves the result returned from selecting image
     *
     * ++PhucLHSE61219_20151202
     */
    public void decodeFile(String filePath){
        // Decode image size
        BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
        bitmapFactoryOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bitmapFactoryOptions);

        // The new size want to scale
        final int REQUIRED_SIZE = 1024;

        //Find the correct scale value (It should be the power of 2)
        int width_tmp = bitmapFactoryOptions.outWidth;
        int height_tmp = bitmapFactoryOptions.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options bitmapFactoryOptions_2 = new BitmapFactory.Options();
        bitmapFactoryOptions_2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, bitmapFactoryOptions_2);

        addProductImageView.setImageBitmap(bitmap);
    }
}
