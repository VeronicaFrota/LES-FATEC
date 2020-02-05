package com.equipemovies.ecommercemovies.DAO;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Genero;
import com.equipemovies.ecommercemovies.domain.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class GeneroDAO implements IDAO {

    private JdbcTemplate jdbcTemplate;

    public GeneroDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {

    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

        // Query para pegar os generos existentes na table de genero
        String query = "SELECT * FROM genero";

        System.out.println("Entrou na DAO de genero");

        return jdbcTemplate.query(query, (rs, i) -> {

            Genero genero = new Genero();

            // Pega o total do genero de acordo com cada filme cadastrado no sistema
            String queryGenerosAcao = "SELECT COUNT(*) AS total_genero FROM filme WHERE id_genero = " + rs.getInt("id");

            System.out.println("queryGenerosAcao = " + queryGenerosAcao);

            // executa o total_genero
            jdbcTemplate.query(queryGenerosAcao, new Object[] { }, (rsGenerosAcao, j) -> {
                genero.setTotalGenero(rsGenerosAcao.getInt("total_genero"));

                return Void.class;
            });

            System.out.println(genero.getId());

            genero.setId(rs.getInt("id"));
            genero.setNome(rs.getString("nome"));

            return genero;
        });
    }
}

