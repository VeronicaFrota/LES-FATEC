import { Component, OnInit } from '@angular/core';
import { Filme, Estoque } from 'src/modelos';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EcommerceIndexService } from './ecommerce-index.service';

@Component({
	selector: 'app-ecommerce-index',
	templateUrl: './ecommerce-index.component.html',
	styleUrls: ['./ecommerce-index.component.css']
})
export class EcommerceIndexComponent implements OnInit {

	filmes: Filme[]
	estoques: Estoque[]
	id_filme: any
	form: FormBuilder

	constructor(
		private route: ActivatedRoute,
		private service: EcommerceIndexService,
		private fb: FormBuilder
	) { 

		this.route.params.subscribe(res => this.id_filme = res.id);

	}

	ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.filmes = res
		})

		this.service.listEstoque().subscribe((res: any[]) => {
			this.estoques = res
		})
	}

}
