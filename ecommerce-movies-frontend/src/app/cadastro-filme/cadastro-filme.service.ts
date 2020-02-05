import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CadastroFilmeService {

	constructor(private http: HttpClient) { }

	create(filme) {
		const body = new HttpParams()
			.set("operacao", "salvar")
			.set("ano", filme.ano)
			.set("classificacaoEtaria", filme.classificacaoEtaria)
			.set("codigoBarras", filme.codigo_barras)
			.set("genero", filme.genero)
			.set("idioma", filme.idioma)
			.set("nome", filme.nome)
			.set("paisOrigem", filme.paisOrigem)
			.set("sinopse", filme.sinopse)
			.set("fornecedor", filme.fornecedor)
			.set("quantidadeEstoque", filme.quantidadeEstoque)
			.set("valorCompra", filme.valorCompra)
			.set("valorVenda", filme.valorVenda);

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/salvar/filme/", body.toString(), {
			headers, observe: 'response'
		});
	}

}
