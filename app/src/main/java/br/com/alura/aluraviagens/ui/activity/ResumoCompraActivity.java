package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo da Compra";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);

        setTitle(TITULO_APPBAR);

        configuraPacote();

        mostraImagem();
        mostraLocal();
        mostraDias();
        mostraPreco();
    }

    private void mostraPreco() {
        TextView preco = findViewById(R.id.resumo_compra_preco);
        preco.setText(MoedaUtil.formataParaBrasileiro(pacote.getPreco()));
    }

    private void mostraDias() {
        TextView dias = findViewById(R.id.resumo_compra_dias);
        dias.setText(DiasUtil.formataEmTexto(pacote.getDias()));
    }

    private void mostraLocal() {
        TextView local = findViewById(R.id.resumo_compra_local);
        local.setText(pacote.getLocal());
    }

    private void mostraImagem() {
        ImageView imagem = findViewById(R.id.resumo_compra_imagem);
        imagem.setImageDrawable(ResourceUtil.devolveDrawable(this, pacote.getImagem()));
    }

    private void configuraPacote() {
        Intent dados = getIntent();
        if(dados.hasExtra("pacoteSelecionado")){
            pacote = (Pacote) dados.getSerializableExtra("pacoteSelecionado");
        } else {
            pacote = new Pacote("São Paulo", "sao_paulo_sp", 2, new BigDecimal("243.99"));
        }
    }
}