import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Filme } from 'src/modelos';
import { ListagemFilmeInativoService } from './listagem-filme-inativo.service';
import { AtivarFilmeService } from '../ativar-filme/ativar-filme.service';

@Component({
	selector: 'app-listagem-filme-inativo',
	templateUrl: './listagem-filme-inativo.component.html',
	styleUrls: ['./listagem-filme-inativo.component.css']
})

export class ListagemFilmeInativoComponent implements OnInit {

	filmes: Filme[]
	form: FormBuilder
	submitted: false
	id_filme: any

	constructor(
		private route: ActivatedRoute,
		private ativarFilmeService: AtivarFilmeService,
		private service: ListagemFilmeInativoService,
		private fb: FormBuilder) { 

		this.route.params.subscribe(res => this.id_filme = res.id);
	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.filmes = res;
		})
	}

	activateMovie(filme) {

		console.log(filme)

		var approve = confirm("Deseja realmente ativar este filme " + filme.nome)

		if(approve) {
			this.ativarFilmeService.ativaFilme(filme.id)
				.subscribe(response => {
					alert(filme.nome + ' ativado com sucesso');
					window.location.href = "/listar-filme";
				})
		}

	}

}
