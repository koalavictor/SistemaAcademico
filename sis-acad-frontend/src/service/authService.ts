import axios from './api';  // importar instância do axios já configurada

// Dados que serão enviados para o backend no login
export interface LoginData {
  username: string;
  password: string;
}

// Função que envia os dados de login para o backend
export async function login(data: LoginData) {
  // Faz POST para /auth/login (endpoint do backend)
  const response = await axios.post('/auth/login', data);

  // Espera um JSON com { token: string, user: {...} }
  return response.data;
}

// Função para limpar o token (logout)
export function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
}
