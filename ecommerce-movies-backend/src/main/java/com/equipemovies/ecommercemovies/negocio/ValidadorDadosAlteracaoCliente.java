package com.equipemovies.ecommercemovies.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.equipemovies.ecommercemovies.DAO.UsuarioDAO;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Genero;
import com.equipemovies.ecommercemovies.domain.SenhaUsuario;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.util.Criptografia;
import com.equipemovies.ecommercemovies.util.Resultado;

public class ValidadorDadosAlteracaoCliente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		//String que pega os erros encontrados em tela
		String erros = "";
		
		if(entidade instanceof Usuario) {
			Usuario usuario = (Usuario) entidade;
		
			String nome = usuario.getNome();
			String cpf = usuario.getCpf();
			String dataNascimento = usuario.getDataNascimento();
			String email = usuario.getEmail();
			String idGenero = usuario.getGenero();
			String telefone = usuario.getTelefone();
			String senha = usuario.getSenha();
			
			//1ª Regra de negócio - Campos Nulos
			if(nome == null || cpf == null || dataNascimento == null ||
					email == null || idGenero == null ||
					telefone == null) {
				
				erros += "Todos os campos são de preenchimento obrigatório!";
			}
			
			//2ª Regra de negócio - Regex de verificação de CPF
			String regexCpf = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$";
			
			Pattern patternCpf = Pattern.compile(regexCpf);
			Matcher matcherCpf = patternCpf.matcher(cpf);
			
			if (!matcherCpf.matches()){
				erros += " || O CPF informado não está correto! Ele deve seguir o padrão xxx.xxx.xxx-xx";
			}
			
			//3ª Regra de Negócio - Regex de verificação de email
			String regexEmail = "^(.+)@(.+)$";
			
			Pattern patternEmail = Pattern.compile(regexEmail);
			Matcher matcherEmail = patternEmail.matcher(email);
			
			if(!matcherEmail.matches()) {
				erros += "|| O email informado é inválido";
			}
			
			//4ª Regra de Negócio - Verificação de telefone
			if(!telefone.contains("-") || !(telefone.length() >= 9)) {
				erros += "|| O telefone informado não é válido! Ele deve seguir o padrão xxxx-xxxx";
			}
			
			//5ª Regra de Negócio - Validação do layout da data
			String regexDataNascimento = "^\\d{4}-\\d{2}-\\d{2}$";
			
			Pattern patternDataNascimento = Pattern.compile(regexDataNascimento);
			Matcher matcherDataNascimento = patternDataNascimento.matcher(dataNascimento);
			
			if(!matcherDataNascimento.matches()) {
				erros += "|| A data de nascimento informada é inválida! Ela deve seguir o padrão YYYY-MM-DD";
			}
			else {
				//6ª Regra de Negócio - Verificação da data de nascimento inserida
				String ano = dataNascimento.substring(0, 4);
				String mes = dataNascimento.substring(5, 7);
				String dia = dataNascimento.substring(8, 10);
				int anoData = Integer.parseInt(ano);
				int mesData = Integer.parseInt(mes);
				int diaData = Integer.parseInt(dia);
				if(anoData <= 1900 || anoData > 2001){
					erros += "|| Deve ser inserida um Ano de nascimento válida";
				}
				if(mesData <= 0 || mesData > 12){
					erros += "|| Deve ser inserida um mês de nascimento válida";
				}
				if(diaData <= 0 || diaData > 31){
					erros += "|| Deve ser inserida um dia de nascimento válida";
				}
			}
		}
		return erros.equals("") ? null : erros;
	}
}
