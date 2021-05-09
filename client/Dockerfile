FROM node:14-alpine as build
WORKDIR /app

# Copy the package.json as well as the package-lock.json and install
# the dependencies. This is a separate step so the dependencies
# will be cached unless changes to one of those two files
# are made.
COPY package.json package-lock.json ./
RUN npm install

# Copy the main application
COPY . ./

# Arguments
ARG REACT_APP_API_ROOT_ENDPOINT
ENV REACT_APP_API_ROOT_ENDPOINT=$REACT_APP_API_ROOT_ENDPOINT

ARG REACT_APP_AUTH0_CLIENT_ID
ENV REACT_APP_AUTH0_CLIENT_ID=$REACT_APP_AUTH0_CLIENT_ID

ARG REACT_APP_AUTH0_DOMAIN
ENV REACT_APP_AUTH0_DOMAIN=$REACT_APP_AUTH0_DOMAIN

FROM node:12.4.0-alpine
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
RUN npm install --silent
RUN npm install react-scripts@3.0.1 -g --silent
COPY . /app
RUN npm run build

# production environment
FROM nginx:1.16.0-alpine
COPY --from=build /app/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/conf.d
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]