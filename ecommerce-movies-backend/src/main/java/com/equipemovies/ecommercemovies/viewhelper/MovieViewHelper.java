package com.equipemovies.ecommercemovies.viewhelper;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipemovies.ecommercemovies.domain.*;
import com.equipemovies.ecommercemovies.util.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.equipemovies.ecommercemovies.util.Resultado;

public class MovieViewHelper implements IViewHelper {

	// Para pegar a entidade
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

    	Filme filme = new Filme();
        Estoque estoque = new Estoque();
        
        if(request.getParameter("operacao") != null 
        		&& request.getParameter("operacao").toLowerCase().equals("salvar")) {

        	String nome = request.getParameter("nome");
        	String codigoBarras = request.getParameter("codigoBarras");
        	String idGenero = request.getParameter("genero");
        	String idPaisOrigem = request.getParameter("paisOrigem");
        	String idIdioma = request.getParameter("idioma");
        	String idClassificacaoEtaria = request.getParameter("classificacaoEtaria");
        	String ano = request.getParameter("ano"); 
        	String sinopse = request.getParameter("sinopse");

			//Informações Estoque do filme
			String fornecedor = request.getParameter("fornecedor");
			String quantidadeEstoque = request.getParameter("quantidadeEstoque");
			String valorCompra = request.getParameter("valorCompra");
			String valorVenda = request.getParameter("valorVenda");

        	// Validações
        	if(nome != null && !nome.trim().equals("")){
        		filme.setNome(nome);
        	}

        	if(codigoBarras != null && !codigoBarras.trim().equals("")){
        		filme.setCodigoBarras(codigoBarras);
        	}

        	Genero genero = new Genero();
        	genero.setId(Integer.parseInt(idGenero));
        	filme.setGenero(genero);

        	PaisOrigem paisOrigem = new PaisOrigem();
        	paisOrigem.setId(Integer.parseInt(idPaisOrigem));
        	filme.setPaisOrigem(paisOrigem);

        	Idioma idioma = new Idioma();
        	idioma.setId(Integer.parseInt(idIdioma));
        	filme.setIdioma(idioma);

        	ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
        	classificacaoEtaria.setId(Integer.parseInt(idClassificacaoEtaria));
        	filme.setClassificacaoEtaria(classificacaoEtaria);

        	filme.setAno(Integer.parseInt(ano));
        	filme.setSinopse(sinopse);

        	// Validações Estoque
			if(fornecedor != null && !fornecedor.trim().equals("")) {
				estoque.setFornecedor(fornecedor);
			}

			if(quantidadeEstoque != null && !quantidadeEstoque.trim().equals("")) {
				estoque.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoque));
			}

			if(valorVenda != null && !valorVenda.trim().equals("")) {
				estoque.setValorVenda(Float.parseFloat(valorVenda));
			}

			if(valorCompra != null && !valorCompra.trim().equals("")) {
				estoque.setValorCompra(Float.parseFloat(valorCompra));
			}

			// ESTOQUE
			estoque.setDataCadastro(new Date());
			filme.setEstoque(estoque);

        	filme.setDataCadastro(new Date());
        	
        }

		// Para realizar a consulta de um filme(id) expecifico
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_filme") != null) {

			String id = request.getParameter("id_filme");
			filme.setId(Integer.parseInt(id));

			if (request.getParameter("status") != null) {
				filme.setStatus(request.getParameter("status").equalsIgnoreCase("1"));
			}
		}

		// Consulta tudo
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("status") != null) {

			filme.setStatus(request.getParameter("status").equalsIgnoreCase("1"));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("buscar")) {

			filme.setBuscar(true);

			if (request.getParameter("nome") != null && !StringUtils.isEmpty(request.getParameter("nome"))) {
				filme.setNome(request.getParameter("nome"));
			}

			if (request.getParameter("codigo_barras") != null && !StringUtils.isEmpty(request.getParameter("codigo_barras"))) {
				filme.setCodigoBarras(request.getParameter("codigo_barras"));
			}

			if (request.getParameter("genero") != null && !StringUtils.isEmpty(request.getParameter("genero"))) {
				Genero genero = new Genero();
				genero.setId(Integer.parseInt(request.getParameter("genero")));
				filme.setGenero(genero);
			}

			if (request.getParameter("ano") != null && !StringUtils.isEmpty(request.getParameter("ano"))) {
				filme.setAno(Integer.parseInt(request.getParameter("ano")));
			}

			if (request.getParameter("paisOrigem") != null && !StringUtils.isEmpty(request.getParameter("paisOrigem"))) {
				PaisOrigem paisOrigem = new PaisOrigem();
				paisOrigem.setId(Integer.parseInt(request.getParameter("paisOrigem")));
				filme.setPaisOrigem(paisOrigem);
			}

			if (request.getParameter("idioma") != null && !StringUtils.isEmpty(request.getParameter("idioma"))) {
				Idioma idioma = new Idioma();
				idioma.setId(Integer.parseInt(request.getParameter("idioma")));
				filme.setIdioma(idioma);
			}

			if (request.getParameter("classificacaoEtaria") != null && !StringUtils.isEmpty(request.getParameter("classificacaoEtaria"))) {
				ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
				classificacaoEtaria.setId(Integer.parseInt(request.getParameter("classificacaoEtaria")));
				filme.setClassificacaoEtaria(classificacaoEtaria);
			}
		}

		return filme;
    }

    //Para pegar o resultado
    @Override
    public ResponseEntity getView(Resultado result,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_filme") != null) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}

        return ResponseEntity.ok(result.getEntidadeDominio());
    }
}


