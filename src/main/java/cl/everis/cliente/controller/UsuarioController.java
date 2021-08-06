package cl.everis.cliente.controller;


import cl.everis.cliente.DTO.UsuarioRequestDTO;
import cl.everis.cliente.DTO.UsuarioResponseDTO;
import cl.everis.cliente.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAllUser(){
        return new ResponseEntity<>(this.usuarioService.findAllUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> leerUsuario(@Validated @PathVariable(value = "id") String usuarioId){
        return new ResponseEntity<>(usuarioService.obtenerUsuario(usuarioId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> insertUser(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.usuarioService.insertUser(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.usuarioService.updateUser(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
