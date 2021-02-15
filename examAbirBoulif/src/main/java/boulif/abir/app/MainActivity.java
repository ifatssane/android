package boulif.abir.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Retrofit.Builder builder;
    public Contact contact;
    List<StudentList> students ;
    public RecyclerView myRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ING 3");
       // https://api.myjson.com/bins/fqlno

        builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create());

        try{
        Retrofit retrofit = builder.build();
        contact = retrofit.create(Contact.class);
        Call<List<StudentList>> call = contact.getContact();

            call.enqueue(new Callback<List<StudentList>>() {
                @Override
                public void onResponse(Call<List<StudentList>> call, Response<List<StudentList>> response) {

                    students= response.body();
                    Log.i("in",students.toString());
                    myRecyclerView = findViewById(R.id.myRecyclerView);
                    myRecyclerView.setAdapter(new Adapter(students, getApplicationContext()));
                    myRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                }

                @Override
                public void onFailure(Call<List<StudentList>> call, Throwable t) {
                    Log.e("Error", "Call error:" + t.toString());
                    Toast.makeText(MainActivity.this, "Error Retrieving Data!!!", Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch ( Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }


    public void showAbout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Application développée par:\nAbir Boulif")
                .setCancelable(false)
                .setPositiveButton("Merci", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                ;
        AlertDialog message=builder.create();
        message.setTitle("About the App");
        message.show();

    }
}
