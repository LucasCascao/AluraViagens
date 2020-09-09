package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.MoedaUtil;

public class PagamentoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pagamento";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        setTitle(TITULO_APPBAR);

        configuraPacote();

        mostraPreco(pacote);

        configuraBotaoFinalizaCompra();

    }

    private void configuraPacote() {
        Intent dados = getIntent();
        if(dados.hasExtra("pacoteSelecionado")){
            pacote = (Pacote) dados.getSerializableExtra("pacoteSelecionado");
        } else {
            pacote = new Pacote("São Paulo", "sao_paulo_sp",
                    2, new BigDecimal("243.99"));
        }
    }

    private void configuraBotaoFinalizaCompra() {
        Button btnFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        btnFinalizaCompra.setOnClickListener(view -> {
            Intent intent = new Intent(this, ResumoCompraActivity.class);
            intent.putExtra("pacoteSelecionado", pacote);
            startActivity(intent);
        });
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.pagamento_preco_pacote);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }
}