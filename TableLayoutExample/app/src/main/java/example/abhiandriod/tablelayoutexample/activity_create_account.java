package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import example.abhiandriod.tablelayoutexample.Model.Model;
import example.abhiandriod.tablelayoutexample.Model.User;

public class activity_create_account extends AppCompatActivity {

    private Model model;
    private EditText emailET;
    private EditText passwordET;
    private EditText confPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.model = (Model) bundle.getSerializable("model");

        if (this.model == null)
            this.model = new Model();

        this.emailET = (EditText) findViewById(R.id.emailTxt);
        this.passwordET = (EditText) findViewById(R.id.passwordTxt);
        this.confPasswordET = (EditText) findViewById(R.id.confPasswordTxt);

        Button createBtn = (Button) findViewById(R.id.createBtn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().toLowerCase();
                String password = passwordET.getText().toString();
                String confPassword = confPasswordET.getText().toString();

                switch (this.validateForm(email,password,confPassword)){
                    case 0:
                        User user = new User(String.valueOf(new Random()), "", email, password, 1 );
                        model.addUser(user);
                        Toast.makeText(activity_create_account.this, String.format("Usuario %s creado con exito con el ID %s!", user.getEmail(), user.getUserId()), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity_create_account.this, MainActivity.class);
                        intent.putExtra("model", model);
                        activity_create_account.this.startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(activity_create_account.this, "Todos los campos del formulario son necesarios.", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(activity_create_account.this, "Las contraseñas ingresadas no coinciden.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(activity_create_account.this, "El email ingresado ya esta registrado para otro usuario.", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(activity_create_account.this, "Error desconocido.", Toast.LENGTH_LONG).show();
                        break;
                }


            }

            public int validateForm(String email, String password, String confPassword){
                if (email.isEmpty() || password.isEmpty() || confPassword.isEmpty()){
                    return 1;
                }
                if (!password.equals(confPassword)){
                    return 2;
                }
                for (User user : model.getListaUsuarios()){
                    if (user.getEmail().toLowerCase().equals(email))
                        return 3;
                }

                return 0;
            }
        });
    }
}
