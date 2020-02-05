import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Filme } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class EcommerceExibeFilmeService {

	constructor(private http: HttpClient) { }

	pegarUmFilme(id_filme: any) {
		return this.http.get<Filme>("http://localhost:8084/filmes/?operacao=consultar&id_filme=" + id_filme);
	}

}
