package com.equipemovies.ecommercemovies.negocio;

import com.equipemovies.ecommercemovies.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorDadosFilmeTests {

    @InjectMocks
    private ValidadorDadosFilme validadorDadosFilme = new ValidadorDadosFilme();

    @Test
    public void testComUmFilmeValido() {

        Filme filmeValido = new Filme();
        filmeValido.setNome("Mock filme");
        filmeValido.setCodigoBarras("0000000");

        Genero genero = new Genero();
        genero.setId(1);
        filmeValido.setGenero(genero);

        PaisOrigem paisOrigem = new PaisOrigem();
        paisOrigem.setId(1);
        filmeValido.setPaisOrigem(paisOrigem);

        Idioma idioma = new Idioma();
        idioma.setId(1);
        filmeValido.setIdioma(idioma);


        ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
        classificacaoEtaria.setId(1);

        filmeValido.setClassificacaoEtaria(classificacaoEtaria);

        filmeValido.setAno(2019);

        filmeValido.setSinopse("hdighdighdifoghdiosg");


        String processar = validadorDadosFilme.processar(filmeValido);

        assertNull(processar);
    }


    @Test
    public void testComUmFilmeComAnoinvalido() {

        Filme filmeValido = new Filme();
        filmeValido.setNome("Mock filme");
        filmeValido.setCodigoBarras("0000000");

        Genero genero = new Genero();
        genero.setId(1);
        filmeValido.setGenero(genero);

        PaisOrigem paisOrigem = new PaisOrigem();
        paisOrigem.setId(1);
        filmeValido.setPaisOrigem(paisOrigem);

        Idioma idioma = new Idioma();
        idioma.setId(1);
        filmeValido.setIdioma(idioma);


        ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
        classificacaoEtaria.setId(1);

        filmeValido.setClassificacaoEtaria(classificacaoEtaria);

        filmeValido.setAno(1990);

        filmeValido.setSinopse("hdighdighdifoghdiosg");


        String processar = validadorDadosFilme.processar(filmeValido);

        assertEquals(
                "Deve ser inserido um ano válido!",
                processar
        );
    }

}