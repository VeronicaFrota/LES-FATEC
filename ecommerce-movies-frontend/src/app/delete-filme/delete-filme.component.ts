import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeleteFilmeService } from './delete-filme.service';

@Component({
	selector: 'app-delete-filme',
	templateUrl: './delete-filme.component.html',
	styleUrls: ['./delete-filme.component.css']
})
export class DeleteFilmeComponent implements OnInit {

	id_filme: any

	constructor(
		private route: ActivatedRoute,
		private service: DeleteFilmeService) {

			this.route.params.subscribe(res => this.id_filme = res.id);

		}

	ngOnInit() {
	}

	onClickMe() {
		this.service.deletarFilme(this.id_filme);
	}

}
