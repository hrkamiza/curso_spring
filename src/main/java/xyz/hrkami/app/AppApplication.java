package xyz.hrkami.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.hrkami.app.entities.Categoria;
import xyz.hrkami.app.entities.Cidade;
import xyz.hrkami.app.entities.Cliente;
import xyz.hrkami.app.entities.Endereco;
import xyz.hrkami.app.entities.Estado;
import xyz.hrkami.app.entities.ItemPedido;
import xyz.hrkami.app.entities.Pagamento;
import xyz.hrkami.app.entities.PagamentoComBoleto;
import xyz.hrkami.app.entities.PagamentoComCartao;
import xyz.hrkami.app.entities.Pedido;
import xyz.hrkami.app.entities.Produto;
import xyz.hrkami.app.entities.enums.EstadoPagamento;
import xyz.hrkami.app.entities.enums.TipoCliente;
import xyz.hrkami.app.repositories.CategoriaRepository;
import xyz.hrkami.app.repositories.CidadeRepository;
import xyz.hrkami.app.repositories.ClienteRepository;
import xyz.hrkami.app.repositories.EnderecoRepository;
import xyz.hrkami.app.repositories.EstadoRepository;
import xyz.hrkami.app.repositories.ItemPedidoRepository;
import xyz.hrkami.app.repositories.PagamentoRepository;
import xyz.hrkami.app.repositories.PedidoRepository;
import xyz.hrkami.app.repositories.ProdutoRepository;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriarepository;

	@Autowired
	ProdutoRepository produtorepository;

	@Autowired
	CidadeRepository cidaderepository;

	@Autowired
	EstadoRepository estadorepository;

	@Autowired
	EnderecoRepository enderecorepository;

	@Autowired
	ClienteRepository clienterepository;

	@Autowired
	PedidoRepository pedidorepository;

	@Autowired
	PagamentoRepository pagamentorepository;
	
	@Autowired
	ItemPedidoRepository itempedidorepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade cid1 = new Cidade(null, "Sao Paulo", est1);
		Cidade cid2 = new Cidade(null, "Campinas", est1);
		Cidade cid3 = new Cidade(null, "Uberlandia", est2);

		estadorepository.saveAll(Arrays.asList(est1, est2));
		cidaderepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cid2);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienterepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017  10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017  19:35"), cli1, end2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped1.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pagamentorepository.saveAll(Arrays.asList(pgto1, pgto2));	
		pedidorepository.saveAll(Arrays.asList(ped1, ped2));
		
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));
		categoriarepository.saveAll(Arrays.asList(cat1, cat2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00,2 , 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidorepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		


	}

}
