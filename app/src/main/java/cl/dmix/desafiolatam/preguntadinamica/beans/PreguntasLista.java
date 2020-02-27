package cl.dmix.desafiolatam.preguntadinamica.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class PreguntasLista {
    @SerializedName("response_code")
    private int response_code;
    @SerializedName("results")
    private ArrayList<Pregunta> results;
    public int getResponse_code() {
        return response_code;
    }
    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }
    public ArrayList<Pregunta> getResults() {
        if(results == null){
            results = new ArrayList<>();
        }
        ArrayList<Pregunta> results = this.results;
        return results;
    }
    public void setResults(ArrayList<Pregunta> results) {
        this.results = results;
    }
}
