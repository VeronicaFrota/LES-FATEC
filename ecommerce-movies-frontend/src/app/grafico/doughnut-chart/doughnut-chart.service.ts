import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Genero } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class DoughnutChartService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/doughnut/categoria/'

	list() {
		return this.http.get<Genero[]>(this.API);
	}

}
