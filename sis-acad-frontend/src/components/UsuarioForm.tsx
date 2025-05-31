'use client'

import { useState } from 'react'

interface UsuarioFormProps {
  tipo: string
  onSuccess?: () => void
}

export default function UsuarioForm({ tipo, onSuccess }: UsuarioFormProps) {
  const [nome, setNome] = useState('')
  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    setError('')
    setSuccess('')

    try {
      const res = await fetch('http://localhost:8080/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email, senha, tipo }),
      })

      if (!res.ok) {
        const data = await res.json()
        throw new Error(data.message || 'Erro ao cadastrar usuário')
      }

      setSuccess('Admin cadastrado com sucesso!')
      setNome('')
      setEmail('')
      setSenha('')

      if (onSuccess) onSuccess()
    } catch (err: any) {
      setError(err.message || 'Erro inesperado')
    }
  }

  return (
    <form onSubmit={handleSubmit} style={{ maxWidth: 400, margin: 'auto' }}>
      <h2>Cadastrar {tipo}</h2>
      <div>
        <label>Nome:</label><br />
        <input
          type="text"
          value={nome}
          onChange={e => setNome(e.target.value)}
          required
          maxLength={100}
        />
      </div>
      <div style={{ marginTop: 10 }}>
        <label>Email:</label><br />
        <input
          type="email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          required
        />
      </div>
      <div style={{ marginTop: 10 }}>
        <label>Senha:</label><br />
        <input
          type="password"
          value={senha}
          onChange={e => setSenha(e.target.value)}
          required
        />
      </div>

      {error && <p style={{ color: 'red' }}>{error}</p>}
      {success && <p style={{ color: 'green' }}>{success}</p>}

      <button type="submit" style={{ marginTop: 20 }}>
        Cadastrar
      </button>
    </form>
  )
}
