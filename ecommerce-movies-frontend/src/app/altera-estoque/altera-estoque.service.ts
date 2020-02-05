import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Estoque } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class AlteraEstoqueService {

	constructor(private http: HttpClient) { }

	pegarUmEstoque(id_estoque: any) {
		return this.http.get<Estoque>("http://localhost:8084/filme/alterar/estoque/?operacao=consultar&id_estoque=" + id_estoque);
	}

	salvarAlteracao(id_estoque: any, estoque) {

		const body = new HttpParams()
			.set("operacao", "alterar")
			.set("id_estoque", id_estoque)
			.set("fornecedor", estoque.fornecedor)
			.set("quantidadeEstoque", estoque.quantidadeEstoque)
			.set("valorCompra", estoque.valorCompra)
			.set("valorVenda", estoque.valorVenda);

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.put("http://localhost:8084/filme/alterar/estoque/", body.toString(), {
			headers, observe: 'response'
		});
	}
}
