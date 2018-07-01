
package pe.cajami.com.cursoandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pe.cajami.com.cursoandroid.R;

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
        String[] arrayTipoDocumento= new String[]{"(Seleccione)", "DNI", "CARNET EXTRANJERÍA"};

        ArrayAdapter<String> adapterTipoUsuario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoUsuario);
        ArrayAdapter<String> adapterTipoDocumento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoDocumento);

        cboTipoUsuario_RegisterActivity.setAdapter(adapterTipoUsuario);
        cboTipoDocumento_RegisterActivity.setAdapter(adapterTipoDocumento);


    }
}
