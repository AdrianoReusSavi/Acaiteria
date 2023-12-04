'use client'
import React, { useState, useEffect } from "react";
import axios from 'axios';
import { TableContainer, Table, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material'

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
            <TableContainer component={Paper} sx={{ maxHeight: '800px' }}>
                <Table aria-label='simple table' stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell className='column'>Categoria</TableCell>
                            <TableCell className='column'>Lucro Categoria</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {fechamentos.map((fechamento, index) => (
                            <TableRow
                                key={index}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                <TableCell className='cell'>{fechamento.categoria}</TableCell>
                                <TableCell className='cell'>{fechamento.totalCategoria?.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL', })}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default CashCLoM;