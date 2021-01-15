package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Cidade;
import xyz.hrkami.app.repositories.CidadeRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository repo;

	public Cidade find(Integer id) {

		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Cidade.class.getName()));

	}
}
