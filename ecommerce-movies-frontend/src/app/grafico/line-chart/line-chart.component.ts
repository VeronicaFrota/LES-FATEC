import { Component, OnInit, ViewChild } from '@angular/core';
import { GeneroLine, GeneroLineData } from 'src/modelos';
import { LineChartService } from './line-chart.service'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
	selector: 'app-line-chart',
	templateUrl: './line-chart.component.html',
	styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent implements OnInit {

	chart = [];
	generos: GeneroLine
	id_filme: any

	public lineChartLabels = []
	public lineChartData: GeneroLine[] = [
		{data: [5, 8, 29, 17, 7, 20, 12, 11, 7, 9, 10, 9], label: "Ação"},
		{data: [5, 8, 22, 17, 23, 20, 8, 11, 7, 9, 10, 10], label: "Ficção"},
		{data: [5, 15, 29, 10, 7, 78, 12, 11, 7, 9, 10, 29], label: "Teste"}
	]
	public lineChartType = 'line'

	constructor(
		private service: LineChartService
	) { }

	ngOnInit (){
		let ano = new Date();
		this.service.list(ano.getFullYear()).subscribe((res: GeneroLine[]) =>{
			this.lineChartData = res
			console.log(res)
			this.service.listData(ano.getFullYear()).subscribe((resData: GeneroLineData[]) =>{
				this.lineChartLabels = resData[0].dataAno
			})
		})	
	}

	realizaFiltro(comeco, fim){
		if(comeco > fim){
			console.log("entrou")
			alert("A Data Inical de Pesquisa não pode ser maior que a Data Final")
		}
		else{
			this.service.listPesquisa(comeco, fim).subscribe((res: GeneroLine[]) =>{
				this.lineChartData = res
				this.service.listaPesquisaData(comeco, fim).subscribe((resData: GeneroLineData[]) => {
					this.lineChartLabels = resData[0].dataAno
				})
			})
		}
	}
}
