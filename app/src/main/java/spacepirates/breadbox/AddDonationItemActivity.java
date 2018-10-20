package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import spacepirates.breadbox.model.Category;
import spacepirates.breadbox.model.DonationItem;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.Tag;

public class AddDonationItemActivity extends AppCompatActivity {

    EditText nameView;
    EditText priceView;
    EditText tagView;
    EditText descriptionview;
    EditText donorView;
    Spinner categorySpinner;

    Location location;
    int i; //used for keeping track of the location while navigating between activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Location location = (Location) i.getParcelableExtra(getString(R.string.pass_location_key));
        //location = (Location) this.getIntent().getSerializableExtra("location");
        i = this.getIntent().getIntExtra("location_index", -1);
        ArrayList<Location> locations = null;
        try {
            locations = Model.getInstance().getLocations();
        } catch (Exception e) {
            Model.getInstance().initializeDatabases(getApplicationContext());
        }
        location = (Location) locations.get(i);

        Log.d("AddDonation", "Got Location: " + location);

        setContentView(R.layout.activity_add_donation_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameView = findViewById(R.id.add_donation_input_name);
        priceView = findViewById(R.id.add_donation_input_price);
        tagView = findViewById(R.id.add_donation_input_tags);
        descriptionview = findViewById(R.id.add_donation_input_description);
        donorView = findViewById(R.id.add_donation_input_donor);
        categorySpinner = findViewById(R.id.add_donation_category_spinner);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDonationItem();
            }
        });


    }

    private void createDonationItem() {
        Context context = getApplicationContext();
        String failureMessage = "";
        boolean validDonation = true;
        String name = nameView.getText().toString();
        double price = 0;
        if (name.isEmpty()) {
            validDonation = false;
            failureMessage += "Must enter a name. ";
        }
        try {
            price = Double.parseDouble(priceView.getText().toString());
        } catch (NumberFormatException e) {
            validDonation = false;
            failureMessage += "Price must be a number. ";
        }

        //TODO tag view should be a multi selection spinner or similar, because tags are enums;
        ArrayList<Tag> tags = new ArrayList<Tag>();
        //splits the string in the tags box into words, and places in String array tags
        //( tagView.getText().toString()).split("\\W+");
        String description =  descriptionview.getText().toString();
        //TODO implement adding users to donation items
        //User donor = donorView.getText().toString();

        //TODO fix category and make sure that a category is entered.
        //Category must be entered, because it might be ised for mapping in the datbase
        Category category = (Category) categorySpinner.getSelectedItem();
        if(validDonation) {
            //Create new Donation Item. DonationItem constructor adds it to the location inventory.
            DonationItem newItem = new DonationItem(name, price, category, location, description, null, tags);
            //Add donation item to donation item datbase
            Model.getInstance().addDonationItem(newItem);

            //create and display a toast to indicate success.
            String successMessage = name + " added to " + location + ".";
            Toast.makeText(getApplicationContext(), successMessage, Toast.LENGTH_SHORT).show();


            //navigate back to LocationActivity
            Intent intent = new Intent(context, LocationActivity.class);
            intent.putExtra("getString(R.string.pass_location_key)", new Integer(i));
            context.startActivity(intent);
        } else {
            //Display a toast if the Donation is not valid with an explanation.
            Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();

        }
    }

}
