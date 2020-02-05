import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Cliente } from 'src/modelos';

@Injectable({
    providedIn: 'root'
})
export class CadastroClienteService {

    constructor(private http: HttpClient) { }

    private readonly API = 'http://localhost:8084/cliente/email/'

    create(cliente) {
        const body = new HttpParams()
            .set("operacao", "salvar")
            .set("nome", cliente.nome)
            .set("cpf", cliente.cpf)
            .set("dataNascimento", cliente.dataNascimento)
            .set("email", cliente.email)
            .set("senha", cliente.senha)
            .set("genero", cliente.genero)
            .set("telefone", cliente.telefone)
            .set("idCidade", cliente.idCidade)
            .set("logradouro", cliente.logradouro)
            .set("numero", cliente.numero)
            .set("bairro", cliente.bairro)
            .set("cep", cliente.cep)
            .set("observacao", cliente.observacao)

        const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

        return this.http.post("http://localhost:8084/salvar/cliente/", body.toString(), {
            headers, observe: 'response'
        });
    }

    verificaEmail(cliente){
        return this.http.get<Cliente>(this.API + "?operacao=consultar&email=" + cliente.email);
    }
}
