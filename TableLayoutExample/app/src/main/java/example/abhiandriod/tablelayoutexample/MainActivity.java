package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import example.abhiandriod.tablelayoutexample.Model.Model;
import example.abhiandriod.tablelayoutexample.Model.User;

public class MainActivity extends AppCompatActivity {

    private Model model;
    private EditText userName;
    private EditText password;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate a button
        Button loginButton = (Button) findViewById(R.id.loginBtn);
        this.userName = (EditText) findViewById(R.id.userName);
        this.password = (EditText) findViewById(R.id.password);
        this.signUp = (TextView) findViewById(R.id.signUp);

        // initiate the model
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.model = (Model) bundle.getSerializable("model");
        if(this.model == null)
            this.model = new Model();

        // perform click event on the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String email = userName.getText().toString();
                for (User user : model.getListaUsuarios()){
                    if (user.getEmail().toLowerCase().equals(email) && user.getPassword().equals(pass)){
                        model.setLoggedUser(user);
                    }
                }
                if (model.getLoggedUser() == null){
                    Toast.makeText(MainActivity.this,"Usuario o contraseña invalido", Toast.LENGTH_LONG).show();
                } else{
                    Intent intent = new Intent(MainActivity.this, activity_singup.class);
                    intent.putExtra("model", model);
                    MainActivity.this.startActivity(intent);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_create_account.class);
                intent.putExtra("model", model);
                MainActivity.this.startActivity(intent);
            }
        });


    }


}
