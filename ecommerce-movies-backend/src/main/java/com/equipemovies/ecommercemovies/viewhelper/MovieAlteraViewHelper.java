package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.*;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieAlteraViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Filme filme = new Filme();

		// verifica se a operação for diferente de nulo, igual a alterar e se o id do livro não for nulo
		if (request.getParameter("operacao") != null
           && request.getParameter("operacao").equals("alterar")
           && request.getParameter("id_filme") != null ) {

			String nome = request.getParameter("nome");
        	String codigoBarras = request.getParameter("codigoBarras");
        	String idGenero = request.getParameter("genero");
        	String idPaisOrigem = request.getParameter("paisOrigem");
        	String idIdioma = request.getParameter("idioma");
        	String idClassificacaoEtaria = request.getParameter("classificacaoEtaria");
        	String ano = request.getParameter("ano"); 
        	String sinopse = request.getParameter("sinopse");

        	String id = request.getParameter("id_filme");
			filme.setId(Integer.parseInt(id));

        	filme.setNome(nome);
        	filme.setCodigoBarras(codigoBarras);

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
        	
        	System.out.println("id " + request.getParameter("id_filme"));
        	System.out.println("Codigo de barras " + request.getParameter("codigoBarras"));
        	System.out.println("nome " + request.getParameter("nome"));
        	System.out.println("genero " + request.getParameter("genero"));
        	System.out.println("pais de origem " + request.getParameter("paisOrigem"));
        	System.out.println("idioma " + request.getParameter("idioma"));
        	System.out.println("classi. etaria " + request.getParameter("classificacaoEtaria"));
        	System.out.println("ano " + request.getParameter("ano"));
        	System.out.println("sinopse " + request.getParameter("sinopse"));


		}
		return filme;

	}

	// Pega o resultado
	@Override
	public ResponseEntity getView(Resultado result,
								  HttpServletRequest request,
								  HttpServletResponse response) {

		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		return null;
	}

}
