import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const usuarios = JSON.parse(localStorage.getItem("usuarios") || "[]");
    const usuarioValido = usuarios.find(
      (u: any) => u.email === email && u.senha === password
    );

    if (usuarioValido) {
      alert(`Bem-vindo(a), ${usuarioValido.nome}!`);
      localStorage.setItem("usuarioLogado", JSON.stringify(usuarioValido));
      navigate("/painel");
    } else {
      alert("E-mail ou senha incorretos!");
    }
  };

  return (
    <div className="app">
      

     
        <section className="login-card">
          <h2>Bem-vindo</h2>
          <p className="subtitle">
            Acesse sua conta ✨
          </p>

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="email">E-mail</label>
              <input
                id="email"
                type="email"
                placeholder="seu@exemplo.com"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Senha</label>
              <input
                id="password"
                type="password"
                placeholder="••••••••"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>

            <div className="options">
              <label>
                <input type="checkbox" /> Lembrar-me
              </label>
              <a href="#">Esqueci a senha</a>
            </div>

            <button type="submit" className="btn-primary">
              Entrar
            </button>

            <p className="register">
              Ainda não tem conta?{" "}
              <Link to="/cadastro">Cadastre-se</Link>
            </p>
          </form>
        </section>
     
    </div>
  );
}

export default Login;
