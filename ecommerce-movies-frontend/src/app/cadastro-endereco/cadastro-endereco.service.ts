import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CadastroEnderecoService {
  
  constructor(private http: HttpClient) { }

  create(endereco, id_cliente){
    const body = new HttpParams()
      .set("operacao", "salvar")
      .set("idCliente", id_cliente)
		  .set("idCidade", endereco.idCidade)
		  .set("logradouro", endereco.logradouro)
		  .set("numero", endereco.numero)
		  .set("bairro", endereco.bairro)
		  .set("cep", endereco.cep)
		  .set("observacao", endereco.observacao)

      console.log(id_cliente)
      const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

      return this.http.post("http://localhost:8084/cliente/salvar/endereco/", body.toString(), {
        headers, observe: 'response'
      });
  }
}
