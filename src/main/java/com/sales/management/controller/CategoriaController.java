package com.sales.management.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sales.management.entity.Categoria;
import com.sales.management.servico.CategoriaServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaServico categoriaServico;

    @ApiOperation(value = "Lista todas as categorias")
    @GetMapping
    public List<Categoria> get() {
        return categoriaServico.get();
    }

    @ApiOperation(value = "Busca uma categoria pelo id")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.getById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cria uma categoria")
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria body) {
        Categoria result = categoriaServico.post(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @ApiOperation(value = "Atualiza uma categoria")
    @PatchMapping("/{codigo}")
    public ResponseEntity<Categoria> patch(@PathVariable Long codigo, @Valid @RequestBody Categoria body) {
        return ResponseEntity.ok(categoriaServico.patch(codigo, body));
    }
}
