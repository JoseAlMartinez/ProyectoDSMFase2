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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DireccionActivity extends AppCompatActivity {

    private TextView mTextViewData;
    private DatabaseReference mDatabase;
    private Button regBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog progressBar;

    //Declarando botones de navegacion
    Button btn_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion);

        initializeViews();

        //Ingreso al carrito de compras
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DireccionActivity.this, AddireccionActivity.class);
                startActivity(intent);
            }
        });


    }




    private void initializeViews() {
        btn_shop = findViewById(R.id.btn_direccAdd);
    }

}