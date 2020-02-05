import { Component, OnInit } from '@angular/core';
import { Pedido, ListagemPedidoPorCliente } from 'src/modelos';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemPedidoService } from '../listagem-pedido/listagem-pedido.service';
import { ListagemPedidoClienteService } from './listagem-pedido-cliente.service';
import { LoginService } from '../login/login.service';


@Component({
	selector: 'app-listagem-pedido-cliente',
	templateUrl: './listagem-pedido-cliente.component.html',
	styleUrls: ['./listagem-pedido-cliente.component.css']
})
export class ListagemPedidoClienteComponent implements OnInit {

	pedidos: ListagemPedidoPorCliente[]
	id_cliente: any
	submitted: false
	form: FormGroup

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private serviceLogin: LoginService,
		private service: ListagemPedidoClienteService,
		private serviceListagemPedido: ListagemPedidoService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_cliente = res.id);
	}

	ngOnInit() {
		this.service.pedidoPorCliente(this.id_cliente).subscribe((res: any[]) => {
			this.pedidos = res;
		})

		this.form = this.fb.group({
			id_transacao: [null]
		})
	}

	andamentoPedido(value) {
		if(value.id_transacao == 4) {
			var aprova = confirm("Deseja realmente realizar a \"Troca\" do produto?");
			if(aprova) {
				this.serviceListagemPedido.alteraStatusPedido(value)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listagem-pedido-troca-cliente/" + this.serviceLogin.getCliente().id;
					})
			}
		}
	}
}
