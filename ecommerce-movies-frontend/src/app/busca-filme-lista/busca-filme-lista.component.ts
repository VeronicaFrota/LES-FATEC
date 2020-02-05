import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-busca-filme-lista',
  templateUrl: './busca-filme-lista.component.html',
  styleUrls: ['./busca-filme-lista.component.css']
})
export class BuscaFilmeListaComponent implements OnInit {

  @Input()
  filmes: any[]

  constructor() { }

  ngOnInit() {
  }

}
