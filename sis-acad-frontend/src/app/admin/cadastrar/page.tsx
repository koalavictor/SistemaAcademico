'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import Link from 'next/link'
import { cadastrarUsuario } from '@/service/usuarioService'

export default function CadastrarAdminPage() {
  const router = useRouter()
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
      await cadastrarUsuario({ nome, email, senha, tipo: 'ADMIN' })
      setSuccess('Admin cadastrado com sucesso!')
      setNome(''); setEmail(''); setSenha('')
      // opcional: redirecionar automaticamente
      // router.push('/admin/listar')
    } catch (err: any) {
      setError(err.message)
    }
  }

  return (
    <main style={{ padding: 20, maxWidth: 400, margin: 'auto' }}>
      <h1>Cadastrar Admin</h1>

      {/* Link de volta à listagem */}
      <Link href="/admin/listar">
        <button style={{ marginBottom: 20 }}>🔙 Voltar à Lista</button>
      </Link>

      <form onSubmit={handleSubmit}>
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

        <button type="submit" style={{ marginTop: 20 }}>Cadastrar</button>
      </form>
    </main>
  )
}
