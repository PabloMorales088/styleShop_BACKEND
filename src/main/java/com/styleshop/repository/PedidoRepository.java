package com.styleshop.repository;

import com.styleshop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Consulta derivada: obtiene todos los pedidos de un usuario ordenados por fecha descendente (más recientes primero)
    List<Pedido> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
}
