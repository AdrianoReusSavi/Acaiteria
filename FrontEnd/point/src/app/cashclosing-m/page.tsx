'use client'
import React, { useState, useEffect } from "react";
import axios from 'axios';

interface Balanceamento {
    data: string;
    nomeItem: string;
    tipo: string;
    qtdCompras: number;
    qtdVendas: number;
    lucroBruto: number;
    lucro: number;
}

const CashCLoM: React.FC = () => {
    const [balanceamentos, setBalanceamentos] = useState<Balanceamento[]>([]);
    const [tipo, setTipo] = useState<string>("");
    const [dataInicial, setDataInicial] = useState<string>("");
    const [dataFinal, setDataFinal] = useState<string>("");

    useEffect(() => {
        consultar();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const consultar = () => {
        const parametros = [];

        if (dataInicial) parametros.push(`dataInicial=${encodeURIComponent(dataInicial)}`);
        if (dataFinal) parametros.push(`dataFinal=${encodeURIComponent(dataFinal)}`);
        if (tipo) parametros.push(`tipo=${encodeURIComponent(tipo)}`);

        const url = `http://localhost:8080/api/movimentacaoEstoque/balanceamento?${parametros.join('&')}`;

        axios.get(url)
            .then(response => {
                const data = response.data;
                setBalanceamentos(data);
            })
            .catch(error => {
                console.error('Erro ao obter balanceamento:', error);
            });
    };

    return (
        <div className="w-full mt-8">
            <div className="flex space-x-4 items-center mb-4">
                <label className="text-gray-600">Tipo:</label>
                <select
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    onChange={(e) => setTipo(e.target.value)}
                >
                    <option value="">Todos</option>
                    <option value="ENTRADA">Entrada</option>
                    <option value="SAIDA">Saída</option>
                </select>
                <label className="text-gray-600">Data Inicial:</label>
                <input
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    type="date"
                    value={dataInicial}
                    onChange={(e) => setDataInicial(e.target.value)}
                />
                <label className="text-gray-600">Data Final:</label>
                <input
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    type="date"
                    value={dataFinal}
                    onChange={(e) => setDataFinal(e.target.value)}
                />
                <button className="bg-red-400 hover:bg-gray-350 px-4 py-2 rounded mr-4" onClick={consultar}>Consultar</button>
            </div>
            <table className="w-full">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Data</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Produto</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Movimentação</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Quantidade Compras</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Quantidade Vendas</th>
                        <th className="p-4 text-left border-r border-gray-300 w-1/7">Lucro Bruto</th>
                        <th className="p-4 text-left w-1/7">Lucro</th>
                    </tr>
                </thead>
                <tbody>
                    {balanceamentos.map((balanceamento, index) => (
                        <tr key={index} className={index % 2 === 0 ? 'bg-gray-100' : ''}>
                            <td className="p-4 border-r border-gray-300">{balanceamento.data}</td>
                            <td className="p-4 border-r border-gray-300">{balanceamento.nomeItem}</td>
                            <td className="p-4 border-r border-gray-300">{balanceamento.tipo}</td>
                            <td className="p-4 border-r border-gray-300">{balanceamento.qtdCompras}</td>
                            <td className="p-4 border-r border-gray-300">{balanceamento.qtdVendas}</td>
                            <td className="p-4 border-r border-gray-300">
                                {balanceamento.lucroBruto.toLocaleString('pt-BR', {
                                    style: 'currency',
                                    currency: 'BRL',
                                })}
                            </td>
                            <td className="p-4 border-r border-gray-300">
                                {balanceamento.lucro.toLocaleString('pt-BR', {
                                    style: 'currency',
                                    currency: 'BRL',
                                })}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default CashCLoM;