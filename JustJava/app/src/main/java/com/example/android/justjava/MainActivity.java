/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String name = getName();
        Boolean creamCheck = getCheckedStateCream();
        Boolean chocCheck = getCheckedStateChoc();
        int price = calculatePrice(creamCheck, chocCheck);
        displayMessage(createOrderSummary(name, price, creamCheck, chocCheck));
        //composeEmail(createOrderSummary(name, price, creamCheck, chocCheck));
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean creamCheck, boolean chocCheck) {
        int pricePerCup = 5;
        if (creamCheck) {pricePerCup = pricePerCup + 1;}
        if (chocCheck) {pricePerCup = pricePerCup + 2;}
        return quantity * pricePerCup;
    }

    /**
     * creates an order summary message.
     *
     * @param price   is total order value
     * @param checked is the state of the whipped cream checkbox
     * @return displays the order summary
     */
    private String createOrderSummary(String name, int price, Boolean cream, Boolean choc) {
        String message = getString(R.string.order_message_customer, name);
        message += "\n" + getString(R.string.order_message_cream, cream);
        message += "\n" + getString(R.string.order_message_choc, choc);
        message += "\n" + getString(R.string.order_message_qty, quantity);
        message += "\n" + getString(R.string.order_message_price, price);
        message += "\n" + getString(R.string.order_message_thankyou);
        return message;
    }

    /**
     * This method gets and displays the customer's name.
     */
    private String getName() {
        EditText nameEntry = (EditText) findViewById(R.id.name);
        String name = nameEntry.getText().toString();
        return name;
    }

    /**
     * This method establishes whether or not the cream checkbox is checked and returns this info.
     */
    private boolean getCheckedStateCream() {
        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.cream);
        boolean cream = creamCheckBox.isChecked();
        return cream;
    }


    /**
     * This method establishes whether or not the chocolate checkbox is checked and returns this info.
     */
    private boolean getCheckedStateChoc() {
        CheckBox chocCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean choc = chocCheckBox.isChecked();
        return choc;
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

        if (quantity == 50){ Context context = getApplicationContext();
            CharSequence text = getString(R.string.max_cups_toast);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();}
        if (quantity < 50) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {

        if (quantity == 1) {Context context = getApplicationContext();
            CharSequence text = getString(R.string.min_cups_toast);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();}
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity); }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void composeEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "info@cyberbunny.co.uk");
        intent.putExtra(Intent.EXTRA_SUBJECT, "test");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}