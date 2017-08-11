package com.android.acadgild.autocompleteproductlist112;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.acadgild.autocompleteproductlist112.database.DBHelper;
import com.android.acadgild.autocompleteproductlist112.model.ProductData;
import com.android.acadgild.autocompleteproductlist112.utils.CommonUtilities;
import com.android.acadgild.autocompleteproductlist112.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    List<ProductData> dataList;
    //Product Names
    String[] product_names= new String[]{
            "Lenovo K5 Vibe Note", "HP Injet Printer", "Lenovo K4 Vibe Note", "VIVO V5", "VIVO V3", "VIVI V5s", "Iphone 5", "Iphone 5S", "Iphone 6", "Iphone 6S", "Iphone 7"
    };
    //Product IDs
    String[] ids= new String[]{"1234", "1245", "2345", "3456", "4567", "5678", "6789", "7890", "8901", "9012", "1010"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);
        dbHelper= CommonUtilities.getDBObject(this);

        int count= dbHelper.getFullCount(Constants.PRODUCT_RECORD, null);
        if(count==0) {
            insertProductRecord();
        }
        //To get list of all preoucts
        dataList = dbHelper.getAllProducts();
        Log.e("size", "= " + dataList.size());
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < dataList.size(); i++) {
            Log.e("PN", "= " +i+" = "+ dataList.get(i).getProductName());
            listTitle.add(i, dataList.get(i).getProductName());
        }

        //ArrayAdapter with simple_dropdown_item_1line
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listTitle);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);
        //Set Adapter to AutoCompleteTextView
        textView.setAdapter(adapter);
        //Set Threshold
        textView.setThreshold(1);
    }
    // TO Insert Product Records inside DB
    private void insertProductRecord(){
        for(int i=0; i<product_names.length; i++) {
            ContentValues vals = new ContentValues();
            vals.put(Constants.PRODUCT_ID, ids[i]);
            vals.put(Constants.PRODUCT_NAME, product_names[i]);
            dbHelper.insertContentVals(Constants.PRODUCT_RECORD, vals);
        }
    }
}
