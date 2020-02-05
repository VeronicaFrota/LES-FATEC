import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Filme, Pedido, Frete, Cupom, ListagemCupom } from 'src/modelos';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class EcommerceCarrinhoService {

	constructor(private http: HttpClient) { }

	private readonly API = 'http://localhost:3000/frete'
    
    // Serviço Http que será consumido
    frete(frete: any): Observable<Frete[]> {
        return this.http.get<Frete[]>(this.API)   
    }

	pegarUmFilme(id_filme: AnalyserNode) {
		return this.http.get<Filme[]>("http://localhost:8084/filmes/?operacao=consultar&id_filme=" + id_filme);
	}

	// Adiciona um filme ao carrinho
	adicionaFilme(filme: Filme) {
		if(!window.sessionStorage.carrinho){
			window.sessionStorage.carrinho = "[]"
		}

		let carrinhoAtual = JSON.parse(window.sessionStorage.carrinho)	// converte string para Json
		carrinhoAtual.push(filme)										// adiciona um filme
		this.atualizaCarrinho(carrinhoAtual)							// Atualiza o novo carrinho para o carrinho atual
	}

	listarCarrinho() {
		return JSON.parse(window.sessionStorage.carrinho)				// converte string para Json
	}

	atualizaCarrinho(novoCarrinho) {
		window.sessionStorage.setItem("carrinho", JSON.stringify(novoCarrinho))	// Converte Json para string
	}

	salvar(carrinho: Pedido) {
		return this.http.post("http://localhost:8084/pedido/salvar/?operacao=salvar", carrinho);
	}

	listaCupons(idCliente: any){
		console.log(idCliente)
		return this.http.get<ListagemCupom[]>("http://localhost:8084/cupom/?operacao=consultar&idCliente=" + idCliente + "&status=1");
	}

	alteraStatusCupom(idCupom: any){
		return this.http.get("http://localhost:8084/cupom/alterar/?operacao=alterar&id=" + idCupom);
	}

}
