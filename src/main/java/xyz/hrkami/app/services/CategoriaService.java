package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Categoria;
import xyz.hrkami.app.repositories.CategoriaRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;

	public Categoria find(Integer id) {

		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Categoria.class.getName()));

	}
}
