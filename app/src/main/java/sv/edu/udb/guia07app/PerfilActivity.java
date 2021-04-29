package sv.edu.udb.guia07app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {

    Button btnLogout;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnLogout = findViewById(R.id.btnLogout);

        googleSignInClient = GoogleSignIn.getClient(PerfilActivity.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);
        mAuth = FirebaseAuth.getInstance();

        //Iniciamos la auth de firebase
        mAuth = FirebaseAuth.getInstance();
        //Iniciamos firebase usuario
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null) {
            //Cuando el usuario ya haya iniciado sesion
            //Redirigimos hacia el perfil
            startActivity(new Intent(PerfilActivity.this
                    , LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, LoginActivity.class);
                //Sign out de google
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Si el task es exitoso
                            //sign out de firebase
                            mAuth.signOut();
                            //Mostramos msj en toast
                            Toast.makeText(getApplicationContext()
                                    ,"Logout exitoso",Toast.LENGTH_SHORT).show();
                            //Finalizamos la activity
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }
}