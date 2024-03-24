package com.example.unitconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

EditText value_input;
private String selectedItem, selectedItem2, selectedUnit;
TextView source_unit_label, destination_unit_label, select_unit_label, result_label, result_output;
Button convert_button;
Spinner source_unit_spinner, destination_unit_spinner, select_unit_spinner;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

setContentView(R.layout.activity_main);

source_unit_label = findViewById(R.id.source_unit_label);
select_unit_label = findViewById(R.id.select_unit_label);
select_unit_spinner = findViewById(R.id.select_unit_spinner);
destination_unit_label = findViewById(R.id.destination_unit_label);
result_label = findViewById(R.id.result_label);
result_output = findViewById(R.id.result_output);
value_input = findViewById(R.id.value_input);
convert_button = findViewById(R.id.convert_button);

source_unit_spinner = findViewById(R.id.source_unit_spinner);
destination_unit_spinner = findViewById(R.id.destination_unit_spinner);

ArrayAdapter<CharSequence> firstAdapter = ArrayAdapter.createFromResource(this,
        R.array.select_units, android.R.layout.simple_spinner_item);
firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
select_unit_spinner.setAdapter(firstAdapter);

// Set a listener on the first spinner to detect changes
select_unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected item from the first spinner
        selectedUnit = parent.getItemAtPosition(position).toString();

        // Dynamically populate the second spinner based on the selection of the first spinner
        ArrayAdapter<CharSequence> secondAdapter;
        if (selectedUnit.equals("Length")) {
            // Populate the second spinner with options based on the selection of the first spinner
            secondAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                    R.array.length_units, android.R.layout.simple_spinner_item);
        } else if (selectedUnit.equals("Weight")) {
            // Populate the second spinner with options based on the selection of the first spinner
            secondAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                    R.array.weight_units, android.R.layout.simple_spinner_item);
        } else {
            // Default options for other selections
            secondAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                    R.array.temp_units, android.R.layout.simple_spinner_item);
        }

        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source_unit_spinner.setAdapter(secondAdapter);

        // Set the listener for the second spinner to populate the third spinner
        source_unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the second spinner
                selectedItem = parent.getItemAtPosition(position).toString();

                // Dynamically populate the third spinner based on the selection of the second spinner
                ArrayAdapter<CharSequence> thirdAdapter;
                if (selectedItem.equals("Celsius") || selectedItem.equals("Fahrenheit") || selectedItem.equals("Kelvin")) {
                    // Populate the third spinner with options based on the selection of the second spinner
                    thirdAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                            R.array.temp_units, android.R.layout.simple_spinner_item);
                } else if (selectedItem.equals("Pound") || selectedItem.equals("Ounce") || selectedItem.equals("Ton") || selectedItem.equals("Gram") || selectedItem.equals("Kilogram")) {
                    // Populate the third spinner with options based on the selection of the second spinner
                    thirdAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                            R.array.weight_units, android.R.layout.simple_spinner_item);
                } else {
                    // Default options for other selections
                    thirdAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                            R.array.length_units, android.R.layout.simple_spinner_item);
                }

                thirdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destination_unit_spinner.setAdapter(thirdAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
});
/*  source_unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        // Get the selected item
        selectedItem = parentView.getItemAtPosition(position).toString();

        // Do something with the selected item
        Log.d("Selected Item", selectedItem);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // Handle the case where nothing is selected
    }
 });*/

destination_unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        // Get the selected item
        selectedItem2 = parentView.getItemAtPosition(position).toString();

        // Do something with the selected item
        Log.d("Selected Item", selectedItem2);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // Handle the case where nothing is selected
    }
});

