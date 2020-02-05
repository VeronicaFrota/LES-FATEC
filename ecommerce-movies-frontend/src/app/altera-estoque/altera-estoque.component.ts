import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlteraEstoqueService } from './altera-estoque.service';

@Component({
	selector: 'app-altera-estoque',
	templateUrl: './altera-estoque.component.html',
	styleUrls: ['./altera-estoque.component.css']
})
export class AlteraEstoqueComponent implements OnInit {

	id_estoque: any
	form: FormGroup
	submitted = false						// verifica se os dados do form estão vazios
	serverError: String

	constructor(
		private route: ActivatedRoute,
		private fb: FormBuilder,
		private service: AlteraEstoqueService
	) {

		this.route.params.subscribe(res => this.id_estoque = res.id)

	}

	ngOnInit() {
		this.form = this.fb.group({
			fornecedor: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
			quantidadeEstoque: [null, [Validators.required, Validators.maxLength(5)]],
			valorCompra: [null, [Validators.required, Validators.maxLength(5)]],
			valorVenda: [null, [Validators.required, Validators.maxLength(5)]],
		})

		this.service.pegarUmEstoque(this.id_estoque)
			.subscribe(r => {
				this.form = this.fb.group({
					fornecedor: [r.fornecedor, [Validators.required]],
					quantidadeEstoque: [r.quantidade_estoque, [Validators.required]],
					valorCompra: [r.valor_compra, [Validators.required]],
					valorVenda: [r.valor_venda, [Validators.required]]
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
			this.service.salvarAlteracao(this.id_estoque, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
					window.location.href = "/listar-filme";
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
		window.location.href = "/listar-filme";
	}

}
