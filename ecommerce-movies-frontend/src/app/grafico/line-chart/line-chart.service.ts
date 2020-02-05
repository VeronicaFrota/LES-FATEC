import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GeneroLine, GeneroLineData } from 'src/modelos';

@Injectable({
	providedIn: 'root'
})
export class LineChartService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/line/categoria/'

	list(ano) {
		return this.http.get<GeneroLine[]>(this.API + "?operacao=consultar&comeco=" + ano + "-01-01&fim=" + ano + "-12-30");
	}

	listData(ano){
		return this.http.get<GeneroLineData[]>(this.API + "?operacao=consultar&comeco=" + ano + "-01-01&fim=" + ano + "-12-30");
	}

	listPesquisa(comeco, fim){
		return this.http.get<GeneroLine[]>(this.API + "?operacao=consultar&comeco="+ comeco +"&fim=" +fim);
	}

	listaPesquisaData(comeco, fim){
		return this.http.get<GeneroLineData[]>(this.API + "?operacao=consultar&comeco="+ comeco +"&fim=" +fim);
	}

}
