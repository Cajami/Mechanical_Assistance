
package pe.cajami.com.cursoandroid.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndStringRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;
import pe.cajami.com.cursoandroid.R;
import pe.cajami.com.cursoandroid.configuration.FunctionsGlobals;
import pe.cajami.com.cursoandroid.configuration.MechanicalApi;

public class RegisterActivity extends AppCompatActivity {

    Spinner cboTipoUsuario_RegisterActivity, cboTipoDocumento_RegisterActivity;
    EditText txtNroDocumento_RegisterActivity, txtUsuario_RegisterActivity, txtContrasenia_RegisterActivity, txtRepetirContrasenia_RegisterActivity;
    Button btnRegistrar_RegisterActivity, btnCancelar_RegisterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cboTipoUsuario_RegisterActivity = (Spinner) findViewById(R.id.cboTipoUsuario_RegisterActivity);
        cboTipoDocumento_RegisterActivity = (Spinner) findViewById(R.id.cboTipoDocumento_RegisterActivity);

        txtNroDocumento_RegisterActivity = (EditText) findViewById(R.id.txtNroDocumento_RegisterActivity);
        txtUsuario_RegisterActivity = (EditText) findViewById(R.id.txtUsuario_RegisterActivity);
        txtContrasenia_RegisterActivity = (EditText) findViewById(R.id.txtContrasenia_RegisterActivity);
        txtRepetirContrasenia_RegisterActivity = (EditText) findViewById(R.id.txtRepetirContrasenia_RegisterActivity);

        btnRegistrar_RegisterActivity = (Button) findViewById(R.id.btnRegistrar_RegisterActivity);
        btnCancelar_RegisterActivity = (Button) findViewById(R.id.btnCancelar_RegisterActivity);

        String[] arrayTipoUsuario = new String[]{"(Seleccione)", "CLIENTE", "PROVEEDOR"};
        String[] arrayTipoDocumento = new String[]{"(Seleccione)", "DNI", "CARNET EXTRANJERÍA"};

        ArrayAdapter<String> adapterTipoUsuario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoUsuario);
        ArrayAdapter<String> adapterTipoDocumento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoDocumento);

        cboTipoUsuario_RegisterActivity.setAdapter(adapterTipoUsuario);
        cboTipoDocumento_RegisterActivity.setAdapter(adapterTipoDocumento);

        btnCancelar_RegisterActivity.setOnClickListener(btnCancelar_OnClickListener);
        btnRegistrar_RegisterActivity.setOnClickListener(btnRegistrar_OnClickListener);

/*
        AndroidNetworking.get("http://192.168.1.9:3000/api/user")
                .setPriority(Priority.IMMEDIATE)
                .setTag("Mechanical")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            txtNroDocumento_RegisterActivity.setText(response.getJSONObject(0).getString("user"));
                            txtUsuario_RegisterActivity.setText(response.getJSONObject(1).getString("user"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        String ssss = "";

                    }
                });
    */

    }

    View.OnClickListener btnCancelar_OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    View.OnClickListener btnRegistrar_OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String vCodTipoUsuario = cboTipoUsuario_RegisterActivity.getSelectedItem().toString();
            String vCodTipoDocumento = cboTipoDocumento_RegisterActivity.getSelectedItem().toString();
            String nroDocumento = txtNroDocumento_RegisterActivity.getText().toString().trim();
            String usuario = txtUsuario_RegisterActivity.getText().toString().trim();
            String clave = txtContrasenia_RegisterActivity.getText().toString().trim();
            String claveRepetir = txtRepetirContrasenia_RegisterActivity.getText().toString().trim();

            if (vCodTipoUsuario.equalsIgnoreCase("(Seleccione)")) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Debe seleccionar un Tipo de Usuario");
                return;
            }else{
                if (vCodTipoUsuario.equalsIgnoreCase(""))
                    vCodTipoUsuario = "1";
                else
                    vCodTipoUsuario = "2";
            }

            if (vCodTipoDocumento.equalsIgnoreCase("(Seleccione)")) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Debe seleccionar un Tipo de Documento");
                return;
            }else{
                if (vCodTipoDocumento.equalsIgnoreCase("DNI"))
                    vCodTipoDocumento = "1";
                else
                    vCodTipoDocumento = "2";
            }

            if (nroDocumento.length() == 0) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Debe ingresar un Nro de Documento");
                return;
            }

            if (usuario.length() == 0) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Debe ingresar un Usuario");
                return;
            }

            if (clave.length() == 0 || claveRepetir.length() == 0) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Clave Inválida");
                return;
            }

            if (!clave.equalsIgnoreCase(claveRepetir)) {
                FunctionsGlobals.showMessageCorto(RegisterActivity.this, "Claves incorrectas");
                return;
            }

            final ProgressDialog mProgressDialog = new ProgressDialog(RegisterActivity.this);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Registrando Usuario");
            mProgressDialog.show();

            AndroidNetworking.post(MechanicalApi.getRegisterUser())
                    .addBodyParameter("iTipoUsuario", vCodTipoUsuario)
                    .addBodyParameter("iTipoDocumento", vCodTipoDocumento)
                    .addBodyParameter("vNroDocumento", nroDocumento)
                    .addBodyParameter("vUsuario", usuario)
                    .addBodyParameter("vClave", clave)
                    .setPriority(Priority.IMMEDIATE)
                    .setTag("Mechanical")
                    .build()
                    //.getAsJSONArray(new JSONArrayRequestListener() {
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            mProgressDialog.dismiss();
                            try{


                            }catch (Exception ex){

                            }

                            String sss = "";


                        }

                        @Override
                        public void onError(ANError anError) {
                            mProgressDialog.dismiss();
                            FunctionsGlobals.MensajeErrorUsuario(RegisterActivity.this,"Error al conectarse con el servidor");
                        }
                    });

        }
    };
}
