import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class DeleteCartaoService {

	constructor(private http: HttpClient) { }

	deletarCartao(id_cartao: any) {
		return this.http.delete("http://localhost:8084/cliente/deleta/cartao/?operacao=excluir&id_cartao=" + id_cartao);
	}

}
