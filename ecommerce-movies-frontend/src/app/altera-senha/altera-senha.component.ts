import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlteraSenhaService } from './altera-senha.service';
import { Cliente } from 'src/modelos';

@Component({
  selector: 'app-altera-senha',
  templateUrl: './altera-senha.component.html',
  styleUrls: ['./altera-senha.component.css']
})
export class AlteraSenhaComponent implements OnInit {

  id_cliente: any;

  constructor(
    private route: ActivatedRoute,
    private service: AlteraSenhaService){
    this.id_cliente = this.route.snapshot.params['id']
    
  }

  ngOnInit() {
    
  }

  alteraSenha(antiga, nova1, nova2){
    console.log(antiga)
    console.log(nova1)
    console.log(nova2)
    if (nova1 != nova2){
      alert("As novas senhas inseridas não são iguais")
    }
    else if (!nova1.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$/)){
      alert("A senha deve conter no mínimo 8 caractéres, sendo pelo menos:" +
      "\r\n - 1 Letra maiuscula" + 
      "\r\n - 1 Letra minuscula" +
      "\r\n - 1 Numero" +
      "\r\n - 1 Carácter Especial") 
    }
    else{
      this.service.verificaSenha(this.id_cliente, antiga).subscribe((res: Cliente) =>{
        if(res.senha == "Incorreta"){
          alert("A senha atual inserida não está correta")
        }
        else{
          this.service.alteraSenha(this.id_cliente, nova1).subscribe(response => {
            alert('Alteração de senha executada com sucesso');
            history.go(-1);
          })
        }
      })
    }
  }
}
