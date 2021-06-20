FROM node:16-alpine as build

WORKDIR /app
COPY . .

COPY package.json /app/package.json
RUN npm install --silent
RUN npm install react-scripts@3.0.1 -g --silent
COPY . /app
RUN npm run build


# production environment
FROM nginx:1.19.10-alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY /assets /usr/share/nginx/html/assets
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/conf.d
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]