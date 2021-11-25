package com.sales.management.servico;

import java.util.List;
import java.util.Optional;

import com.sales.management.entity.Categoria;
import com.sales.management.repository.CategoriaRepositorio;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public Categoria post(Categoria data) {
        return categoriaRepositorio.save(data);
    }

    public List<Categoria> get() {
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> getById(Long id) {
        return categoriaRepositorio.findById(id);
    }

    public Categoria patch(Long id, Categoria data) {
        Categoria result = isValid(id);
        BeanUtils.copyProperties(data, result, "codigo");
        return categoriaRepositorio.save(result);
    }

    private Categoria isValid(Long id) {
        Optional<Categoria> valid = getById(id);
        if (valid.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return valid.get();
    }

}
