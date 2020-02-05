import { Component, OnInit } from '@angular/core';
import { GeneroBar, GeneroBarData } from 'src/modelos';
import { PieChartService } from './pie-chart.service';

@Component({
	selector: 'app-pie-chart',	
	templateUrl: './pie-chart.component.html',
	styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {

	constructor(
		private service: PieChartService
	) { }

	public barChartOptions = {
		scaleShowVerticalLines: false,
		responsive: true
	};

	public barChartType = 'bar';
	public barChartLabels = []
	public barChartLegend = true;

	public barChartData : GeneroBar[] = [
		{ data: [21, 43, 54, 65, 76, 87, 21, 43, 54, 65, 76, 87], label:'Feminino' },
		{ data: [28, 48, 19, 86, 27, 90, 28, 48, 19, 86, 27, 90], label:'Masculino' }
	]

	ngOnInit (){
		let ano = new Date();
		this.service.list(ano.getFullYear()).subscribe((res: GeneroBar[]) =>{
			this.barChartData = res
			console.log(res)
			this.service.listData(ano.getFullYear()).subscribe((resData: GeneroBarData[]) =>{
				this.barChartLabels = resData[0].dataAno
			})
		})	
	}

	realizaFiltro(comeco, fim, ano){
		let anoAtual = new Date();
		if(comeco > fim){
			console.log("entrou")
			alert("A Data Inical de Pesquisa não pode ser maior que a Data Final")
		}
		else if (ano < 1800 || ano > anoAtual.getFullYear()){
			alert("Insira um Ano Válido");
		}
		else{
			this.service.listPesquisa(comeco, fim, ano).subscribe((res: GeneroBar[]) =>{
				this.barChartData = res
				this.service.listaPesquisaData(comeco, fim, ano).subscribe((resData: GeneroBarData[]) => {
					this.barChartLabels = resData[0].dataAno
				})
			})
		}
	}



}
