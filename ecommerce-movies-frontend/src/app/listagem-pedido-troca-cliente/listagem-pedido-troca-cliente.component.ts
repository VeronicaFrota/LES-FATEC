import { Component, OnInit } from '@angular/core';
import { ListagemPedidoPorCliente } from 'src/modelos';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemPedidoClienteService } from '../listagem-pedido-cliente/listagem-pedido-cliente.service';

@Component({
	selector: 'app-listagem-pedido-troca-cliente',
	templateUrl: './listagem-pedido-troca-cliente.component.html',
	styleUrls: ['./listagem-pedido-troca-cliente.component.css']
})
export class ListagemPedidoTrocaClienteComponent implements OnInit {

	pedidos: ListagemPedidoPorCliente[]
	id_cliente: any
	submitted: false
	form: FormGroup

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: ListagemPedidoClienteService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_cliente = res.id);
	}

	ngOnInit() {
		this.service.pedidoPorCliente(this.id_cliente).subscribe((res: any[]) => {
			this.pedidos = res;
		})
	}
}
