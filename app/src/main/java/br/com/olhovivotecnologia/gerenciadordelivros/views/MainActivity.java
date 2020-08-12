    package br.com.olhovivotecnologia.gerenciadordelivros.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.olhovivotecnologia.gerenciadordelivros.R;
import br.com.olhovivotecnologia.gerenciadordelivros.adapter.LivroAdapter;
import br.com.olhovivotecnologia.gerenciadordelivros.data.LivroDAO;
import br.com.olhovivotecnologia.gerenciadordelivros.dominio.Livro;

public class MainActivity extends AppCompatActivity implements LivroAdapter.OnLivroListener {

    private LivroDAO livroDAO;
    LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager( this ));

//        listaLivros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",0));
//        listaLivros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
//        listaLivros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
//        listaLivros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
//        listaLivros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
//        listaLivros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
//        listaLivros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));

        livroDAO = LivroDAO.getInstance(this);

        List<Livro> listaLivros = livroDAO.list();

        livroAdapter = new LivroAdapter(listaLivros, this, this);

        recyclerView.setAdapter(livroAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actions,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_adicionar:
                Intent intent = new Intent(getApplicationContext(),EditarLivroActivity.class);
                startActivityForResult(intent, 100);
                return true;
            case R.id.action_sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            atualizaListaLivros();
        }
    }

    public void atualizaListaLivros(){
        List<Livro> livros = livroDAO.list();
        livroAdapter.setItems(livros);
        livroAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLivroClick(int posicao) {
        Toast.makeText(this, "onLivroClick = "+posicao, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLivroLongClick(int posicao) {
        Toast.makeText(this, "onLivroLongClick = "+posicao, Toast.LENGTH_SHORT).show();
    }
}