import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlteraFilmeService } from './altera-filme.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Estoque } from 'src/modelos';
import { DeleteEstoqueService } from '../delete-estoque/delete-estoque.service';

@Component({
	selector: 'app-altera-filme',
	templateUrl: './altera-filme.component.html',
	styleUrls: ['./altera-filme.component.css']
})
export class AlteraFilmeComponent implements OnInit {

	id_filme: any;
	form: FormGroup
	submitted = false						// verifica se os dados do form estão vazios
	serverError: String;					// Erro do servidor
	estoques: Estoque[]

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private service: AlteraFilmeService,
		private deleteEstoqueService: DeleteEstoqueService,
		private fb: FormBuilder) {

		this.route.params.subscribe(res => this.id_filme = res.id);
	}

	ngOnInit() {
		this.form = this.fb.group({
			nome: [null, [Validators.required]],
			codigo_barras: [null, [Validators.required, Validators.maxLength(9)]],
			genero: ['', [Validators.required]],
			ano: [2019, [Validators.required, Validators.minLength(3), Validators.maxLength(9)]],
			paisOrigem: ['', [Validators.required]],
			idioma: ['', [Validators.required]],
			classificacaoEtaria: ['', [Validators.required]],
			sinopse: [null, [Validators.required]]
		})

		this.service.pegarUmFilme(this.id_filme)
			.subscribe(r => {
				this.form = this.fb.group({
					nome: [r.nome, [Validators.required]],
					codigo_barras: [r.codigo_barras, [Validators.required, Validators.maxLength(9)]],
					genero: [r.genero.id, [Validators.required]],
					ano: [r.ano, [Validators.required, Validators.minLength(3), Validators.maxLength(9)]],
					paisOrigem: [r.pais_origem.id, [Validators.required]],
					idioma: [r.idioma.id, [Validators.required]],
					classificacaoEtaria: [r.classificacao_etaria.id, [Validators.required]],
					sinopse: [r.sinopse, [Validators.required]]
				})
			})

		// Para listar estoque
		this.service.list(this.id_filme).subscribe((res: any[]) => {
			this.estoques = res
		})

	}

	
	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;
		if(this.form.valid) {
			this.service.salvarAlteracao(this.id_filme, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
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

	deletarEstoque(estoque) {
		var approve = confirm("Deseja realmente deletar o estoque com id: " + estoque.id)

		if (approve) {
			this.deleteEstoqueService.deletarEstoque(estoque.id)
			.subscribe(response => {
				alert('Estoque com id ' + estoque.id + ' deletado com sucesso');
				window.location.href = "/listar-filme";
			})
		}
	}

	/* cadastrarFornecedor(){
		this.router.navigate(['cadastro-endereco/', this.id_filme])
	}
 */
}
