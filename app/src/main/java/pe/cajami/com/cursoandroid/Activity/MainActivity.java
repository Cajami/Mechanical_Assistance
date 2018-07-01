package pe.cajami.com.cursoandroid.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import pe.cajami.com.cursoandroid.R;

public class MainActivity extends AppCompatActivity {

    CardView tarjetaNotificaciones_Main,tarjetaGPS,tarjetaAsistenciaMecanica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarjetaNotificaciones_Main = (CardView) findViewById(R.id.tarjetaNotificaciones_Main);
        tarjetaGPS = (CardView) findViewById(R.id.tarjetaGPS);
        tarjetaAsistenciaMecanica = (CardView) findViewById(R.id.tarjetaAsistenciaMecanica);

        tarjetaNotificaciones_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DetailsNotificationActivity.class);
                startActivity(intent);
            }
        });
        tarjetaGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        tarjetaAsistenciaMecanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence arrayAsistencia [] = new CharSequence[] {"Equipo y maquinaria", "Material de Consumo" , "Repuestos"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("¿Qué desea solicitar?");
                builder.setItems(arrayAsistencia, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        Toast.makeText(MainActivity.this    ,"Uds Seleccionó: " + arrayAsistencia[which] ,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }
}
