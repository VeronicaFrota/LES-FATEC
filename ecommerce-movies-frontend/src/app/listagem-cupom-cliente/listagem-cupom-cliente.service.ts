import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemCupom } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemCupomClienteService {

	constructor(private http: HttpClient) { }

	cupomPorCliente(id_cliente: any) {
		return this.http.get<ListagemCupom[]>("http://localhost:8084/cupom/especifico/?operacao=consultar&id_cliente=" + id_cliente)
	}

}
