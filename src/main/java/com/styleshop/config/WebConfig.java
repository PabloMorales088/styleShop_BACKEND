package com.styleshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapea las solicitudes que empiezan con /imagenes/ a archivos del sistema en la carpeta "uploads"
        // Esto permite servir imágenes estáticas almacenadas fuera del classpath
        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:uploads/"); // La ruta debe ser absoluta o con el prefijo "file:"
    }
}
