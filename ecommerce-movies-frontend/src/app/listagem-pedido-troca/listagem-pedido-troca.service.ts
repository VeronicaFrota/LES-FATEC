import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { ListagemPedido } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemPedidoTrocaService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/pedido/'

	list() {
		return this.http.get<ListagemPedido[]>(this.API);
	}

	alteraStatusPedido(pedido: any, id_transacao: any) {
		const body = new HttpParams()
			.set("operacao", "alterar")
			.set("id_transacao", id_transacao)
			.set("id_pedido", pedido.id)


		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/pedido/alterar/", body.toString(), {
			headers, observe: 'response'
		});

	}

	geraCupomTroca(dadosPedido) {
		//Gera Cupom de Troca
		var cupom = '';
		var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
		for (var i = 0; i < 7; i++) {
			cupom += characters.charAt(Math.floor(Math.random() * characters.length));
		}

		const bodyCupom = new HttpParams()
			.set("operacao", "salvar")
			.set("idCliente", dadosPedido.id_cliente)
			.set("codigo", cupom)
			.set("valor", dadosPedido.total_compra)
			.set("status", "1")

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		alert("O cupom " + cupom + " no valor de R$" + dadosPedido.total_compra + " foi salvo para o cliente de ID "
			+ dadosPedido.id_cliente);

		return this.http.post("http://localhost:8084/cupom/salvar/", bodyCupom.toString(), {
			headers, observe: 'response'
		});
	}
}
