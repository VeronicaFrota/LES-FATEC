import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemCartao } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemCartaoService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
  private readonly API = 'http://localhost:8084/cliente/cartao/'
  
  list() {
		return this.http.get<ListagemCartao[]>(this.API);
	}
}
