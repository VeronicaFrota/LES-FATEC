package com.equipemovies.ecommercemovies.DAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.GeneroLine;
import com.equipemovies.ecommercemovies.domain.GeneroPie;

public class GeneroLineDAO implements IDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public GeneroLineDAO(JdbcTemplate jdbcTemplate) {
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
		String query = "SELECT * FROM genero";
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");		
		DateFormat dp = new SimpleDateFormat ("yyyy-MM-dd");
		GeneroLine generoEnt = (GeneroLine) entidade;
		
		
		return jdbcTemplate.query(query, (rs, i) -> {
			
			GeneroLine genero = new GeneroLine();

			Date comeco = new Date();
			Date fim = new Date();

			System.out.println(rs.getInt("id"));

			try {
				comeco = dp.parse(generoEnt.getComeco());
				fim = dp.parse(generoEnt.getFim());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(comeco);
			cal.getTime();

			genero.setNome(rs.getString("nome"));
			genero.setId(rs.getInt("id"));
					
			List<Integer> listaValores = new ArrayList<>();

			List<String> listaDatas = new ArrayList<>();
			
			for (Date data = comeco; data.compareTo(fim) <= 0;) {
				
				String queryMes = "";
				try {
					queryMes = "SELECT count(*) as total_mes FROM produto WHERE status=1 AND data_cadastro = '" + 
				dp.format(cal.getTime()) + "' AND id_filme in (SELECT id FROM filme WHERE id_genero=" + rs.getInt("id") + " )";

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 jdbcTemplate.query(queryMes, (rsMes, j) -> { 
					 listaValores.add(rsMes.getInt("total_mes"));
					 listaDatas.add(df.format(cal.getTime()));
					 
					 return null;
				 });
				 
				 cal.add(Calendar.DATE, +1);
				 data = cal.getTime();
			}
			
			if (!CollectionUtils.isEmpty(listaValores))
				genero.setData(listaValores);
			genero.setDataAno(listaDatas);
					
			return genero;
				
		});
	}
}