convert_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                try {
                    if (Objects.equals(selectedItem, selectedItem2)) {
                        throw new IllegalArgumentException("Source and destination values should not be same");
                    }
                } catch (IllegalArgumentException e) {
                    // Handle the error: Source and destination values are the same
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        //From Feet*******************************************************************
        if (selectedItem.equals("Feet"))
        {
                if (selectedItem2.equals("Meters"))
                {
                    ConvertFromFeetToMeter();
                }
                if (selectedItem2.equals("Yards"))
                {
                    ConvertFromFeetToYard();
                }
                if (selectedItem2.equals("Miles"))
                {
                    ConvertFromFeetToMiles();
                }
                if (selectedItem2.equals("Centimeters"))
                {
                  ConvertFromFeetToCentimeter();
                }
                if (selectedItem2.equals("Inches"))
                {
                 ConvertFromFeetToInches();
                }
                if (selectedItem2.equals("Kilometers"))
                {
                ConvertFromFeetToKilometer();
                }
        }

        //From Meters***************************************
        if (selectedItem.equals("Meters"))
        {
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromMeterToFeet();
            }
            if (selectedItem2.equals("Yards"))
            {
                ConvertFromMeterToYard();
            }
            if (selectedItem2.equals("Miles"))
            {
                ConvertFromMeterToMiles();
            }
            if (selectedItem2.equals("Centimeters"))
            {
                ConvertFromMeterToCentimeter();
            }
            if (selectedItem2.equals("Inches"))
            {
                ConvertFromMeterToInches();
            }
            if (selectedItem2.equals("Kilometers"))
            {
                ConvertFromMeterToKilometer();
            }

        }

        //From Yards****************************
        if (selectedItem.equals("Yards"))
        {
            if (selectedItem2.equals("Meters"))
            {
                ConvertFromYardToMeter();
            }
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromYardToFeet();
            }
            if (selectedItem2.equals("Miles"))
            {
                ConvertFromYardToMiles();
            }
            if (selectedItem2.equals("Centimeters"))
            {
                ConvertFromYardToCentimeter();
            }
            if (selectedItem2.equals("Inches"))
            {
                ConvertFromYardToInches();
            }
            if (selectedItem2.equals("Kilometers"))
            {
                ConvertFromYardToKilometer();
            }

        }

        //From Miles****************************
        if (selectedItem.equals("Miles"))
        {
            if (selectedItem2.equals("Meters"))
            {
                ConvertFromMilesToMeter();
            }
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromMilesToFeet();
            }
            if (selectedItem2.equals("Yards"))
            {
                ConvertFromMilesToYard();
            }
            if (selectedItem2.equals("Centimeters"))
            {
                ConvertFromMilesToCentimeter();
            }
            if (selectedItem2.equals("Inches"))
            {
                ConvertFromMilesToInches();
            }
            if (selectedItem2.equals("Kilometers"))
            {
                ConvertFromMilesToKilometer();
            }

        }
        //From Centimeters********************************************
        if (selectedItem.equals("Centimeters"))
        {
            if (selectedItem2.equals("Meters"))
            {
                ConvertFromCentimeterToMeter();
            }
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromCentimeterToFeet();
            }
            if (selectedItem2.equals("Yards"))
            {
                ConvertFromCentimeterToYard();
            }
            if (selectedItem2.equals("Miles"))
            {
                ConvertFromCentimeterToMiles();
            }
            if (selectedItem2.equals("Inches"))
            {
                ConvertFromCentimeterToInches();
            }
            if (selectedItem2.equals("Kilometers"))
            {
                ConvertFromCentimeterToKilometer();
            }

        }

        //From Inches********************************************
        if (selectedItem.equals("Inches"))
        {
            if (selectedItem2.equals("Meters"))
            {
                ConvertFromInchesToMeter();
            }
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromInchesToFeet();
            }
            if (selectedItem2.equals("Yards"))
            {
                ConvertFromInchesToYard();
            }
            if (selectedItem2.equals("Miles"))
            {
                ConvertFromInchesToMiles();
            }
            if (selectedItem2.equals("Centimeters"))
            {
                ConvertFromInchesToCentimeter();
            }
            if (selectedItem2.equals("Kilometers"))
            {
                ConvertFromInchesToKilometer();
            }

        }

        //From Kilometers********************************************
        if (selectedItem.equals("Kilometers"))
        {
            if (selectedItem2.equals("Meters"))
            {
                ConvertFromKilometerToMeter();
            }
            if (selectedItem2.equals("Feet"))
            {
                ConvertFromKilometerToFeet();
            }
            if (selectedItem2.equals("Yards"))
            {
                ConvertFromKilometerToYard();
            }
            if (selectedItem2.equals("Miles"))
            {
                ConvertFromKilometerToMiles();
            }
            if (selectedItem2.equals("Centimeters"))
            {
                ConvertFromKilometerToCentimeter();
            }
            if (selectedItem2.equals("Inches"))
            {
                ConvertFromKilometerToInches();
            }

        }

        //Weight***********************************************************************************
        //From Pound********************************************
        if (selectedItem.equals("Pound"))
        {
            if (selectedItem2.equals("Ounce"))
            {
                ConvertFromPoundstoOunce();
            }
            if (selectedItem2.equals("Ton"))
            {
                ConvertFromPoundstoTon();
            }
            if (selectedItem2.equals("Gram"))
            {
                ConvertFromPoundstoGrams();
            }
            if (selectedItem2.equals("Kilogram"))
            {
                ConvertFromPoundstoKilogram();
            }
        }

        //From Ounce********************************************
        if (selectedItem.equals("Ounce"))
        {
            if (selectedItem2.equals("Pound"))
            {
                ConvertFromOuncetoPounds();
            }
            if (selectedItem2.equals("Ton"))
            {
                ConvertFromOuncetoTon();
            }
            if (selectedItem2.equals("Gram"))
            {
                ConvertFromOuncetoGrams();
            }
            if (selectedItem2.equals("Kilogram"))
            {
                ConvertFromOuncetoKilogram();
            }
        }

        //From Ton********************************************
        if (selectedItem.equals("Ton"))
        {
            if (selectedItem2.equals("Pound"))
            {
                ConvertFromTontoPounds();
            }
            if (selectedItem2.equals("Ounce"))
            {
                ConvertFromTontoOunce();
            }
            if (selectedItem2.equals("Gram"))
            {
                ConvertFromTontoGrams();
            }
            if (selectedItem2.equals("Kilogram"))
            {
                ConvertFromTontoKilogram();
            }
        }

        //From Gram********************************************
        if (selectedItem.equals("Gram"))
        {
            if (selectedItem2.equals("Pound"))
            {
                ConvertFromGramstoPounds();
            }
            if (selectedItem2.equals("Ton"))
            {
                ConvertFromGramstoTon();
            }
            if (selectedItem2.equals("Ounce"))
            {
                ConvertFromGramstoOunce();
            }
            if (selectedItem2.equals("Kilogram"))
            {
                ConvertFromGramstoKilogram();
            }
        }

        //From Kilogram********************************************
        if (selectedItem.equals("Kilogram"))
        {
            if (selectedItem2.equals("Pound"))
            {
                ConvertFromKilogramtoPounds();
            }
            if (selectedItem2.equals("Ton"))
            {
                ConvertFromKilogramtoTon();
            }
            if (selectedItem2.equals("Gram"))
            {
                ConvertFromKilogramtoGrams();
            }
            if (selectedItem2.equals("Ounce"))
            {
                ConvertFromKilogramtoOunce();
            }
        }

        //Temperature***********************************************************************
        //From Celsius********************************************
        if (selectedItem.equals("Celsius"))
        {
            if (selectedItem2.equals("Fahrenheit"))
            {
                ConvertFromCelsiustoFahrenheit();
            }
            if (selectedItem2.equals("Kelvin")) {
                ConvertFromCelsiustoKelvin();
            }
        }

        //From Fahrenheit********************************************
        if (selectedItem.equals("Fahrenheit"))
        {
            if (selectedItem2.equals("Celsius"))
            {
                ConvertFromFahrenheittoCelsius();
            }
            if (selectedItem2.equals("Kelvin")) {
                ConvertFromFahrenheitToKelvin();
            }
        }

        //From Kelvin********************************************
        if (selectedItem.equals("Kelvin"))
        {
             if (selectedItem2.equals("Celsius"))
            {
                ConvertFromKelvintoCelsius();
            }
            if (selectedItem2.equals("Fahrenheit")) {
                ConvertFromKelvinToFahrenheit();
            }
        }


    }
});
}

