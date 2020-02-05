import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemFilme, ListagemEstoque } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class EcommerceIndexService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/filmes/'
	private readonly APIEstoque = 'http://localhost:8084/filme/estoque/'

	list() {
		return this.http.get<ListagemFilme[]>(this.API);
	}

	listEstoque() {
		return this.http.get<ListagemEstoque[]>(this.APIEstoque);
	}
}
