'use client'
import React, { useState, useEffect } from "react";
import axios from 'axios';

interface Fechamento {
    categoria: string;
    totalCategoria: number;
}

const CashCLoM: React.FC = () => {
    const [fechamentos, setFechamentos] = useState<Fechamento[]>([]);
    const [dataRelatorio, setDataRelatorio] = useState<string>("");

    useEffect(() => {
        consultar();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const consultar = () => {
        const parametros = [];

        if (dataRelatorio) parametros.push(`dataRelatorio=${encodeURIComponent(dataRelatorio)}`);

        const url = `http://localhost:8080/api/movimentacaoEstoque/fechamento?${parametros.join('&')}`;

        axios.get(url)
            .then(response => {
                const data = response.data;
                setFechamentos(data);
            })
            .catch(error => {
                console.error('Erro ao obter fechamento:', error);
            });
    };

    return (
        <div className="w-full mt-8">
            <div className="flex space-x-4 items-center mb-4">
                <label className="text-gray-600">Data Inicial:</label>
                <input
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    type="date"
                    value={dataRelatorio}
                    onChange={(e) => setDataRelatorio(e.target.value)}
                />
                <button className="bg-red-400 hover:bg-gray-350 px-4 py-2 rounded mr-4" onClick={consultar}>Consultar</button>
            </div>
            <table className="w-full">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Categoria</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Total Categoria</th>
                    </tr>
                </thead>
                <tbody>
                    {fechamentos.map((fechamento, index) => (
                        <tr key={index} className={index % 2 === 0 ? 'bg-gray-100' : ''}>
                            <td className="p-4 border-r border-gray-300">{fechamento.categoria}</td>
                            <td className="p-4 border-r border-gray-300">{fechamento.totalCategoria ?? 0}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default CashCLoM;