package com.example.proyecto_fisica;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.proyecto_fisica.fragmentos.fr_informacion;
import com.example.proyecto_fisica.fragmentos.fr_inicio;
import com.example.proyecto_fisica.fragmentos.fr_login_cuestionario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_fisica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        drawer=findViewById(R.id.drawer_layout);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_calculadora, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawer, binding.appBarMain.toolbar,0,0);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(id==R.id.nav_home){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("INICIO");
            cargarFragmento(new fr_inicio());
        }else if(id==R.id.nav_calculadora) {
            abrir(CalculadoraActivity.class);
        }else if(id==R.id.nav_cuestionario) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("CUESTIONARIO");
            cargarFragmento(new fr_login_cuestionario());
        }else if(id==R.id.nav_instagram) {
            Uri uri = Uri.parse("https://www.instagram.com/_bitedev_?igsh=YnQzeTk5czk4ZjQ3");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.instagram.android");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {

                //No encontró la aplicación, abre la versión web.
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/_bitedev_?igsh=YnQzeTk5czk4ZjQ3")));

            }
        } else if (id == R.id.nav_contacto) {
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME,"Programador");
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL,"mikyloher@gmail.com");
            intent.putExtra(ContactsContract.Intents.Insert.PHONE,"5628486153");
            startActivity(intent);

        } else if (id == R.id.nav_informacion) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("INFORMACION ");
            cargarFragmento(new fr_informacion());
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void cargarFragmento(Fragment fragment){
        FragmentManager fragmentM= getSupportFragmentManager();
        fragmentM.beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
    }
    public void abrir(Class cl){

        Intent intent = new Intent(this, cl);
        startActivity(intent);

    }
}