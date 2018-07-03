package pe.cajami.com.cursoandroid.configuration;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.Toast;

import pe.cajami.com.cursoandroid.R;

public class FunctionsGlobals {

    public static void showMessageCorto(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void MensajeErrorUsuario(Context context, String mensaje) {
        MostrarAlertDialog(context, "(ERROR)", mensaje, R.drawable.warning);
    }

    private static void MostrarAlertDialog(
            Context context,
            String titulo,
            String mensaje,
            int icono) {
        new AlertDialog.Builder(context)
                .setMessage(Html.fromHtml(mensaje))
                .setIcon(icono)
                .setTitle(titulo)
                .setCancelable(false)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }



}
