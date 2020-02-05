import { Component, OnInit } from '@angular/core';
import { Endereco, Cartao } from 'src/modelos';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlteraClienteService } from '../altera-cliente/altera-cliente.service';

@Component({
	selector: 'app-ecommerce-pagamento',
	templateUrl: './ecommerce-pagamento.component.html',
	styleUrls: ['./ecommerce-pagamento.component.css']
})
export class EcommercePagamentoComponent implements OnInit {

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
		private fb: FormBuilder) {

		this.id_cliente = this.route.snapshot.params['id'];
		this.route.params.subscribe(res => this.id_cliente = res.id);

	}

	ngOnInit() {
		//Verifica se os inputs foram preenchidos
		console.log(this.route)

		this.service.list(this.id_cliente).subscribe((res: any[]) => {
			this.enderecos = res;
		})

		this.service.listCartao(this.id_cliente).subscribe((res: any[]) => {
			this.cartaos = res;
		})

	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;
		if (this.form.valid) {
			this.service.salvarAlteracao(this.id_cliente, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
					window.location.href = "/listar-cliente";
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

	cadastrarEndereco() {
		this.router.navigate(['cadastro-endereco/', this.id_cliente])
	}

	cadastrarCartao() {
		this.router.navigate(['cadastro-cartao/', this.id_cliente])
	}

}
