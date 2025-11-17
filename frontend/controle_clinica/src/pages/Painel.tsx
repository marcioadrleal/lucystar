// src/pages/Painel.tsx
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../App.css";

function Painel() {
  const navigate = useNavigate();

  useEffect(() => {
    const usuarioLogado = localStorage.getItem("usuarioLogado");
    if (!usuarioLogado) {
      alert("Você precisa estar logado para acessar esta página!");
      navigate("/");
    }
  }, [navigate]);

  const usuario = JSON.parse(localStorage.getItem("usuarioLogado") || "{}");

  const logout = () => {
    localStorage.removeItem("usuarioLogado");
    navigate("/");
  };

  return (
    <div className="app">
      <header className="navbar">
        <div className="logo">
          <span className="logo-mark">CE</span>
          <h1>Painel da Clínica</h1>
        </div>

        <nav className="menu">
          <ul>
            <li><a href="#">Agendamentos</a></li>
            <li><a href="#">Clientes</a></li>
            <li><a href="#">Relatórios</a></li>
            <li><button className="menu-btn" onClick={logout}>Sair</button></li>
          </ul>
        </nav>
      </header>

      <main className="login-container">
        <section className="login-card">
          <h2>Bem-vindo(a), {usuario.nome} 👋</h2>
          <p className="subtitle">
            Aqui é sua área interna da Clínica Estética.
          </p>

          <p style={{ marginTop: "2rem" }}>
            Em breve você verá aqui o painel completo com agendamentos, estoque, relatórios e muito mais!
          </p>
        </section>
      </main>
    </div>
  );
}

export default Painel;
