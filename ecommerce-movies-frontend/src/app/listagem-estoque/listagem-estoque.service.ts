import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';
import { ListagemEstoque } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemEstoqueService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/filme/estoque/'

	list() {
		return this.http.get<ListagemEstoque[]>(this.API);
	}

}
