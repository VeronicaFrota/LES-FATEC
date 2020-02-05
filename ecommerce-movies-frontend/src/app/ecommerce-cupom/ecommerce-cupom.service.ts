import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class EcommerceCupomService {

	constructor(private http: HttpClient) { }

	salvar(cupom) {

		/* const body = new HttpParams()
			.set("operacao", "salvar")
			.set("codigo", cupom.codigo)
			.set("valor", cupom.valor); ??

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/pedido/salvar/cupom/", body.toString(), {
			headers, observe: 'response'
		}); */
	}
}
