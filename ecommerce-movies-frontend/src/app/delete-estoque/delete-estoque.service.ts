import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DeleteEstoqueService {

  constructor(private http: HttpClient) { }

  deletarEstoque(id_estoque: any) {
		return this.http.delete("http://localhost:8084/filme/deletar/estoque/?operacao=excluir&id_estoque=" + id_estoque);
	}

}
