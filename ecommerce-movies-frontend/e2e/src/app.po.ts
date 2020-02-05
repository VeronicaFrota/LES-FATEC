import { browser, by, element } from 'protractor';
import { elementEnd } from '@angular/core/src/render3';
import { protractor } from 'protractor/built/ptor';

export class AppPage {


	// FILME E FORNECEDOR
	// Abre página para salvar filme
	abrirSalvarFilme() {
		return browser.get(`http://localhost:4200/cadastro-filme`) as Promise<any>;
	}

	// Abre página de alteração de filme
	abrirUpdateFilme() {
		return browser.get(`http://localhost:4200/altera-filme/1`) as Promise<any>;
	}

	//Abre a pagina de listagem de filmes
	abrirListagemFilme(){
		return browser.get(`http://localhost:4200/listar-filme`) as Promise<any>;
	}

	//Abre a pagina de listagem de estoque
	abrirListagemEstoque(){
		return browser.get(`http://localhost:4200/listar-estoque`) as Promise<any>;
	}

	//Abre a pagina de alteração de endereco
	abrirUpdateEstoque(){
		return browser.get(`http://localhost:4200/altera-estoque/1`) as Promise<any>;
	}

	// Limpar e aceitar alert
	limparJSAlert() {
		browser.driver.sleep(2000)
		var alertDialog = browser.switchTo().alert();
		alertDialog.accept()
	}

	novoPedido(){
		return browser.get(`http://localhost:4200`) as Promise<any>;
	}

	//Faz a verificação se a listagem de filme contem mais de um registro
	getListagemTamanho() {
		browser.driver.sleep(2000)
		return element.all(by.css('.item-filme')).count() as Promise<number>;
	}


	//Faz a verificação se a listagem de estoque contem mais de um registro
	getListagemTamanhoEstoque(){
		browser.driver.sleep(2000)
		return element.all(by.css('.item-estoque')).count() as Promise<number>;
	}

	//Insere dados no cadastro de filme e estoque
	setInputsSalvar() {
		element(by.id('nome')).sendKeys('Filme do selenium')
		element(by.id('codigo_barras')).sendKeys('4242')
		element(by.id('genero')).$('[value="3"]').click()
		element(by.id('ano')).sendKeys('2019')
		element(by.id('paisOrigem')).$('[value="1"]').click()
		element(by.id('idioma')).$('[value="2"]').click()
		element(by.id('classificacaoEtaria')).$('[value="2"]').click()
		element(by.id('sinopse')).sendKeys('testetestesteste')
		element(by.id('fornecedor')).sendKeys('Fornecedor do Selenium')
		element(by.id('quantidadeEstoque')).sendKeys('42')
		element(by.id('valorCompra')).sendKeys('52')
		element(by.id('valorVenda')).sendKeys('62')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar

		browser.driver.sleep(2000)
	}

	//Insere informações de alteração de filme
	setInputsUpdate() {
		element(by.id('nome')).clear()								// Limpar para atualizar o input
		element(by.id('nome')).sendKeys('Filme do selenium alterar')
		element(by.id('codigo_barras')).clear()
		element(by.id('codigo_barras')).sendKeys('2')
		element(by.id('genero')).$('[value="3"]').click()
		element(by.id('ano')).sendKeys('2019')
		element(by.id('paisOrigem')).$('[value="1"]').click()
		element(by.id('idioma')).$('[value="2"]').click()
		element(by.id('classificacaoEtaria')).$('[value="2"]').click()
		element(by.id('sinopse')).sendKeys('testetestesteste')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
	}

	//Insere informações de alteração do estoque
	setInputsUpdateEstoque(){
		element(by.id('fornecedor')).sendKeys('Fornecedor do Selenium altera')
		element(by.id('quantidadeEstoque')).sendKeys('24')
		element(by.id('valorCompra')).sendKeys('54')
		element(by.id('valorVenda')).sendKeys('64')
		
		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar
		browser.driver.sleep(2000)
	}


	//Deleta o ultimo filme Cadastrado
	deletarUltimoFilme() {
		element.all(by.css('.item-filme-delete')).last().click()	// Deleta o ultimo registro
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}


	//Deleta o ultimo estoque Cadastrado
	deletarUltimoEstoque(){
		element.all(by.css('.item-estoque-delete')).last().click()	// Deleta o ultimo registro
		browser.driver.sleep(2000)
		this.limparJSAlert()
	} 
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////


