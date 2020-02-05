import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CadastroFilmeService } from './cadastro-filme.service';

@Component({
	selector: 'app-cadastro-filme',
	templateUrl: './cadastro-filme.component.html',
	styleUrls: ['./cadastro-filme.component.css']
})
export class CadastroFilmeComponent implements OnInit {

	form: FormGroup;
	submitted: Boolean; 					// Verifica se os dados do form estão vazios
	serverError: String;					// Erro do servidor				

	constructor(
		private fb: FormBuilder,
		private service: CadastroFilmeService) { }

	ngOnInit() {

		this.form = this.fb.group({
			nome: [null, [Validators.required]],
			codigo_barras: [null, [Validators.required, Validators.maxLength(9)]],
			genero: ['', [Validators.required]],
			ano: [2019, [Validators.required, Validators.minLength(3), Validators.maxLength(9)]],
			paisOrigem: ['', [Validators.required]],
			idioma: ['', [Validators.required]],
			classificacaoEtaria: ['', [Validators.required]],
			sinopse: [null, [Validators.required]],
			fornecedor: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
			quantidadeEstoque: [null, [Validators.required, Validators.maxLength(5)]],
			valorCompra: [null, [Validators.required, Validators.maxLength(5)]],
			valorVenda: [null, [Validators.required, Validators.maxLength(5)]],
		})

	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;

		if(this.form.valid) {
			this.service.create(this.form.value)
			.subscribe(response => {
				alert('Cadastro executada com sucesso');
				window.location.href = "/listar-filme";
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
		window.location.href = "/listar-filme";
	}
}
