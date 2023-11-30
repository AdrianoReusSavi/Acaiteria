"use client"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ReportTableMonthly.css';


// Defina a interface para os dados que você espera da API
interface Item {
  id: number;
  qtdvenda: number;
  lucrobruto: number;
  lucro: number;
  dinheiro: number;
  pix: number;
  credito: number;
  debito: number;
}

// Componente funcional principal
const Relatorio: React.FC = () => {
  // Estado para armazenar os dados da API
  const [dados, setDados] = useState<Item[] | null>(null);

  // Efeito para buscar os dados da API ao montar o componente
  useEffect(() => {
    const fetchData = async () => {
      try {
        // Substitua 'URL_DA_API' pela URL real da sua API
        const response = await axios.get('URL_DA_API').then(response => console.log(response)); //COLOCAR URL DA API
        setDados(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
      }
    };

    fetchData();
  }, []); // O segundo argumento vazio garante que o efeito é executado apenas uma vez ao montar o componente

  // Renderização condicional com base nos dados da API
  return (
    <div>
      
      {dados ? (
        <ul>
          {dados.map((item) => (
            <li key={item.id}>{item.qtdvenda}</li>
          
            // Adicione outras propriedades conforme necessário
          ))}
        </ul>
      ) : (
        <p>Carregando dados...</p>
      )
};
    </div>
  )

export default Relatorio;
