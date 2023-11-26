import React from "react";

interface CardTbProps {
    mesaNumero: number;
    pedido: Pedido;
    onClick: (index: number) => void;
}

interface Pedido {
    pedidoItens: PedidoItens[];
    dataHora: string;
    valorTotal: number;
    desconto: number;
    cliente: string;
    status: string;
}

interface PedidoItens {
    id: number;
    item: { id: number };
    descricaoItem: string;
    valorVenda: number;
    quantidade: number;
}

const CardTb: React.FC<CardTbProps> = ({ mesaNumero, pedido, onClick }) => {
    const isPedidoAberto = pedido.status === "ABERTO";
    const isMesaReservada = pedido.pedidoItens.length === 0;
    const corFundo = isPedidoAberto && isMesaReservada ? "bg-orange-500" : isPedidoAberto ? "bg-red-500" : "bg-green-500";

    return (
        <div
            className={`w-44 h-48 flex flex-col items-center justify-center ${corFundo} p-15 hover:scale-105`}
            onClick={() => onClick(mesaNumero - 1)}
        >
            <h1 className="text-white">Mesa {mesaNumero}</h1>
        </div>
    );
};


export default CardTb;