package sv.edu.udb.guia07app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import sv.edu.udb.guia07app.Direccion.ActividadDireccion;
import sv.edu.udb.guia07app.Direccion.AdaptadorDireccion;
import sv.edu.udb.guia07app.Direccion.AgregarDireccion;
import sv.edu.udb.guia07app.Modelo.Direccion;

public class SeleccionDireccionActivity extends AppCompatActivity {

    TextView txDireccion;
    List<Direccion> direcciones;
    ListView listaDireccion;
    Button btnCancel;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refDireccion = database.getReference("direccion");
    DatabaseReference ref;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_direccion);
        listaDireccion = findViewById(R.id.ListaDireccion);
        txDireccion = (TextView)findViewById(R.id.tvDirecc);
        btnCancel = (Button)findViewById(R.id.btnCancelar);

       /* ref = FirebaseDatabase.getInstance().getReference();

        ref.child("carrito").child()*/


        inicializar();

    }

    private void inicializar() {
        //Iniciamos la auth de firebase
        mAuth = FirebaseAuth.getInstance();
        //Iniciamos firebase usuario
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Query consulta = refDireccion.orderByChild("correo").equalTo(firebaseUser.getEmail());

        direcciones = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consulta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                direcciones.removeAll(direcciones);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Direccion direccion = dato.getValue(Direccion.class);
                    direccion.setKey(dato.getKey());
                    direcciones.add(direccion);
                }

                AdaptadorDireccion adapter = new AdaptadorDireccion(SeleccionDireccionActivity.this,
                        direcciones);
                listaDireccion.setAdapter(adapter);

                if(adapter.getCount() == 0){
                    txDireccion.setText("No tiene ninguna direccion registrada");
                }else {
                    txDireccion.setText("Selecciona una direccion para el envio");
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Cuando el usuario haga clic en la lista
        try {
            listaDireccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                    //String direc = direcciones.get(i).getDireccion();
                    Toast.makeText(SeleccionDireccionActivity.this,
                            "Se realizo la compra correctamente", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(SeleccionDireccionActivity.this,
                    "No tiene direcciones registradas!", Toast.LENGTH_SHORT).show();
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(i);
            }
        });

    }

}