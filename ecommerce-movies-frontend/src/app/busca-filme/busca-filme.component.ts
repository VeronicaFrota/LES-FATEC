import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BuscaFilmeService } from './busca-filme.service';
import { Filme } from 'src/modelos';

@Component({
	selector: 'app-busca-filme',
	templateUrl: './busca-filme.component.html',
	styleUrls: ['./busca-filme.component.css']
})
export class BuscaFilmeComponent implements OnInit {

	form: FormGroup
	filmes: Filme[]

	constructor(
		private fb: FormBuilder,
		private service: BuscaFilmeService
	) { }

	ngOnInit() {
		this.form = this.fb.group({
			nome: ['', ''],
			codigo_barras: ['', ''],
			genero: ['', ''],
			ano: ['', ''],
			paisOrigem: ['', ''],
			idioma: ['', ''],
			classificacaoEtaria: ['', '']
		}) 
	}

	onSubmit() {
		this.service.buscar(this.form.value).subscribe((res: Filme[]) => {
			this.filmes = res;
		})


		/* if(this.form.valid) {
			this.service.create(this.form.value)
			.subscribe(response => {
				alert('Cadastro executada com sucesso');
				window.location.href = "/listar-filme";
			}
		} */
	}

}
