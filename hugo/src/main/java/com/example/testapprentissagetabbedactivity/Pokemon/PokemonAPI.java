package com.example.testapprentissagetabbedactivity.Pokemon;

import java.util.ArrayList;

public class PokemonAPI {
    int height ;
    int weight ;
    ArrayList<Types> types;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTypes(ArrayList<Types> types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public ArrayList<Types> getTypes() {
        return types;
    }
}
