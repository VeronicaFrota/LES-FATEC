import { Component, OnInit } from '@angular/core';
import { Estoque } from 'src/modelos';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ListagemEstoqueService } from './listagem-estoque.service';
import { DeleteEstoqueService } from '../delete-estoque/delete-estoque.service';

@Component({
	selector: 'app-listagem-estoque',
	templateUrl: './listagem-estoque.component.html',
	styleUrls: ['./listagem-estoque.component.css']
})
export class ListagemEstoqueComponent implements OnInit {

	estoques: Estoque[]
	id_estoque: any
	submitted: false
	form: FormBuilder

	constructor(
		private route: ActivatedRoute,
		private service: ListagemEstoqueService,
		private deleteEstoqueServico: DeleteEstoqueService,
		private fb: FormBuilder) {

			this.route.params.subscribe(res => this.id_estoque = res.id_estoque)

		}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.estoques = res
		})
	}

	deletarEstoque(estoque) {

		console.log(estoque)

		var approve = confirm("Deseja realmente deletar o item do estoque: " + estoque.id)

		if (approve) {
			this.deleteEstoqueServico.deletarEstoque(estoque.id)
				.subscribe(response => {
					alert('Estoque com id ' + estoque.id + ' deletado com sucesso');
					window.location.href = "/listar-filme";
				})
		}
	}
}
