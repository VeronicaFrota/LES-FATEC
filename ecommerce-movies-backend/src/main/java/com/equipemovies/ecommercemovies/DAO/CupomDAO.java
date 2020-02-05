package com.equipemovies.ecommercemovies.DAO;

import com.equipemovies.ecommercemovies.domain.Cupom;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Pedido;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CupomDAO implements IDAO {

    private JdbcTemplate jdbcTemplate;

    public CupomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metodo que salva os dados de estoque do filme
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {

        Cupom cupom = (Cupom) entidade;
        
        String query = "INSERT INTO cupom(id_cliente, codigo, valor, status)"
        		+ "VALUES(?,?,?,?)";
        
        jdbcTemplate.update(query, new Object[] {
        	cupom.getIdCliente(),
        	cupom.getCodigo(),
        	cupom.getValor(),
        	1
        });

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
    	Cupom cupom = (Cupom) entidade;
    	
    	String query = "UPDATE cupom SET status = ? WHERE id = ?";
    	
    	jdbcTemplate.update(query, new Object [] {
    		cupom.getStatus(),
    		cupom.getId()
    	});

    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        Cupom cupomBusca = (Cupom) entidade;
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT * FROM cupom WHERE 1=1");
        
        if (cupomBusca.getIdCliente() != null) {
        	query.append(" AND id_cliente=" + cupomBusca.getIdCliente());
        }
        
        if (cupomBusca.getStatus() != null) {
        	query.append(" AND status=" + cupomBusca.getStatus());
        }

       /* if (cupomBusca.getIdCliente() != null) {
            query.append("SELECT * FROM cupom WHERE id_cliente=" + cupomBusca.getIdCliente());
        }*/
        
        return jdbcTemplate.query(query.toString(), (rs, i) -> {

            String queryCupomEspecifico = "SELECT * FROM pedido";

            if (cupomBusca.getIdCliente() != null) {
                queryCupomEspecifico = "SELECT * FROM cupom WHERE id_cliente=" + cupomBusca.getIdCliente();

                System.out.println("ENTROU NO IF ");

            }
        	Cupom cupom = new Cupom();
        	cupom.setId(rs.getInt("id"));
        	cupom.setIdCliente(rs.getInt("id_cliente"));
        	cupom.setCodigo(rs.getString("codigo"));
        	cupom.setValor(rs.getFloat("valor"));
        	cupom.setStatus(rs.getBoolean("status"));
        	
        	System.out.println(cupom);
        	
        	return cupom;
        });
    }

}
