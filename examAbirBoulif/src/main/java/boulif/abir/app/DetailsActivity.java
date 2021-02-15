package boulif.abir.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView photo ;
    TextView name , number , address;
    Button linkedin, appel , maps ;

    //Intent intentStart = getIntent() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Détail");

        photo= findViewById(R.id.imageEtud);
        name= findViewById(R.id.nameEtud);
        number = findViewById(R.id.numberEtud);
        address=findViewById(R.id.adresseEtud);
        appel=findViewById(R.id.appel);
        maps = findViewById(R.id.maps);


        linkedin=findViewById(R.id.linkedin);
        linkedin.setOnClickListener(this);
        maps=findViewById(R.id.maps);
        maps.setOnClickListener(this);
        appel = findViewById(R.id.appel);
        appel.setOnClickListener(this);

        Intent intentStart = getIntent() ;
        if (intentStart.hasExtra("image")){
            String StudentName = getIntent().getExtras().getString("name") ;
            String StudentNumber = getIntent().getExtras().getString("number");
            String StudentAddress = getIntent().getExtras().getString("address");
            String StudentImage = getIntent().getExtras().getString("image") ;

            name.setText(StudentName);
            number.setText("Téléphone\n\n" + StudentNumber);
            address.setText("Adresse\n\n"+StudentAddress);
            Glide.with(this).load(StudentImage).into(photo);


        }
        else {
            Toast.makeText(this,"No Data Available" , Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {

        String StudentLinkedin = getIntent().getExtras().getString("linkedin") ;
        Intent intent;
        switch (v.getId()) {
            case R.id.linkedin:
                String url1 = StudentLinkedin ;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                startActivity(intent);
                break;
            case R.id.maps :
                String url2 = "https://www.google.com/maps" ;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                startActivity(intent);
                break;

            case R.id.appel :

                break;


        }

    }
}
