import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemPedidoService } from './listagem-pedido.service';
import { Pedido } from 'src/modelos';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
	selector: 'app-listagem-pedido',
	templateUrl: './listagem-pedido.component.html',
	styleUrls: ['./listagem-pedido.component.css']
})
export class ListagemPedidoComponent implements OnInit {

	pedidos: Pedido[]
	id_pedido: any
	submitted: false
	form: FormGroup

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: ListagemPedidoService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_pedido = res.id);
	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.pedidos = res;
		})

		this.form = this.fb.group({
			id_transacao: [null],
			id: [null],
		})
	}

	andamentoPedido(value) {
		if (value.id_transacao == 1) {
			var aprova = confirm("Deseja passar o pedido do Status de \"Processamento\" para \"Separação\"?");
			if (aprova) {
				this.service.alteraStatusPedido(value)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listar-pedido";
					});
			}
		}
		else if (value.id_transacao == 2) {
			var aprova = confirm("Deseja passar o pedido do Status de \"Separação\" para \"Transito\"?");
			if (aprova) {
				this.service.alteraStatusPedido(value)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!")
						window.location.href = "/listar-pedido"
					});
			}
		}
		else if (value.id_transacao == 3) {
			var aprova = confirm("Deseja passar o pedido do Status de \"Transito\" para \"Entregue\"?");
			if (aprova) {
				this.service.alteraStatusPedido(value)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listar-pedido";
					});
			}
		}
		else if (value.id_transacao == 4) {
			var aprova = confirm("Deseja passar o pedido do Status de \"Entregue\" para \"Troca\"?");
			if (aprova) {
				this.service.alteraStatusPedido(value)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listar-pedido";
					});
			}
		}
	}
}
