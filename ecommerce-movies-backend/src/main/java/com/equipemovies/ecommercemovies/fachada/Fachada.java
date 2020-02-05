package com.equipemovies.ecommercemovies.fachada;

import java.sql.SQLException;
import java.util.*;

import com.equipemovies.ecommercemovies.DAO.*;
import com.equipemovies.ecommercemovies.domain.*;
import com.equipemovies.ecommercemovies.negocio.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.equipemovies.ecommercemovies.domain.Cartao;
import com.equipemovies.ecommercemovies.domain.Endereco;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Filme;
import com.equipemovies.ecommercemovies.domain.Usuario;
import com.equipemovies.ecommercemovies.util.Resultado;

@Component
public class Fachada implements IFachada {

	private JdbcTemplate jdbcTemplate;

	/*
	 * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância do
	 * DAO para uma dada entidade;
	 */
	private Map<String, IDAO> daos;

	/*
	 * Mapa para conter as regras de negócio de todas operações por entidade; O
	 * valor é um mapa que de regras de negócio indexado pela operação, ou seja
	 * realiza o mapeamento de cada regra de negócio existente
	 */

	// atraves de uma acao, chama uma entidade, nesta entidade passa as validações
	// que cada uma tem que executar
	private Map<String, Map<String, List<IStrategy>>> regrasValidacao;

	// atraves de uma acao, chama uma entidade, nesta entidade passa as validações
	// que cada uma tem que executar
	private Map<String, Map<String, List<IStrategyRegraNegocio>>> regrasNegocio;

	// resultado que será retornado ap[os ser realizado uma requisição
	private Resultado resultado = new Resultado();

	public Fachada(JdbcTemplate jdbcTemplate) {
		// Intânciando o Map de DAOS
		this.daos = new HashMap<>();
		
		// Intânciando o Map de Regras de Negócio
		regrasValidacao = new HashMap<String, Map<String, List<IStrategy>>>();
		regrasNegocio = new LinkedHashMap<String, Map<String, List<IStrategyRegraNegocio>>>();

		/**
		 * INSTANCIAS DO DAO Adicionando cada dao no MAP indexando pelo nome da classe
		 */
		// FILME E ESTOQUE
		daos.put(Filme.class.getName(), new FilmeDAO(jdbcTemplate));
		daos.put(Estoque.class.getName(), new EstoqueDAO(jdbcTemplate));

		// CLIENTE, ENDEREÇO E CARTAO
		daos.put(Usuario.class.getName(), new UsuarioDAO(jdbcTemplate));
		daos.put(EmailUsuario.class.getName(), new UsuarioEmailDAO(jdbcTemplate));
		daos.put(Endereco.class.getName(), new EnderecoDAO(jdbcTemplate));
		daos.put(Cartao.class.getName(), new CartaoDAO(jdbcTemplate));
		daos.put(SenhaUsuario.class.getName(), new UsuarioSenhaDAO(jdbcTemplate));

		// PEDIDO
		daos.put(Pedido.class.getName(), new PedidoDAO(jdbcTemplate));
		
		// CUPOM
		daos.put(Cupom.class.getName(), new CupomDAO(jdbcTemplate));

		// Genero
		daos.put(Genero.class.getName(), new GeneroDAO(jdbcTemplate));
		
		//Genero por Mes
		daos.put(GeneroPie.class.getName(), new GeneroPieDAO(jdbcTemplate));
		
		//Genero por Line
		daos.put(GeneroLine.class.getName(), new GeneroLineDAO(jdbcTemplate));
		
		//Quantidade de filmes vendido por Mes e por sexo
		daos.put(GeneroBar.class.getName(), new GeneroBarDAO(jdbcTemplate));



		/**
		 * REGRAS DE NEGOCIO
		 */
		// Criando o mapa de regras de negócio do Filme
		Map<String, List<IStrategy>> regrasNegocioFilme = new HashMap<>();
		Map<String, List<IStrategy>> regrasNegocioEstoque = new HashMap<>();


		// Criando o mapa de regras de negócio do Cliente
		Map<String, List<IStrategy>> regrasNegocioCliente = new HashMap<>();
		Map<String, List<IStrategy>> regrasNegocioEndereco = new HashMap<>();
		Map<String, List<IStrategy>> regrasNegocioCartao = new HashMap<>();



		// Lista de regras de regras de negócio do Filme
		List<IStrategy> listaDeRegras = Collections.singletonList(new ValidadorDadosFilme());
		List<IStrategy> listaDeRegrasAlteracaoCliente = Collections.singletonList(new ValidadorDadosAlteracaoCliente());
		List<IStrategy> listaDeRegrasInclusaoCliente = Arrays.asList(new ValidadorDadosInclusaoCliente());
		List<IStrategy> listaDeRegrasEndereco = Collections.singletonList(new ValidadorDadosEndereco());
		List<IStrategy> listaDeRegrasCartao = Collections.singletonList(new ValidadorDadosCartao());
		List<IStrategy> listaDeRegrasEstoque = Collections.singletonList(new ValidadorDadosEstoque());

		/*List<IStrategy> listaDeRegrasAlterar = Arrays.asList(
				new ValidadorDadosFilmeAlterar(),
				new ValdadorOutroValidador()
		);*/


		// Lista de regras de regras de negócio do Cliente
		// ------------

		// Usando o mapa com chave e valor
		regrasNegocioFilme.put("salvar", listaDeRegras);
		regrasNegocioFilme.put("alterar", listaDeRegras);
		
		regrasNegocioEstoque.put("salvar", listaDeRegrasEstoque);
		regrasNegocioEstoque.put("alterar", listaDeRegrasEstoque);
		
		regrasNegocioCliente.put("salvar", listaDeRegrasInclusaoCliente);
		regrasNegocioCliente.put("alterar", listaDeRegrasAlteracaoCliente);
		
		regrasNegocioEndereco.put("salvar", listaDeRegrasEndereco);
		regrasNegocioEndereco.put("alterar", listaDeRegrasEndereco);
		
		regrasNegocioCartao.put("salvar", listaDeRegrasCartao);
		regrasNegocioCartao.put("alterar", listaDeRegrasCartao);


		// colocando as regras do filme na lista global de regras
		regrasValidacao.put(Filme.class.getName(), regrasNegocioFilme);
		
		regrasValidacao.put(Estoque.class.getName(), regrasNegocioEstoque);
		
		regrasValidacao.put(Usuario.class.getName(), regrasNegocioCliente);
		
		regrasValidacao.put(Endereco.class.getName(), regrasNegocioEndereco);
		
		regrasValidacao.put(Cartao.class.getName(), regrasNegocioCartao);
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {

		// Limpa a mensagem de error
		resultado.setMsg(null);

		if (entidade == null) {
			resultado.setMsg("Entidade nula");
			return resultado;
		}

		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "salvar");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				resultado = executarRegrasNegocio("salvar", entidade, resultado);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}

