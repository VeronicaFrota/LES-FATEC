import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListagemCupomService } from './listagem-cupom.service';
import { ListagemCupom } from 'src/modelos';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-listagem-cupom',
  templateUrl: './listagem-cupom.component.html',
  styleUrls: ['./listagem-cupom.component.css']
})
export class ListagemCupomComponent implements OnInit {

  cupons: ListagemCupom[]
	id_cupom: any
	submitted: false
  form: FormGroup
  
  constructor(
  private route: ActivatedRoute,
  private router: Router,
  private service: ListagemCupomService,
  private fb: FormBuilder) {
    this.route.params.subscribe(res => this.id_cupom = res.id);
  }

  ngOnInit() {
		this.service.list().subscribe((res: any[]) => {
			this.cupons = res;
    })
  }
}
