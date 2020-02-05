import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from 'src/modelos';

@Injectable({
  providedIn: 'root'
})
export class AlteraSenhaService {

	constructor(private http: HttpClient) { }

	//private readonly API = 'https://swapi.co/'
	private readonly API = 'http://localhost:8084/alterar/senha/'

	verificaSenha(id, senha) {
    console.log(this.API + "?operacao=consultar&id_cliente="+ id +"&senha=" + senha)
		return this.http.get<Cliente>(this.API + "?operacao=consultar&id_cliente="+ id +"&senha=" + senha);
  }
  
  alteraSenha(id, senha){
    console.log(this.API + "?operacao=alterar&id_cliente="+ id +"&senha=" + senha)
    return this.http.get<Cliente>(this.API + "?operacao=alterar&id_cliente="+ id +"&senha=" + senha);
  }
}
