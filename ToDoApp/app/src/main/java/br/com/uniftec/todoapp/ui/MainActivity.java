package br.com.uniftec.todoapp.ui;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.ds.AtividadeDataSource;
import br.com.uniftec.todoapp.model.Atividade;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gerarMocks();

        Toolbar toolbar = (Toolbar)findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main_drawer);
        navigationView = (NavigationView)findViewById(R.id.activity_main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ToDoListFragment listFragment = new ToDoListFragment();
        MapFragment mapFragment = new MapFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_container, mapFragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();

        switch (item.getItemId()){
            case R.id.menu_to_do:
                return true;
            case R.id.menu_estatistica:
                Toast.makeText(this, "Estatísticas", Toast.LENGTH_LONG).show();
                return true;
        }



        return false;
    }

    private void gerarMocks(){
        Atividade atividade = new Atividade();
        atividade.setTitulo("Titulo 1");
        atividade.setNota("Nota 1");
        AtividadeDataSource.getInstance().adicionar(atividade);

        atividade = new Atividade();
        atividade.setTitulo("Titulo 2");
        atividade.setNota("Nota 2");
        AtividadeDataSource.getInstance().adicionar(atividade);

        atividade = new Atividade();
        atividade.setTitulo("Titulo 3");
        atividade.setNota("Nota 3");
        AtividadeDataSource.getInstance().adicionar(atividade);
    }
}
