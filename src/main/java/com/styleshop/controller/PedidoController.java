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
@RequestMapping("/api/pedidos") // Ruta base para operaciones sobre pedidos
@RequiredArgsConstructor // Inyección automática de dependencias vía constructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    /**
     * POST /api/pedidos/confirmar
     * Crea un nuevo pedido para el usuario autenticado,
     * validando stock y vaciando el carrito después de confirmar.
     */
    @PostMapping(value = "/confirmar", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> confirmarPedido() {
        try {
            // Obtiene al usuario autenticado desde el contexto de seguridad
            Usuario usuario = authenticatedUserProvider.getAuthenticatedUser();

            // Ejecuta el proceso de confirmación de pedido
            pedidoService.confirmarPedido(usuario.getId());

            return ResponseEntity.ok("Pedido realizado con éxito");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Para diagnóstico en consola (debería sustituirse por logging)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al confirmar pedido");
        }
    }

    /**
     * GET /api/pedidos
     * Devuelve la lista de pedidos ordenados por fecha (más recientes primero)
     * pertenecientes al usuario autenticado.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PedidoDTO>> listarPedidosPorUsuario() {
        Usuario usuario = authenticatedUserProvider.getAuthenticatedUser();

        // Recupera los pedidos ordenados por fecha descendente
        List<Pedido> pedidos = pedidoRepository.findByUsuarioIdOrderByFechaDesc(usuario.getId());

        // Convierte la lista de entidades Pedido a una lista de DTOs
        List<PedidoDTO> dtos = pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
