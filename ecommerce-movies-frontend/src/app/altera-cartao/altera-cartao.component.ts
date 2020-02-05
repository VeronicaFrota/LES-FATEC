import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlteraCartaoService } from './altera-cartao.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
	selector: 'app-altera-cartao',
	templateUrl: './altera-cartao.component.html',
	styleUrls: ['./altera-cartao.component.css']
})
export class AlteraCartaoComponent implements OnInit {

	id_cartao: any;
	form: FormGroup
	submitted = false						// verifica se os dados do form estão vazios
	serverError: String

	constructor(
		private route: ActivatedRoute,
		private service: AlteraCartaoService,
		private fb: FormBuilder) {

		this.route.params.subscribe(res => this.id_cartao = res.id);
	}

  ngOnInit() {
    //Verifica se os inputs foram preenchidos

    this.form = this.fb.group({
      numeroCartao: [null, [Validators.required]],
      idBandeira: ['', [Validators.required]],
      cvv: [null, [Validators.required]],
      dataValidade: [null, [Validators.required, Validators.maxLength(7)]],
    })

		this.service.pegarUmCartao(this.id_cartao)
			.subscribe(r => {
				this.form = this.fb.group({
					numeroCartao: [r.numeroCartao, [Validators.required]],
          idBandeira : [r.idBandeira, [Validators.required]],
          cvv : [r.cvv, [Validators.required]],
          dataValidade : [r.dataValidade, [Validators.required]],
				})
			})
	}

	// Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
	}

	onSubmit() {
		this.submitted = true;
		if(this.form.valid) {
			this.service.salvarAlteracao(this.id_cartao, this.form.value)
				.subscribe(response => {
					alert('Alteração executada com sucesso');
					history.go(-1);
				},
				error => {
					this.serverError = error.error.error;
					console.log(this.serverError);
				}
			);
		}
	}

	onCancel() {
		this.submitted = false;
		this.form.reset();
		history.go(-1);
	}

}
