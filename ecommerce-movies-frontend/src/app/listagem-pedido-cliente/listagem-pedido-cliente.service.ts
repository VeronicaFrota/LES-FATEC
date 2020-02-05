import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { ListagemPedido, ListagemPedidoPorCliente } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemPedidoClienteService {

	constructor(private http: HttpClient) { }

	pedidoPorCliente(id_cliente: any) {
		return this.http.get<ListagemPedidoPorCliente[]>("http://localhost:8084/pedido/especifico/?operacao=consultar&id_cliente=" + id_cliente);
	}

}