package com.equipemovies.ecommercemovies.command;

import com.equipemovies.ecommercemovies.fachada.Fachada;
import com.equipemovies.ecommercemovies.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {

    //O Padrão Facade fornece uma interface unificada para um conjunto de interfaces em um subsistema.
    //protected IFachada fachada = new Fachada();

	protected IFachada fachada;

    public void setFachada(IFachada fachada) {
        this.fachada = fachada;
    }

	
}
