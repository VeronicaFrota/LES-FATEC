package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.GeneroBar;
import com.equipemovies.ecommercemovies.domain.GeneroLine;

public class GeneroBarDAO implements IDAO  {
	
private JdbcTemplate jdbcTemplate;
	
	public GeneroBarDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		//Primeira query a ser executada, retorna todos os generos presentes no sistema
		String query = "SELECT DISTINCT genero FROM cliente";

		GeneroBar generoEnt = (GeneroBar) entidade;
		
		return jdbcTemplate.query(query, (rs, i) -> {
			
			GeneroBar genero = new GeneroBar();
					
			List<Integer> listaValores = new ArrayList<>();
			List<String> listaMeses = new ArrayList<>();
			
			int comeco = Integer.parseInt(generoEnt.getComeco());
			int fim = Integer.parseInt(generoEnt.getFim());
			
			if (rs.getString("genero").equals("M")) {
				System.out.println("genero masculino definido");
				genero.setNome("Masculino");
			}
			else {
				System.out.println("genero feminino definido");
				genero.setNome("Feminino");
			}
				
			
			IntStream.rangeClosed(comeco, fim).forEach(n -> {
				
				String queryMes = "";
				try {
					queryMes = "SELECT COUNT(*) AS total_calculado FROM cliente AS C JOIN (SELECT id_cliente FROM pedido AS PED JOIN " 
									+ "(SELECT id_pedido FROM produto WHERE month(data_cadastro) = " + n + " AND year(data_cadastro) =" + generoEnt.getAno() +") "
											+ "as PROD ON PED.id = PROD.id_pedido ) AS P ON C.id = P.id_cliente WHERE C.genero = '"+ rs.getString("genero")+ "'";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(queryMes);
				
				
				 jdbcTemplate.query(queryMes, (rsMes, j) -> { 
					 listaValores.add(rsMes.getInt("total_calculado"));
					 if(n == 1) {
						 listaMeses.add("Janeiro");
					 }
					 else if (n == 2) {
						 listaMeses.add("Fevereiro");
					 }
					 else if (n == 3) {
						 listaMeses.add("Março");
					 }
					 else if (n == 4) {
						 listaMeses.add("Abril");
				   	 }
					 else if (n == 5) {
						 listaMeses.add("Maio");
					 }
					 else if (n == 6) {
						 listaMeses.add("Junho");
					 }
					 else if (n == 7) {
						 listaMeses.add("Julho");
					 }
					 else if (n == 8) {
						 listaMeses.add("Agosto");
					 }
					 else if (n == 9) {
						 listaMeses.add("Setembro");
					 }
					 else if (n == 10) {
						 listaMeses.add("Outubro");
					 }
					 else if (n == 11) {
						 listaMeses.add("Novembro");
					 }
					 else if (n == 12) {
						 listaMeses.add("Dezembro");
					 }
					
					 return null;
				 });
			});
			
			if (!CollectionUtils.isEmpty(listaValores))
				genero.setData(listaValores);
			genero.setDataAno(listaMeses);
					
			return genero;
				
		});
	}

}
