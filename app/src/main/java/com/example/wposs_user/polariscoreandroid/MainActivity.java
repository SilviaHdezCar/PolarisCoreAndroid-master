package com.example.wposs_user.polariscoreandroid;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Logger;

import static com.example.wposs_user.polariscoreandroid.DialogOpcionesConsulta.objeto;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    Vector<Repuesto> repuestos;
    Vector<Etapas> etapas;

    private TextView serial;
    private Button buscar_serial_terminal;
    private Vector<Terminal> terminales;
    Terminal t6;
    Terminal t7;
    Terminal t9;

    //private AutoCompleteTextView serial;

    private Spinner spinner_estado_terminal;
    private EditText f_inicio;
    private EditText f_fin;
    FragmentManager fragmentManager;
   /* public static String Fecha1, Fecha2;

    boolean isChanged=false;
    private static final String CERO = "0";
    private static final String BARRA = "/";


    public final java.util.Calendar c = java.util.Calendar.getInstance();
    final int mes = c.get(java.util.Calendar.MONTH);
    final int dia = c.get(java.util.Calendar.DAY_OF_MONTH);
    final int anio = c.get(java.util.Calendar.YEAR);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(null);
        setSupportActionBar(toolbar);

        agregarTeminalesVector();
        agregarRepuestos();
       // agregarEtapasVector();
        objeto = this;
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


    private void agregarTeminalesVector() {
        this.terminales = new Vector<>();
        Terminal t1 = new Terminal("12AAE4D", "Gertec", "Newpos9220", "WIFI", "nuevo", null);
        Terminal t2 = new Terminal("345", "Gertec", "Newpos6210", "LAN", "Diagnostico", null);
        Terminal t3 = new Terminal("ASFAFAD", "Gertec", "Newpos7210", "DIAL", "Reparación", null);
        Terminal t4 = new Terminal("123", "Gertec", "Newpos7210", "DIAL", "Reparación", null);
        Terminal t5 = new Terminal("1425", "Gertec", "Newpos6210", "DIAL", "Autorizada", null);
        t6 = new Terminal("678", "Gertec", "Newpos9220", "DIAL", "Asociada", null, null, 56, "Le está fallando algo");
        t9 = new Terminal("147", "Newposs", "9220", "DIAL", "Asociada", null, null, -3, "Cuidado, Le está fallando algo");
        t7 = new Terminal("342", "Gertec", "Newpos7220", "WIFI", "Asociada", null, null, 0, "Algo tiene mal");
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

    //***********************************AGREGAR  ETAPAS*************************************
    private void agregarEtapasVector()    {
            this.etapas=new Vector<>();
            Etapas e1=new Etapas(t6,null );
            Etapas e2=new Etapas(t7,null );
            Etapas e3=new Etapas(t7,null );
            Etapas e4=new Etapas(t7,null );

            etapas.add(e1);
            etapas.add(e2);
            etapas.add(e3);
            etapas.add(e4);

        System.err.println("******************estapas creadas");
        }



    //********************************************AGREGAR REPUESTOS*********************************************************************************************



    private void agregarRepuestos() {
        repuestos = new Vector<>();

        Repuesto r1 = new Repuesto("AS1252", "TORNILLO", "DISPONIBLE", 3);
        Repuesto r2 = new Repuesto("AB233", "PANTALLA", "AGOTADA", 2);
        Repuesto r3 = new Repuesto("AW3456", "TECLADO", "DISPONIBLE", 3);

        repuestos.add(r1);
        repuestos.add(r2);
        repuestos.add(r3);

    }


    //*******************************BUSQUEDA POR SERIAL

    public void buscarTerminalesPorSerial(View v) {
        this.buscar_serial_terminal = (Button) findViewById(R.id.btn_buscar_terminales_serial);
        serial = (TextView) findViewById(R.id.serial);

        Vector<Terminal> terminal = new Vector<>();
        terminal.removeAllElements();

        if (serial.getText().toString().isEmpty()) {
            Toast.makeText(this, "POR FAVOR INGRESE EL SERIAL", Toast.LENGTH_SHORT).show();
            return;
        }


        for (Terminal ter : this.terminales) {
            if (ter.getSerial().equalsIgnoreCase(serial.getText().toString())) {
                terminal.add(ter);
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales_por_serial);
                recyclerView.setAdapter(new AdapterTerminal(this, terminal));//le pasa los datos-> lista de usuarios
                layoutManager = new LinearLayoutManager(this);// en forma de lista
                recyclerView.setLayoutManager(layoutManager);

            }
        }
        if (terminal.size() == 0) {
            Toast.makeText(this, "NO SE ENCONTRARON TERMINALES REGISTRADAS CON ESE SERIAL", Toast.LENGTH_SHORT).show();
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales_por_serial);
            recyclerView.setAdapter(new AdapterTerminal(this, terminal));//le pasa los datos-> lista de usuarios
            layoutManager = new LinearLayoutManager(this);// en forma de lista
            recyclerView.setLayoutManager(layoutManager);
        }
        serial.setText("");
    }

    //******************************BUSQUEDA POR FECHAS*****************************


    public void buscarTerminalesFechas(View V) {
        spinner_estado_terminal = (Spinner) findViewById(R.id.sp_estado_terminal);
      String estado= spinner_estado_terminal.getSelectedItem().toString();

     // if()




    }


    //METODOS PARA MOSTRAR EL CALENDARIO




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
        recyclerView.setAdapter(new AdapterTerminal_asociada(this, terminales_asoc));//le pasa los datos-> lista de terminales

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


    public void verDetalles(View view) {
        TextView cod = (TextView) findViewById(R.id.serial_ter_asociada);
        fragmentManager.beginTransaction().replace(R.id.contenedor_main, new EtapasTerminal()).commit();
        Toast.makeText(this, "seleccionó: " + cod.getText().toString(), Toast.LENGTH_LONG).show();

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


    public Vector<Terminal> getTerminales() {
        System.out.println("***************************************LISTA DE TERMINALES********************************************" + this.terminales.size());
        return terminales;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public EditText getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(EditText f_inicio) {
        this.f_inicio = f_inicio;
    }

    public EditText getF_fin() {
        return f_fin;
    }

    public void setF_fin(EditText f_fin) {
        this.f_fin = f_fin;
    }

    public Vector<Etapas> getEtapas() {
        return etapas;
    }

    public Terminal getT6() {
        return t6;
    }

    public Terminal getT7() {
        return t7;
    }

    public Terminal getT9() {
        return t9;
    }
}





