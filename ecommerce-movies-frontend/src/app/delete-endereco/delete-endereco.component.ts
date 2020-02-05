import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeleteEnderecoService } from './delete-endereco.service';

@Component({
	selector: 'app-delete-endereco',
	templateUrl: './delete-endereco.component.html',
	styleUrls: ['./delete-endereco.component.css']
})
export class DeleteEnderecoComponent implements OnInit {

	id_endereco: any

	constructor(
		private route: ActivatedRoute,
		private service: DeleteEnderecoService) {

			this.route.params.subscribe(res => this.id_endereco = res.id);

		}

	ngOnInit() {
	}

	onClickMe() {
		this.service.deletarEndereco(this.id_endereco);
	}

}
