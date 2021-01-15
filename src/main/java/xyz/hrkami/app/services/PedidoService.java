package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Pedido;
import xyz.hrkami.app.repositories.PedidoRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repo;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Pedido.class.getName()));

	}
}
