import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeleteEstoqueService } from './delete-estoque.service';

@Component({
	selector: 'app-delete-estoque',
	templateUrl: './delete-estoque.component.html',
	styleUrls: ['./delete-estoque.component.css']
})
export class DeleteEstoqueComponent implements OnInit {

	id_estoque: any

	constructor(
		private route: ActivatedRoute,
		private service: DeleteEstoqueService
	) { 

		this.route.params.subscribe(res => this.id_estoque = res.id)

	}

	ngOnInit() {
	}

	onClickMe() {
		this.service.deletarEstoque(this.id_estoque)
	}

}