	// CLIENTE E ENDERECO
	//Abre a pagina de listagem de cliente

	//Abre a pagina de alteração de cartao
	abrirUpdateCartao(){
		return browser.get(`http://localhost:4200/altera-cartao/1`) as Promise<any>;
	}

	//Abre a pagina de alteração de senha do cliente
	abrirAlteracaoSenhaCliente(){
		return browser.get(`http://localhost:4200/altera-senha/1`) as Promise<any>;
	}

	abrirListagemCliente(){
		return browser.get(`http://localhost:4200/listar-cliente`) as Promise<any>;
	}

	//Abre a pagina de alteração de cliente
	abrirUpdateCliente() {
		return browser.get(`http://localhost:4200/altera-cliente/1`) as Promise<any>;
	}

	//Abre a pagina de alteração de endereco
	abrirUpdateEndereco(){
		return browser.get(`http://localhost:4200/altera-endereco/17`) as Promise<any>;
	}

	//Faz a verificação se a listagem de cliente contem mais de um registro 
	getListagemTamanhoCliente() {
		browser.driver.sleep(2000)
		return element.all(by.css('.item-cliente')).count() as Promise<number>;
	}

	//Insere dados no cadastro de cliente
	setInputsSalvarCliente() {
		element(by.id('nome')).sendKeys('Cliente Teste Automatico')
		//Manda CPF incorreto
		element(by.id('cpf')).sendKeys('416.947.018-40')
		element(by.id('dataNascimento')).sendKeys('1998-12-20')
		element(by.id('email')).sendKeys(this.geraEmailAleatorio() + '@gmail.com')
		element(by.id('senha')).sendKeys('123456@Automatico')
		element(by.id('genero')).$('[value="M"]').click()
		element(by.id('telefone')).sendKeys('4758-2311')
		element(by.id('idCidade')).$('[value="1"]').click()
		element(by.id('logradouro')).sendKeys('Rua Automatico')
		element(by.id('numero')).sendKeys('123')
		element(by.id('bairro')).sendKeys('Bairro Automatico')
		element(by.id('cep')).sendKeys('08736-952')
		element(by.id('observacao')).sendKeys('Observacao Automatico')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)
	}

	geraEmailAleatorio(){
		var result           = '';
   		var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
   		var charactersLength = characters.length;
   		for ( var i = 0; i < 10; i++ ) {
      		result += characters.charAt(Math.floor(Math.random() * charactersLength));
		}
		return result;   
	}

	setInputsSalvarClienteCPFErrado() {
		element(by.id('nome')).sendKeys('Cliente Teste Automatico')
		//Manda CPF incorreto
		element(by.id('cpf')).sendKeys('41694701840')
		element(by.id('dataNascimento')).sendKeys('1998-12-20')
		element(by.id('email')).sendKeys(this.geraEmailAleatorio() + '@gmail.com')
		element(by.id('senha')).sendKeys('123456@Automatico')
		element(by.id('genero')).$('[value="M"]').click()
		element(by.id('telefone')).sendKeys('4758-2311')
		element(by.id('idCidade')).$('[value="1"]').click()
		element(by.id('logradouro')).sendKeys('Rua Automatico')
		element(by.id('numero')).sendKeys('123')
		element(by.id('bairro')).sendKeys('Bairro Automatico')
		element(by.id('cep')).sendKeys('08736-952')
		element(by.id('observacao')).sendKeys('Observacao Automatico')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteDataNascimentoOrdemErrado() {
		element(by.id('cpf')).clear()
		element(by.id('cpf')).sendKeys('416.947.018-40')
		//Manda data nascimento em ordem errada
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('20-12-1998')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteDataNascimentoAnoErrado() {
		//Manda data nascimento com ano errado
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('2019-12-20')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteDataNascimentoMesErrado() {
		//Manda data nascimento com ano errado
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1998-13-20')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteDataNascimentoDiaErrado() {
		//Manda data nascimento com ano errado
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1998-12-33')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteEmailErrado() {
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1998-12-20')
		element(by.id('email')).clear()
		element(by.id('email')).sendKeys('testeAutomatico')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteEmailJaCadastrado(){
		element(by.id('email')).clear()
		element(by.id('email')).sendKeys('rafaelnunesvazquez@gmail.com')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	setInputsSalvarClienteTelefoneErrado() {
		element(by.id('email')).clear()
		element(by.id('email')).sendKeys(this.geraEmailAleatorio() + '@gmail.com')
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('47582311')

		element(by.css('.btn.btn-primary')).click()	

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteCEPErrado(){
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('4758-2311')
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08326320')
		

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}

	setInputsSalvarClienteObservacaoErrado(){
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08326-320')

		element(by.id('observacao')).clear()
		element(by.id('observacao')).sendKeys('Teste')
		

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}	

	//Insere informações de alteração de cliente
	setInputsUpdateCliente(){
		element(by.id('nome')).clear()
		element(by.id('nome')).sendKeys('Atualizacao Cadastro Automatico')
		element(by.id('cpf')).clear()
		element(by.id('cpf')).sendKeys('572.365.025-86')
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1990-11-15')
		element(by.id('genero')).$('[value="F"]').click()
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('96582-1257')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
	}

	//Insere informações de alteração de cliente
	setInputsUpdateClienteCPFErrado(){
		element(by.id('nome')).clear()
		element(by.id('nome')).sendKeys('Atualizacao Cadastro Automatico')
		element(by.id('cpf')).clear()
		element(by.id('cpf')).sendKeys('57236502586')
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1990-11-15')
		element(by.id('genero')).$('[value="F"]').click()
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('96582-1257')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar
	
		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}

	//Insere informações de alteração de cliente
	setInputsUpdateClienteDataNascimetoErrado(){
		element(by.id('cpf')).clear()
		element(by.id('cpf')).sendKeys('572.365.025-86')
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('15-11-1990')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}

	setInputsUpdateClienteDataNascimetoAnoErrado(){
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('2019-11-15')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}

	setInputsUpdateClienteDataNascimetoMesErrado(){
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1990-13-15')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}

	setInputsUpdateClienteDataNascimetoDiaErrado(){
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1990-11-33')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}

	setInputsUpdateClienteTelefoneErrado(){
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1990-11-12')
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('965821257')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		return element(by.id('mensagemErro')).getText()
	}


	//Insere informações anteriores do cliente para futuras alterações
	setRestauraInputsUpdateCliente(){
		element(by.id('nome')).clear()
		element(by.id('nome')).sendKeys('Rafael Nunes Vazquez')
		element(by.id('cpf')).clear()
		element(by.id('cpf')).sendKeys('416.947.018-40')
		element(by.id('dataNascimento')).clear()
		element(by.id('dataNascimento')).sendKeys('1998-12-20')
		element(by.id('genero')).$('[value="M"]').click()
		element(by.id('telefone')).clear()
		element(by.id('telefone')).sendKeys('98488-7218')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
	}

	setInputsUpdateEnderecoCEPErrado(){
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08326320')
		

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}	

	setInputsUpdateEnderecoObservacaoErrado(){
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08326-320')

		element(by.id('observacao')).clear()
		element(by.id('observacao')).sendKeys('Teste')
		

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()
	}	

	//Insere informações de alteração do endereco
	setInputsUpdateEndereco(){
		element(by.id('idCidade')).$('[value="2"]')
		element(by.id('logradouro')).clear()
		element(by.id('logradouro')).sendKeys('Rua Teste Automatizado')
		element(by.id('numero')).clear()
		element(by.id('numero')).sendKeys('12345A')
		element(by.id('bairro')).clear()
		element(by.id('bairro')).sendKeys('Bairro Teste')
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08326-320')
		element(by.id('observacao')).clear()
		element(by.id('observacao')).sendKeys('Observacao Teste Automatizado')
		

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
	}

	//Insere informações anteriores do endereco para futuras alterações
	setRestauraInputsUpdateEndereco(){
		element(by.id('idCidade')).$('[value="1"]')
		element(by.id('logradouro')).clear()
		element(by.id('logradouro')).sendKeys('Rua Francisco Martins Feitosa')
		element(by.id('numero')).clear()
		element(by.id('numero')).sendKeys('1202')
		element(by.id('bairro')).clear()
		element(by.id('bairro')).sendKeys('Vila Lavinia')
		element(by.id('cep')).clear()
		element(by.id('cep')).sendKeys('08735-420')
		element(by.id('observacao')).clear()
		element(by.id('observacao')).sendKeys('Casa verde, Ao lado do Lava Rapido')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
	}

	setUpdateCartao(){
		element(by.id('numeroCartao')).clear()
		element(by.id('numeroCartao')).sendKeys('1234123412341234')
		element(by.id('idBandeira')).$('[value="2"]')
		element(by.id('cvv')).clear()
		element(by.id('cvv')).sendKeys('123')
		element(by.id('dataValidade')).clear()
		element(by.id('dataValidade')).sendKeys('12/2020')
		
		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)
		
	}

	setUpdateCartaoNumeroErrado(){
		element(by.id('numeroCartao')).clear()
		element(by.id('numeroCartao')).sendKeys('123412341234')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()

	}

	setUpdateCartaoCVVErrado(){
		element(by.id('numeroCartao')).clear()
		element(by.id('numeroCartao')).sendKeys('1234123412341234')

		element(by.id('cvv')).clear()
		element(by.id('cvv')).sendKeys('1')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()

	}

	setUpdateCartaoDataFormatoErrado(){
		element(by.id('cvv')).clear()
		element(by.id('cvv')).sendKeys('123')

		element(by.id('dataValidade')).clear()
		element(by.id('dataValidade')).sendKeys('2020/12')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()

	}

	setUpdateCartaoDataMesErrado(){
		element(by.id('dataValidade')).clear()
		element(by.id('dataValidade')).sendKeys('13/2020')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()

	}

	setUpdateCartaoDataAnoErrado(){
		element(by.id('dataValidade')).clear()
		element(by.id('dataValidade')).sendKeys('12/2018')


		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de alterar

		browser.driver.sleep(2000)

		return element(by.id('mensagemErro')).getText()

	}

	//Delata o ultimo cliente cadastrado
	deletarUltimoCliente() {
		element.all(by.css('.item-cliente-delete')).last().click()	// Deleta o ultimo registro
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}

	//Deleta o ultimo endereco Cadastrado
	deletarUltimoEndereco(){
		element.all(by.css('.item-endereco-delete')).last().click()	// Deleta o ultimo registro
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}

	//Abre página de cadasto de Cliente
	abrirSalvarCliente(){
		return browser.get(`http://localhost:4200/cadastro-cliente`) as Promise<any>;
	} 

	//Altera senha com senha atual incorreta
	insereInputSenhaAtualIncorreta(){
		element(by.id('senhaAtual')).sendKeys('123456Ra')
		element(by.id('senhaNova1')).sendKeys('123456Ra@')
		element(by.id('senhaNova2')).sendKeys('123456Ra@')
		element(by.id('btnAlteraSenha')).click()

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	//Altera senha com senhas novas divergentes
	insereInputSenhasNovasDivergentes(){
		element(by.id('senhaAtual')).clear()
		element(by.id('senhaAtual')).sendKeys('123456Ra@')
		element(by.id('senhaNova2')).clear()
		element(by.id('senhaNova2')).sendKeys('123456Rs@')
		element(by.id('btnAlteraSenha')).click()

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	//Altera senha com senha fraca
	insereInputSenhaCorreto(){
		element(by.id('senhaNova1')).clear()
		element(by.id('senhaNova1')).sendKeys('123456Ra@')
		element(by.id('senhaNova2')).clear()
		element(by.id('senhaNova2')).sendKeys('123456Ra@')
		element(by.id('btnAlteraSenha')).click()

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////

	login() {
		return browser.get(`http://localhost:4200`) as Promise<any>;
	}

	// URL PARA ESCOLHA
	indexFilme() {
		return browser.get(`http://localhost:4200/ecommerce-index`) as Promise<any>;
	}

	// Abre página de alteração de filme
	exibeFilme() {
		return browser.get(`http://localhost:4200/ecommerce-exibe-filme/1`) as Promise<any>;
	}

	// Abre página de alteração de filme
	carrinhoFilme() {
		return browser.get(`http://localhost:4200/ecommerce-carrinho`) as Promise<any>;
	}

	saiDaConta(){
		element(by.id('botaoSair')).click()
	}

	setLogin(){
		element(by.id('email')).sendKeys('rafaelnunesvazquez@gmail.com')
		element(by.id('senha')).sendKeys('123456Ra@')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar
		browser.driver.sleep(2000)
		
	}

	setLoginInexistente(){
		element(by.id('email')).sendKeys('emailinexistente@gmail.com')
		element(by.id('senha')).sendKeys('123456Ra@')

		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar
		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert;
	}

	//Insere dados PARA A ESCOLHA
	setInputsIndexFilme() {
		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar
		browser.driver.sleep(2000)
		//return element.all(by.css('.col-sm-4 item-filme')).count() as Promise<number>;
	}

	//Insere dados no cadastro de filme e estoque
	setExibeFilme() {
		//element.all(by.css('.item-filme-delete')).first().click()	// Deleta o ultimo registro
		element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar
		browser.driver.sleep(2000)
	}

	//Insere dados no cadastro de filme e estoque
	setInputsCarrinhoFilme() {

		element(by.id('quantidadeComprada')).$('[value="1"]').click()
		
		element(by.id('codigo')).sendKeys('vtx7785')
		//element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar
		
		element(by.id('frete')).sendKeys('08789999')
		//element(by.css('.btn.btn-primary')).click()					// Para clicar no botão de salvar

		//element(by.id('quantidadeParcela')).sendKeys('1')

		element(by.id('success')).click()					// Para clicar no botão de salvar

		browser.driver.sleep(2000)
	} 

	//Abre a página de listagem de pedido
	abrirListagemPedido(){
		return browser.get(`http://localhost:4200/listar-pedido`) as Promise<any>;
	}

	//Da seguimento da proposta cadastrada de processamento até troca
	processamentoPedido(){
		
		//Passa o processamento de processamento para separação
		element.all(by.css('.item-pedido')).last().$('[value="separacao"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()

		//Passa o processamento de separação para transito
		element.all(by.css('.item-pedido')).last().$('[value="transito"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()

		//Passa o processamento de transito para entregue
		element.all(by.css('.item-pedido')).last().$('[value="entregue"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()

		//Passa o processamento de entregue para troca
		element.all(by.css('.item-pedido')).last().$('[value="troca"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}

	//Abre página de listagem de pedido de troca
	abrirListagemPedidoTroca(){
		return browser.get(`http://localhost:4200/listar-pedido-troca`) as Promise<any>;
	}

	//Aceita proposta de troca
	processamentoPedidoTrocaAceito(){
		element.all(by.css('.item-pedido-troca')).last().$('[value="autorizado"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}

	//Recusa proposta de troca
	processamentoPedidoTrocaRecusado(){
		element.all(by.css('.item-pedido-troca')).last().$('[value="recusado"]').click()
		browser.driver.sleep(2000)
		this.limparJSAlert()
		browser.driver.sleep(2000)
		this.limparJSAlert()
	}


	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////

	//GRAFICO

	abreGrafico1(){
		return browser.get(`http://localhost:4200/doughnut-chart`) as Promise<any>;
	}

	abreGrafico2(){
		return browser.get(`http://localhost:4200/line-chart`) as Promise<any>;
	}

	abreGrafico3(){
		return browser.get(`http://localhost:4200/pie-chart`) as Promise<any>;
	}

	insereDataInicioMaiorDataFim(){
		element(by.id('diaComeco')).sendKeys('01042019')
		element(by.id('diaFim')).sendKeys('01032019')
		element(by.id('btnPesquisa')).click()
		
		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	insereDataCorreta(){
		element(by.id('diaComeco')).clear()
		element(by.id('diaComeco')).sendKeys('01042019')
		element(by.id('diaFim')).clear()
		element(by.id('diaFim')).sendKeys('01052019')
		element(by.id('btnPesquisa')).click()
	}

	insereMesInicioMaiorMesFim(){
		element(by.id('mesComeco')).$('[value="5"]').click()
		element(by.id('mesFim')).$('[value="1"]').click()
		element(by.id('ano')).sendKeys("2019")
		element(by.id('btnPesquisa')).click()

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	insereAnoInvalido(){
		element(by.id('mesComeco')).$('[value="1"]').click()
		element(by.id('mesFim')).$('[value="5"]').click()
		element(by.id('ano')).clear()
		element(by.id('ano')).sendKeys("1500")
		element(by.id('btnPesquisa')).click()

		browser.driver.sleep(2000)

		var alert = browser.switchTo().alert()
		var textoAlert = alert.getText()
        return textoAlert ;
	}

	insereMesesAnoCorretos(){
		element(by.id('ano')).clear()
		element(by.id('ano')).sendKeys("2019")
		element(by.id('btnPesquisa')).click()
	}
}
