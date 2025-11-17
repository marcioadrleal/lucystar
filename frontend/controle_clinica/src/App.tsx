import { BrowserRouter } from 'react-router-dom';
import './App.css'
import AppRoutes from './routes/AppRoutes';
import logo from "./assets/logo.png";

function App() {
  return (
    <div className="app">
      <header className="navbar">
        <div className="logo">
          <img src={logo} alt="Logo Wr102" width="200" />
          <h1>Wr 102</h1>
        </div>
      </header>
      <main className="login-container">
        <BrowserRouter>
          <AppRoutes />
        </BrowserRouter>
      </main>
      
    </div>
  );
}

export default App
