package sv.edu.udb.guia07app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.guia07app.Modelo.Carrito;
import sv.edu.udb.guia07app.Modelo.Producto;
import sv.edu.udb.guia07app.Productos.AgregarProducto;

public class ShopActivity extends AppCompatActivity {


    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refCarrito = database.getReference("carrito");

    List<Carrito> carritos;
    ListView listaCarrito;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;
    Button btn_shop;
    ProgressDialog progressDialog;
    TextView txTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        inicializar();
        totalPrecio();

        btn_shop = (Button) findViewById(R.id.btn_shop);
        txTotal = (TextView)findViewById(R.id.txTotalPrecio);

        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DashboardActivity.class);

                startActivity(i);
            }
        });
    }

    private void inicializar() {
        listaCarrito = findViewById(R.id.ListaCarro);
        carritos = new ArrayList<>();

        googleSignInClient = GoogleSignIn.getClient(ShopActivity.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);
        //Iniciamos la auth de firebase
        mAuth = FirebaseAuth.getInstance();
        //Iniciamos firebase usuario
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        // Ordenamiento
        Query consultaOrdenada = refCarrito.orderByChild("correo_usuario").equalTo(firebaseUser.getEmail());

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                carritos.removeAll(carritos);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Carrito carrito = dato.getValue(Carrito.class);
                    carrito.setKey(dato.getKey());
                    carritos.add(carrito);
                }

                AdaptadorShop adapter = new AdaptadorShop(ShopActivity.this,
                        carritos);
                listaCarrito.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void totalPrecio() {
        //Iniciamos la auth de firebase
        mAuth = FirebaseAuth.getInstance();
        //Iniciamos firebase usuario
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Query consulta = refCarrito.orderByChild("correo_usuario").equalTo(firebaseUser.getEmail());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double count = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    double rating = ds.child("precio").getValue(Double.class);
                    count = count + rating;
                }
                txTotal.setText("$" + count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        consulta.addListenerForSingleValueEvent(eventListener);
    }
}