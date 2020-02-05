import { Component, OnInit, Directive, NgModule } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { CadastroClienteService } from './cadastro-cliente.service';
import { Cliente } from 'src/modelos';

@Component({
  selector: 'app-cadastro-cliente',
  templateUrl: './cadastro-cliente.component.html',
  styleUrls: ['./cadastro-cliente.component.css']
})

export class CadastroClienteComponent implements OnInit {

  form: FormGroup;
  submitted: Boolean; 					// verifica se os dados do form estão vazios
  serverError: String;          //Pega erro do servidor

  constructor(
    private fb: FormBuilder,
		private service: CadastroClienteService) { }

  ngOnInit() {
    //Verifica se os inputs foram preenchidos

    this.form = this.fb.group({
      nome: [null, [Validators.required, Validators.maxLength(40)]],
      //Validação do CPF
      cpf: [null, [Validators.required, Validators.maxLength(14)]],
      //Validação da Data no formato informado
      dataNascimento: [null, [Validators.required]],
      //Validação email
      email: [null, [Validators.required, Validators.maxLength(40)]],
      senha: [null, [Validators.required, Validators.maxLength(20)]],
      genero: ['', [Validators.required]],
      telefone: [null, [Validators.required, Validators.maxLength(11)]],
      idCidade: ['', [Validators.required]],
      logradouro: [null, [Validators.required, Validators.maxLength(50)]],
      numero: [null, [Validators.required, Validators.maxLength(10)]],
      bairro: [null, [Validators.required, Validators.maxLength(20)]],
      //Validação CEP
      cep: [null, [Validators.required]],
      observacao: [null, [Validators.required, Validators.maxLength(80)]]
    })
  }

  // Verifica se os campos estão vazio e inválidos para exibir mensagem de erro
	hasError(field: any) {
		return this.form.get(field).errors;
  }
  
  //Click do botão salvar
  onSubmit() {
    this.submitted = true;

		if(this.form.valid) {
      this.service.verificaEmail(this.form.value).subscribe((res: Cliente) => {
        if(res.email == "Existente"){
          alert("O Email Inserido já foi cadastrado!")
        }
        else{
          console.log(this.form.value)
          this.service.create(this.form.value).subscribe(response => {
				    alert('Cadastro de Usuário realizado com sucesso!');
				    window.location.href = "/ecommerce-index";
          },
          error => {
            this.serverError = error.error.error;
          });
        }
      })	
		}
  }
  
  //Click do botão cancelar
  onCancel() {
		this.submitted = false;
		this.form.reset();
    history.go(-1);
  }
}
