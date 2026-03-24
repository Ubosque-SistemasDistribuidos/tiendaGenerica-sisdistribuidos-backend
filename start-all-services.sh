#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}🚀 Iniciando todos los servicios...${NC}\n"

# Kill any existing Java processes on these ports
echo "Limpiando puertos previos..."
for port in 8088 8089 8090 8091 8092 8093 8094; do
    pkill -f "port=$port" 2>/dev/null || true
done

sleep 2

# Start all services in background
cd /workspaces/tiendaGenerica-sisdistribuidos-backend

echo -e "${GREEN}✓ Backend Service (8088)${NC}"
java -jar backend-service/target/backend-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Cliente Service (8089)${NC}"
java -jar cliente-service/target/cliente-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Usuario Service (8090)${NC}"
java -jar usuario-service/target/usuario-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Proveedor Service (8091)${NC}"
java -jar proveedor-service/target/proveedor-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Producto Service (8092)${NC}"
java -jar producto-service/target/producto-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Venta Service (8093)${NC}"
java -jar venta-service/target/venta-service-0.0.1-SNAPSHOT.jar &

echo -e "${GREEN}✓ Detalle Venta Service (8094)${NC}"
java -jar detalle-venta-service/target/detalle-venta-service-0.0.1-SNAPSHOT.jar &

echo -e "\n${BLUE}Esperando que se inicien los servicios...${NC}"
sleep 5

echo -e "\n${GREEN}✅ Todos los servicios se han iniciado:${NC}"
echo "   - Backend: http://localhost:8088"
echo "   - Cliente: http://localhost:8089"
echo "   - Usuario: http://localhost:8090"
echo "   - Proveedor: http://localhost:8091"
echo "   - Producto: http://localhost:8092"
echo "   - Venta: http://localhost:8093"
echo "   - Detalle Venta: http://localhost:8094"
echo -e "\n${BLUE}Presiona Ctrl+C para detener todos los servicios${NC}"

# Wait for all background jobs
wait
