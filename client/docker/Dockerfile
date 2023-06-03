FROM node:16.16.0-alpine as build
COPY . /app
WORKDIR /app
RUN npm install
RUN npm run build

FROM nginx:1.21.1-alpine
EXPOSE 3000
COPY --from=build /app/dist /usr/share/nginx/html
COPY docker/nginx.conf /etc/nginx/nginx.conf