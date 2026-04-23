# Docker Setup - Tienda Genérica Backend

## Descripción
Este proyecto contiene Dockerfiles para cada microservicio y un `docker-compose.yml` para orquestar todos los contenedores juntos con una base de datos MySQL.

## Estructura de Contenedores

| Servicio | Puerto | Contenedor |
|----------|--------|-----------|
| Backend Service | 8080 | backend-service |
| Cliente Service | 8081 | cliente-service |
| Usuario Service | 8082 | usuario-service |
| Proveedor Service | 8083 | proveedor-service |
| Producto Service | 8084 | producto-service |
| Venta Service | 8085 | venta-service |
| Detalle Venta Service | 8086 | detalle-venta-service |
| MySQL Database | 3306 | mysql-db |

## Requisitos Previos

- Docker >= 20.10
- Docker Compose >= 1.29

## Instalación

1. **Copiar archivo de configuración:**
```bash
cp .env.example .env
```

2. **Editar el archivo .env (opcional):**
```bash
nano .env
```

## Uso

### Iniciar todos los servicios:
```bash
docker-compose up -d
```

### Ver logs de los servicios:
```bash
docker-compose logs -f
```

### Ver logs de un servicio específico:
```bash
docker-compose logs -f backend-service
```

### Detener todos los servicios:
```bash
docker-compose down
```

### Detener y eliminar volúmenes (base de datos):
```bash
docker-compose down -v
```

### Rebuild sin caché:
```bash
docker-compose up --build --no-cache
```

## Construir una imagen individual

Para construir la imagen de un servicio específico:
```bash
docker build -t usuario-service:latest ./usuario-service
```

## Verificar Estado de los Contenedores

```bash
docker-compose ps
```

## Acceder a la Base de Datos

```bash
docker exec -it mysql-db mysql -u root -p
# Contraseña: root
```

## Configuración de Red

Todos los servicios están conectados a una red de Docker personalizada `tienda-network`, lo que permite que se comuniquen entre sí usando el nombre del servicio como hostname.

Ejemplo: Para conectarse desde `usuario-service` a `cliente-service`:
```
http://cliente-service:8080
```

## Notas Importantes

- Todos los Dockerfiles utilizan un build multi-stage para optimizar el tamaño de las imágenes
- Java 11 es la versión utilizada en todas las imágenes
- Las imágenes base son: `maven:3.8.4-openjdk-11` para build y `openjdk:11-jre-slim` para runtime
- Los puertos internos de todos los servicios son 8080, pero están mapeados a diferentes puertos en el host

## Solución de Problemas

### Los contenedores no inician

1. Verificar los logs: `docker-compose logs`
2. Revisar que los puertos no estén en uso
3. Asegurar que Docker tiene suficiente memoria

### Error de conexión a base de datos

1. Verificar que el contenedor MySQL está corriendo: `docker-compose ps`
2. Verificar que las variables de entorno están correctas en `.env`
3. Esperar a que MySQL esté completamente inicializado (puede tardar unos segundos)

### Reconstruir una imagen sin usar caché

```bash
docker-compose build --no-cache nombre-servicio
```
