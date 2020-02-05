import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filme, ListagemEstoque } from 'src/modelos';


@Injectable({
	providedIn: 'root'
})
export class AlteraFilmeService {

	constructor(private http: HttpClient) { }

	pegarUmFilme(id_filme: any) {
		return this.http.get<Filme>("http://localhost:8084/filmes/?operacao=consultar&id_filme=" + id_filme);
	}

	list(id_filme: any) {
		return this.http.get<ListagemEstoque[]>("http://localhost:8084/filme/estoque/?operacao=consultar&id_filme=" + id_filme);
	}

	salvarAlteracao(id_filme: any, filme) {

		const body = new HttpParams()
		.set("operacao", "alterar")
		.set("id_filme", id_filme)
		.set("ano", filme.ano)
		.set("classificacaoEtaria", filme.classificacaoEtaria)
		.set("codigoBarras", filme.codigo_barras)
		.set("genero", filme.genero)
		.set("idioma", filme.idioma)
		.set("nome", filme.nome)
		.set("paisOrigem", filme.paisOrigem)
		.set("sinopse", filme.sinopse);

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.put("http://localhost:8084/alterar/filme/", body.toString(), {
			headers, observe: 'response'
		});
	}

	deletarEstoque(id_estoque: any) {
		return this.http.delete("http://localhost:8084/filme/deletar/estoque/?operacao=excluir&id_estoque=" + id_estoque);
	}
}
