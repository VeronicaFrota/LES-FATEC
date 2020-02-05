import { Component, OnInit } from '@angular/core';
import { DoughnutChartService } from './doughnut-chart.service';
import { Genero } from 'src/modelos';

@Component({
	selector: 'app-doughnut-chart',
	templateUrl: './doughnut-chart.component.html',
	styleUrls: ['./doughnut-chart.component.css']
})
export class DoughnutChartComponent implements OnInit {

	chart = [];
	generos: Genero[]
	id_filme: any

	public doughnutChartLabels = []
	public doughnutChartData = []
	public doughnutChartType = 'doughnut'

	constructor(
		private service: DoughnutChartService
	) { }

	ngOnInit() {
		this.service.list().subscribe((res: Genero[]) => {
			this.doughnutChartLabels = res.map(_ => _.nome); 		// "navega" pelos atrubutos dentro da lista
			this.doughnutChartData = res.map(_ => _.totalGenero);	// "navega" pelos atrubutos dentro da lista
		})
	}



}

