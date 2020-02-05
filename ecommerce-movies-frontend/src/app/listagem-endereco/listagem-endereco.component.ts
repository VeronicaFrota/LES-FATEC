import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListagemEnderecoService } from './listagem-endereco.service';
import { DeleteEnderecoService } from '../delete-endereco/delete-endereco.service';
import { Endereco } from 'src/modelos';
import { FormBuilder } from '@angular/forms';

@Component({
	selector: 'app-listagem-endereco',
	templateUrl: './listagem-endereco.component.html',
	styleUrls: ['./listagem-endereco.component.css']
})
export class ListagemEnderecoComponent implements OnInit {

	enderecos: Endereco[]
	id_endereco: any
	submitted: false
	form: FormBuilder

	constructor(
		private route: ActivatedRoute,
		private service: ListagemEnderecoService,
		private deleteEnderecoService: DeleteEnderecoService,
		private fb: FormBuilder) {

		this.route.params.subscribe(res => this.id_endereco = res.id);

	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.enderecos = res;
		})
	}

	deletarEndereco(endereco) {

		console.log(endereco)

		var approve = confirm("Deseja realmente deletar o endereco com id: " + endereco.id)

		if (approve) {
			this.deleteEnderecoService.deletarEndereco(endereco.id)
				.subscribe(response => {
					alert('Enderedo com id ' + endereco.id + ' deletado com sucesso');
					window.location.href = "/listar-cliente";
				})
		}
	}
}
