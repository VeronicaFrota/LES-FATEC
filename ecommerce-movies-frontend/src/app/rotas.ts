import { Routes, RouterModule } from "@angular/router";
import { CadastroFilmeComponent } from "./cadastro-filme/cadastro-filme.component";
import { ListagemFilmeComponent } from './listagem-filme/listagem-filme.component';
import { AlteraFilmeComponent } from './altera-filme/altera-filme.component';
import { DeleteFilmeComponent } from './delete-filme/delete-filme.component';
import { CadastroClienteComponent } from './cadastro-cliente/cadastro-cliente.component';
import { ListagemClienteComponent } from './listagem-cliente/listagem-cliente.component';
import { DeleteClienteComponent } from './delete-cliente/delete-cliente.component';
import { AlteraClienteComponent } from './altera-cliente/altera-cliente.component';
import { ListagemEnderecoComponent } from './listagem-endereco/listagem-endereco.component';
import { ListagemCartaoComponent } from './listagem-cartao/listagem-cartao.component';
import { AlteraEnderecoComponent } from './altera-endereco/altera-endereco.component';
import { AlteraCartaoComponent } from './altera-cartao/altera-cartao.component';
import { ListagemFilmeInativoComponent } from './listagem-filme-inativo/listagem-filme-inativo.component';
import { CadastroEnderecoComponent } from './cadastro-endereco/cadastro-endereco.component';
import { CadastroCartaoComponent } from './cadastro-cartao/cadastro-cartao.component';
import { ListagemEstoqueComponent } from './listagem-estoque/listagem-estoque.component';
import { AlteraEstoqueComponent } from './altera-estoque/altera-estoque.component';
/* import { LoginComponent } from './login/login.component'; */
import { EcommerceIndexComponent } from './ecommerce-index/ecommerce-index.component';
import { EcommerceExibeFilmeComponent } from './ecommerce-exibe-filme/ecommerce-exibe-filme.component';
import { EcommerceCarrinhoComponent } from './ecommerce-carrinho/ecommerce-carrinho.component';
import { EcommercePagamentoComponent } from './ecommerce-pagamento/ecommerce-pagamento.component';
import { BuscaFilmeComponent } from './busca-filme/busca-filme.component';
import { EcommerceFreteComponent } from './ecommerce-frete/ecommerce-frete.component';
import { EcommerceCupomComponent } from './ecommerce-cupom/ecommerce-cupom.component';
import { LoginComponent } from './login/login.component';
import { ListagemPedidoComponent } from './listagem-pedido/listagem-pedido.component';
import { ListagemPedidoTrocaComponent } from './listagem-pedido-troca/listagem-pedido-troca.component';
import { ListagemCupomComponent } from './listagem-cupom/listagem-cupom.component';
import { GraficoComponent } from './grafico/grafico.component';
import { DoughnutChartComponent } from './grafico/doughnut-chart/doughnut-chart.component';
import { PieChartComponent } from './grafico/pie-chart/pie-chart.component';
import { LineChartComponent } from './grafico/line-chart/line-chart.component';
import { ListagemPedidoClienteComponent } from './listagem-pedido-cliente/listagem-pedido-cliente.component';
import { AlteraSenhaComponent } from './altera-senha/altera-senha.component';
import { ListagemCupomClienteComponent } from './listagem-cupom-cliente/listagem-cupom-cliente.component';
import { ListagemPedidoTrocaClienteComponent } from './listagem-pedido-troca-cliente/listagem-pedido-troca-cliente.component';
import { CadastroClienteTelaInicialComponent } from './cadastro-cliente-tela-inicial/cadastro-cliente-tela-inicial.component';

export const rotas : Routes = [
	/* { path: '' , component: EcommerceIndexComponent, 
		children: [
			{ path: 'login', component: LoginComponent, pathMatch: 'full'},
		]
	}, */

	// LOGIN
	{ path: '' , component: LoginComponent},
	{ path: 'login', component: LoginComponent, pathMatch: 'full'},

	// FILME
	{ path: 'cadastro-filme'       , component: CadastroFilmeComponent },
	{ path: 'listar-filme'         , component: ListagemFilmeComponent },
	{ path: 'altera-filme/:id'     , component: AlteraFilmeComponent },
	{ path: 'delete-filme/:id'     , component: DeleteFilmeComponent },
	{ path: 'listar-estoque'       , component: ListagemEstoqueComponent },
	{ path: 'altera-estoque/:id'   , component: AlteraEstoqueComponent },
	{ path: 'listar-filme-inativo' , component: ListagemFilmeInativoComponent },

	// CLIENTE
	{ path: 'cadastro-cliente'    , component: CadastroClienteComponent },
	{ path: 'listar-cliente'      , component: ListagemClienteComponent },
	{ path: 'delete-cliente/:id'  , component: DeleteClienteComponent },
	{ path: 'altera-cliente/:id'  , component: AlteraClienteComponent },
	{ path: 'listar-endereco'     , component: ListagemEnderecoComponent },
	{ path: 'altera-endereco/:id' , component: AlteraEnderecoComponent },
	{ path: 'altera-senha/:id'    , component: AlteraSenhaComponent },

	// CADASTRO DE CLIENTE DA TELA INICIAL - (TELA DE LOGIN)
	{ path: 'cadastro-cliente-tela-inicial', component: CadastroClienteTelaInicialComponent },

	// PERFIL CLIENTE
	{ path: 'listagem-pedido-cliente/:id'      , component:ListagemPedidoClienteComponent },
	{ path: 'listagem-cupom-cliente/:id'       , component: ListagemCupomClienteComponent },
	{ path: 'listagem-pedido-troca-cliente/:id', component: ListagemPedidoTrocaClienteComponent },

	// ECOMMERCE
	{ path: 'ecommerce-index'          , component: EcommerceIndexComponent },
	{ path: 'ecommerce-exibe-filme/:id', component: EcommerceExibeFilmeComponent }, 
	{ path: 'ecommerce-carrinho'       , component: EcommerceCarrinhoComponent },
	{ path: 'ecommerce-pagamento'      , component: EcommercePagamentoComponent },
	{ path: 'ecommerce-frete'    	   , component: EcommerceFreteComponent },
	{ path: 'ecommerce-cupom'          , component: EcommerceCupomComponent },

	// CARTAO
	{ path: 'altera-cartao/:id'     , component: AlteraCartaoComponent },
	{ path: 'cadastro-cartao/:id'   , component: CadastroCartaoComponent },
	{ path: 'listar-cartao'         , component: ListagemCartaoComponent },

	// ENDERECO
	{ path: 'cadastro-endereco/:id'   , component: CadastroEnderecoComponent },
	{ path: 'altera-endereco/:id'     , component: AlteraEnderecoComponent },
	{ path: 'listar-endereco'         , component: ListagemEnderecoComponent },

	// BUSCA POR FILME
	{ path: 'busca-filme' , component: BuscaFilmeComponent },

	// PEDIDOS
	{ path: 'listar-pedido'      , component: ListagemPedidoComponent },
	{ path: 'listar-pedido-troca', component: ListagemPedidoTrocaComponent },

	// CUPOM
	{ path: 'listar-cupom', component: ListagemCupomComponent },

	// GRAFICOS
	{ path: 'bar-chart'     , component: GraficoComponent },
	{ path: 'doughnut-chart', component: DoughnutChartComponent },
	{ path: 'line-chart'    , component: LineChartComponent },
	{ path: 'pie-chart'     , component: PieChartComponent },
	{ path: '**'            , component: GraficoComponent }
]
