package com.example.smartpantry;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smartpantry.database.spDatabase;

public class EditIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_ingredient);
        findViewById(R.id.main).requestFocus();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//      CANCEL BUTTON
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(v -> {
            finish();
        });
//      GET INGREDIENT VALUES
        int id = getIntent().getIntExtra("ingredient_id", -1);
        String name = getIntent().getStringExtra("ingredient_name");
        int quantity = getIntent().getIntExtra("ingredient_quantity", 0);
        String unit = getIntent().getStringExtra("ingredient_unit");
//      GET TEXT BOXES
        EditText nameEdit = findViewById(R.id.nameEditTxt);
        EditText quantityEdit = findViewById(R.id.quantityEditTxt);
        EditText unitEdit = findViewById(R.id.unitEditTxt);
        EditText dateEdit = findViewById(R.id.expiryDateEditTxt);
//      SET CURRENT VALUE IN TEXT BOXES
        nameEdit.setText(name);
        quantityEdit.setText(String.valueOf(quantity));
        unitEdit.setText(unit);


        Button saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(v -> {
            String nameEdited = nameEdit.getText().toString();
            int quantityEdited = Integer.parseInt(quantityEdit.getText().toString());
            String unitEdited = unitEdit.getText().toString();
            String expiryEdited = dateEdit.getText().toString();

            spDatabase db = new spDatabase(this);

            db.updateIngredient(id, nameEdited,quantityEdited,unitEdited,expiryEdited);

            finish();
        });


    }


}