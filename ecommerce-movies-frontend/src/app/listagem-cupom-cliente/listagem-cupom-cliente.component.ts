import { Component, OnInit } from '@angular/core';
import { ListagemCupom } from 'src/modelos';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemCupomService } from '../listagem-cupom/listagem-cupom.service';
import { ListagemCupomClienteService } from './listagem-cupom-cliente.service';

@Component({
	selector: 'app-listagem-cupom-cliente',
	templateUrl: './listagem-cupom-cliente.component.html',
	styleUrls: ['./listagem-cupom-cliente.component.css']
})
export class ListagemCupomClienteComponent implements OnInit {

	cupons: ListagemCupom[]
	id_cliente: any
	submitted: false
	form: FormGroup

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private service: ListagemCupomClienteService,
		private fb: FormBuilder) {
		this.route.params.subscribe(res => this.id_cliente = res.id);
	}

	ngOnInit() {
		this.service.cupomPorCliente(this.id_cliente).subscribe((res: any[]) => {
			this.cupons = res;
		})
	}

}
