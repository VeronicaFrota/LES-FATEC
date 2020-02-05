import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class AtivarFilmeService {

	constructor(private http: HttpClient) { }

	ativaFilme(id_filme: any) {

		const body = new HttpParams()
		.set("operacao", "alterar")
		.set("id_filme", id_filme)
		.set("status", "1")

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.put("http://localhost:8084/ativa/filme/", body.toString(), {
			headers, observe: 'response'
		});
	}
}
