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
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(null);
        setSupportActionBar(toolbar);
        agregarTeminalesVector();
        agregarRepuestos();

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

            //a lo que de clic me debe salir un cuadro de dialogo para que seleccione el tipo de busqueda
            fragmentManager.beginTransaction().replace(R.id.contenedor_main, new ConsultaTerminalesReparadasFragm()).commit();

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
        repuestos= new Vector<>();

       Repuesto r1= new Repuesto("AS1252", "TORNILLO","DISPONIBLE",3);
        Repuesto r2= new Repuesto("AB233", "PANTALLA","AGOTADA",2);
        Repuesto r3= new Repuesto("AW3456", "TECLADO","DISPONIBLE",3);

        repuestos.add(r1);
        repuestos.add(r2);
        repuestos.add(r3);

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
        btn_autorizadas= (Button) findViewById(R.id.btn_terminales_autorizadas);

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
    private Button btn_diagnostico;
    private Button btn_reparadas;
    private Button btn_asociadas;
    private Button btn_autorizadas;

    public void verTerminalesDiasnostico(View view) {
        btn_diagnostico = (Button) findViewById(R.id.btn_terminales_diagnostico);
        btn_reparadas = (Button) findViewById(R.id.btn_terminales_reparadas);

        btn_reparadas.setBackgroundColor(0x802196F5);

        btn_diagnostico.setBackgroundColor(0x45A5F3);


     /*   Vector<Terminal> terminales_diagnostico = new Vector<>();
        for (Terminal ter : this.terminales) {
            if (ter.getEstado().equalsIgnoreCase("Diagnostico")) {
                terminales_diagnostico.add(ter);
            }

        }


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales);
        recyclerView.setAdapter(new AdapterTerminal(this, terminales_diagnostico));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);*/


    }

    //ESTE METODO LISTA LAS TERMINALES REPARADAS
    public void verTerminalesReparadas(View view) {
        btn_diagnostico = (Button) findViewById(R.id.btn_terminales_diagnostico);
        btn_reparadas = (Button) findViewById(R.id.btn_terminales_reparadas);

        btn_diagnostico.setBackgroundColor(0x802196F5);
        btn_reparadas.setBackgroundColor(0x45A5F3);

    /*    Vector<Terminal> terminales_rep = new Vector<>();
        for(Terminal ter:this.terminales){
            if (ter.getEstado().equalsIgnoreCase("Reparación")){
                terminales_rep.add(ter);
            }

        }

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_consultaTerminales);
        recyclerView.setAdapter(new AdapterTerminal(this, terminales_rep));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);*/
    }


    private Button buscar_serial_terminal;
    private TextView lbl_msj_buscar_serial;
    private EditText txt_serial;

    public void buscarPorSerial(View c) {
        this.buscar_serial_terminal = (Button) findViewById(R.id.btn_buscar_serial);
        lbl_msj_buscar_serial = (TextView) findViewById(R.id.lbl_busq_serial);
        txt_serial = (EditText) findViewById(R.id.serial);
        lbl_msj_buscar_serial.setText("");

        Vector<Terminal> terminal = new Vector<>();
        for (Terminal ter : this.terminales) {
            if (ter.getSerial().equalsIgnoreCase(txt_serial.getText().toString())&&
                    (ter.getEstado().equalsIgnoreCase("Diagnostico")||ter.getEstado().equalsIgnoreCase("Reparación"))) {
                terminal.add(ter);
            }

        }
        if(txt_serial.getText().toString().isEmpty()){
            this.lbl_msj_buscar_serial.setText("POR FAVOR INGRESE EL SERIAL");
            return;
        }
        if (terminal.size() == 0) {
            this.lbl_msj_buscar_serial.setText("NO SE ENCONTRARON TERMINALES REGISTRADAS CON ESE SERIAL");
            terminal.removeAllElements();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultaTerminales);
        recyclerView.setAdapter(new AdapterTerminal(this, terminal));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);


        this.txt_serial.setText("");
    }


    public void limpiar(View v) {
        Vector<Terminal> terminal = new Vector<>();
    }


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
        recyclerView.setAdapter(new AdapterRepuesto(this,repuestos));//le pasa los datos-> lista de usuarios

        layoutManager = new LinearLayoutManager(this);// en forma de lista
        recyclerView.setLayoutManager(layoutManager);
    }
}





