import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EcommerceExibeFilmeService } from './ecommerce-exibe-filme.service';
import { ActivatedRoute } from '@angular/router';
import { ListagemEstoque, Filme } from 'src/modelos';
import { ListagemEstoqueService } from '../listagem-estoque/listagem-estoque.service';
import { AlteraEstoqueService } from '../altera-estoque/altera-estoque.service';
import { EcommerceCarrinhoService } from '../ecommerce-carrinho/ecommerce-carrinho.service';

@Component({
	selector: 'app-ecommerce-exibe-filme',
	templateUrl: './ecommerce-exibe-filme.component.html',
	styleUrls: ['./ecommerce-exibe-filme.component.css']
})
export class EcommerceExibeFilmeComponent implements OnInit {

	id_filme: any
	
	@Input()
	filme: Filme = new Filme()

	constructor(
		private service: EcommerceExibeFilmeService,	
		private route: ActivatedRoute,
		private carrinhoService: EcommerceCarrinhoService
	) {

		this.route.params.subscribe(res => this.id_filme = res.id);

	}

	ngOnInit() {
		this.service.pegarUmFilme(this.id_filme)
			.subscribe(r => {
				this.filme = r

				console.log(this.filme)
			})
	}

	teste(filme: Filme) {
		this.carrinhoService.adicionaFilme(filme)
		window.location.href = "/ecommerce-carrinho";
	}

}
