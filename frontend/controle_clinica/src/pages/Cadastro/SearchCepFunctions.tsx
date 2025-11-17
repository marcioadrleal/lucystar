import type { Endereco } from "./endereco";

export async function BuscaCep( cep : string) : Promise<Endereco>{
  const res = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
  const data: Endereco = await res.json();
  return data;
}