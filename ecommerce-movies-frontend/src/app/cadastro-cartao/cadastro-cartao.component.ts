import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CadastroCartaoService } from './cadastro-cartao.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-cartao',
  templateUrl: './cadastro-cartao.component.html',
  styleUrls: ['./cadastro-cartao.component.css']
})
export class CadastroCartaoComponent implements OnInit {

  form: FormGroup;
  submitted: Boolean; 					// verifica se os dados do form estão vazios
  serverError: String;          //Pega erro do servidor
  id_cliente: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
		private service: CadastroCartaoService) {  

      this.id_cliente = this.route.snapshot.params['id'];
		  this.route.params.subscribe(res => this.id_cliente = res.id);
    }

  ngOnInit() {
    //Verifica se os inputs foram preenchidos

    this.form = this.fb.group({
      numeroCartao: [null, [Validators.required, Validators.maxLength(20)]],
      idBandeira: ['', [Validators.required]],
      cvv: [null, [Validators.required, Validators.maxLength(3)]],
      dataValidade: [null, [Validators.required, Validators.maxLength(7)]],
    })
  }

  // Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
  }
  
  //Click do botão salvar
  onSubmit() {
		this.submitted = true;

		if(this.form.valid) {
			this.service.create(this.form.value, this.id_cliente)
			.subscribe(response => {
				alert('Cadastro de Cartao realizado com sucesso!');
        this.router.navigate(['altera-cliente/', this.id_cliente])
      },
        error => {
          this.serverError = error.error.error;
        }
      );
		}
  }
  
  //Click do botão cancelar
  onCancel() {
		this.submitted = false;
    this.form.reset();
    this.router.navigate(['altera-cliente/', this.id_cliente])
	}

}
