import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CadastroCartaoService {
  
  constructor(private http: HttpClient) { }

  create(cartao, id_cliente){
    const body = new HttpParams()
      .set("operacao", "salvar")
      .set("idCliente", id_cliente)
      .set("idBandeira", cartao.idBandeira)  
      .set("numeroCartao", cartao.numeroCartao)
      .set("cvv", cartao.cvv)
      .set("dataValidade", cartao.dataValidade)

      const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

      return this.http.post("http://localhost:8084/cliente/salvar/cartao/", body.toString(), {
        headers, observe: 'response'
      });
  }
}
