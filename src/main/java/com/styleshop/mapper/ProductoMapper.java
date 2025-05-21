    package com.styleshop.mapper;

    import com.styleshop.dto.ProductoDTO;
    import com.styleshop.model.Categoria;
    import com.styleshop.model.Producto;

    public class ProductoMapper {

        public static Producto toEntity(ProductoDTO dto, Categoria categoria) {
            Producto p = new Producto();
            p.setNombre(dto.getNombre());
            p.setDescripcion(dto.getDescripcion());
            p.setPrecio(dto.getPrecio());
            p.setTalla(dto.getTalla());
            p.setStock(dto.getStock());
            p.setImagen(dto.getImagen());
            p.setCategoria(categoria);
            return p;
        }

        public static ProductoDTO toDTO(Producto p) {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setDescripcion(p.getDescripcion());
            dto.setPrecio(p.getPrecio());
            dto.setTalla(p.getTalla());
            dto.setStock(p.getStock());
            dto.setImagen(p.getImagen());
            dto.setCategoriaId(p.getCategoria() != null ? p.getCategoria().getId() : null);
            return dto;
        }



        public static void updateEntityFromDTO(ProductoDTO dto, Producto producto, Categoria categoria) {
            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
            producto.setPrecio(dto.getPrecio());
            producto.setTalla(dto.getTalla());
            producto.setStock(dto.getStock());
            producto.setImagen(dto.getImagen());
            producto.setCategoria(categoria);
        }
    }
