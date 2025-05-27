'use client'

import { createContext, useState, useContext, ReactNode, useEffect } from 'react';

// Interface para os dados do usuário
interface User {
  username: string;
  // outros campos que quiser salvar do usuário
}

// Interface do contexto de autenticação
interface AuthContextType {
  user: User | null; // usuário logado (ou null se deslogado)
  token: string | null; // token JWT
  login: (token: string, user: User) => void; // função para logar
  logout: () => void; // função para deslogar
}

// Criando o contexto
const AuthContext = createContext<AuthContextType | undefined>(undefined);

// Provider que envolve a aplicação
export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);

  // Ao montar o componente, tenta carregar dados do localStorage
  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const storedUser = localStorage.getItem('user');
    if (storedToken && storedUser) {
      setToken(storedToken);
      setUser(JSON.parse(storedUser));
    }
  }, []);

  // Função para fazer login (salva token e user no estado e localStorage)
  function login(token: string, user: User) {
    setToken(token);
    setUser(user);
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(user));
  }

  // Função para logout (limpa token e user)
  function logout() {
    setToken(null);
    setUser(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  return (
    <AuthContext.Provider value={{ user, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

// Hook para usar o contexto com mais facilidade
export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth deve ser usado dentro de AuthProvider');
  return context;
}
