import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListagemFilme } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class ListagemFilmeInativoService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/filmes/?operacao=consultar&status=0'

	list() {
		return this.http.get<ListagemFilme[]>(this.API);
	}

}
