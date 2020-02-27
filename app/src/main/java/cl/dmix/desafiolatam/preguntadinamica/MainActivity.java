package cl.dmix.desafiolatam.preguntadinamica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import cl.dmix.desafiolatam.preguntadinamica.api.RetrofitClient;
import cl.dmix.desafiolatam.preguntadinamica.api.api;
import cl.dmix.desafiolatam.preguntadinamica.beans.Pregunta;
import cl.dmix.desafiolatam.preguntadinamica.beans.PreguntasLista;
import cl.dmix.desafiolatam.preguntadinamica.fragments.FragmentMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Creando la actividad");

        api service = RetrofitClient.getRetrofitInstance().create(api.class);
        Call<PreguntasLista> call = service.getAllQuestions();

        //Async

        call.enqueue(new Callback<PreguntasLista>() {
            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                Log.d(TAG, response.toString());
                PreguntasLista preguntas = response.body();
                if (preguntas != null) {
                    //MOSTRAMOS PRIMERA PREGUNTA COMO FRAGMENTO PARA VISUALIZAR
                    Pregunta pregunta;
                    pregunta = preguntas.getResults().get(0);
                    FragmentMain preguntaFragment = FragmentMain
                            .newInstance(
                                    pregunta.getQuestion(),
                                    pregunta.getCategory(),
                                    pregunta.getCorrect_answer(),
                                    pregunta.getIncorrect_answers()
                            );
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_container, preguntaFragment, "FRAGMENTO")
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {

            }
        });
    }
}
