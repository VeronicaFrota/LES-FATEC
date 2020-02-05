import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListagemClienteService } from './listagem-cliente.service';
import { DeleteClienteService } from '../delete-cliente/delete-cliente.service';
import { Cliente } from 'src/modelos';
import { FormBuilder } from '@angular/forms';

@Component({
	selector: 'app-listagem-cliente',
	templateUrl: './listagem-cliente.component.html',
	styleUrls: ['./listagem-cliente.component.css']
})
export class ListagemClienteComponent implements OnInit {

	clientes: Cliente[]
	id_cliente: any
	submitted: false
	form: FormBuilder

	constructor(
		private route: ActivatedRoute,
		private service: ListagemClienteService,
		private deleteClienteService: DeleteClienteService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_cliente = res.id);
	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.clientes = res;
		})
	}

	deletarCliente(cliente) {

		console.log(cliente)

		var approve = confirm("Deseja realmente deletar o cliente " + cliente.nome)

		if (approve) {
			this.deleteClienteService.deletarCliente(cliente.id)
				.subscribe(response => {
					alert(cliente.nome + ' deletado com sucesso');
					window.location.href = "/listar-cliente";
				})
		}
	}
}
