package com.equipemovies.ecommercemovies.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EntidadeDominio implements IEntidade, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

////    @JsonProperty("dt_cadastro")
//    private Date dtCadastro;
//
////    @JsonProperty("dt_alteracao")
//    private Date dtAlteracao;

    private Boolean status;

    // Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Date getDtCadastro() {
//        return dtCadastro;
//    }
//
//    public void setDtCadastro(Date dtCadastro) {
//        this.dtCadastro = dtCadastro;
//    }
//
//    public Date getDtAlteracao() {
//        return dtAlteracao;
//    }
//
//    public void setDtAlteracao(Date dtAlteracao) {
//        this.dtAlteracao = dtAlteracao;
//    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
