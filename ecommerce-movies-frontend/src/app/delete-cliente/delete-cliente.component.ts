import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeleteClienteService } from './delete-cliente.service';

@Component({
	selector: 'app-delete-cliente',
	templateUrl: './delete-cliente.component.html',
	styleUrls: ['./delete-cliente.component.css']
})
export class DeleteClienteComponent implements OnInit {

	id_cliente: any

	constructor(
		private route: ActivatedRoute,
		private service: DeleteClienteService) {

			this.route.params.subscribe(res => this.id_cliente = res.id);

		}

	ngOnInit() {
	}

	onClickMe() {
		this.service.deletarCliente(this.id_cliente);
	}

}
