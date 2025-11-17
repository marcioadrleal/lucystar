import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { IMaskInput } from "react-imask";
import "./Cadastro.css";
import { BuscaCep } from "./SearchCepFunctions";
import type { Endereco } from "./endereco";

function Cadastro() {
  const [formData, setFormData] = useState({
    senha: "",
    confirmaSenha: "",
    nome: "",
    email: "",
    cpf: "",
    dataNascimento: "",
    endereco: "",
    numero: "",
    bairro: "",
    cidade: "",
    estado: "",
    cep: "",
    celular: "",
    telefone: "",
  });

  const navigate = useNavigate();

  // Atualiza qualquer campo do formulário
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleCepChange = async (e: any) => {
    const { value } = e.target;
    const cep: string = value.replace("-", "");
    if (cep.length === 8) {
      try {
        const address: Endereco = await BuscaCep(cep);
        if (address.logradouro === undefined) {
          alert("CEP inválido ou não encontrado!");
          setFormData( (prev) => ({
            ...prev,
            cep: '' ,
            endereco:'', 
            bairro: '',
            cidade: '',
            estado: '',

        }));
        } else {

          setFormData((prev) => ({
            ...prev,
            cep: address.cep,
            endereco: address.logradouro, // ✅ Aqui vai para o input "Endereço"
            bairro: address.bairro,
            cidade: address.localidade,
            estado: address.uf,
          }));


        }
        // aqui você pode atualizar o estado do formulário, por exemplo:
        // setFormData({ ...formData, ...address });
      } catch (error: any) {
        console.error("Erro ao buscar CEP:", error.message);
        alert("CEP inválido ou não encontrado!");
      }
    }

  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // Valida campos obrigatórios
    if (
      !formData.nome ||
      !formData.email ||
      !formData.dataNascimento ||
      !formData.celular
    ) {
      alert("Preencha todos os campos obrigatórios!");
      return;
    }

    // Recupera lista de usuários
    const usuarios = JSON.parse(localStorage.getItem("usuarios") || "[]");

    // Verifica duplicidade de e-mail
    const existe = usuarios.some((u: any) => u.email === formData.email);
    if (existe) {
      alert("Este e-mail já está cadastrado!");
      return;
    }

    // Adiciona novo usuário
    usuarios.push(formData);
    localStorage.setItem("usuarios", JSON.stringify(usuarios));

    alert("Cadastro realizado com sucesso!");
    navigate("/"); // Redireciona para login
  };

  return (




      <section className="login-card">
        <h2>Cadastro de Usuário</h2>
        <p className="subtitle">Preencha seus dados para criar sua conta ✨</p>

        <form onSubmit={handleSubmit}>
          {/* NOME */}
          <div className="form-group">
            <label htmlFor="nome">Nome *</label>
            <input
              id="nome"
              name="nome"
              type="text"
              placeholder="Seu nome completo"
              required
              value={formData.nome}
              onChange={handleChange}
              style={{ width: "100%" }}
            />
          </div>

          {/* EMAIL */}
          <div className="form-group">
            <label htmlFor="email">E-mail *</label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="seu@exemplo.com"
              required
              value={formData.email}
              onChange={handleChange}
              style={{ width: "100%" }}
            />
          </div>

          {/* CPF */}
          <div className="form-group">
            <label htmlFor="cpf">CPF</label>
            <IMaskInput
              id="cpf"
              type="text"
              name="cpf"
              mask="000.000.000-00"
              placeholder="000.000.000-00"
              value={formData.cpf}
              onChange={handleChange}
              style={{ width: "30%" }}
            />
          </div>

          {/* DATA NASCIMENTO */}
          <div className="form-group">
            <label htmlFor="dataNascimento">Data de Nascimento *</label>
            <input
              id="dataNascimento"
              name="dataNascimento"
              type="date"
              required
              value={formData.dataNascimento}
              onChange={handleChange}
            />
          </div>

          {/* CEP */}
          <div className="form-group">
            <label htmlFor="cep">CEP</label>
            <IMaskInput
              id="cep"
              name="cep"
              type="text"
              mask="00000-000"
              placeholder="00000-000"
              value={formData.cep}
              onChange={handleChange}
              style={{ width: "20%" }}
              onAccept={(value: any) =>
                handleCepChange({ target: { name: "cep", value } })
              }
            />
          </div>

          {/* ENDEREÇO */}
          <div className="form-group">
            <label htmlFor="endereco">Endereço</label>
            <input
              id="endereco"
              name="endereco"
              type="text"
              placeholder="Rua / Avenida"
              value={formData.endereco}
              onChange={handleChange}
              style={{ width: "100%" }}
              disabled
            />
          </div>

          {/* NÚMERO */}
          <div className="form-group">
            <label htmlFor="numero">Número</label>
            <input
              id="numero"
              name="numero"
              type="text"
              placeholder="123"
              value={formData.numero}
              onChange={handleChange}
              style={{ width: "20%" }}
            />
          </div>

          {/* BAIRRO */}
          <div className="form-group">
            <label htmlFor="bairro">Bairro</label>
            <input
              id="bairro"
              name="bairro"
              type="text"
              placeholder="Centro"
              value={formData.bairro}
              onChange={handleChange}
              style={{ width: "100%" }}
              disabled
            />
          </div>

          {/* CIDADE */}
          <div className="form-group">
            <label htmlFor="cidade">Cidade</label>
            <input
              id="cidade"
              name="cidade"
              type="text"
              placeholder="São Paulo"
              value={formData.cidade}
              onChange={handleChange}
              style={{ width: "100%" }}
              disabled
            />
          </div>

          {/* ESTADO */}
          <div className="form-group">
            <label htmlFor="estado">Estado</label>
            <input
              id="estado"
              name="estado"
              type="text"
              placeholder="SP"
              value={formData.estado}
              onChange={handleChange}
              size={2}
              maxLength={2}
              style={{ width: "10%" }}
              disabled
            />
          </div>

          {/* CELULAR */}
          <div className="form-group">
            <label htmlFor="celular">Celular *</label>
            <IMaskInput
              id="celular"
              name="celular"
              type="text"
              mask="(00) 0 0000-0000"
              placeholder="(11) 9 9999-9999"
              required
              value={formData.celular}
              onChange={handleChange}
              style={{ width: "30%" }}
            />
          </div>

          {/* TELEFONE */}
          <div className="form-group">
            <label htmlFor="telefone">Telefone Residencial</label>
            <IMaskInput
              id="telefone"
              name="telefone"
              type="text"
              mask="(00) 0000-0000"
              placeholder="(11) 3333-4444"
              value={formData.telefone}
              onChange={handleChange}
              style={{ width: "30%" }}
            />
          </div>

          {/* SENHA */}
          <div className="form-group">
            <label htmlFor="senha">Senha *</label>
            <input
              id="senha"
              name="senha"
              type="password"
              placeholder="123456"
              value={formData.senha}
              onChange={handleChange}
              size={10}
              maxLength={10}
              required
              style={{ width: "30%" }}
            />
          </div>           
          {/* CONFIRMA SENHA */}
          <div className="form-group">
            <label htmlFor="confirmaSenha">Confirma Senha *</label>
            <input
              id="confirmaSenha"
              name="confirmaSenha"
              type="password"
              placeholder="123456"
              value={formData.confirmaSenha}
              onChange={handleChange}
              size={10}
              maxLength={10}
              required
              style={{ width: "30%" }}
            />
          </div>           


          <button type="submit" className="btn-primary">
            Cadastrar
          </button>

          <p className="register">
            Já tem conta? <Link to="/">Voltar ao Login</Link>
          </p>
        </form>
      </section>
  

  );
}

export default Cadastro;
