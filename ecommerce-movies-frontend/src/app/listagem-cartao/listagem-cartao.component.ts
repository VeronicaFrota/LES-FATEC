import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListagemCartaoService } from './listagem-cartao.service';
import { DeleteCartaoService } from '../delete-cartao/delete-cartao.service';
import { Cartao } from 'src/modelos';
import { FormBuilder } from '@angular/forms';	

@Component({
  selector: 'app-listagem-cartao',
  templateUrl: './listagem-cartao.component.html',
  styleUrls: ['./listagem-cartao.component.css']
})
export class ListagemCartaoComponent implements OnInit {

  cartaos: Cartao[]
	id_cartao: any
	submitted: false
  form: FormBuilder
  
  constructor(
	private route: ActivatedRoute,
	private service: ListagemCartaoService,
	private deleteCartaoService: DeleteCartaoService,
	private fb: FormBuilder) {
    this.route.params.subscribe(res => this.id_cartao = res.id);
  }

  ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.cartaos = res;
		})
  }
  
  deletarCartao(cartao) {

		console.log(cartao)

		var approve = confirm("Deseja realmente deletar o cartao com id: " + cartao.id)

		if (approve) {
			this.deleteCartaoService.deletarCartao(cartao.id)
			.subscribe(response => {
				alert('Enderedo com id ' + cartao.id + ' deletado com sucesso');
				window.location.href = "/listar-cliente";
			})
		}
	}
}
