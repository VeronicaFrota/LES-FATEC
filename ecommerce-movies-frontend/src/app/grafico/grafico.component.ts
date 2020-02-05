import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-grafico',
	templateUrl: './grafico.component.html',
	styleUrls: ['./grafico.component.css']
})
export class GraficoComponent implements OnInit {

	public barChartOptions = {
		scaleShowVerticalLines: false,
		responsive: true
	};

	// Genero de clientes cadastrados no sistema (Feminino e Masculino)
	public barChartLabels = ['Ação', 'Ficção', 'Aventura', 'Suspense', 'Terror'];

	public barChartType = 'bar';

	public barChartLegend = true;

	public barChartData = [
		{ data: [21, 43, 54, 65, 76, 87], label:'Feminino' },
		{ data: [28, 48, 19, 86, 27, 90], label:'Masculino' }
	]

	constructor() { }

	ngOnInit() {
	}

}
