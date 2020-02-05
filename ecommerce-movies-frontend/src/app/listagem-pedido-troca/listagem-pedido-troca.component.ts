import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemPedidoTrocaService } from './listagem-pedido-troca.service';
import { Pedido } from 'src/modelos';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
	selector: 'app-listagem-pedido-troca',
	templateUrl: './listagem-pedido-troca.component.html',
	styleUrls: ['./listagem-pedido-troca.component.css']
})
export class ListagemPedidoTrocaComponent implements OnInit {

	pedidos: Pedido[]
	id_pedido: any
	submitted: false
	form: FormGroup

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: ListagemPedidoTrocaService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_pedido = res.id);
	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.pedidos = res;
		})
	}

	andamentoPedido(dadosPedido: any, transacao: any) {
		if (transacao == "autorizado") {
			var aprova = confirm("Deseja autorizar esse pedido de Troca?");
			if (aprova) {
				this.service.geraCupomTroca(dadosPedido)
					.subscribe(response => {
					});
				this.service.alteraStatusPedido(dadosPedido, 6)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listar-pedido-troca";
					});
			}
		}
		else if (transacao == "recusado") {
			var aprova = confirm("Deseja recusar esse pedido de Troca?");
			if (aprova) {
				this.service.alteraStatusPedido(dadosPedido, 7)
					.subscribe(response => {
						alert("Alteração de status realizada com sucesso!");
						window.location.href = "/listar-pedido-troca";
					});
			}
		}
	}
}
