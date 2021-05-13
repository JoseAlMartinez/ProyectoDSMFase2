package sv.edu.udb.guia07app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class AddireccionActivity extends AppCompatActivity {

    private TextView mTextViewData;
    private DatabaseReference mDatabase;
    private EditText emailTV, passwordTV;
    private Button regBtn;
    private ProgressDialog progressBar;
    GoogleSignInClient googleSignInClient;

    //Declarando botones de navegacion
    Button btn_shop;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);

        initializeViews();

        //Ingreso al carrito de compras
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddireccionActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });



    }




    private void initializeViews() {
        btn_shop = findViewById(R.id.btn_addireccion1);
    }



}