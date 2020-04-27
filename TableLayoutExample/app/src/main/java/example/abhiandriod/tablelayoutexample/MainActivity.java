package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.abhiandriod.tablelayoutexample.Model.Model;
import example.abhiandriod.tablelayoutexample.Model.User;

public class MainActivity extends AppCompatActivity {

    private Model model;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate a button
        Button loginButton = (Button) findViewById(R.id.loginBtn);
        this.userName = (EditText) findViewById(R.id.userName);
        this.password = (EditText) findViewById(R.id.password);

        // initiate the model
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
    }


}
