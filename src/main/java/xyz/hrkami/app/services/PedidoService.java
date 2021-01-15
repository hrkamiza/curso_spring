package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Produto;
import xyz.hrkami.app.repositories.ProdutoRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	ProdutoRepository repo;

	public Produto find(Integer id) {

		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Produto.class.getName()));

	}
}
