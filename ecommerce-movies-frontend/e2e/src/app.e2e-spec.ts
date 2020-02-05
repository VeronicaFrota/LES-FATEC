import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('workspace-project App', () => {
	let page: AppPage;

	beforeEach(() => {
		page = new AppPage();
	});

	//Teste Automatizado Login
	it('insere dados inexistentes de login', () =>{
		page.login()
		expect(page.setLoginInexistente()).toEqual('Email ou Senha Incorretos');
		page.limparJSAlert()
	})

	it('insere dados de login corretos', () =>{
		page.login()
		page.setLogin()
		page.limparJSAlert()
	})

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	/// TESTE AUTOMATIZADO DE FILME 
	//Inicio
	/*it('realizar login', () => {
		page.login() // abrindo o browser
		page.setLogin()
		page.limparJSAlert()
	})*/

	it('deve ser maior que 1 filme', () => {
		page.abrirListagemFilme(); // abrir o browser
		expect(page.getListagemTamanho()).toBeGreaterThan(1); // pega a contagem e faz > 1
	});

	it('salvar um filme', () => {
		page.abrirSalvarFilme() // abrindo o browser
		page.setInputsSalvar()
		page.limparJSAlert()
	})

	it('alterar um filme', () => {
		page.abrirUpdateFilme() // abrindo o browser
		page.setInputsUpdate()
		page.limparJSAlert()
	})

	it('delete um filme', () => {
		page.abrirListagemFilme() // abrindo o browser
		page.deletarUltimoFilme()
		page.limparJSAlert()
	})
	// Fim

	// TESTE AUTOMATIZADO DE ESTOQUE
	// Inicio
	// Verifica se a listagem de estoque é maior que 1
	it('listagem estoque maior que 1', () => {
		page.abrirListagemEstoque();
		expect(page.getListagemTamanhoEstoque()).toBeGreaterThan(1); //Verifica se a listagem de cliente é maior que um
	});

	//Altera um estoque
	it('altera um estoque', () => {
		page.abrirUpdateEstoque()
		page.setInputsUpdateEstoque()
		page.limparJSAlert()
	})

	//Deleta um estoque
	it('deleta um estoque', () => {
		page.abrirListagemEstoque()
		page.deletarUltimoEstoque()
		page.limparJSAlert()
	})
	//FIM FILME

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	// TESTE AUTOMATIZADO DE CLIENTE
	//Inicio
	/*it('realizar login', () => {
		page.login() // abrindo o browser
		page.setLogin()
		page.limparJSAlert()
	})*/

	//Verifica se listagem de cliente contem mais de um registro
	it('listagem cliente maior que 1', () => {
		page.abrirListagemCliente();
		expect(page.getListagemTamanhoCliente()).toBeGreaterThan(1); //Verifica se a listagem de cliente é maior que um
	});

	//Salva um cliente com CPF errado
	it('salva um cliente CPF errado', () => {
		page.abrirSalvarCliente()
		expect(page.setInputsSalvarClienteCPFErrado()).toEqual('|| O CPF informado não está correto! Ele deve seguir o padrão xxx.xxx.xxx-xx');
	})

	//Salva Cliente com data de Nascimento em ordem errada
	it('salva um cliente Data Nascimento em ordem errada', () => {
		expect(page.setInputsSalvarClienteDataNascimentoOrdemErrado()).toEqual('|| A data de nascimento informada é inválida! Ela deve seguir o padrão YYYY-MM-DD');
	})

	//Salva cliente com ano incorreto
	it('salva um cliente Data Nascimento ano incorreto', () => {
		expect(page.setInputsSalvarClienteDataNascimentoAnoErrado()).toEqual('|| Deve ser inserida um Ano de nascimento válida');
	})

	//Salva cliente com mes incorreto
	it('salva um cliente Data Nascimento mes incorreto', () => {
		expect(page.setInputsSalvarClienteDataNascimentoMesErrado()).toEqual('|| Deve ser inserida um mês de nascimento válida');
	})

	//Salva cliente com dia incorreto
	it('salva um cliente Data Nascimento dia incorreto', () => {
		expect(page.setInputsSalvarClienteDataNascimentoDiaErrado()).toEqual('|| Deve ser inserida um dia de nascimento válida');
	})

	//Salva cliente com email incorreto
	it('salva um cliente com email incorreto', () => {
		expect(page.setInputsSalvarClienteEmailErrado()).toEqual('|| O email informado é inválido');
	})

	it('salva um cliente com email ja existente', () => {
		expect(page.setInputsSalvarClienteEmailJaCadastrado()).toEqual("O Email Inserido já foi cadastrado!")
		page.limparJSAlert()
	})

	//Salva cliente com telefone incorreto
	it('salva um cliente com telefone incorreto', () => {
		expect(page.setInputsSalvarClienteTelefoneErrado()).toEqual('|| O telefone informado não é válido! Ele deve seguir o padrão xxxx-xxxx');
	})

	//Salva cliente com cep com o layout incorreto
	it('salva um cliente com o layout do cep incorreto', () => {
		expect(page.setInputsSalvarClienteCEPErrado()).toEqual('|| O CEP informado não é válido! Ele deve seguir o layout XXXXX-XXX')
	})

	//Salva cliente com observação pequena
	it('salva clinte com uma observação pequena', () => {
		expect(page.setInputsSalvarClienteObservacaoErrado()).toEqual('|| A observação deve conter pelo menos 20 caracteres')
	})

	//Salva cliente com informações corretas
	it('salva um cliente informações corretas', () => {
		page.abrirSalvarCliente()
		page.setInputsSalvarCliente();
		page.limparJSAlert()
	})

	//Altera um cliente com CPF errado
	it('altera um cliente CPF incorreto', () => {
		page.abrirUpdateCliente()
		expect(page.setInputsUpdateClienteCPFErrado()).toEqual('|| O CPF informado não está correto! Ele deve seguir o padrão xxx.xxx.xxx-xx')
	})

	//Altera um cliente com Data de nascimento com formato errado
	it('altera um cliente formato data de nascimento errado', () => {
		expect(page.setInputsUpdateClienteDataNascimetoErrado()).toEqual('|| A data de nascimento informada é inválida! Ela deve seguir o padrão YYYY-MM-DD')
	})

	//Altera um cliente com ano errado
	it('altera um cliente ano da data de nascimento errado', () => {
		expect(page.setInputsUpdateClienteDataNascimetoAnoErrado()).toEqual('|| Deve ser inserida um Ano de nascimento válida')
	})

	//Altera um cliente com mes errado
	it('altera um cliente mes da data de nascimento errado', () => {
		expect(page.setInputsUpdateClienteDataNascimetoMesErrado()).toEqual('|| Deve ser inserida um mês de nascimento válida')
	})

	//Altera um cliente com dia errado
	it('altera um cliente dia da data de nascimento errado', () => {
		expect(page.setInputsUpdateClienteDataNascimetoDiaErrado()).toEqual('|| Deve ser inserida um dia de nascimento válida')
	})

	//Altera um cliente com telefone errado
	it('altera um cliente telefone errado', () => {
		expect(page.setInputsUpdateClienteTelefoneErrado()).toEqual('|| O telefone informado não é válido! Ele deve seguir o padrão xxxx-xxxx')
	})

	//Altera um cliente
	it('altera um cliente', () => {
		page.abrirUpdateCliente()
		page.setInputsUpdateCliente()
		page.limparJSAlert()
	})

	//Restaura a alteração de cliente para futura alteração
	it('restaura alteracao cliente', () => {
		page.abrirUpdateCliente()
		page.setRestauraInputsUpdateCliente()
		page.limparJSAlert()
	})

	//Deleta um cliente
	it('deleta um cliente', () => {
		page.abrirListagemCliente()
		page.deletarUltimoCliente()
		page.limparJSAlert()
	})
	// Fim



	// TESTE AUTOMATIZADO DE endereço
	// Inicio
	// Altera um endereco com cep errado
	it('altera um endereco com CEP errado', () => {
		page.abrirUpdateEndereco()
		expect(page.setInputsUpdateEnderecoCEPErrado()).toEqual('|| O CEP informado não é válido! Ele deve seguir o layout XXXXX-XXX')
	})

	// Altera um endereco com observação pequena
	it('altera um endereco com observacao errada', () => {
		expect(page.setInputsUpdateEnderecoObservacaoErrado()).toEqual('|| A observação deve conter pelo menos 20 caracteres')
	})

	// Altera um endereco com informações corretas
	it('altera um endereco', () => {
		page.abrirUpdateEndereco()
		page.setInputsUpdateEndereco()
		page.limparJSAlert()
	})

	// Restaura a alteração de endereco para futura alteração
	it('restaura alteracao endereco', () => {
		page.abrirUpdateEndereco()
		page.setRestauraInputsUpdateEndereco()
		page.limparJSAlert()
	})
	// Fim



	// TESTE AUTOMATIZADO DE CARTAO
	// Inicio
	// Altera um endereco
	it('altera um cartao com numero de cartao errado', () => {
		page.abrirUpdateCartao()
		expect(page.setUpdateCartaoNumeroErrado()).toEqual('|| O número de cartão informado não está correto')
	})

	it('altera um cartao com cvv errado', () => {
		expect(page.setUpdateCartaoCVVErrado()).toEqual('|| O CVV informado não está correto')
	})

	it('altera um cartao com data formato errado', () => {
		expect(page.setUpdateCartaoDataFormatoErrado()).toEqual('|| A data de validade informada é inválida! Ela deve seguir o padrão MM-YYYY')
	})

	it('altera um cartao com data mes errado', () => {
		expect(page.setUpdateCartaoDataMesErrado()).toEqual('|| Deve ser inserida um mês de nascimento válida')
	})

	it('altera um cartao com data ano errado', () => {
		expect(page.setUpdateCartaoDataAnoErrado()).toEqual('|| O ano da data de validade do cartão não é válido')
	})

	it('altera um cartao com dados corretos', () => {
		page.abrirUpdateCartao()
		page.setUpdateCartao()
		page.limparJSAlert()
	})

	// Alteração de senha do cliente
	it('altera senha com senha atual incorreta', () => {
		page.abrirAlteracaoSenhaCliente()
		expect(page.insereInputSenhaAtualIncorreta()).toEqual('A senha atual inserida não está correta')
		page.limparJSAlert()
	})

	it('altera senha com senhas novas divergentes', () => {
		expect(page.insereInputSenhasNovasDivergentes()).toEqual('As novas senhas inseridas não são iguais')
		page.limparJSAlert()
	})

	it('altera senha corretamente', () => {
		expect(page.insereInputSenhaCorreto()).toEqual('Alteração de senha executada com sucesso')
		page.limparJSAlert()
	})
	// Fim

	afterEach(async () => {
		// Assert that there are no errors emitted from the browser
		const logs = await browser.manage().logs().get(logging.Type.BROWSER);
		expect(logs).not.toContain(jasmine.objectContaining({
			level: logging.Level.SEVERE,
		} as logging.Entry));
	});

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	//
	//INICIO TESTE DE COMPRA ATÉ TROCA COM TROCA ACEITA
	//

	//Testes Automatizados Compra 
	/*it('realizar login', () => {
		page.login() // abrindo o browser
		page.setLogin()
		page.limparJSAlert()
	})*/

	it('escolher um filme', () => {
		page.indexFilme() // abrindo o browser
		page.setInputsIndexFilme()		
	})

	it('exibe um filme', () => {
		page.exibeFilme() // abrindo o browser
		page.setExibeFilme()
	})

	it('carrinho', () => {
		page.carrinhoFilme() // abrindo o browser
		page.setInputsCarrinhoFilme()
		page.limparJSAlert()
	})

	//Testes Automatizados Listagem Pedido/Sequencia até troca aceita (necessário o cadastro de pedido)
	it('acessa listagem pedidos', () => {
		page.abrirListagemPedido()
	})

	it('da sequencia do pedido de processamento até transito', () => {
		page.processamentoPedido()
	})

	it('acessa listagem pedidos de troca', () => {
		page.abrirListagemPedidoTroca()
	})

	it('aceita proposta de troca', () => {
		page.processamentoPedidoTrocaAceito()
	})

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	//
	//INICIO TESTE DE COMPRA ATÉ TROCA COM TROCA RECUSADA
	//

	//Testes Automatizados Listagem Pedido/Sequencia até troca recusada

	it('realizar login', () => {
		page.saiDaConta()
		page.login() // abrindo o browser
		page.setLogin()
		page.limparJSAlert()
	})

	it('escolher um filme', () => {
		page.indexFilme() // abrindo o browser
		page.setInputsIndexFilme()
	})

	it('exibe um filme', () => {
		page.exibeFilme() // abrindo o browser
		page.setExibeFilme()
	})

	it('carrinho', () => {
		page.carrinhoFilme() // abrindo o browser
		page.setInputsCarrinhoFilme()
		page.limparJSAlert()
	})

	it('acessa listagem pedidos', () => {
		page.abrirListagemPedido()
	})

	it('da sequencia do pedido de processamento até transito', () => {
		page.processamentoPedido()
	})

	it('acessa listagem pedidos de troca', () => {
		page.abrirListagemPedidoTroca()
	})

	it('aceita proposta de troca', () => {
		page.processamentoPedidoTrocaRecusado()
	})

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	//Inicio
	//TESTE AUTOMATIZADO Do GRÁFICO

	/*it('realizar login', () => {
		page.login() // abrindo o browser
		page.setLogin()
		page.limparJSAlert()
	})*/

	it('verifica grafico 1', () =>{
		page.abreGrafico1()
	})

	it('verifica data inicial maior que data final no grafico 2', () =>{
		page.abreGrafico2()
		expect(page.insereDataInicioMaiorDataFim()).toEqual('A Data Inical de Pesquisa não pode ser maior que a Data Final')
		page.limparJSAlert()
	})

	it('insere data correta no grafico 2', () =>{
		page.insereDataCorreta()
	})

	it('verifica mes inicial maior que mes final no grafico 3', () =>{
		page.abreGrafico3()
		expect(page.insereMesInicioMaiorMesFim()).toEqual('A Data Inical de Pesquisa não pode ser maior que a Data Final')
		page.limparJSAlert()
	})

	it('verfica ano invalido no grafico 3', () =>{
		expect(page.insereAnoInvalido()).toEqual('Insira um Ano Válido')
		page.limparJSAlert()
	})

	it('insere data correta no grafico 3', () =>{
		page.insereMesesAnoCorretos()
	})

});
