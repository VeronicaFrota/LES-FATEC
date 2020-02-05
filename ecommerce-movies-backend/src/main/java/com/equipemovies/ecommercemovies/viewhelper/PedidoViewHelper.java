package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.*;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Pedido pedido = new Pedido();
        
        if (request.getParameter("operacao") != null
        		&& request.getParameter("operacao").toLowerCase().equals("consultar")){
        	
        }
        else {
        	// Pega uma lista de itens do carrinho
            try {
                String json = request.getReader().lines().collect(Collectors.joining());

                ObjectMapper mapper = new ObjectMapper();

                Map<String, Object> pedidoJason = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
                });

                // Lista de filmes
                List<Filme> filmes = new ArrayList<>();

                // Loop de filmes
                for (Map<String, Object> item  : (List<Map<String, Object>>) pedidoJason.get("filmes")) {

                    //if(request.getParameter("operacao") != null
                    //        && request.getParameter("operacao").toLowerCase().equals("salvar")) {

                    System.out.println("item = " + item.get("id"));
                    System.out.println("item = " + item);

                    Integer idFilme = (Integer) item.get("id");
                    System.out.println("-------> idFilme = " + idFilme);

                    /*Map<String, Object> estoque = (Map<String, Object>) item.get("estoque");
                    System.out.println("-------> estoque = " + estoque);

                    Map<String, Object> classificacaoEtaria = (Map<String, Object>) item.get("classificacao_etaria");
                    System.out.println("-------> classificacaoEtaria = " + classificacaoEtaria);

                    Map<String, Object> paisOrigem = (Map<String, Object>) item.get("pais_origem");
                    System.out.println("-------> paisOrigem = " + paisOrigem);*/

                    Filme filme = new Filme();

                    filme.setId(idFilme);

                    Integer quantidadeComprada = Integer.parseInt(item.get("quantidade_comprada").toString());
                    filme.setQuantidadeComprada(quantidadeComprada);
                    pedido.setQuantidadeComprada(1);

                    filme.setDataCadastro(new Date());

                    filmes.add(filme);
                }

            pedido.setFilmes(filmes);

            // Lista de cartoes
            List<Cartao> cartoes = new ArrayList<>();

            // Loop de cartoes
            for (Map<String, Object> cartao : (List<Map<String, Object>>) pedidoJason.get("cartoes")) {
                Cartao novoCartao = new Cartao();
                novoCartao.setId((Integer) cartao.get("id"));
                novoCartao.setQuantidadeParcela(cartao.get("quantidadeParcela").toString());
                cartoes.add(novoCartao);
            }

            pedido.setCartoes(cartoes);

            Integer quantidadeParcela = Integer.parseInt(pedidoJason.get("quantidade_parcela").toString());
            pedido.setQuantidadeParcela(quantidadeParcela);


                String totalCompra = pedidoJason.get("total_compra").toString();
                pedido.setTotalCompra(Float.parseFloat(totalCompra));


                String valorFrete = pedidoJason.get("valor_frete").toString();
                pedido.setValorFrete(Float.parseFloat(valorFrete));
                
            if (pedidoJason.get("id_cupom") != null && !StringUtils.isEmpty(pedidoJason.get("id_cupom").toString())) {
                Integer idCupom = Integer.parseInt(pedidoJason.get("id_cupom").toString());
                pedido.setIdCupom(idCupom);
            }

                Integer idCliente = Integer.parseInt(pedidoJason.get("id_cliente").toString());
                pedido.setIdCliente(idCliente);

            Integer idEndereco = Integer.parseInt(pedidoJason.get("id_endereco").toString());
            pedido.setIdEndereco(idEndereco);

            pedido.setIdCartao(1);
            pedido.setIdTransacao(1);
            pedido.setValorFrete((float) 1);
            pedido.setSubtotalCompra((float) 1);


                pedido.setDataCadastro(new Date());


                /*pedido.setIdCliente(idCliente);
                pedido.setIdFilme(idFilme);
                pedido.setIdCupom(idCupom);
                pedido.setIdCartao(idCartao);
                pedido.setIdTransacao(idTransacao);
                pedido.setQuantidadeComprada(quantidadeComprada);
                pedido.setValorFrete(valorFrete);
                pedido.setSubtotalCompra(subtotalCompra);*/


                System.out.println("**************************************************");

            //}


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        /*if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("salvar")) {

            String idCliente = request.getParameter("idCliente");
            String idFilme = request.getParameter("idFilme");
            String idCupom = request.getParameter("idCupom");
            String idCartao = request.getParameter("idCartao");
            String idTransacao = request.getParameter("idTransacao");
            String quantidadeComprada = request.getParameter("quantidadeComprada");
            String quantidadeParcela = request.getParameter("quantidadeParcela");
            String valorFrete = request.getParameter("valorFrete");
            String totalCompra = request.getParameter("totalCompra");
            String subtotalCompra = request.getParameter("subtotalCompra");


            pedido.setIdCliente(Integer.valueOf(idCliente));

            pedido.setIdFilme(Integer.valueOf(idFilme));

            pedido.setIdCupom(Integer.valueOf(idCupom));

            pedido.setIdCartao(Integer.valueOf(idCartao));

            pedido.setIdTransacao(Integer.valueOf(idTransacao));

            pedido.setQuantidadeComprada(Integer.valueOf((quantidadeComprada)));

            pedido.setQuantidadeParcela(Integer.valueOf(quantidadeParcela));

            pedido.setValorFrete(Float.parseFloat(valorFrete));

            pedido.setTotalCompra(Float.parseFloat(totalCompra));

            pedido.setSubtotalCompra(Float.parseFloat(subtotalCompra));

        }*/

        return pedido;
    }

    @Override
    public ResponseEntity getView(Resultado result,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    	if (result.getMsg() != null) {
			return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
		}

		if(request.getParameter("operacao") != null
				&& request.getParameter("operacao").toLowerCase().equals("consultar")) {

			if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(result.getEntidadeDominio().get(0));
		}
		
		return ResponseEntity.ok(result.getEntidadeDominio());
    }
}
