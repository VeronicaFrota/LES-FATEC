import { Component, OnInit, EventEmitter } from '@angular/core';
import { LoginService } from './login.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Cliente } from 'src/modelos';
import { Router } from '@angular/router';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	form: FormGroup
	submitted: Boolean; 					// verifica se os dados do form estão vazios
	serverError: String;          			//Pega erro do servidor
	cliente: Cliente

	mostrarMenuEmitter = new EventEmitter<boolean>();

	constructor(
		private fb: FormBuilder,
		private loginService: LoginService,
		private router: Router) { }

	ngOnInit() {
		this.form = this.fb.group({
			email: [null, [Validators.required]],
			senha: [null, [Validators.required]]
		})
	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;

		if (this.form.valid) {
			this.loginService.create(this.form.value)
				.subscribe(response => {
					alert('Seja Bem Vindo ao MovieS');
					this.loginService.sessionCliente(response.body)
					window.location.href = "/ecommerce-index";
				},
					error => {
						alert("Email ou Senha Incorretos");
					}

				);
		}
	}

	navigateTo(){
		this.router.navigate(["/cadastro-cliente-tela-inicial"]);
	} 

}
