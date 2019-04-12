package com.example.wposs_user.polariscoreandroid;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import static com.example.wposs_user.polariscoreandroid.DialogOpcionesConsulta.objeto;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;
    private TextView lbl_msj_buscar_serial;
    private AutoCompleteTextView serial;

    private Spinner spinner_estado_terminal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(null);
        setSupportActionBar(toolbar);
        agregarTeminalesVector();
        agregarRepuestos();
        objeto=this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor_main, new InicialFragment()).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {


            case R.id.btn_home:
                fragmentManager.beginTransaction().replace(R.id.contenedor_main, new InicialFragment()).commit();
                return true;

            case R.id.btn_aumentar:
                aumentar();
                return true;

            case R.id.btn_disminuir:
                dismuir();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    private void dismuir() {

    }

    private void aumentar() {
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // AL SELECCIONAR ALGUUNA OPCION DEL MENU
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            fragmentManager.beginTransaction().replace(R.id.contenedor_main, new PerfilFragment()).commit();
            // cargarDatosPerfil();
        } else if (id == R.id.nav_stock) {
            fragmentManager.beginTransaction().replace(R.id.contenedor_main, new StockFragment()).commit();
        } else if (id == R.id.nav_consultar_terminales_reparadas) {
            opcionesBusqueda();

        } else if (id == R.id.nav_productividad) {
            fragmentManager.beginTransaction().replace(R.id.contenedor_main, new ProductividadFragment()).commit();

        } else if (id == R.id.nav_cerrar_sesion) {
            Intent i = new Intent(this, Activity_login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private ImageView imgPerfil;
    private TextView nomUsuario;
    private TextView usuario;
    private TextView cargo;
    private TextView telefono;
    private TextView correo;
    private TextView ubicacion;


    void cargarDatosPerfil() {
        imgPerfil = findViewById(R.id.perfil_imagen_usuario);
        //imgPerfil.setBackground();//agregar img que viene del servicio (json) al campo  imagenText

        nomUsuario = (TextView) findViewById(R.id.perfil_nombre_usuario);
        nomUsuario.setText("Silvia HC");//agregar el nomUser que viene del servicio (json) al campo  nombre usuario

        usuario = (TextView) findViewById(R.id.perfil_usuario);
        usuario.setText("");//agregar el nomUser que viene del servicio (json) al campo  usuario

        cargo = (TextView) findViewById(R.id.perfil_cargo);
        cargo.setText("Tecnica");//agregar el cargo que viene del servicio (json) al campo  respectivo

        telefono = (TextView) findViewById(R.id.perfil_telefono);
        telefono.setText("3113203021");//agregar el telefono que viene del servicio (json) al campo  respectivo

        correo = (TextView) findViewById(R.id.perfil_correo);
        correo.setText("silviahernadez@wposs.com");//agregar el cargo que viene del servicio (json) al campo  respectivo

        ubicacion = (TextView) findViewById(R.id.perfil_ubicacion);
        ubicacion.setText("Colombia. Cúcuta");//agregar el telefono que viene del servicio (json) al campo  respectivo


    }


    //METODO QUE MUESTRA EL PANEL PARA ACTUALIZAR LA CLAVE
    public void actualizarClave(View view) {
        CambiarClaveDialogo cambiarClaveDialogo = new CambiarClaveDialogo();
        cambiarClaveDialogo.show(getSupportFragmentManager(), "Actualización de la clave.main");
    }

    public void opcionesBusqueda() {
        DialogOpcionesConsulta dialog = new DialogOpcionesConsulta();
        dialog.show(getSupportFragmentManager(), "Actualización de la clave.main");
    }


    //********************************************AGREGAR TERMINALES*********************************************************************************************
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Vector<Terminal> terminales;

    private void agregarTeminalesVector() {
        this.terminales = new Vector<>();
        Terminal t1 = new Terminal("12AAE4D", "Gertec", "Newpos9220", "WIFI", "nuevo", null);
        Terminal t2 = new Terminal("345", "Gertec", "Newpos6210", "LAN", "Diagnostico", null);
        Terminal t3 = new Terminal("ASFAFAD", "Gertec", "Newpos7210", "DIAL", "Reparación", null);
        Terminal t4 = new Terminal("123", "Gertec", "Newpos7210", "DIAL", "Reparación", null);
        Terminal t5 = new Terminal("1425", "Gertec", "Newpos6210", "DIAL", "Autorizada", null);
        Terminal t6 = new Terminal("678", "Gertec", "Newpos9220", "DIAL", "Asociada", null);
        Terminal t7 = new Terminal("342", "Gertec", "Newpos7220", "WIFI", "Asociada", null);
        Terminal t8 = new Terminal("912", "Gertec", "Newpos6210", "DIAL", "Autorizada", null);
        terminales.add(t1);
        terminales.add(t2);
        terminales.add(t3);
        terminales.add(t4);
        terminales.add(t5);
        terminales.add(t6);
        terminales.add(t7);
        terminales.add(t8);
    }


    //********************************************AGREGAR REPUESTOS*********************************************************************************************

    Vector<Repuesto> repuestos;

    private void agregarRepuestos() {
        repuestos = new Vector<>();

        Repuesto r1 = new Repuesto("AS1252", "TORNILLO", "DISPONIBLE", 3);
        Repuesto r2 = new Repuesto("AB233", "PANTALLA", "AGOTADA", 2);
        Repuesto r3 = new Repuesto("AW3456", "TECLADO", "DISPONIBLE", 3);

        repuestos.add(r1);
        repuestos.add(r2);
        repuestos.add(r3);

    }

//*********************************************METODOS DE BÚSQUEDA***************************
    //metodo que autocompleta
    public void buscarTerminalSerial() {

        serial = (AutoCompleteTextView) findViewById(R.id.serial);
        lbl_msj_buscar_serial = (TextView) findViewById(R.id.lbl_busq_serial);

        lbl_msj_buscar_serial.setText("");

        ArrayList<String> terminales = new ArrayList<>();
        for (Terminal ter : this.terminales) {
            terminales.add(ter.getSerial());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, terminales);
        serial.setAdapter(adapter);
        serial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                buscarPorSerial();
            }
        });
        serial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager in = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(textView.getApplicationWindowToken(), 0);
                    buscarPorSerial();
                    return true;
                }
                return false;
            }
        });
    }


    public void buscarPorSerial() {
        lbl_msj_buscar_serial = (TextView) findViewById(R.id.lbl_busq_serial);
        lbl_msj_buscar_serial.setText("");
        serial = (AutoCompleteTextView) findViewById(R.id.serial);

        Vector<Terminal> terminal = new Vector<>();
        for (Terminal ter : this.terminales) {
            if (ter.getSerial().equalsIgnoreCase(serial.getText().toString())){
                terminal.add(ter);
            }

        }
        if (serial.getText().toString().isEmpty()) {
            this.lbl_msj_buscar_serial.setText("POR FAVOR INGRESE EL SERIAL");
            return;
        }
        if (terminal.size() == 0) {
            this.lbl_msj_buscar_serial.setText("NO SE ENCONTRARON TERMINALES REGISTRADAS CON ESE SERIAL");
            terminal.removeAllElements();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales_serial);
        recyclerView.setAdapter(new AdapterTerminal(this, terminal));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);


        this.serial.setText("");
    }

    //******************************BUSQUEDA POR FECHAS*****************************

        public void buscarTerminalesFechas(View V){
        spinner_estado_terminal = (Spinner)findViewById(R.id.sp_estado_terminal);


        }


    //METODOS PARA MOSTRAR EL CALENDARIO
    public void mostrarCalendar(View view){
        EditText f_inicio =(EditText)findViewById(R.id.txt_fecha_inicio);
        EditText f_fin =(EditText)findViewById(R.id.txt_fecha_fin);

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker")
    }

    //***********TERMINALES ASOCIADAS
    public void verTerminalesAsociadas(View v) {
        btn_asociadas = (Button) findViewById(R.id.btn_terminales_asociadas);
        btn_autorizadas = (Button) findViewById(R.id.btn_terminales_autorizadas);

        btn_autorizadas.setBackgroundColor(0x802196F5);

        btn_asociadas.setBackgroundColor(0x45A5F3);

        Vector<Terminal> terminales_asoc = new Vector<>();
        for (Terminal ter : this.terminales) {
            if (ter.getEstado().equalsIgnoreCase("Asociada")) {
                terminales_asoc.add(ter);
            }

        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales_inicial);
        recyclerView.setAdapter(new AdapterTerminal(this, terminales_asoc));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);

    }








    //*************AUTORIZADAS
    public void verTerminalesAutorizadas(View v) {
        btn_asociadas = (Button) findViewById(R.id.btn_terminales_asociadas);
        btn_autorizadas = (Button) findViewById(R.id.btn_terminales_autorizadas);

        btn_asociadas.setBackgroundColor(0x802196F5);

        btn_autorizadas.setBackgroundColor(0x45A5F3);

        Vector<Terminal> terminales_aut = new Vector<>();
        for (Terminal ter : this.terminales) {
            if (ter.getEstado().equalsIgnoreCase("Autorizada")) {
                terminales_aut.add(ter);
            }

        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales_inicial);
        recyclerView.setAdapter(new AdapterTerminal(this, terminales_aut));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);
    }


    //METODOS LISTA LAS TERMINALES QUE TIENEN DIAGNOSTICO
    private Button btn_asociadas;
    private Button btn_autorizadas;


    public void verDetalles() {
        TextView serial = (TextView) findViewById(R.id.serial_ter);

        Toast.makeText(this, "seleccionó: " + serial.getText().toString(), Toast.LENGTH_LONG).show();
    }

    //*****CARGAR TERMINALES A STOCK


    private Button btn_mostrarTerminales;


    public void cargarTerminal_stock(View view) {


        btn_mostrarTerminales = (Button) findViewById(R.id.btn_ter_stock);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_stock);
        recyclerView.setAdapter(new AdapterTerminal(this, this.terminales));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);
    }

    Button btn_mostrarRepuestos;

    public void cargarRepuesto_stock(View view) {

        btn_mostrarRepuestos = (Button) findViewById(R.id.btn_rep_stock);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_stock);
        recyclerView.setAdapter(new AdapterRepuesto(this, repuestos));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);
    }
}





