import { Injectable } from '@angular/core';

import { Pedido, Frete } from 'src/modelos';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class EcommerceFreteService {

    constructor(private http: HttpClient) { }
    
    private readonly API = 'http://localhost:3000/frete'
    
    // Serviço Http que será consumido
    frete(frete: any): Observable<Frete[]> {
        return this.http.get<Frete[]>(this.API)   
    }

    salvar(frete: any) {
		/* const body = new HttpParams()
			.set("operacao", "salvar")
			.set("valorFrete", frete.valorFrete);

		const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }); */

		/* return this.http.post("http://localhost:8084/pedido/salvar/frete/", body.toString(), {
			headers, observe: 'response'
        }); */
    }

}
