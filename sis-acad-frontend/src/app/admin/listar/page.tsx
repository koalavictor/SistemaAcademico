'use client'

import React, { useEffect, useState } from 'react'
import Link from 'next/link'
import { listarAdmins } from '@/service/usuarioService'
import { Usuario } from '@/models/usuario'  // ou '@/models/usuario'


export default function ListarAdminsPage() {
  const [admins, setAdmins] = useState<Usuario[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    listarAdmins()
      .then(data => setAdmins(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [])

  return (
    <main style={{ padding: 20 }}>
      <h1>Lista de Administradores</h1>

      {/* Botão para ir para a tela de cadastro */}
      <Link href="/admin/cadastrar">
        <button style={{ marginBottom: 20 }}>➕ Cadastrar Novo Admin</button>
      </Link>

      {loading && <p>Carregando...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {!loading && !error && (
        admins.length > 0 ? (
          <ul>
            {admins.map(admin => (
              <li key={admin.email}>
                <strong>{admin.nome}</strong> &mdash; {admin.email}
              </li>
            ))}
          </ul>
        ) : (
          <p>Nenhum administrador encontrado.</p>
        )
      )}
    </main>
  )
}
