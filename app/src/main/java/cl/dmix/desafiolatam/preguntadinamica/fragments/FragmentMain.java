package cl.dmix.desafiolatam.preguntadinamica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import cl.dmix.desafiolatam.preguntadinamica.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {
    private int radioButtonValue = 0;
    private TextView preguntaView, categoriaView;
    private RadioGroup grupoRespuestasView;
    private RadioButton respuestaUno, respuestaDos;

    public FragmentMain() {
        // Required empty public constructor
    }

    public static FragmentMain newInstance(String pregunta,
                                                String categoria,
                                                String respuestaCorrecta,
                                                ArrayList<String> respuestasIncorrectas){

        FragmentMain fragment = new FragmentMain();
        Bundle arguments = new Bundle();
        arguments.putString("PREGUNTA", pregunta);
        arguments.putString("CATEGORIA", categoria);
        arguments.putString("RESPUESTA_CORRECTA", respuestaCorrecta);
        arguments.putStringArrayList("RESPUESTAS_INCORRECTAS", respuestasIncorrectas);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final String pregunta = Objects.requireNonNull(getArguments()).getString("PREGUNTA");
        final String categoria = Objects.requireNonNull(getArguments().getString("CATEGORIA"));
        final String respuestaCorrecta = Objects.requireNonNull(getArguments().getString("RESPUESTA_CORRECTA"));
        final ArrayList<String> respuestasIncorrectas = Objects.requireNonNull(getArguments().getStringArrayList("RESPUESTAS_INCORRECTAS"));

        //INICIALIZAMOS LAS VISTAS DECLARADAS
        initializeViews(view);

        //ASIGNANDO VALORES DINAMICOS
        //EN BASE A LOS DATOS RECIBIDOS DE NUESTRA API ASIGNAMOS VALORES A LAS VISTAS
        preguntaView.setText(pregunta);
        categoriaView.setText(categoria);
        //RECORREMOS EL ARREGLO DE STRINGS "RESPUESTAS INCORRECTAS" DE NUESTRA API DE DATOS
        for (int x = 0; x < 1; x++) {
            switch (x) {
                case 0:
                    respuestaUno.setText(respuestasIncorrectas.get(x));
                    break;
                case 1:
                    respuestaDos.setText(respuestasIncorrectas.get(x));
                    break;
            }
        }
        //AGREGAMOS LA RESPUESTA CORRECTA DE NUESTRA API EN UN CUARTO RADIO BUTTON
        respuestaDos = view.findViewById(R.id.radioRespuestaDos);
        respuestaDos.setText(respuestaCorrecta);
        //EVENTOS DE RADIO BUTTONS - CODIGO PARA QUE LA SELECCION DEL RADIO BUTTO SEA ACTUALIZADO EN LA VISTA
        grupoRespuestasView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (respuestaUno.isChecked()) {
                    radioButtonValue = 1;
                } else if (respuestaDos.isChecked()) {
                    radioButtonValue = 2;
                }
            }
        });

        return view;
    }

    private void initializeViews(View view) {
        preguntaView = view.findViewById(R.id.pregunta);
        categoriaView = view.findViewById(R.id.categoria);
        grupoRespuestasView = view.findViewById(R.id.radioGrupoRespuestas);
        respuestaUno = view.findViewById(R.id.radioRespuestaUno);
        respuestaDos = view.findViewById(R.id.radioRespuestaDos);
    }
}
