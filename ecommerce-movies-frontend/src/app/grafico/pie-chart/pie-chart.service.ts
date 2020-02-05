import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GeneroBar, GeneroBarData } from 'src/modelos';

@Injectable({
  providedIn: 'root'
})
export class PieChartService {


	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/bar/categoria/'

	list(ano) {
    console.log(ano)
		return this.http.get<GeneroBar[]>(this.API + "?operacao=consultar&comeco=1&fim=12&ano=" + ano);
	}

	listData(ano){
		return this.http.get<GeneroBarData[]>(this.API + "?operacao=consultar&comeco=1&fim=12&ano=" + ano);
	}

	listPesquisa(comeco, fim, ano){
		return this.http.get<GeneroBar[]>(this.API + "?operacao=consultar&comeco="+ comeco +"&fim=" + fim + "&ano=" + ano);
	}

	listaPesquisaData(comeco, fim, ano){
		return this.http.get<GeneroBarData[]>(this.API + "?operacao=consultar&comeco="+ comeco +"&fim=" + fim + "&ano=" + ano);
	}

}
