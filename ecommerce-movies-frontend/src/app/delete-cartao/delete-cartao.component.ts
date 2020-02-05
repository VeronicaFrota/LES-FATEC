import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeleteCartaoService } from './delete-cartao.service';

@Component({
	selector: 'app-delete-cartao',
	templateUrl: './delete-cartao.component.html',
	styleUrls: ['./delete-cartao.component.css']
})
export class DeleteCartaoComponent implements OnInit {

	id_cartao: any

	constructor(
		private route: ActivatedRoute,
		private service: DeleteCartaoService) {

			this.route.params.subscribe(res => this.id_cartao = res.id);

		}

	ngOnInit() {
	}

	onClickMe() {
		this.service.deletarCartao(this.id_cartao);
	}

}
