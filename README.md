# TiendaGenérica - Microservicios

Sistema de gestión de tienda genérica con arquitectura de microservicios distribuidos.

## 📂 Estructura

```
tiendaGenerica-sisdistribuidos-backend/
├── backend-service/          # API Gateway (8088)
├── cliente-service/          # Microservicio Cliente (8089)
├── usuario-service/          # Microservicio Usuario (8090)
├── proveedor-service/        # Microservicio Proveedor (8091)
├── producto-service/         # Microservicio Producto (8092)
├── venta-service/            # Microservicio Venta (8093)
└── detalle-venta-service/    # Microservicio Detalle Venta (8094)
```

## 🚀 Ejecución

Cada servicio ejecuta independientemente:

```bash
cd [servicio]
mvn clean install
mvn spring-boot:run
```

### Puertos

| Servicio | Puerto |
|----------|--------|
| Backend (API Gateway) | 8088 |
| Cliente | 8089 |
| Usuario | 8090 |
| Proveedor | 8091 |
| Producto | 8092 |
| Venta | 8093 |
| Detalle Venta | 8094 |

## 💾 Base de Datos

MySQL compartida - AWS RDS
- Host: `tienda-generica-db.c8z242ws0swx.us-east-1.rds.amazonaws.com`
- BD: `tienda_generica`

## 📝 Dependencias Externas

- `cliente-service` → ninguna
- `usuario-service` → ninguna  
- `proveedor-service` → ninguna
- `producto-service` → proveedor
- `venta-service` → cliente, usuario
- `detalle-venta-service` → producto, venta
- `backend-service` → todos

## 🔗 Endpoints

Accede a través del API Gateway (8088):

- `GET /cliente/listar`
- `GET /usuarios/listar`
- `GET /productos/listar`
- `GET /proveedores/listar`
- `GET /ventas/listar`
- `GET /detalle_ventas/listar`
