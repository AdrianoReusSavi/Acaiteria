'use client'
import { TableContainer, Table, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material'
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
            <TableContainer component={Paper} sx={{ maxHeight: '800px' }}>
                <Table aria-label='simple table' stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell className='column'>Data</TableCell>
                            <TableCell className='column'>Produto</TableCell>
                            <TableCell className='column'>Movimentação</TableCell>
                            <TableCell className='column'>Quantidade Compras</TableCell>
                            <TableCell className='column'>Quantidade Vendas</TableCell>
                            <TableCell className='column'>Lucro Bruto</TableCell>
                            <TableCell className='column'>Lucro</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {balanceamentos.map((balanceamento, index) => (
                            <TableRow
                                key={index}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                <TableCell className='cell'>{balanceamento.data}</TableCell>
                                <TableCell className='cell'>{balanceamento.nomeItem}</TableCell>
                                <TableCell className='cell'>{balanceamento.tipo}</TableCell>
                                <TableCell className='cell'>{balanceamento.qtdCompras}</TableCell>
                                <TableCell className='cell'>{balanceamento.qtdVendas}</TableCell>
                                <TableCell className='cell'>{balanceamento.lucroBruto.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL',})}</TableCell>
                                <TableCell className='cell'>{balanceamento.lucro.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL',})}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default CashCLoM;