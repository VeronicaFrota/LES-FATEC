import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Cliente, Endereco } from 'src/modelos';
import { ListagemEndereco } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class AlteraClienteService {

	constructor(private http: HttpClient) { }

	pegarUmCliente(id_cliente: any) {
		return this.http.get<Cliente>("http://localhost:8084/cliente/?operacao=consultar&id_cliente=" + id_cliente);
	}

	list(id_cliente: any) {
		return this.http.get<ListagemEndereco[]>("http://localhost:8084/cliente/endereco/?operacao=consultar&id_cliente=" + id_cliente);
	}

	listCartao(id_cliente: any) {
		return this.http.get<ListagemEndereco[]>("http://localhost:8084/cliente/cartao/?operacao=consultar&id_cliente=" + id_cliente);
	}

	salvarAlteracao(id_cliente: any, cliente) {
		const body = new HttpParams()
			.set("operacao", "alterar")
			.set("id_cliente", id_cliente)
			.set("nome", cliente.nome)
			.set("cpf", cliente.cpf)
			.set("dataNascimento", cliente.dataNascimento)
			.set("email", cliente.email)
			.set("senha", cliente.senha)
			.set("genero", cliente.genero)
			.set("telefone", cliente.telefone)

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

		return this.http.post("http://localhost:8084/alterar/cliente/", body.toString(), {
			headers, observe: 'response'
		});
	}

	deletarEndereco(id_endereco: any) {
		return this.http.delete("http://localhost:8084/cliente/deleta/endereco/?operacao=excluir&id_endereco=" + id_endereco);
	}
}
