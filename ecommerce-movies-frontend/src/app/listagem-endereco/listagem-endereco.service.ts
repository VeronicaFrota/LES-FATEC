import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemEndereco } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemEnderecoService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/cliente/endereco/'

	list() {
		return this.http.get<ListagemEndereco[]>(this.API);
	}
}
