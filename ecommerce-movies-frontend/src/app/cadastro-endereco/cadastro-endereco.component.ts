import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CadastroEnderecoService } from './cadastro-endereco.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-endereco',
  templateUrl: './cadastro-endereco.component.html',
  styleUrls: ['./cadastro-endereco.component.css']
})
export class CadastroEnderecoComponent implements OnInit {

  form: FormGroup;
  submitted: Boolean; 					// verifica se os dados do form estão vazios
  serverError: String;          //Pega erro do servidor
  id_cliente: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
		private service: CadastroEnderecoService) {  

      this.id_cliente = this.route.snapshot.params['id'];
		  this.route.params.subscribe(res => this.id_cliente = res.id);
    }

  ngOnInit() {
    //Verifica se os inputs foram preenchidos

    this.form = this.fb.group({
      idCidade: ['', [Validators.required]],
      logradouro: [null, [Validators.required, Validators.maxLength(50)]],
      numero: [null, [Validators.required, Validators.maxLength(10)]],
      bairro: [null, [Validators.required, Validators.maxLength(20)]],
      //Validação CEP
      cep: [null, [Validators.required]],
      observacao: [null, [Validators.required, Validators.maxLength(80)]]
    })
  }

  // Verifica se os campos estão vazio e inválidos para exiibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
  }
  
  //Click do botão salvar
  onSubmit() {
		this.submitted = true;

    console.log(this.id_cliente);

		if(this.form.valid) {
			this.service.create(this.form.value, this.id_cliente)
			.subscribe(response => {
        alert('Cadastro de Endereco realizado com sucesso!');
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
