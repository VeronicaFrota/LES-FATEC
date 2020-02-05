package com.equipemovies.ecommercemovies.DAO;

import com.equipemovies.ecommercemovies.domain.*;
import com.mysql.jdbc.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PedidoDAO implements IDAO {

    private JdbcTemplate jdbcTemplate;

    public PedidoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metodo que salva os dados de estoque do filme
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {

        Pedido pedido = (Pedido) entidade;


        KeyHolder key = new GeneratedKeyHolder();

        String query = "INSERT INTO pedido (id_cliente, id_cupom, id_cartao, id_transacao, quantidade_comprada, "
                + "quantidade_parcela, valor_frete, total_compra, subtotal_compra, data_cadastro, id_endereco, status) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,1)";

        int update  = jdbcTemplate.update(new PreparedStatementCreator() {
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
              final PreparedStatement ps = connection.prepareStatement(query,
                      Statement.RETURN_GENERATED_KEYS);

              ps.setInt(1, pedido.getIdCliente());

              if (pedido.getIdCupom() != null)
                ps.setInt(2, pedido.getIdCupom());
              else
                ps.setNull(2, java.sql.Types.INTEGER);

              ps.setInt(3, pedido.getIdCartao());
              ps.setInt(4, pedido.getIdTransacao());
              ps.setInt(5, pedido.getQuantidadeComprada());
              ps.setInt(6, pedido.getQuantidadeParcela());
              ps.setFloat(7, pedido.getValorFrete());
              ps.setFloat(8, pedido.getTotalCompra());
              ps.setFloat(9, pedido.getSubtotalCompra());
              ps.setDate(10, new java.sql.Date(pedido.getDataCadastro().getTime()));
              ps.setInt(11, pedido.getIdEndereco());
              return ps;
          }}, key);


        // id pedido
        long ultimoId = key.getKey().longValue();

        // Loop de filme para salvar o produto/filme no pedido
        for (Filme filme : pedido.getFilmes()) {
            //Insere no banco informações do produto
            String queryProduto = "INSERT INTO produto(id_filme, id_pedido, quantidade_comprada, data_cadastro, status) "
                    + "VALUES (?,?,?,?,1)";

            jdbcTemplate.update(queryProduto, new Object[] {
                    filme.getId(),
                    ultimoId,
                    filme.getQuantidadeComprada(),
                    new Timestamp(filme.getDataCadastro().getTime())
            });

        }

        // Loop de cartão para salvar o cartao no pedido
        for (Cartao cartao : pedido.getCartoes()) {
            String queryPedidoCartao = "INSERT INTO pedido_cartao(id_pedido, id_cartao, quantidade_parcela) "
                    + "VALUES (?,?,?)";

            jdbcTemplate.update(queryPedidoCartao, new Object[] {
                    ultimoId,
                    cartao.getId(),
                    cartao.getQuantidadeParcela()
            });
        }


        // Loop de filme para realizar a baixa no estoque
        for (Filme filme : pedido.getFilmes()) {

            String queryBeixaQuantidadeEstoque = "SELECT quantidade_estoque FROM estoque WHERE id_filme = " + filme.getId();

            jdbcTemplate.query(queryBeixaQuantidadeEstoque, (rs, i) -> {

                String queryBaixaEstoque = "UPDATE estoque SET quantidade_estoque = ? WHERE  id_filme = ?";

                jdbcTemplate.update(queryBaixaEstoque, new Object[] {
                        rs.getInt("quantidade_estoque") - filme.getQuantidadeComprada(),
                        filme.getId()
                });

                return Void.class;
            });
        }

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
    	Pedido pedido = (Pedido) entidade;
    	
    	System.out.println("DAO ALTERACAO");
    	System.out.println(pedido);

    	String query = "UPDATE pedido SET id_transacao = ? WHERE id = ?";
    	
    	jdbcTemplate.update(query, new Object[]{
    		pedido.getIdTransacao(),
    		pedido.getId()
    	});

    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        String query = "SELECT * FROM pedido";

        Pedido pedido = (Pedido) entidade;

        if (pedido.getIdCliente() != null) {
            query = "SELECT * FROM pedido WHERE id_cliente=" + pedido.getIdCliente();

            System.out.println("ENTROU NO IF ");

        }

        return jdbcTemplate.query(query, (rs, i) -> {
            Pedido pedidos = new Pedido();
            
            pedidos.setId(rs.getInt("id"));
            pedidos.setIdCliente(rs.getInt("id_cliente"));
            pedidos.setTotalCompra(rs.getFloat("total_compra"));
            pedidos.setIdTransacao(rs.getInt("id_transacao"));

            return pedidos;
        });
    }
}
