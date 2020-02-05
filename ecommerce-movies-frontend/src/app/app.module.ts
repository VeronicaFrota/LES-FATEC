import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {RouterModule} from '@angular/router';
import { rotas } from './rotas';
import { MenuComponent } from './menu/menu.component';
import { CadastroFilmeComponent } from './cadastro-filme/cadastro-filme.component';
import { ListagemFilmeComponent } from './listagem-filme/listagem-filme.component';
import { HttpClientModule } from '@angular/common/http';
import { AlteraFilmeComponent } from './altera-filme/altera-filme.component';
import { DeleteFilmeComponent } from './delete-filme/delete-filme.component';
import { CadastroClienteComponent } from './cadastro-cliente/cadastro-cliente.component';
import { ListagemClienteComponent } from './listagem-cliente/listagem-cliente.component';
import { DeleteClienteComponent } from './delete-cliente/delete-cliente.component';
import { AlteraClienteComponent } from './altera-cliente/altera-cliente.component';
import { ListagemEnderecoComponent } from './listagem-endereco/listagem-endereco.component';
import { DeleteEnderecoComponent } from './delete-endereco/delete-endereco.component';
import { AlteraEnderecoComponent } from './altera-endereco/altera-endereco.component';
import { ListagemFilmeInativoComponent } from './listagem-filme-inativo/listagem-filme-inativo.component';
import { AtivarFilmeComponent } from './ativar-filme/ativar-filme.component';
import { ListagemCartaoComponent } from './listagem-cartao/listagem-cartao.component';
import { DeleteCartaoComponent } from './delete-cartao/delete-cartao.component';
import { AlteraCartaoComponent } from './altera-cartao/altera-cartao.component';
import { CadastroEnderecoComponent } from './cadastro-endereco/cadastro-endereco.component';
import { CadastroCartaoComponent } from './cadastro-cartao/cadastro-cartao.component';
import { ListagemEstoqueComponent } from './listagem-estoque/listagem-estoque.component';
import { DeleteEstoqueComponent } from './delete-estoque/delete-estoque.component';
import { AlteraEstoqueComponent } from './altera-estoque/altera-estoque.component';
import { EcommerceIndexComponent } from './ecommerce-index/ecommerce-index.component';
import { EcommerceExibeFilmeComponent } from './ecommerce-exibe-filme/ecommerce-exibe-filme.component';
import { EcommerceCarrinhoComponent } from './ecommerce-carrinho/ecommerce-carrinho.component';
import { EcommercePagamentoComponent } from './ecommerce-pagamento/ecommerce-pagamento.component';
import { EcommerceCarrinhoService } from './ecommerce-carrinho/ecommerce-carrinho.service';
import { BuscaFilmeComponent } from './busca-filme/busca-filme.component';
import { EcommerceFreteComponent } from './ecommerce-frete/ecommerce-frete.component';
import { EcommerceCupomComponent } from './ecommerce-cupom/ecommerce-cupom.component';
import { BuscaFilmeListaComponent } from './busca-filme-lista/busca-filme-lista.component';
import { LoginComponent } from './login/login.component';
import { ListagemPedidoComponent } from './listagem-pedido/listagem-pedido.component';
import { ListagemPedidoTrocaComponent } from './listagem-pedido-troca/listagem-pedido-troca.component';
import { ListagemCupomComponent } from './listagem-cupom/listagem-cupom.component';
import { NgxMaskModule } from 'ngx-mask'
import { ChartsModule } from 'ng2-charts';
import { GraficoComponent } from './grafico/grafico.component';
import { DoughnutChartComponent } from './grafico/doughnut-chart/doughnut-chart.component';
import { PieChartComponent } from './grafico/pie-chart/pie-chart.component';
import { LineChartComponent } from './grafico/line-chart/line-chart.component';
import { ListagemPedidoClienteComponent } from './listagem-pedido-cliente/listagem-pedido-cliente.component';
import { AlteraSenhaComponent } from './altera-senha/altera-senha.component';
import { ListagemCupomClienteComponent } from './listagem-cupom-cliente/listagem-cupom-cliente.component';
import { ListagemPedidoTrocaClienteComponent } from './listagem-pedido-troca-cliente/listagem-pedido-troca-cliente.component';
import { CadastroClienteTelaInicialComponent } from './cadastro-cliente-tela-inicial/cadastro-cliente-tela-inicial.component';

@NgModule({
	declarations: [
		AppComponent,
		MenuComponent,
		CadastroFilmeComponent,
		ListagemFilmeComponent,
		AlteraFilmeComponent,
		DeleteFilmeComponent,
		CadastroClienteComponent,
		ListagemClienteComponent,
		DeleteClienteComponent,
		AlteraClienteComponent,
		ListagemEnderecoComponent,
		DeleteEnderecoComponent,
		AlteraEnderecoComponent,
		ListagemFilmeInativoComponent,
		AtivarFilmeComponent,
		ListagemCartaoComponent,
		DeleteCartaoComponent,
		AlteraCartaoComponent,
		CadastroEnderecoComponent,
		CadastroCartaoComponent,
		ListagemEstoqueComponent,
		DeleteEstoqueComponent,
		AlteraEstoqueComponent,
		LoginComponent ,
		EcommerceIndexComponent,
		EcommerceExibeFilmeComponent,
		EcommerceCarrinhoComponent,
		EcommercePagamentoComponent,
		EcommerceFreteComponent,
		BuscaFilmeComponent,
		EcommerceCupomComponent,
		BuscaFilmeListaComponent,
		ListagemPedidoComponent,
		ListagemPedidoTrocaComponent,
		ListagemCupomComponent,
		GraficoComponent,
		DoughnutChartComponent,
		PieChartComponent,
		LineChartComponent,
		ListagemPedidoClienteComponent,
		AlteraSenhaComponent,
		ListagemCupomClienteComponent,
		ListagemPedidoTrocaClienteComponent,
		CadastroClienteTelaInicialComponent
	],
	exports: [
			RouterModule
		],
	imports: [
		HttpClientModule,
		BrowserModule,
		RouterModule.forRoot(rotas),
		ReactiveFormsModule,
		ChartsModule,
		NgxMaskModule.forRoot()
	],
	providers: [
		EcommerceCarrinhoService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
