package com.equipemovies.ecommercemovies.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.equipemovies.ecommercemovies.domain.*;
import com.google.common.base.Joiner;
import com.mysql.jdbc.Statement;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class FilmeDAO implements IDAO {

	private JdbcTemplate jdbcTemplate;

    public FilmeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		// Fazendo um cast de entidade para filme
		Filme filme = (Filme) entidade;

        System.out.println(filme.getEstoque());
        Estoque estoque = filme.getEstoque();
        System.out.println(filme.getEstoque());
        KeyHolder key = new GeneratedKeyHolder();

		String query = "INSERT INTO filme(nome, codigo_barras, id_genero, id_pais_origem, id_idioma, "
        		 		+ "id_classificacao_etaria, ano, sinopse, data_cadastro, status) "
        		 		+ "VALUES (?,?,?,?,?,?,?,?,?,1)";

		int update  = jdbcTemplate.update(new PreparedStatementCreator()  {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, filme.getNome());
				ps.setString(2, filme.getCodigoBarras());
				ps.setInt(3, filme.getGenero().getId());
				ps.setInt(4, filme.getPaisOrigem().getId());
				ps.setInt(5, filme.getIdioma().getId());
				ps.setInt(6, filme.getClassificacaoEtaria().getId());
				ps.setInt(7, filme.getAno());
				ps.setString(8, filme.getSinopse());
				ps.setDate(9, new java.sql.Date(filme.getDataCadastro().getTime()));
				return ps;
			}}, key);


		//Insere no banco informações do estoque do filme
		String queryEstoque = "INSERT INTO estoque(id_filme, fornecedor, quantidade_estoque, valor_compra, valor_venda, " +
				"data_cadastro, status) "
				+ "VALUES (?,?,?,?,?,?,1)";

		jdbcTemplate.update(queryEstoque, new Object[] {
				key.getKey().longValue(),
				estoque.getFornecedor(),
				estoque.getQuantidadeEstoque(),
				estoque.getValorCompra(),
				estoque.getValorVenda(),
				new Timestamp(estoque.getDataCadastro().getTime())
		});


	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// Fazendo um cast de entidade para filme
		Filme filme = (Filme) entidade;

		if(entidade.getId() != null
				&& entidade.getStatus() != null
				&& entidade.getStatus() == true) {

			String query1 =  "UPDATE filme SET status = 1 WHERE id = ? ";

			jdbcTemplate.update(query1, new Object[] {
					filme.getId()
			});
		} else {

			String query = "UPDATE filme SET nome = ?, codigo_barras = ?, id_genero = ?, id_pais_origem = ?, "
					+ "id_idioma = ?, id_classificacao_etaria = ?, ano = ?, sinopse = ? "
					+ "WHERE id = ? ";

			System.out.println("aqui aqui");
			jdbcTemplate.update(query, new Object[]{
					filme.getNome(),
					filme.getCodigoBarras(),
					filme.getGenero().getId(),
					filme.getPaisOrigem().getId(),
					filme.getIdioma().getId(),
					filme.getClassificacaoEtaria().getId(),
					filme.getAno(),
					filme.getSinopse(),
					filme.getId()
			});
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Filme filme = (Filme) entidade;

		String query = "UPDATE filme SET status = 0 WHERE id = ? ";

		jdbcTemplate.update(query, new Object[] {
				filme.getId()
			});

		System.out.println("query = " + filme.getId());
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		final String query;

		Filme filmeSelect = (Filme) entidade;

		List<String> where = new ArrayList<>();

		// Pega o filme expecifico de acordo com o Id dele
		if (filmeSelect.isBuscar()) {

			if (filmeSelect.getNome() != null) {
				where.add("nome LIKE '%" + filmeSelect.getNome() +  "%'");
			}

			if (filmeSelect.getCodigoBarras() != null) {
				where.add("codigo_barras = '" + filmeSelect.getCodigoBarras() +  "'");
			}

			if (filmeSelect.getGenero() != null && filmeSelect.getGenero().getId() != null) {
				where.add("id_genero = '" + filmeSelect.getGenero().getId() +  "'");
			}

			if (filmeSelect.getAno() != null && filmeSelect.getAno() > 0) {
				where.add("ano = '" + filmeSelect.getAno() +  "'");
			}

			if (filmeSelect.getPaisOrigem() != null) {
				where.add("id_pais_origem = '" + filmeSelect.getPaisOrigem().getId() + "'");
			}

			if (filmeSelect.getIdioma() != null) {
				where.add("id_idioma = '" + filmeSelect.getIdioma().getId() + "'");
			}

			if (filmeSelect.getClassificacaoEtaria() != null) {
				where.add("id_classificacao_etaria = '" + filmeSelect.getClassificacaoEtaria().getId() + "'");
			}

			if (!CollectionUtils.isEmpty(where)) {
				query = "SELECT * FROM filme WHERE " + Joiner.on(" AND ").join(where);
			} else {
				query = "SELECT * FROM filme";
			}
		}
		else if(entidade.getId() != null
				&& entidade.getStatus() != null
				&& entidade.getStatus() == false) {
			query = "SELECT * FROM filme WHERE status=0 AND id=" + entidade.getId().toString();
		}
		else if(entidade.getId() != null) {
			query = "SELECT * FROM filme WHERE id=" + entidade.getId().toString();
		}
		else if(entidade.getStatus()!= null
				&& entidade.getStatus() == false) {
			query = "SELECT * FROM filme WHERE status=0";
		}
		else {
			// Select que faz a selecao de todos os dados presentes dentro da tabela filme
			query = "SELECT * FROM filme WHERE status=1";
		}
		System.out.println("query = " + query);

		//DAO de repeticao que pega todas as informacoes dos filmes
		return jdbcTemplate.query(query, (rs, i) -> {

			Filme filme = new Filme();

            filme.setId(rs.getInt("id"));
            filme.setNome(rs.getString("nome"));
            filme.setCodigoBarras(rs.getString("codigo_barras"));
            filme.setAno(Integer.parseInt(rs.getString("ano")));
            filme.setSinopse(rs.getString("sinopse"));
            filme.setStatus(rs.getBoolean("status"));

            Genero genero = new Genero();
            genero.setId(rs.getInt("id_genero"));
            // genero.setNome(rs.getString("nome"));
            filme.setGenero(genero);



            PaisOrigem paisOrigem = new PaisOrigem();
        	paisOrigem.setId(rs.getInt("id_pais_origem"));
        	//paisOrigem.setNome(rs.getString("nome"));
        	filme.setPaisOrigem(paisOrigem);

        	Idioma idioma = new Idioma();
        	idioma.setId(rs.getInt("id_idioma"));
        	filme.setIdioma(idioma);

        	ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
        	classificacaoEtaria.setId(rs.getInt("id_classificacao_etaria"));
        	filme.setClassificacaoEtaria(classificacaoEtaria);


        	// ESTOQUE
			List<Estoque> estoques = jdbcTemplate.query("SELECT * FROM estoque WHERE status=1 AND id_filme=" + filme.getId(), (rsEstoque, n) -> {
				Estoque estoque = new Estoque();

				estoque.setId(rsEstoque.getInt("id"));
				estoque.setFornecedor(rsEstoque.getString("fornecedor"));
				estoque.setQuantidadeEstoque(rsEstoque.getInt("quantidade_estoque"));
				estoque.setValorCompra(rsEstoque.getFloat("valor_compra"));
				estoque	.setValorVenda(rsEstoque.getFloat("valor_venda"));
				estoque.setStatus(rsEstoque.getBoolean("status"));

				return estoque;
			});

			// Verifica se a lista de estoques esta nula e vazia
			if (!CollectionUtils.isEmpty(estoques)) {
				filme.setEstoque(estoques.get(0));
			}


			return filme;
        });

    }
}
