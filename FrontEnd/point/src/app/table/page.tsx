'use client'
import React, { useState, useEffect } from "react";
import CardTb from "@/components/cardTb";
import DetalhesPedido from "@/components/Mesa/ModalMesa";

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

const Mesa: React.FC = () => {
    debugger;
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [pedidoSelecionado, setPedidoSelecionado] = useState<number>(0);

    const pedidosIniciais: Pedido[] = Array.from({ length: 8 }, (_, index) => ({
        pedidoItens: [],
        dataHora: "",
        valorTotal: 0,
        desconto: 0,
        cliente: "MESA " + (index + 1),
        status: "",
    }));

    const [pedidos, setPedidos] = useState<Pedido[]>(pedidosIniciais);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setPedidoSelecionado(0);
        setIsModalOpen(false);
    };

    const handleMesaClick = (index: number) => {
        debugger;
        console.log(pedidos);
        console.log(pedidos[pedidoSelecionado]);
        setPedidoSelecionado(index);
        openModal();
    };

    const saveAndCloseModal = (updatedPedido: Pedido) => {
        debugger;
        const updatedPedidos = pedidos.map((p, index) => (index === pedidoSelecionado ? updatedPedido : p));
        setPedidos(updatedPedidos);
        localStorage.setItem("pedidos", JSON.stringify(updatedPedidos));
        closeModal();
    };

    useEffect(() => {
        const storedPedidos = localStorage.getItem("pedidos");
        if (storedPedidos) {
            setPedidos(JSON.parse(storedPedidos));
        }
    }, []);

    return (
        <div className="flex p-9 items-start content-start">
            <div className="flex flex-wrap gap-4">
                {pedidos.map((pedido, index) => (
                    <CardTb key={index} mesaNumero={index + 1} pedido={pedido} onClick={handleMesaClick} />
                ))}
            </div>

            {isModalOpen && (
                <div className="modal">
                    <DetalhesPedido
                        pedido={pedidos[pedidoSelecionado]}
                        onSave={saveAndCloseModal}
                        onCancel={closeModal}
                    />
                </div>
            )}
        </div>
    );
};

export default Mesa;