private void ConvertFromFeetToCentimeter() {
String valueEnterMeter = value_input.getText().toString();
double feet = Double.parseDouble(valueEnterMeter);
double centimeter = feet * 30.48;
String resultText = String.format(getString(R.string.result_format), centimeter);
result_output.setText(resultText);
}

private void ConvertFromCentimeterToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.0328084;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);

}

private void ConvertFromMeterToCentimeter() {
String valueEnterMeter = value_input.getText().toString();
double meter = Double.parseDouble(valueEnterMeter);
double centimeter = meter * 100;
String resultText = String.format(getString(R.string.result_format), centimeter);
result_output.setText(resultText);
}

private void ConvertFromCentimeterToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.01;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromFeetToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.3048;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromMeterToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 3.28084;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromYardToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.9144;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromMeterToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 1.09361;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 1609.34;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);

}

private void ConvertFromMeterToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.000621371 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromInchesToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.0254 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);

}
private void ConvertFromMeterToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 39.3701 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilometerToMeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 1000 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMeterToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.001 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromFeetToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.333333 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromYardToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 3;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromFeetToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.000189394;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 5280;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromInchesToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.0833333;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromFeetToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 12;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromFeetToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.0003048;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilometerToFeet() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 3280.84;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromYardToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.000568182;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  1760;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromYardToCentimeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  91.44;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromCentimeterToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.0109361 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromYardToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.0009144;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromKilometerToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  1093.61;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromYardToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 36 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromInchesToYard() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.0277778;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromCentimeterToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00000621371;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToCentimeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  160934;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromInchesToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.000015783;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 63360 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromMilesToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 1.60934 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilometerToMiles() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.621371 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromCentimeterToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.393701;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromInchesToCentimeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  2.54 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromCentimeterToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00001  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilometerToCentimeter() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  100000 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromInchesToKilometer() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *   0.0000254;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilometerToInches() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  39370.1 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
//Weight***********************

