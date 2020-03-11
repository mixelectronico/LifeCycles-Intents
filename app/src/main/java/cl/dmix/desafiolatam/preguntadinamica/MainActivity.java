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
        //Instancio la interfaz api junto a RetrofitClient.
        api service = RetrofitClient.getRetrofitInstance().create(api.class);
        // Instancio la clase Call usando como argumento la raiz del modelo que corresponde a la respuesta JSON.
        Call<PreguntasLista> call = service.getAllQuestions();
        //Async -- Se hace la transacción.
        call.enqueue(new Callback<PreguntasLista>() {
            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                Log.d(TAG, response.toString());
                PreguntasLista preguntas = response.body();
                if (preguntas != null) {
                    //MOSTRAMOS PRIMERA PREGUNTA COMO FRAGMENTO PARA VISUALIZAR
                    Pregunta pregunta;
                    pregunta = preguntas.getResults().get(0);
                    /*
                    *     INICIO DE LLAMADO AL FRAGMENT, EN ESTE CASO JUSTO DESPUES DE QUE LA RESPUESTA API NO ARROJE ERRORES.
                    *
                    * */
                    //Instancio el fragmento.newInstance con los parámetros que le voy a pasar según el constructor que voy a usar.
                    FragmentMain preguntaFragment = FragmentMain
                            .newInstance(
                                    pregunta.getQuestion(),//Pregunta como argumento del constructor.
                                    pregunta.getCategory(),//Categoría de la pregunta como argumento del constructor.
                                    pregunta.getCorrect_answer(),//Respuesta correcta como argumento del constructor.
                                    pregunta.getIncorrect_answers()//Lista de respuestas incorrectas como argumento del constructor.
                            );
                    /* SE PEGA EL FRAGMENTO SOBRE LA MainActivity */
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_container, preguntaFragment, "FRAGMENTO")
                            .commit();
                    /*
                    *     FIN DEL LLAMADO AL FRAGMENT, CODIGO PARA REUTILIZAR.
                    * */
                }
            }

            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {

            }
        });
    }
}
