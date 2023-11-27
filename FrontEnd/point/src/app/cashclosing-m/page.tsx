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
    const [filteredBalanceamentos, setFilteredBalanceamentos] = useState<Balanceamento[]>([]);
    const [filtroTipo, setFiltroTipo] = useState<string | null>(null);
    const [filtroDataInicio, setFiltroDataInicio] = useState<string>('');
    const [filtroDataFim, setFiltroDataFim] = useState<string>('');
    const [pageNumber, setPageNumber] = useState(0);
    const itemsPerPage = 10;

    useEffect(() => {
        axios.get('http://localhost:8080/api/movimentacaoEstoque/relatorio')
            .then(response => {
                const data = response.data;
                setBalanceamentos(data);
                setFilteredBalanceamentos(data);
            })
            .catch(error => {
                console.error('Erro ao obter balanceamento:', error);
            });
    }, []);

    useEffect(() => {
        let filteredData = balanceamentos;

        if (filtroTipo) {
            filteredData = filteredData.filter(balanceamento => balanceamento.tipo === filtroTipo);
        }

        if (filtroDataInicio && filtroDataFim) {
            filteredData = filteredData.filter(balanceamento =>
                balanceamento.data >= filtroDataInicio && balanceamento.data <= filtroDataFim
            );
        }

        setFilteredBalanceamentos(filteredData);
    }, [filtroTipo, filtroDataInicio, filtroDataFim, balanceamentos]);

    const pageCount = Math.ceil(filteredBalanceamentos.length / itemsPerPage);

    const handlePageChange = (newPage: number) => {
        setPageNumber(newPage);
    };

    const filteredBalanceamentosPaginated = filteredBalanceamentos.slice(
        pageNumber * itemsPerPage,
        (pageNumber + 1) * itemsPerPage
    );

    return (
        <div className="w-full mt-8">
            <div className="flex space-x-4 items-center mb-4">
                <label className="text-gray-600">Tipo:</label>
                <select
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    onChange={(e) => setFiltroTipo(e.target.value)}
                >
                    <option value="">Todos</option>
                </select>
                <label className="text-gray-600">Data Inicial:</label>
                <input
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    type="date"
                    value={filtroDataInicio}
                    onChange={(e) => setFiltroDataInicio(e.target.value)}
                />
                <label className="text-gray-600">Data Final:</label>
                <input
                    className="border rounded-md px-2 py-1 focus:outline-none"
                    type="date"
                    value={filtroDataFim}
                    onChange={(e) => setFiltroDataFim(e.target.value)}
                />
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
                    {filteredBalanceamentosPaginated.map((balanceamento, index) => (
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
            <div className="flex items-center space-x-4 mt-4">
                {Array.from({ length: pageCount }).map((_, index) => (
                    <button
                        key={index}
                        onClick={() => handlePageChange(index)}
                        className={`border rounded-md px-3 py-1 focus:outline-none ${pageNumber === index ? 'bg-blue-500 text-white' : 'text-blue-500'}`}
                    >
                        {index + 1}
                    </button>
                ))}
            </div>
        </div>
    );
}

export default CashCLoM;