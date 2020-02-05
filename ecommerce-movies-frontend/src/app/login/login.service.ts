import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Cliente, Endereco,Cartao, ListagemCartao } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) { }

	create(cliente) {
        const body = new HttpParams()
            .set("operacao", "salvar")
            .set("email", cliente.email)
            .set("senha", cliente.senha)

        const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

        return this.http.post<Cliente>("http://localhost:8084/login/?operacao=login", body.toString(), {
            headers, observe: 'response'
        });
    }

	sessionCliente(cliente: Cliente) {
		if(!window.sessionStorage.cliente){
            window.sessionStorage.cliente = "{}"
        }

        this.atualizaCliente(cliente)
    }
    
    getCliente(): Cliente {
        const cliente = JSON.parse(window.sessionStorage.cliente)				// converte string para Json

        if (cliente.endereco && Array.isArray(cliente.endereco) && cliente.endereco.length) {
            cliente.endereco = cliente.endereco.map((e: Endereco, i: number) => {
                e.selected = i == 0;
                return e;
            })
        }

        if (cliente.cartoes && Array.isArray(cliente.cartoes) && cliente.cartoes.length) {
            cliente.cartoes = cliente.cartoes.map((e: ListagemCartao, i: number) => {
                e.selected = i == 0;
                if (i == 0) {
                    e.quantidadeParcela = 1;
                }
                return e;
            })
        }

        return cliente;
			
    }

    atualizaCliente(novoCliente) {
		window.sessionStorage.setItem("cliente", JSON.stringify(novoCliente))	// Converte Json para string
    }

    sessionLogout() {
        sessionStorage.clear()
        window.location.href = "/";
    }

}

