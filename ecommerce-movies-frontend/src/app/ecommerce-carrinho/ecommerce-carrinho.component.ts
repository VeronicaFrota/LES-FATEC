import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, NgModel } from '@angular/forms';
import { EcommerceCarrinhoService } from './ecommerce-carrinho.service';
import { ActivatedRoute } from '@angular/router';
import { Filme, Frete, Pedido, Cupom, Cliente, Endereco, Cartao, ListagemCartao } from 'src/modelos';
import { EcommerceFreteService } from '../ecommerce-frete/ecommerce-frete.service';
import { LoginService } from '../login/login.service';
import { forEach } from '@angular/router/src/utils/collection';
import { isNgTemplate } from '@angular/compiler';
import { nullSafeIsEquivalent } from '@angular/compiler/src/output/output_ast';
/* import { FreteService } from '../app.service';
 */
@Component({
	selector: 'app-ecommerce-carrinho',
	templateUrl: './ecommerce-carrinho.component.html',
	styleUrls: ['./ecommerce-carrinho.component.css']
})
export class EcommerceCarrinhoComponent implements OnInit {

	carrinho: Filme[]
	cupomEncontrado: Boolean
	frete: Frete
	pedido: Pedido = new Pedido()
	cliente: Cliente
	form: FormGroup

	constructor(
		private service: EcommerceCarrinhoService,
		private serviceLogin: LoginService,
		private fb: FormBuilder
	) {}

	ngOnInit() {
		this.form = this.fb.group({
			quantidadeParcela:  [null, [Validators.required]],
			quantidadeComprada: [null, [Validators.required]],
			frete:  ['', [Validators.required]],
			codigo: ['', '']
		})
		this.cliente = this.serviceLogin.getCliente()
		this.carrinho = this.service.listarCarrinho()
	}

	// Remove itens do carrinho atraves do index
	removerCarrinho(index) {
		this.service.atualizaCarrinho(this.carrinho.filter((item, n) => n !== index))
		window.location.reload()						// recarrega a pagina para que os dados do item excluido desapareca da página
	}

	// Soma a quantidade comprada com o valor do produto
	testeSoma(event, index) {
		console.log(event.target.value)
		console.log(index)

		this.carrinho[index].quantidade_comprada = event.target.value			// pega quantidade comprada
		this.carrinho[index].total_compra = this.carrinho[index].estoque.valor_venda * this.carrinho[index].quantidade_comprada	// soma quantidade comprada
	
		console.log("**** Quantidade comprada ****")
		console.log(this.carrinho[index].quantidade_comprada)
	}

	//
	atualizaQuantidadeParcela(event) {
		this.pedido.quantidade_parcela = event.target.value
	}

	atualizaQuantidadeParcelaPorCartao(event, index) {
		this.cliente.cartoes[index].quantidadeParcela = event.target.value
	}

	selectCartao(indexCartao) {
		this.cliente.cartoes = this.cliente.cartoes.map((c: ListagemCartao, i: number) => {
			if (i == indexCartao) {
				c.selected = !c.selected;
			}
			return c;
		})
	}

	selectEndereco(endereco: Endereco) {
		this.cliente.endereco = this.cliente.endereco.map((e: Endereco) => {
			e.selected = e.id == endereco.id;
			return e;
		})
	}

	// // Pega a quantidade de parcela
	testeParcela(event) {
		console.log("**** testeConsoleParcela - event ****")
		console.log(event.target.value)
		this.pedido.quantidade_parcela = event.target.value

		console.log("**** testeConsoleParcela - quantidade_parcela ****")
		console.log(this.pedido.quantidade_parcela)
	}

	aplicarCupom() {

		//Zera informações gravadas no pedido para inserção de novo cupom
		this.pedido.valor_cupom = null
		this.pedido.id_cupom = null

		this.cupomEncontrado = false
		let cupomValor = false
		let cupons = []

		let verificaCodigo = this.form.get("codigo").value

		this.service.listaCupons(this.cliente.id).subscribe((res: any[]) => {
			cupons = res;

			for (let index = 0; index < cupons.length; index++) {
				if(verificaCodigo == cupons[index].codigo){
					this.cupomEncontrado = true;
					if(this.total() >= cupons[index].valor){
						this.pedido.id_cupom = cupons[index].id
						this.pedido.valor_cupom = cupons[index].valor
						cupomValor = true;
					}
				}
			}
	
			if (!this.cupomEncontrado){
				alert("O cupom inserido não existe!")
			}
			else{
				if (!cupomValor){
					alert("O cupom inserido contém um valor maior que o total da compra!")
				}
			}

		})
	}

	// Calcula o total de produtos comprados
	total(): number {
		let valor_frete = this.form.get("frete").value

        return this.carrinho
            .map(item => item.total_compra)      															// troca o cartItem para um valor do cartItem
            .reduce((prev, value) => prev + value, 0) - this.pedido.valor_cupom	- this.pedido.valor_frete	// soma o valor anterior + o valor atual
	}

	mandarPedido() {
		this.pedido.filmes = this.carrinho
		this.pedido.total_compra = this.total()
		this.pedido.id_cliente = this.cliente.id
		this.pedido.cartoes = this.cliente.cartoes.filter(_ => _.selected)
		this.pedido.id_endereco = this.cliente.endereco.filter(_ => _.selected)[0].id

		if(this.pedido.id_cupom != null){
			this.service.alteraStatusCupom(this.pedido.id_cupom).subscribe()
			alert('O cupom não poderá ser mais utilizado');
		}

		this.service.salvar(this.pedido)
			.subscribe(
				r => console.log("Retorno " + r))
		alert('Compra realizada com sucesso!');
			window.location.href = "/ecommerce-index";
	}

	calcularFrete() {
		this.service.frete(this.frete).subscribe((res: Frete[]) => {
			console.log("Frete: " + this.form.get("frete").value)
			this.frete = res[0];
			console.log("****Frete ****")
			console.log(this.frete.valor_frete)
		})
	}
}