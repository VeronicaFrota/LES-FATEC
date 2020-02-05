	package com.equipemovies.ecommercemovies.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipemovies.ecommercemovies.viewhelper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equipemovies.ecommercemovies.command.AbstractCommand;
import com.equipemovies.ecommercemovies.command.AlterarCommand;
import com.equipemovies.ecommercemovies.command.ConsultarCommand;
import com.equipemovies.ecommercemovies.command.DeletarCommand;
import com.equipemovies.ecommercemovies.command.SalvarCommand;
import com.equipemovies.ecommercemovies.domain.EntidadeDominio;
import com.equipemovies.ecommercemovies.fachada.Fachada;
import com.equipemovies.ecommercemovies.util.Resultado;
import com.equipemovies.ecommercemovies.util.UtilFatec;

@RestController
public class MovieServlet {

	@Autowired
    private Fachada fachada;

    private Map<String, AbstractCommand> commands;
    private Map<String, IViewHelper> viewHelpers;

    public MovieServlet() {

        viewHelpers = new HashMap<>();
        // Mapeamento dos filmes
        //viewHelpers.put("/movies/", new MovieViewHelper());
        viewHelpers.put("/filmes/", new MovieViewHelper());
        viewHelpers.put("/salvar/filme/", new MovieViewHelper());
        viewHelpers.put("/ativa/filme/", new MovieAtivaViewHelper());
        viewHelpers.put("/alterar/filme/", new MovieAlteraViewHelper());
        viewHelpers.put("/deletar/filme/", new MovieDeletaViewHelper());

        // Mapeamento para busca de filme (filtragem de campos simples e combinados)
        viewHelpers.put("/filme/busca/", new MovieViewHelper());

        // Mapeamento do estoque
        viewHelpers.put("/filme/estoque/", new EstoqueViewHelper());
        viewHelpers.put("/filme/salvar/estoque/", new EstoqueViewHelper());
        viewHelpers.put("/filme/alterar/estoque/", new EstoqueAlteraViewHelper());
        viewHelpers.put("/filme/deletar/estoque/", new EstoqueDeletaViewHelper());

        // Mapeamento dos clientes
        viewHelpers.put("/login/", new LoginViewHelper());
        viewHelpers.put("/cliente/", new UsuarioViewHelper());
        viewHelpers.put("/salvar/cliente/", new UsuarioViewHelper());
        viewHelpers.put("/alterar/cliente/", new UsuarioAlteraViewHelper());
        viewHelpers.put("/deletar/cliente/", new UsuarioDeletaViewHelper());
        viewHelpers.put("/alterar/senha/", new UsuarioSenhaViewHelper());
        viewHelpers.put("/cliente/email/", new UsuarioEmailViewHelper());

        // Mapeamento de dados especificos do Cliente
        viewHelpers.put("/pedido/especifico/", new PedidoEspecificoViewHelper());
        viewHelpers.put("/cupom/especifico/", new CupomEspecificoViewHelper());

        // Mapeamento do endereco do cliente
        viewHelpers.put("/cliente/endereco/", new EnderecoViewHelper());
        viewHelpers.put("/cliente/salvar/endereco/", new EnderecoViewHelper());
        viewHelpers.put("/cliente/alterar/endereco/", new EnderecoAlteraViewHelper());
        viewHelpers.put("/cliente/deleta/endereco/", new EnderecoDeletaViewHelper());
        
        // Mapeamento do cartao do cliente
        viewHelpers.put("/cliente/cartao/", new CartaoViewHelper());
        viewHelpers.put("/cliente/salvar/cartao/", new CartaoViewHelper());
        viewHelpers.put("/cliente/alterar/cartao/", new CartaoAlteraViewHelper());
        viewHelpers.put("/cliente/deleta/cartao/", new CartaoDeletaViewHelper());

        // Mapeamento de pedido
        viewHelpers.put("/pedido/", new PedidoViewHelper());
        viewHelpers.put("/pedido/salvar/", new PedidoViewHelper());
        viewHelpers.put("/pedido/alterar/", new PedidoAlteraViewHelper());
        
        // Mapeamento do cupom
        viewHelpers.put("/cupom/", new CupomViewHelper());
        viewHelpers.put("/cupom/salvar/", new CupomViewHelper());
        viewHelpers.put("/cupom/alterar/", new CupomAlteraViewHelper());

        // Mapeamento para gráfico - doughnut-chart
        viewHelpers.put("/doughnut/categoria/", new DoughnutViewHelper());
        
        //Mapeamento para gráfico - line-chart
        viewHelpers.put("/pie/categoria/", new PieViewHelper());
        
        //Mapeamento para grafico - pie-chart
        viewHelpers.put("/line/categoria/", new LineViewHelper());
        
        //Mapeamento para grafico - bar-chart
        viewHelpers.put("/bar/categoria/", new BarViewHelper());


        commands = new HashMap<>();
        commands.put("consultar", new ConsultarCommand());
        commands.put("buscar", new ConsultarCommand());
        commands.put("login", new ConsultarCommand());
        commands.put("salvar", new SalvarCommand());
        commands.put("alterar", new AlterarCommand());
        commands.put("excluir", new DeletarCommand());
    }

    // Faz o mapeamento de todos os request methods
    @CrossOrigin(origins = "*")
    @RequestMapping(
            value = "/**",
            method = {
                    RequestMethod.GET,
                    RequestMethod.POST,
                    RequestMethod.PUT,
                    RequestMethod.PATCH,
                    RequestMethod.DELETE,
            }
    )
    public ResponseEntity service(HttpServletRequest request,
                                  HttpServletResponse response) {

        String uriRequested = request.getRequestURI();
        System.out.println(uriRequested);
        /*
         * Procurando a URL nos viewHelper's
         */
        IViewHelper viewHelperFound  = viewHelpers.get(uriRequested);

        if (viewHelperFound != null) {

            final String operacao;

            if (UtilFatec.hasParameterOperation(request)) {
                operacao = UtilFatec.getParameterOperation(request);
            } else {
                operacao = "consultar";
            }

            EntidadeDominio entidade = viewHelperFound.getEntidade(request);

            AbstractCommand command = commands.get(operacao);
            command.setFachada(fachada);

            Resultado resultado = command.execute(entidade);

            /*
             * Executando o view entrado
             */
            return viewHelperFound.getView(resultado, request, response);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("URL não encontrada na servlet");
    }

}
