// src/service/api.ts
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // ajuste a URL conforme necessário
  timeout: 10000,
  // outras configurações...
});

export default api;
