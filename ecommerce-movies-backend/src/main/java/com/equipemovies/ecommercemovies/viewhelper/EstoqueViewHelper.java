package com.equipemovies.ecommercemovies.viewhelper;

import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.domain.Estoque;
import com.equipemovies.ecommercemovies.util.ResponseError;
import com.equipemovies.ecommercemovies.util.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class EstoqueViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Estoque estoque = new Estoque();

        //Verifica se a operação é do tipo salvar.
        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("salvar")) {

            //Informações de estoque
            String idFilme = request.getParameter("idFilme");
            String fornecedor = request.getParameter("fornecedor");
            String quantidadeEstoque = request.getParameter("quantidadeEstoque");
            String valorCompra = request.getParameter("valorCompra");
            String valorVenda = request.getParameter("valorVenda");

            // Validações
            estoque.setIdFilme(Integer.valueOf(idFilme));

            if(fornecedor != null && !fornecedor.trim().equals("")){
                estoque.setFornecedor(fornecedor);
            }

            if(quantidadeEstoque != null && !quantidadeEstoque.trim().equals("")){
                estoque.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoque));
            }

            if(valorCompra != null && !valorCompra.trim().equals("")){
                estoque.setValorCompra(Float.parseFloat(valorCompra));
            }

            if(valorVenda != null && !valorVenda.trim().equals("")){
                estoque.setValorVenda(Float.parseFloat(valorVenda));
            }

            //Insere data de cadastro do estoque
            estoque.setDataCadastro(new Date());

        }

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")) {

            String idEstoque = request.getParameter("id_estoque");
            String idFilme = request.getParameter("id_filme");

            if(!StringUtils.isEmpty(idEstoque)) {
                estoque.setId(Integer.parseInt(idEstoque));
            }

            if(!StringUtils.isEmpty(idFilme)) {
                estoque.setIdFilme(Integer.parseInt(idFilme));
            }

        }

        System.out.println(estoque.getId());

        return estoque;
    }

    //Para pegar o resultado
    @Override
    public ResponseEntity getView(Resultado result,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        if (result.getMsg() != null) {
            return ResponseEntity.badRequest().body( new ResponseError(result.getMsg()));
        }

        if(request.getParameter("operacao") != null
                && request.getParameter("operacao").toLowerCase().equals("consultar")
                && request.getParameter("id_endereco") != null) {

            if (CollectionUtils.isEmpty(result.getEntidadeDominio())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(result.getEntidadeDominio().get(0));
        }

        return ResponseEntity.ok(result.getEntidadeDominio());
    }

}
