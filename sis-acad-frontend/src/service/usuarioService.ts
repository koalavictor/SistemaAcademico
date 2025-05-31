export async function cadastrarUsuario(usuario: {
  nome: string
  email: string
  senha: string
  tipo: string
}) {
  const res = await fetch('http://localhost:8080/usuarios', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(usuario),
  })

  if (!res.ok) {
    // Tenta ler o corpo do erro, pode ser texto ou JSON
    let errorMessage = 'Erro ao cadastrar usuário'
    try {
      const data = await res.json()
      errorMessage = data.message || JSON.stringify(data) || errorMessage
    } catch {
      // se não for JSON, tenta ler como texto
      const text = await res.text()
      if (text) errorMessage = text
    }
    throw new Error(errorMessage)
  }

  // Se o backend não retorna corpo, evite erro
  try {
    return await res.json()
  } catch {
    return null
  }
}

export async function listarAdmins() {
  const res = await fetch('http://localhost:8080/usuarios')
  if (!res.ok) {
    throw new Error('Erro ao buscar usuários')
  }
  const usuarios = await res.json()
  return usuarios.filter((u: { tipo: string }) => u.tipo === 'ADMIN')
}
