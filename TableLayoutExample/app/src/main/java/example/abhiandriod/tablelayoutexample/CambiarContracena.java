package example.abhiandriod.tablelayoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.abhiandriod.tablelayoutexample.Model.Model;
import example.abhiandriod.tablelayoutexample.Model.User;

public class CambiarContracena extends AppCompatActivity {

    private EditText correo;
    private EditText antiguaContracena;
    private EditText nuevaContracena;
    private EditText confirmarContracena;

    private Button crear;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contracena);
        this.correo = (EditText) findViewById(R.id.idCorreo);
        this.antiguaContracena = (EditText) findViewById(R.id.idAntiguaContracena);
        this.nuevaContracena = (EditText) findViewById(R.id.idNuevaContracena);
        this.confirmarContracena = (EditText) findViewById(R.id.idConfirmarContracena);

        this.crear = (Button) findViewById(R.id.idCrear);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.model = (Model) bundle.getSerializable("model");

        if (this.model == null)
            this.model = new Model();

        crear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String Vcorreo = correo.getText().toString();
                String VantiguaContracena = antiguaContracena.getText().toString();
                String VnuevaContracena = nuevaContracena.getText().toString();
                String VconfirmarContracena = confirmarContracena.getText().toString();

                switch (validateForm(Vcorreo,VantiguaContracena,VnuevaContracena,VconfirmarContracena)) {
                    case 0:
                        for (User user : model.getListaUsuarios()) {
                            if (user.getEmail().toLowerCase().equals(Vcorreo)) {
                                user.setPassword(VnuevaContracena);
                            }
                        }
                        Intent intent = new Intent(CambiarContracena.this, MainActivity.class);
                        intent.putExtra("model", model);
                        CambiarContracena.this.startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(CambiarContracena.this, "Todos los campos del formulario son necesarios.", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(CambiarContracena.this, "Las contracena no coincide.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(CambiarContracena.this, "Contracena nueva y la antigua no coinsiden.", Toast.LENGTH_LONG).show();
                        break;
                }



            }
            public int validateForm(String email, String password,String newPassword, String confPassword) {
                if (email.isEmpty() || password.isEmpty() || confPassword.isEmpty() || newPassword.isEmpty()) {
                    return 1;
                }
                if (!newPassword.equals(confPassword)) {
                    return 2;
                }
                for (User user : model.getListaUsuarios()) {
                    if (user.getEmail().toLowerCase().equals(email)&&!user.getPassword().equals(password))
                        return 3;
                }

                return 0;
            }
        });


    }
}
