FROM node:21.5.0
WORKDIR /app

COPY package.json .
COPY package-lock.json .

RUN npm install
CMD ["npm", "run", "start"]
