import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filme } from 'src/modelos';

@Injectable({
  providedIn: 'root'
})
export class BuscaFilmeService {
    
    constructor(private http: HttpClient) { }

    buscar(filme) {
        const body: any = {}
        body.operacao = "buscar";

        if (filme.nome) {
            body.nome = filme.nome;
        }
    
        if (filme.codigo_barras) {
            body.codigo_barras = filme.codigo_barras;
        }
    
        if (filme.genero) {
            body.genero = filme.genero;
        }
    
        if (filme.ano) {
            body.ano = filme.ano;
        }
    
        if (filme.paisOrigem) {
            body.paisOrigem = filme.paisOrigem;
        }
    
        if (filme.idioma) {
            body.idioma = filme.idioma;
        }
    
        if (filme.classificacaoEtaria) {
            body.classificacaoEtaria = filme.classificacaoEtaria;
        }

		return this.http.get<Filme[]>("http://localhost:8084/filmes/", {params: body});
	}
}
