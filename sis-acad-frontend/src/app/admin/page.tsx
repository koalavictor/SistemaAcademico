import Link from 'next/link'

export default function AdminPage() {
  return (
    <main>
      <h1>Painel Admin</h1>
      <Link href="/admin/listar">Listar Administradores</Link>
    </main>
  )
}
