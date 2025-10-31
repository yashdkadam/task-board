export default function Header({ user, onLogout }) {
  return (
    <header className="bg-white shadow p-4 flex justify-between items-center">
      <h1 className="text-xl font-bold">Task Board (API)</h1>
      <div className="flex items-center gap-4">
        <div className="text-sm">{user?.username} ({user?.email})</div>
        <button onClick={onLogout} className="py-1 px-3 rounded border">Logout</button>
      </div>
    </header>
  );
}