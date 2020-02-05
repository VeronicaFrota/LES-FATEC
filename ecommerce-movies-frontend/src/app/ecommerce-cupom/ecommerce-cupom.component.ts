import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EcommerceCupomService } from './ecommerce-cupom.service';
import { Cupom } from 'src/modelos';

@Component({
	selector: 'app-ecommerce-cupom',
	templateUrl: './ecommerce-cupom.component.html',
	styleUrls: ['./ecommerce-cupom.component.css']
})
export class EcommerceCupomComponent implements OnInit {

	cupom: Cupom
	form: FormGroup

	constructor(
		private fb: FormBuilder,
		private service: EcommerceCupomService
	) { }

	ngOnInit() {
		this.form = this.fb.group({
			codigo: ['', '']
		})
	}

	onSubmit() {
		/* if(this.form.valid) {
			this.service.salvar(this.cupom)
				.subscribe(response => {
					alert('cadastro ok');
					window.location.href = "/listar-filme";
				},
					error => {
						this.serverError = error.error.error;
					}
				);
		} */
	}

	aplicarCupom() {
		console.log("***** Cupom *****")
		console.log(this.form.value)
		return this.form.value
	}

}
