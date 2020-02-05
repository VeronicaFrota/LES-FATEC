import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class DeleteEnderecoService {

	constructor(private http: HttpClient) { }

	deletarEndereco(id_endereco: any) {
		return this.http.delete("http://localhost:8084/cliente/deleta/endereco/?operacao=excluir&id_endereco=" + id_endereco);
	}

}