private void ConvertFromPoundstoOunce() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  16 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromOuncetoPounds() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.0625 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromPoundstoTon() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.0005 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromTontoPounds() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  2000 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromPoundstoGrams() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 453.592  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromGramstoPounds() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00220462  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromPoundstoKilogram() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 0.453592  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromKilogramtoPounds() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 2.20462  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromOuncetoTon() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00003125 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromTontoOunce() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  32000 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromOuncetoGrams() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  28.3495 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromGramstoOunce() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *   0.03527396 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromOuncetoKilogram() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *   0.0283495 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilogramtoOunce() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 35.27396  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromTontoGrams() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from * 907185  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromGramstoTon() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00000110231  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromTontoKilogram() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  907.185  ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilogramtoTon() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  0.00110231 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromGramstoKilogram() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *   0.001 ;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
private void ConvertFromKilogramtoGrams() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = from *  1000;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
/////////temperature****************************************
private void ConvertFromCelsiustoFahrenheit() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from * 1.8) +32;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromFahrenheittoCelsius() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from - 32) /1.8;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}



private void ConvertFromCelsiustoKelvin() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from + 273.15);
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromKelvintoCelsius() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from - 273.15);
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}

private void ConvertFromFahrenheitToKelvin() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from - 32) * 0.5556 + 273.15;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}


private void ConvertFromKelvinToFahrenheit() {
String valueFrom = value_input.getText().toString();
double from = Double.parseDouble(valueFrom);
double to = (from - 273.15) * 1.8 + 32;
String resultText = String.format(getString(R.string.result_format), to);
result_output.setText(resultText);
}
















}