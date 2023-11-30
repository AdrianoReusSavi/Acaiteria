import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Item {
  id: number;
  nome: string;
  status: string;
  data: number;
  qtdvenda: number;
  total: number;
  // Adicione outras propriedades conforme necessário
}

const ApiPage: React.FC = () => {
  const [data, setData] = useState<Item[] | null>(null);
  const [report, setReport] = useState<number | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<Item[]>('URL_DA_API'); // URL DA API
        setData(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h1>Dados</h1>
      {data && (
        <ul>
          {data.map(item => (
            <li key={item.id}>{item.nome}</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ApiPage;
