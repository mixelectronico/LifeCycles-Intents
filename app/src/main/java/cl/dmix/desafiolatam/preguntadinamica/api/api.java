package cl.dmix.desafiolatam.preguntadinamica.api;

import cl.dmix.desafiolatam.preguntadinamica.beans.PreguntasLista;
import retrofit2.Call;
import retrofit2.http.GET;

public interface api {

    @GET("api.php?amount=10&category=15&difficulty=easy")
    Call<PreguntasLista> getAllQuestions();
}
