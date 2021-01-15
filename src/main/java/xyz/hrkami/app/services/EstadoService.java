package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Estado;
import xyz.hrkami.app.repositories.EstadoRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository repo;

	public Estado find(Integer id) {

		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Estado.class.getName()));

	}
}
