import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EcommerceFreteService } from './ecommerce-frete.service';
import { Frete, Cupom } from 'src/modelos';

@Component({
	selector: 'app-ecommerce-frete',
	templateUrl: './ecommerce-frete.component.html',
	styleUrls: ['./ecommerce-frete.component.css']
})
export class EcommerceFreteComponent implements OnInit {

	@Input() frete: Frete
	@Output() add = new EventEmitter()		// para pegar a saida
	form: FormGroup

	constructor(
		private fb: FormBuilder,
		private service: EcommerceFreteService
	) { }

	ngOnInit() {
		this.form = this.fb.group({			
			frete: ['', '']
		})
	}

	onSubmit() {
		/* if(this.form.valid) {
			this.service.salvar(this.frete)
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

	calcularFrete(): void {
		this.service.frete(this.frete).subscribe((res: Frete[]) => {
			this.frete = res[0];
			console.log("*********Frete*********")
			console.log(this.frete)
			this.add.emit(this.frete)
		})
	}

}
