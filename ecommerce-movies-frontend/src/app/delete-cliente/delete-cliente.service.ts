import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class DeleteClienteService {

	constructor(private http: HttpClient) { }

	deletarCliente(id_cliente: any) {
		return this.http.delete("http://localhost:8084/deletar/cliente/?operacao=excluir&id_cliente=" + id_cliente);
	}

}
