import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class DeleteFilmeService {

	constructor(private http: HttpClient) { }

	deletarFilme(id_filme: any) {
		return this.http.delete("http://localhost:8084/deletar/filme/?operacao=excluir&id_filme=" + id_filme);
	}

}
