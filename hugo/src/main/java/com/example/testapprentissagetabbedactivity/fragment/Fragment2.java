package com.example.testapprentissagetabbedactivity.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapprentissagetabbedactivity.API.InterfaceAPI;
import com.example.testapprentissagetabbedactivity.API.RetrofitClientExemple;
import com.example.testapprentissagetabbedactivity.Pokemon.PokemonAPI;
import com.example.testapprentissagetabbedactivity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private ImageView imageView ;
    private TextView tailleTxt, poidsTXT, typesTXT ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

 public void chargementAPI()
 {
     Retrofit retrofit = RetrofitClientExemple.getInstance() ;
     InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
     Call<PokemonAPI> call = interfaceAPI.GetPokemon(1);
     call.enqueue(new Callback<PokemonAPI>() {
         @Override
         public void onResponse(Call<PokemonAPI> call, Response<PokemonAPI> response) {
             if(!response.isSuccessful())
             {
                 Log.d("debug", String.valueOf(response.code()));
                 return;
             }
             PokemonAPI currentPokemon = response.body();
             poidsTXT.setText("Poids : "+currentPokemon.getWeight());
             tailleTxt.setText("Taille : "+currentPokemon.getHeight());
             String content = "Types : " ;
             for (int i =0 ; i<currentPokemon.getTypes().size();i++)
             {
                 if(i==0)
                     content+=currentPokemon.getTypes().get(i).getType().getName();
                 else
                     content+= "-"+currentPokemon.getTypes().get(i).getType().getName()  ;

             }
             typesTXT.setText(content);

         }

         @Override
         public void onFailure(Call<PokemonAPI> call, Throwable t) {
             Log.d("debug",t.getMessage());
         }
     });


 }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(view).load("https://pokeres.bastionbot.org/images/pokemon/1.png").into(imageView);
      chargementAPI();
       poidsTXT.setText("salut salut ");


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_layout, container, false);
        imageView = v.findViewById(R.id.ImageView_Details);
        poidsTXT=v.findViewById(R.id.textView_PoidsTEST);
        tailleTxt= v.findViewById(R.id.textView_TailleTEST);
        typesTXT = v.findViewById(R.id.textView_TypeTEST);

        return  v ;
    }


}