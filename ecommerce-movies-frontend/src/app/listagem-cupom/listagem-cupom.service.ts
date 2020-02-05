import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { ListagemCupom } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemCupomService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/cupom/?operacao=consultar'

	list() {
		return this.http.get<ListagemCupom[]>(this.API);
	}
}
