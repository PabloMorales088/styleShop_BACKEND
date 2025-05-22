package com.styleshop.controller;

import com.styleshop.dto.PedidoDTO;
import com.styleshop.mapper.PedidoMapper;
import com.styleshop.model.Pedido;
import com.styleshop.model.Usuario;
import com.styleshop.repository.PedidoRepository;
import com.styleshop.service.PedidoService;
import com.styleshop.util.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    /**
     * Confirma un nuevo pedido para el usuario autenticado.
     */
    @PostMapping(value = "/confirmar", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> confirmarPedido() {
        try {
            Usuario usuario = authenticatedUserProvider.getAuthenticatedUser();
            pedidoService.confirmarPedido(usuario.getId());
            return ResponseEntity.ok("Pedido realizado con éxito");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al confirmar pedido");
        }
    }

    /**
     * Devuelve los pedidos del usuario autenticado.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PedidoDTO>> listarPedidosPorUsuario() {
        Usuario usuario = authenticatedUserProvider.getAuthenticatedUser();
        List<Pedido> pedidos = pedidoRepository.findByUsuarioIdOrderByFechaDesc(usuario.getId());
        List<PedidoDTO> dtos = pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
