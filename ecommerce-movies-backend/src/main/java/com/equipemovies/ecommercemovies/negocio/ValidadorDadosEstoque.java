package com.equipemovies.ecommercemovies.negocio;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Estoque;

public class ValidadorDadosEstoque implements IStrategy {


    @Override
    public String processar(EntidadeDominio entidade) {

        // para pegar os erros dos campos e exibir m uma mensagem no form(para fazer a concatenação nos if's aqui em baixo)
        String erros = "";

        if(entidade instanceof Estoque) {
            Estoque estoque = (Estoque) entidade;

            if (estoque.getId() != null && estoque.getStatus() != null) {
                return null;
            }

            String fornecedor = estoque.getFornecedor();
            int quantidadeEstoque = estoque.getQuantidadeEstoque();
            float valorCompra = estoque.getValorCompra();
            float valorVenda = estoque.getValorVenda();

            // 1º regra
            if (fornecedor == null || quantidadeEstoque == 0 || valorCompra == 0 || valorVenda == 0 ){
                erros += "Todos os campos são de preenchimento obrigatório!\n";
            } else {
                if (fornecedor.trim().equals("")){
                    erros += "Nome do fornecedor é de preenchimento obrigatório!";
                }
            }

            // 2º regra
            if (fornecedor != null && fornecedor.length() > 50) {
                erros += "O nome do fornecedor deve conter até 50 (cinquenta) caracteres";
            }

            // 3º regra
            if (valorCompra <= 0) {
                erros += "O valor de compra deve ser maior que 0 (zero)";
            }

            // 4º regra
            if (valorVenda <= 0) {
                erros += "O valor de venda deve ser maior que 0 (zero)";
            }

            // 5º regra
            if (quantidadeEstoque <= 0) {
                erros += "A quantidade em estoque deve ser maio que 0 (zero)";
            }

        } else {

            erros += "Deve ser realizado o registro de um filme!";
        }

        return erros.equals("") ? null : erros;

    }
}