		return resultado;
	}


	@Override
	public Resultado alterar(EntidadeDominio entidade) {

		// Limpa a mensagem de error
		resultado.setMsg(null);

		if (entidade == null) {
			resultado.setMsg("Entidade nula");
			return resultado;
		}

		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "alterar");
		
		System.out.println("Variavel msg" + msg);

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
				try {
					dao.alterar(entidade);
					resultado = executarRegrasNegocio("alterar", entidade, resultado);
				} catch (SQLException e) {
					e.printStackTrace();
					resultado.setMsg("Não foi possível realizar o registro!");
				}
			}
		else {
			resultado.setMsg(msg);
		}
		
		System.out.println("Resultado final " + resultado.getMsg());
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {

		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				resultado.setMsg("Foi possível realizar o registro!");
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
		}
		
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {

		if (entidade == null) {
			return null;
		}

		String nmClasse = entidade.getClass().getName();

		IDAO dao = daos.get(nmClasse);

		Resultado resultado = new Resultado();
		
		// chamada o DAO aqui
		resultado.setEntidadeDominio(entidade);

		try {
			resultado.putEntidadeDominio(dao.consultar(entidade));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	// para executar as regras de negocio
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		// realiza o mapeamento das operações que deverão ser realizadas no sistema,
		// verificando a escolha realizada
		// (salvar, alterar...)
		
		System.out.println(nmClasse);
		Map<String, List<IStrategy>> regrasOperacao = regrasValidacao.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);

					if (m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

	public Resultado executarRegrasNegocio(String operacao, EntidadeDominio entidade, Resultado resultado) {

		Map<String, List<IStrategyRegraNegocio>> regrasNegocioDominio = regrasNegocio
				.get(entidade.getClass().getName());

		if (regrasNegocioDominio != null) {
			List<IStrategyRegraNegocio> regras = regrasNegocioDominio.get(operacao);

			if (regras != null) {
				for (IStrategyRegraNegocio regra : regras) {
					resultado = regra.processar(entidade, resultado);

					if (resultado.hasMsg()) {
						return resultado;
					}
				}
			}
		}
		return resultado;
	}
}