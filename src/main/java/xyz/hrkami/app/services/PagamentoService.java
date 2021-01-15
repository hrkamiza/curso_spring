package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Pagamento;
import xyz.hrkami.app.repositories.PagamentoRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	PagamentoRepository repo;

	public Pagamento find(Integer id) {

		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Pagamento.class.getName()));

	}
}
