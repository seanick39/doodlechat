FROM node:21.5.0
WORKDIR /app

COPY . .

RUN npm install \
    && npm install react-scripts@5.0.1 --location=global
CMD ["npm", "run", "start"]
