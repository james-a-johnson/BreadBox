package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameField = findViewById(R.id.UsernameField);
        final EditText passwordField = findViewById(R.id.PasswordField);
        Button loginButton = findViewById(R.id.LoginButton);
        Button registerButton = findViewById(R.id.RegisterButton);

        final String username = "user";
        final String password = "pass";

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (usernameField.getText().equals(username)
                        && passwordField.getText().equals(password)) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
        }}
        );

    }

}
