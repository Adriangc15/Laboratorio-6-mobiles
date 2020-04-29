package example.abhiandriod.tablelayoutexample;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Random;

public class activity_singup extends AppCompatActivity implements View.OnClickListener {

    public EditText fecha;
    public ImageButton imgFecha;

    public Button guardar;


    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);



    private EditText nombre;
    private EditText apellido;
    private EditText direccion;
    private EditText segundaDireccion;
    private EditText ciudad;
    private EditText provincia;
    private EditText zip;
    private Spinner pais;
    private EditText correo;
    private EditText codigoArea;
    private EditText telefono;
    private Spinner puesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        nombre = (EditText)findViewById(R.id.IdNombre);
        apellido  = (EditText) findViewById(R.id.idApellido);
        direccion = (EditText) findViewById(R.id.idDireccion);
        segundaDireccion = (EditText) findViewById(R.id.idSegundaDireccion);
        ciudad = (EditText) findViewById(R.id.idCiudad);
        provincia = (EditText) findViewById(R.id.idProvincia);
        zip = (EditText) findViewById(R.id.idZip);
        pais = (Spinner) findViewById(R.id.idPais);
        correo = (EditText) findViewById(R.id.idCorreo);
        codigoArea =(EditText) findViewById(R.id.idCodigoArea);
        telefono = (EditText) findViewById(R.id.idTelefono);
        puesto = (Spinner) findViewById(R.id.idPosicion);

        fecha = (EditText) findViewById(R.id.idFecha);
        imgFecha = (ImageButton) findViewById(R.id.idObtenerFecha);
        imgFecha.setOnClickListener(this);

        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Vnombre = nombre.getText().toString();
                String Vapellido = apellido.getText().toString();
                String Vdireccion = direccion.getText().toString();
                String VsegundaDireccin = segundaDireccion.getText().toString();
                String Vprovincia = provincia.getText().toString();
                String Vciudad = ciudad.getText().toString();
                String Vzip = zip.getText().toString();
                String Vpais = pais.getSelectedItem().toString();
                String VcodigoArea = codigoArea.getText().toString();
                int Vnumero = Integer.parseInt(telefono.getText().toString()) ;
                String Vfecha = fecha.getText().toString();
                String VformID = String.valueOf(new Random());
                String Vpuesto = puesto.getSelectedItem().toString();

                Intent intent = new Intent(activity_singup.this, ADMListar.class);
                activity_singup.this.startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idObtenerFecha:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}
