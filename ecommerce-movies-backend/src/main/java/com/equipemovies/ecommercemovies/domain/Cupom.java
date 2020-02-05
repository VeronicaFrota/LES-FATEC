package com.equipemovies.ecommercemovies.domain;

public class Cupom extends EntidadeDominio {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idCliente;
    private String codigo;
    private float valor;
    private Boolean status;

    public Cupom() {

    }

    public Cupom(Integer id, Integer idCliente, String codigo, float valor, Boolean status) {
        this.id = id;
        this.idCliente = idCliente;
        this.codigo = codigo;
        this.valor = valor;
        this.status = status;
    }

	@Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
    
    public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
