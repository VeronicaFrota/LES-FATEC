import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemCliente } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemClienteService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/cliente/'

	list() {
		return this.http.get<ListagemCliente[]>(this.API);
	}
}
