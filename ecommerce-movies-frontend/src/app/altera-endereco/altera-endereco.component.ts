import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlteraEnderecoService } from './altera-endereco.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
	selector: 'app-altera-endereco',
	templateUrl: './altera-endereco.component.html',
	styleUrls: ['./altera-endereco.component.css']
})
export class AlteraEnderecoComponent implements OnInit {

	id_endereco: any;
	form: FormGroup
	submitted = false						// verifica se os dados do form estão vazios
	serverError: String
	id_cliente: number

	constructor(
		private route: ActivatedRoute,
		private service: AlteraEnderecoService,

		private fb: FormBuilder) {

		this.route.params.subscribe(res => this.id_endereco = res.id);
	}

	ngOnInit() {
		//Verifica se os inputs foram preenchidos

		this.form = this.fb.group({
			idCidade: ['', [Validators.required]],
			logradouro: [null, [Validators.required, Validators.maxLength(50)]],
			numero: [null, [Validators.required, Validators.maxLength(10)]],
			bairro: [null, [Validators.required, Validators.maxLength(20)]],
			//Validação CEP
			cep: [null, [Validators.required]],
			observacao: [null, [Validators.required, Validators.maxLength(80)]]
		})

		this.service.pegarUmEndereco(this.id_endereco)
			.subscribe(r => {
				this.form = this.fb.group({
					idCidade: [r.idCidade, [Validators.required]],
					logradouro: [r.logradouro, [Validators.required]],
					numero: [r.numero, [Validators.required]],
					bairro: [r.bairro, [Validators.required]],
					cep: [r.cep, [Validators.required]],
					observacao: [r.observacao, [Validators.required]]
				})
			})
	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;
		if (this.form.valid) {
			this.service.salvarAlteracao(this.id_endereco, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
					history.go(-1);
				},
					error => {
						this.serverError = error.error.error;
						console.log(this.serverError);
					}
				);
		}
	}

	onCancel() {
		this.submitted = false;
		this.form.reset();
		history.go(-1);
	}

}
