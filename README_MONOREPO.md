# TiendaGenérica - Arquitectura Monorepo con Microservicios

Este repositorio utiliza una estructura **monorepo** que contiene múltiples módulos independientes, incluyendo microservicios.

## 📁 Estructura de Carpetas

```
tiendaGenerica-sisdistribuidos-backend/
├── pom.xml                          # POM padre que define los módulos
├── backend-service/                 # Módulo del servicio principal
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/gestion/tiendag/
│   │   │   │   ├── TiendaGenericaGrupo10BackendApplication.java
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── exception/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── mvnw
│
├── proveedor-service/               # Microservicio independiente de Proveedores
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/gestion/proveedor/
│   │   │   │   ├── ProveedorServiceApplication.java
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── exception/
│   │   │   │   ├── model/
│   │   │   │   └── repository/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── mvnw
│
├── mvnw                             # Maven Wrapper
├── mvnw.cmd
├── .mvn/
└── README.md
```

## 🚀 Ejecución

### Opción 1: Construir toda la estructura desde la raíz

```bash
cd /workspaces/tiendaGenerica-sisdistribuidos-backend
mvn clean install
```

### Opción 2: Ejecutar cada servicio independientemente

**Microservicio de Proveedores (Puerto 8081):**
```bash
cd proveedor-service
mvn spring-boot:run
```

**Servicio Principal (Puerto 8080):**
```bash
cd backend-service
mvn spring-boot:run
```

### Opción 3: Ejecutar en paralelo (Terminal 1 y 2)

**Terminal 1:**
```bash
cd /workspaces/tiendaGenerica-sisdistribuidos-backend/proveedor-service
mvn spring-boot:run
```

**Terminal 2:**
```bash
cd /workspaces/tiendaGenerica-sisdistribuidos-backend/backend-service
mvn spring-boot:run
```

## 📋 Base de Datos

Necesitas crear dos bases de datos:

```sql
CREATE DATABASE tienda_db;
CREATE DATABASE proveedor_db;
```

## 🔗 Endpoints Principales

**Servicio Principal (8080):**
- `GET http://localhost:8080/clientes/listar`
- `GET http://localhost:8080/productos/listar`
- `GET http://localhost:8080/proveedores/listar`

**Microservicio Proveedores (8081):**
- `GET http://localhost:8081/proveedores/listar`
- `POST http://localhost:8081/proveedores/guardar`
- `GET http://localhost:8081/proveedores/buscar/{id}`
- `PUT http://localhost:8081/proveedores/actualizar/{id}`
- `DELETE http://localhost:8081/proveedores/eliminar/{id}`

## ⚙️ Ventajas de esta Estructura

✅ **Monorepo**: Todo el código en un único repositorio
✅ **Independencia**: Cada microservicio puede ejecutarse por separado
✅ **Maven Hierarchical**: El pom.xml padre gestiona versiones comunes
✅ **Escalabilidad**: Fácil agregar nuevos módulos/microservicios
✅ **Separación de responsabilidades**: Cada módulo tiene su propia BD y lógica

## 📝 Notas

- El servicio principal ahora consume el microservicio de Proveedores mediante HTTP
- El cliente HTTP se encuentra en `backend-service/src/main/java/com/gestion/tiendag/service/ProveedorServiceClient.java`
- Cada módulo tiene su propio `application.properties` con configuración independiente
