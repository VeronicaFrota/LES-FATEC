import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Endereco } from 'src/modelos';


@Injectable({
	providedIn: 'root'
})
export class AlteraEnderecoService {

	constructor(private http: HttpClient) { }

	pegarUmEndereco(id_endereco: any) {
		return this.http.get<Endereco>("http://localhost:8084/cliente/endereco/?operacao=consultar&id_endereco=" + id_endereco);
	}

	salvarAlteracao(id_endereco: any, endereco) {
		const body = new HttpParams()
			.set("operacao", "alterar")
			.set("id_endereco", id_endereco)
			.set("id_cidade", endereco.idCidade)
			.set("logradouro", endereco.logradouro)
			.set("numero", endereco.numero)
			.set("bairro", endereco.bairro)
			.set("cep", endereco.cep)
			.set("observacao", endereco.observacao)

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/cliente/alterar/endereco/", body.toString(), {
			headers, observe: 'response'
		});
	}
}
