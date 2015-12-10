package com.swd2015.shopdocu.Controller.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Adapter.ChoosePhotoModeAdapter;
import com.swd2015.shopdocu.Controller.Task.UploadTask;
import com.swd2015.shopdocu.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestSellProductFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;
    private Bitmap bitmap;
    private ImageView addProductImageView;
    private Button btnContinue;
    private EditText productNameEditText;
    private Spinner categorySpinner;
    private EditText productPriceEditText;
    private EditText placeExchangeEditText;
    private EditText productDescriptionEditText;
    private String productName;
    private int categoryID;
    private int productPrice;
    private String description;
    private String placeExchange;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_sell_product, container, false);

        // Initialize Edit Text and Spinner to get product information
        productNameEditText = (EditText) view.findViewById(R.id.input_product_name);
        categorySpinner = (Spinner) view.findViewById(R.id.input_product_category_spinner);
        productPriceEditText = (EditText) view.findViewById(R.id.input_product_price);
        placeExchangeEditText = (EditText) view.findViewById(R.id.input_place_exchange);
        productDescriptionEditText = (EditText) view.findViewById(R.id.input_product_description);

        // Image View - click to take photo or choose image from gallery
        addProductImageView = (ImageView) view.findViewById(R.id.add_product_photos);


        addProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {
                        getResources().getString(R.string.take_product_photo_action),
                        getResources().getString(R.string.choose_product_photo_action)
                };

                final Integer[] icons = new Integer[] {R.drawable.ic_use_camera, R.drawable.ic_use_gallery};
                ListAdapter adapter = new ChoosePhotoModeAdapter(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity())
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                if (item == 0) {
                                    takingProductPhoto();
                                } else {
                                    selectImageFromGallery();
                                }
                            }
                        }).show();
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

                if (!productDescriptionEditText.getText().toString().equals("")) {
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
                    RequestSellCustomerFragment.imageUri = imageUri;
                    viewPager.setCurrentItem(1);
                }
            }
        });

        return view;
    }

    /**
     * Method: validateInput(String productName, String placeExchange)
     * <p/>
     * Validate Product Name and Place Exchange. If null -> show error message
     * <p/>
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
     * <p/>
     * Request camera and take photo of product
     * <p/>
     * ++PhucLHSE61219_201511
     */
    private void takingProductPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //folder stuff
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        imagesFolder.mkdirs();

        File mCurrentPhoto = new File(imagesFolder, "QR_" + timeStamp + ".png");
        Uri uriSavedImage = Uri.fromFile(mCurrentPhoto);

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage.getPath());

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * @Override Method: onActivityResult(int requestCode, int resultCode, Intent data)
     * <p/>
     * Do task follow requestCode when call startActivityForResult(Intent intent, int requestCode)
     * <p/>
     * PhucLHSE61219_201511
     */
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageUri = data.getData();
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            addProductImageView.setImageBitmap(imageBitmap);
        } else if (requestCode == PICK_IMAGE && resultCode == getActivity().RESULT_OK) {
            Glide.with(getActivity()).load(imageUri).override(70, 70).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(addProductImageView);
        }
    }

    /**
     * Method: selectImageFromGallery()
     * <p/>
     * Select image from gallery
     * <p/>
     * ++PhucLHSE61219_20151202
     */
    private void selectImageFromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        try {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
        }
    }
}
