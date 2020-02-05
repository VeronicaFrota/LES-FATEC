import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { ListagemPedido } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemPedidoService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/pedido/'
	
	list() {
		return this.http.get<ListagemPedido[]>(this.API);
	}

	alteraStatusPedido(pedido){
		const body = new HttpParams()
		.set("operacao", "alterar")
		.set("id_transacao", (pedido.id_transacao + 1))
		.set("id_pedido", pedido.id)
		

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/pedido/alterar/", body.toString(), {
			headers, observe: 'response'
		});

	}
}
