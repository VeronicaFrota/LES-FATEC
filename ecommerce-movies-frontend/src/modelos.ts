// LOGIN
/* export class LoginCliente {
	email: String
	senha: String
}
 */


// FILME
export class Filme {
	id: number
	nome: String
	codigo_barras: String
	genero: Genero = new Genero()
	ano: number
	pais_origem: PaisOrigem = new PaisOrigem()
	idioma: Idioma	= new Idioma()
	classificacao_etaria: ClassificacaoEaria = new ClassificacaoEaria()
	sinopse: String
	status: boolean
	total_compra: number
	quantidade_comprada: number
	subtotal_compra: number

	estoque: Estoque = new Estoque()
}

export class ListagemFilme {
	id: number
	nome: String
	codigo_barras: String
	ano: number
	status: boolean
}

export class AlteraFilme {
	nome: String
	codigo_barras: String
	genero: Genero
	ano: number
	pais_origem: PaisOrigem
	idioma: Idioma
	classificacao_etaria: ClassificacaoEaria
	sinopse: String
}

export class Genero {
	id: number
	nome: String
	totalGenero: number
}

export class GeneroLine{
	label: String
	data: number[]
}

export class GeneroBar{
	label: String
	data: number[]
}

export class GeneroLineData{
	dataAno: String[]
}

export class GeneroBarData{
	dataAno: String[]
}

export class GeneroPie{
	nome: String
	totalGenero : number
}

export class ClassificacaoEaria {
	id: number
	nome: String
}

export class Idioma {
	id: number
	nome: String
}

export class PaisOrigem {
	id: number
	nome: String
}
//////////////////////////////////


// ESTOQUE
export class Estoque {
	id: number
	idFilme: number
	fornecedor: String
	quantidade_estoque: number
	valor_compra: number
	valor_venda: number
	valor_frete: number
	dataCadastro: Date
	status: boolean
}

export class ListagemEstoque {
	id: number
	idFilme: number
	fornecedor: String
	quantidade_estoque: number
	valor_compra: number
	valor_venda: number
	status: boolean
}
//////////////////////////////////


// PEDIDO
export class Pedido {
	id: number
	id_cliente: number
	id_filme: number
	id_cupom: number
	id_cartao: number
	id_transacao: number
	id_endereco: number
	quantidade_comprada: number
	quantidade_parcela: number = 1
	valor_frete: number = 0
	valor_cupom: number = 0
	total_compra: number = 0
	subtotal_compra: number
	data_cadastro: Date
	status: boolean
	filmes: Filme[]
	cartoes: ListagemCartao[]
}

export class ListagemPedido{
	id: number
	id_cliente: number
	total_compra: number
	id_transacao: number
}

export class ListagemPedidoPorCliente{
	id: number
	id_cliente: number
	total_compra: number
}
//////////////////////////////////


// FRETE
export interface Frete {
    id: string
    nome: string
    valor_frete: number
    prazo_entrega: number
}
//////////////////////////////////


// CUPOM
export interface Cupom {
	id: number
	id_cliente:number
	codigo: string
	valor: number
	status: boolean
}

export class ListagemCupom{
	id: number
	idCliente:number
	codigo: string
	valor: number
	status: boolean
}
//////////////////////////////////


// CLIENTE
export class Cliente {
	id: number
	data_cadastro: Date
	nome: String
	cpf: String
	data_nascimento: Date
	email: String
	senha: String
	genero: String
	telefone: String
	status: boolean
	endereco: Endereco[]
	cartoes: ListagemCartao[]
}

export class Endereco {
	id: number
	idCliente: number
	idCidade: number
	logradouro: String
	numero: String
	bairro: String
	cep: String
	observacao: String
	dataCadastro: Date
	status: boolean
	selected: boolean = false
}

export class Cartao{
	id: number
	idCliente: number
	idBandeira: number
	cvv: String
	numeroCartao: String
	dataValidade: String
	status: boolean
}

export class ListagemCliente {
	id: number
	nome: String
	cpf: String
	email: String
	status: boolean
}

export class ListagemEndereco {
	id: number
	idCliente: String 
	idCidade: String
	logradouro: String
	bairro: String
	status: boolean
}

export class ListagemCartao{
	id: number
	idCliente: number
	numeroCartao: String
	dataValidade: String
	idBandeira: number
	status: boolean
	quantidadeParcela: number = 0
	selected: boolean = false
}
