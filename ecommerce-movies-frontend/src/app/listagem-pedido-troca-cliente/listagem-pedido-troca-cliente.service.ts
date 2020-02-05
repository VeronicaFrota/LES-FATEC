import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemPedidoPorCliente } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemPedidoTrocaClienteService {

	constructor(private http: HttpClient) { }

	pedidoPorCliente(id_cliente: any) {
		return this.http.get<ListagemPedidoPorCliente[]>("http://localhost:8084/pedido/especifico/?operacao=consultar&id_cliente=" + id_cliente);
	}

}
