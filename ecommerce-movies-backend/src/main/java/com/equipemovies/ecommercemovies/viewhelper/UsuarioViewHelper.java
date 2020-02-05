package com.equipemovies.ecommercemovies.viewhelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.GeneroCliente;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.Criptografia;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;

public class UsuarioViewHelper implements IViewHelper {

	//Pega a entidade
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		//Instancia a entidade usuario
		Usuario usuario = new Usuario();
		Endereco endereco = new Endereco();
		
		//Verifica se a opereção corresponde as informações de de get
		if(request.getParameter("operacao") != null 
        		&& request.getParameter("operacao").toLowerCase().equals("salvar")) {
			
			//Informações Cliente
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String dataNascimento = request.getParameter("dataNascimento");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String genero = request.getParameter("genero");
			String telefone = request.getParameter("telefone");
			
			//Informações Endereco Cliente
			String idCidade = request.getParameter("idCidade");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cep = request.getParameter("cep");
			String observacao = request.getParameter("observacao");
			
			//Validações das informações
			
			//Pega data de cadastro
			usuario.setDataCadastro(new Date());

			//Validação nome
			if (nome != null && !nome.trim().equals("")) {
				usuario.setNome(nome);
			}
			
			//Validação CPF
			if (cpf != null && !cpf.trim().equals("")) {
				usuario.setCpf(cpf);
			}
			
			System.out.println(dataNascimento);
			//Validação data de nascimento
			if (dataNascimento != null && !dataNascimento.trim().contentEquals("")){
				usuario.setDataNascimento(dataNascimento);
			}
			
			//Validação email
			if (email != null && !email.trim().equals("")) {
				usuario.setEmail(email);
			}
			
			//Validação senha
			if (senha != null && !senha.trim().equals("")) {
				usuario.setSenha(senha);
			}
			
			//Validação genero
			if(genero != null && !genero.trim().equals("")) {
				usuario.setGenero(genero);	
			}
			
			//Validação telefone
			if (telefone != null && !telefone.trim().equals("")) {
				usuario.setTelefone(telefone);
			}
			
			
			//Validação do endereco
			
			//Valida o id da Cidade
			if(idCidade != null) {
				endereco.setIdCidade(Integer.valueOf(idCidade));
			}
			
			//Valida o logradouro
			if (logradouro != null && !logradouro.trim().equals("")) {
				endereco.setLogradouro(logradouro);
			}
			
			//Valida o numero
			if (numero != null && !numero.trim().equals("")) {
				endereco.setNumero(numero);
			}
			
			//Valida o bairro
			if (bairro != null && !bairro.trim().equals("")) {
				endereco.setBairro(bairro);
			}
			
			//Valida o cep
			if (cep != null && !cep.trim().equals("")) {
				endereco.setCep(cep);
			}
			
			//Valida a observação
			if (observacao != null && !observacao.trim().equals("")) {
				endereco.setObservacao(observacao);
			}
			
			//Insere data de cadastro do endereco
			endereco.setDataCadastro(new Date());
			
			//Valida se as inforamações foram inseridas de forma correta
			if (endereco != null)
				usuario.setEndereco(new ArrayList(Collections.singletonList(endereco)));
		}
		
		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_cliente") != null) {

			String id = request.getParameter("id_cliente");
			usuario.setId(Integer.parseInt(id));
		}
		
		return usuario;
	}

	//Pega o retorno
	@Override
	public ResponseEntity getView(Resultado result, HttpServletRequest request, HttpServletResponse response) {
		
		if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")
				&& request.getParameter("id_cliente") != null) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}
		
		return ResponseEntity.ok(result.getEntidadeDominio());
	}

}
