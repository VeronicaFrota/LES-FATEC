package com.equipemovies.ecommercemovies.DAO;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Estoque;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class EstoqueDAO implements IDAO {

    private JdbcTemplate jdbcTemplate;

    public EstoqueDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metodo que salva os dados de estoque do filme
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {

        Estoque estoque =  (Estoque) entidade;
        String query = "INSERT INTO estoque (id_filme, fornecedor, quantidade_estoque, valor_compra, valor_venda, data_cadastro, status) "
                + "VALUES (?,?,?,?,?,?,1)";

        jdbcTemplate.update(query, new Object[] {
                estoque.getIdFilme(),
                estoque.getFornecedor(),
                estoque.getQuantidadeEstoque(),
                estoque.getValorCompra(),
                estoque.getValorVenda(),
                new Timestamp(estoque.getDataCadastro().getTime())
        });

        System.out.println("queryCadastro = " + query);

    }

    // Metodo que faz a alteração dos dados de estoque do filme
    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {

        Estoque estoque = (Estoque) entidade;

        String query = "UPDATE estoque SET fornecedor = ?, quantidade_estoque = ?, valor_compra = ?, valor_venda = ?"
                + "WHERE id = ?";

        jdbcTemplate.update(query, new Object[] {
                estoque.getFornecedor(),
                estoque.getQuantidadeEstoque(),
                estoque.getValorCompra(),
                estoque.getValorVenda(),
                estoque.getId()
        });

    }

    // Metodo que faz a exclusão do estoque
    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {

        Estoque estoque = (Estoque) entidade;

        String query = "UPDATE estoque SET status = 0 WHERE id = ?";

        jdbcTemplate.update(query, new Object[] {
                estoque.getId()
        });

        System.out.println("query = " + estoque.getId());

    }

    // Metodo que lista os estoques de determinado filme
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

        Estoque estoqueBusca = (Estoque) entidade;

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM estoque WHERE 1=1");

        if(estoqueBusca.getId() != null) {
            query.append(" AND id=" + estoqueBusca.getId());
        }

        if(estoqueBusca.getIdFilme() != null) {
            query.append(" AND id_filme=" + estoqueBusca.getIdFilme());
        }

        System.out.println(query);

        return jdbcTemplate.query(query.toString(), (rs, i) -> {
            Estoque estoque = new Estoque();

            //Pega as informações de estoque que serão apresentados em tela
            estoque.setId(rs.getInt("id"));
            estoque.setIdFilme(rs.getInt("id_filme"));
            estoque.setFornecedor(rs.getString("fornecedor"));
            estoque.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
            estoque.setValorCompra(rs.getFloat("valor_compra"));
            estoque.setValorVenda(rs.getFloat("valor_venda"));
            estoque.setDataCadastro(rs.getDate("data_cadastro"));
            estoque.setStatus(rs.getBoolean("status"));

            System.out.println(estoque.getIdFilme());
            System.out.println(estoque.getFornecedor());
            System.out.println(estoque.getQuantidadeEstoque());
            System.out.println(estoque.getValorCompra());
            System.out.println(estoque.getValorVenda());
            System.out.println(estoque.getStatus());

            return estoque;

        });
    }
}
