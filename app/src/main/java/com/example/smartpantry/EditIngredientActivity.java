package com.example.smartpantry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smartpantry.database.spDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_ingredient);
        findViewById(R.id.main).requestFocus();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
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
        String expire = getIntent().getStringExtra("ingredient_expiry");

//      GET TEXT BOXES
        EditText nameEdit = findViewById(R.id.nameEditTxt);
        EditText quantityEdit = findViewById(R.id.quantityEditTxt);
        EditText unitEdit = findViewById(R.id.unitEditTxt);
        EditText dateEdit = findViewById(R.id.expiryDateEditTxt);

//      SET CURRENT VALUES IN TEXT BOXES
        nameEdit.setText(name);
        quantityEdit.setText(String.valueOf(quantity));
        unitEdit.setText(unit);
        dateEdit.setText(expire);


        Button saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(v -> {

            TextView message = findViewById(R.id.message);

            try {

                String nameEdited = nameEdit.getText().toString().trim();
                String quantityText = quantityEdit.getText().toString().trim();
                String unitEdited = unitEdit.getText().toString().trim();
                String expiryEdited = dateEdit.getText().toString().trim();


                if (!nameEdited.isEmpty()
                        && !quantityText.isEmpty()
                        && !unitEdited.isEmpty()) {

                    int quantityEdited = Integer.parseInt(quantityText);


                    // ONLY VALIDATE EXPIRY IF ONE WAS ENTERED
                    if (!expiryEdited.isEmpty()) {

                        SimpleDateFormat format =
                                new SimpleDateFormat("dd/MM/yyyy");

                        format.setLenient(false);

                        Date expiry = format.parse(expiryEdited);
                        Date today = new Date();

                        if (expiry.before(today)) {

                            message.setText(
                                    "Expiry date cannot be before today"
                            );
                            message.setVisibility(View.VISIBLE);
                            return;
                        }
                    }


                    if (quantityEdited <= 0) {

                        message.setText(
                                "Quantity must be greater than 0"
                        );
                        message.setVisibility(View.VISIBLE);
                        return;
                    }


                    spDatabase db = new spDatabase(this);

                    db.updateIngredient(
                            id,
                            nameEdited,
                            quantityEdited,
                            unitEdited,
                            expiryEdited
                    );

                    message.setVisibility(View.GONE);
                    finish();

                } else {

                    message.setText("fill the entire form");
                    message.setVisibility(View.VISIBLE);
                }

            } catch (ParseException e) {

                message.setText("Invalid date");
                message.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {

                message.setText("Quantity must be a number");
                message.setVisibility(View.VISIBLE);
            }

        });

    }

}