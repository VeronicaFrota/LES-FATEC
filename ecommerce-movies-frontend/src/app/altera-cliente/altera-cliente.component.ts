import { Component, OnInit, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlteraClienteService } from './altera-cliente.service';
import { DeleteEnderecoService } from '../delete-endereco/delete-endereco.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Endereco, Cliente } from 'src/modelos';
import { Cartao } from 'src/modelos'
import { DeleteCartaoService } from '../delete-cartao/delete-cartao.service';
import { LoginService } from '../login/login.service';

@Component({
	selector: 'app-altera-cliente',
	templateUrl: './altera-cliente.component.html',
	styleUrls: ['./altera-cliente.component.css']
})

export class AlteraClienteComponent implements OnInit {

	id_cliente: any;
	enderecos: Endereco[]
	cartaos: Cartao[]
	form: FormGroup
	submitted = false						// verifica se os dados do form estão vazios
	serverError: String
	urlSite: String

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: AlteraClienteService,
		private loginService: LoginService,
		private deleteEnderecoService: DeleteEnderecoService,
		private deleteCartaoService: DeleteCartaoService,
		private fb: FormBuilder) {

		this.id_cliente = this.route.snapshot.params['id'];
		this.route.params.subscribe(res => this.id_cliente = res.id);

	}

	ngOnInit() {
		//Verifica se os inputs foram preenchidos
		this.form = this.fb.group({
		nome: [null, [Validators.required, Validators.maxLength(40)]],
		//Validação do CPF
		cpf: [null, [Validators.required, Validators.maxLength(14)]],
		//Validação da Data no formato informado
		dataNascimento: [null, [Validators.required]],
		//Validação email
		email: [null, [Validators.required, Validators.maxLength(40)]],
		genero: ['', [Validators.required]],
		telefone: [null, [Validators.required, Validators.maxLength(11)]],
	})
	
	this.service.list(this.id_cliente).subscribe((res: any[]) => {
		this.enderecos = res;})

	this.service.listCartao(this.id_cliente).subscribe((res: any[]) => {
		this.cartaos = res;})

		this.service.pegarUmCliente(this.id_cliente)
			.subscribe(r => {
				console.log(r)
				this.form = this.fb.group({
					nome: [r.nome, [Validators.required]],
					cpf: [r.cpf, [Validators.required, Validators.maxLength(14)]],
					dataNascimento: [r.data_nascimento, [Validators.required]],
					email: [r.email, [Validators.required]],
					genero: [r.genero, [Validators.required]],
					telefone: [r.telefone]
				})
			})

	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;

		if(this.form.valid) {
			this.service.salvarAlteracao(this.id_cliente, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
					//window.location.href = "/listar-cliente";
					if(this.route.snapshot.queryParams.from && this.route.snapshot.queryParams.from == "admin") {
						window.location.href = "/listar-cliente";
					} else {
						window.location.href = "/ecommerce-index";
					}
				},
					error => {
						this.serverError = error.error.error;
					}
				);
		}
	}

	onCancel() {
		this.submitted = false;
		this.form.reset();
		window.location.href = "/listar-cliente";
	}

	deletarEndereco(endereco) {
		var approve = confirm("Deseja realmente deletar o endereco com id: " + endereco.id)

		if (approve) {
			this.deleteEnderecoService.deletarEndereco(endereco.id)
			.subscribe(response => {
				alert('Enderedo com id ' + endereco.id + ' deletado com sucesso');
				window.location.href = "/listar-cliente";
			})
		}
	}

	deletarCartao(cartao) {
		var approve = confirm("Deseja realmente deletar o cartao com id: " + cartao.id)

		if (approve) {
			this.deleteCartaoService.deletarCartao(cartao.id)
			.subscribe(response => {
				alert('Enderedo com id ' + cartao.id + ' deletado com sucesso');
				window.location.href = "/listar-cliente";
			})
		}
	}

	cadastrarEndereco(){
		this.router.navigate(['cadastro-endereco/', this.id_cliente])
	}

	cadastrarCartao(){
		this.router.navigate(['cadastro-cartao/', this.id_cliente])
	}

	navigateTo(){
		this.router.navigate(["altera-senha/", this.id_cliente]);
	}

}
