import { Component, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from './login/login.service';
import { Cliente } from 'src/modelos';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})

export class AppComponent {

	title = 'ecommerce-movies-admin';
	cliente: Cliente

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private loginService: LoginService
	) { }

	logout() {
		this.loginService.sessionLogout()
	}

	login() {
		if (window.sessionStorage.cliente) {
			return this.isAdmin()
		}
		return false
	}

	loginCliente() {
		if (window.sessionStorage.cliente) {
			return !this.isAdmin()
		}
		return false
	}

	isAdmin() {
		if (window.sessionStorage.cliente) {
			var admins: String[] = ["rafaelnunesvazquez@gmail.com"]
			return admins.includes(this.loginService.getCliente().email)
		}

		return false
	}

	// Acessar informações do perfil do cliente
	linkAlterar() {
		window.location.href = "/altera-cliente/" + this.loginService.getCliente().id
	}

	linkPedidoPorCliente() {
		window.location.href = "/listagem-pedido-cliente/" + this.loginService.getCliente().id
	}

	linkCupomPorCliente() {
		window.location.href = "/listagem-cupom-cliente/" + this.loginService.getCliente().id
	}

	linkPedidoTrocaPorCliente() {
		window.location.href = "/listagem-pedido-troca-cliente/" + this.loginService.getCliente().id
	}

	navigateTo(value) {
		if (value == "cadastro") {
			this.router.navigate(["/cadastro-filme"]);
		}
		else if (value == "listaAtivo") {
			this.router.navigate(["/listar-filme"]);
		}
		else if (value == "listaInativo") {
			this.router.navigate(["/listar-filme-inativo"]);
		}
		else if (value == "cadastroCliente") {
			this.router.navigate(["/cadastro-cliente"]);
		}
		else if (value == "listaCliente") {
			this.router.navigate(["/listar-cliente"]);
		}
		else if (value == "listaEstoque") {
			this.router.navigate(["/listar-estoque"]);
		}
		else if (value == "listarTrocas") {
			this.router.navigate(["/listar-pedido-troca"]);
		}
		else if (value == "listarCupons") {
			this.router.navigate(["/listar-cupom"]);
		}
		else if (value == "bar") {
			this.router.navigate(["/bar-chart"]);
		}
		else if (value == "doughnut") {
			this.router.navigate(["/doughnut-chart"])
		}
		else if (value == "line") {
			this.router.navigate(["/line-chart"])
		}
		else if (value == "pie") {
			this.router.navigate(["/pie-chart"])
		}
	}
}
