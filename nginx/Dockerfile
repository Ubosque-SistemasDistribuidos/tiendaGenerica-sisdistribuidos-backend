FROM nginx:alpine

# Copiar configuración de nginx
COPY nginx/ /etc/nginx/conf.d/

# Exponer puerto
EXPOSE 8090

# Comando por defecto
CMD ["nginx", "-g", "daemon off;"]
