package com.equipemovies.ecommercemovies.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;

public class Resultado {
	
	private String msg;

    private List<EntidadeDominio> entidadeDominio;

    /*
	 * Método de recuperação do campo msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/*
	 * Valor de msg atribuído a msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean hasMsg() {
        return !(msg == null) && !(msg.isEmpty());
	}
    
    public List<EntidadeDominio> getEntidadeDominio() {
        return entidadeDominio;
    }

    public void setEntidadeDominio(EntidadeDominio entidadeDominio) {
    	this.entidadeDominio = Collections.singletonList(entidadeDominio);
    }

    public void setEntidadeDominio(List<EntidadeDominio> entidadeDominio) {
        this.entidadeDominio = entidadeDominio;
    }

    public void putEntidadeDominio(List<EntidadeDominio> entidadeDominio) {
        this.entidadeDominio = entidadeDominio;
    }
}

