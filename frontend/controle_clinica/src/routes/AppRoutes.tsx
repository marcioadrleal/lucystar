import { Routes, Route, useLocation } from "react-router-dom";
import Login from "../pages/Login";
import Cadastro from "../pages/Cadastro/Cadastro";
import Painel from "../pages/Painel";

export function AppRoutes() {
  //const isLogged = !!localStorage.getItem("usuario");

  const location = useLocation();

  console.log("Rota atual:", location.pathname);

  return (
    
      <Routes>
        {/* Rota padrão → Login */}
        <Route path="/" element={<Login />} />

        {/* Cadastro */}
        <Route path="/cadastro" element={<Cadastro />} />

        {/* Painel (após login) */}
        <Route path="/painel" element={
          
          <Painel />
          
          } />
      </Routes>
    
  );
}

export default AppRoutes;