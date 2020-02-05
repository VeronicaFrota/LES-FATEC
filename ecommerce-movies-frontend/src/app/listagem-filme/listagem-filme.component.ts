import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListagemFilmeService } from './listagem-filme.service';
import { DeleteFilmeService } from '../delete-filme/delete-filme.service';
import { Filme } from 'src/modelos';
import { FormBuilder } from '@angular/forms';

@Component({
	selector: 'app-listagem-filme',
	templateUrl: './listagem-filme.component.html',
	styleUrls: ['./listagem-filme.component.css']
})

export class ListagemFilmeComponent implements OnInit {

	filmes: Filme[]
	id_filme: any
	submitted: false
	form: FormBuilder

	constructor(
		private route: ActivatedRoute,
		private service: ListagemFilmeService,
		private deleteFilmeService: DeleteFilmeService,
		private fb: FormBuilder) {

		this.route.params.subscribe(res => this.id_filme = res.id);
	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.filmes = res;
		})
	}

	deleteMovie(filme) {

		console.log(filme)

		var approve = confirm("Deseja realmente deletar o filme " + filme.nome)

		if (approve) {
			this.deleteFilmeService.deletarFilme(filme.id)
				.subscribe(response => {
					alert(filme.nome + ' deletado com sucesso');
					window.location.href = "/listar-filme";
				})
		}

	}

}
