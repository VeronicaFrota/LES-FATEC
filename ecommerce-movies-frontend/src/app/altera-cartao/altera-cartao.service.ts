import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cartao } from 'src/modelos';


@Injectable({
	providedIn: 'root'
})
export class AlteraCartaoService {

	constructor(private http: HttpClient) { }

	pegarUmCartao(id_cartao: any) {
		return this.http.get<Cartao>("http://localhost:8084/cliente/cartao/?operacao=consultar&id_cartao=" + id_cartao);
	}

	salvarAlteracao(id_cartao: any, cartao){
		const body = new HttpParams()
      .set("operacao", "alterar")
      .set("id_bandeira", cartao.idBandeira)  
      .set("numero_cartao", cartao.numeroCartao)
      .set("cvv", cartao.cvv)
      .set("data_validade", cartao.dataValidade)
		  .set("id_cartao", id_cartao)
	
		  const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
	
		  return this.http.post("http://localhost:8084/cliente/alterar/cartao/", body.toString(), {
			headers, observe: 'response'
		  });
	}
}
