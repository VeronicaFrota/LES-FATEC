package com.equipemovies.ecommercemovies.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		// importando o Driver de conexao com o banco.
        Class.forName("com.mysql.jdbc.Driver");
        
        // Criar a conexao com o banco
        String url = "jdbc:mysql://localhost:3306/ecommercemovies";
        
        Connection conexao = DriverManager.getConnection(url, "root", "");
        return conexao;
	}

}
