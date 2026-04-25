FROM nginx:alpine

# Copiar configuración de nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf

# Exponer puerto
EXPOSE 8090

# Comando por defecto
CMD ["nginx", "-g", "daemon off;"]
