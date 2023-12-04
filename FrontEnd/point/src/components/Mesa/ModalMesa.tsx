import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { error } from 'console';
interface DetalhesPedidoProps {
    pedido: Pedido;
    onSave: (pedido: Pedido) => void;
    onCancel: () => void;
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

interface ProductProps {
    id: number;
    descricao: string;
    precoVenda: number;
    imagem: string;
    ativo: boolean;
    filtro: string;
}

interface Item {
    id: number;
    descricao: string;
    precoVenda: number;
    quantidade: number;
}

const DetalhesPedido: React.FC<DetalhesPedidoProps> = ({ pedido, onSave, onCancel }) => {
    const [cliente, setCliente] = useState(pedido.cliente);
    const [desconto, setDesconto] = useState(0);
    const [activeProducts, setActiveProducts] = useState<ProductProps[]>([]);

    const [selectedProducts, setSelectedProducts] = useState<Item[]>(
        pedido?.pedidoItens.map((pedidoItem) => ({
            id: pedidoItem.id,
            descricao: pedidoItem.descricaoItem,
            precoVenda: pedidoItem.valorVenda,
            quantidade: pedidoItem.quantidade,
        })) || []
    );

    const [mensagensDeErro, setMensagensDeErro] = useState<string[]>([]);

    useEffect(() => {
        const savedData = localStorage.getItem("stockData");
        if (savedData) {
            const parsedData: ProductProps[] = JSON.parse(savedData);
            const filteredProducts = parsedData.filter((product) => product.ativo);
            setActiveProducts(filteredProducts);
        }
    }, []);

    const converterParaItem = (product: ProductProps): Item => {
        return {
            id: product.id,
            descricao: product.descricao,
            precoVenda: product.precoVenda,
            quantidade: 1,
        };
    };

    const adicionarItemAoPedido = (product: ProductProps) => {
        const produtoJaAdicionado = selectedProducts.find((selectedProduct) => selectedProduct.id === product.id);

        if (!produtoJaAdicionado) {
            const item = converterParaItem(product);
            setSelectedProducts((prevSelectedProducts) => [...prevSelectedProducts, item]);
        } else {
            const updatedSelectedProducts = selectedProducts.map((selectedProduct) => {
                if (selectedProduct.id === product.id) {
                    return {
                        ...selectedProduct,
                        quantidade: selectedProduct.quantidade + 1,
                    };
                }
                return selectedProduct;
            });
            setSelectedProducts(updatedSelectedProducts);
        }
    };

    const decreasequantidade = (item: Item) => {
        const id = selectedProducts.findIndex((selectedItem) => selectedItem.id === item.id);
        if (id !== -1 && selectedProducts[id].quantidade > 1) {
            const updatedSelectedProducts = [...selectedProducts];
            updatedSelectedProducts[id].quantidade -= 1;
            setSelectedProducts(updatedSelectedProducts);
        }
    };

    const increasequantidade = (item: Item) => {
        const id = selectedProducts.findIndex((selectedItem) => selectedItem.id === item.id);
        if (id !== -1) {
            const updatedSelectedProducts = [...selectedProducts];
            updatedSelectedProducts[id].quantidade += 1;
            setSelectedProducts(updatedSelectedProducts);
        }
    };

    const removerItemDoPedido = (descricao: string) => {
        const updatedSelectedProducts = selectedProducts.filter((item) => item.descricao !== descricao);
        setSelectedProducts(updatedSelectedProducts);
    };

    const savePedido = () => {
        const valorTotal = selectedProducts.reduce((total, product) => {
            return product.precoVenda * product.quantidade;
        }, 0);

        const descontoEmReais = (desconto / 100) * valorTotal;

        const valorTotalComDesconto = valorTotal - descontoEmReais;

        if (pedido !== null) {
            pedido.cliente = cliente;
            pedido.desconto = desconto;
            pedido.status = "ABERTO";
            pedido.valorTotal = valorTotalComDesconto;
            pedido.pedidoItens = selectedProducts.map((product) => ({
                id: product.id,
                item: { id: product.id },
                descricaoItem: product.descricao,
                quantidade: product.quantidade,
                valorVenda: product.precoVenda,
            }));

            onSave(pedido);
        } else {
            const newPedido = {
                dataHora: new Date().toISOString(),
                valorTotal: valorTotalComDesconto,
                status: "ABERTO",
                desconto: desconto,
                cliente: cliente,
                pedidoItens: selectedProducts.map((product) => ({
                    id: product.id,
                    item: { id: product.id },
                    descricaoItem: product.descricao,
                    quantidade: product.quantidade,
                    valorVenda: product.precoVenda,
                })),
            }

            onSave(newPedido);
        }
    };

    const cancelPedido = () => {
        onCancel();
    };

    const resetPedido = () => {
        const newPedido = {
            pedidoItens: [],
            dataHora: "",
            valorTotal: 0,
            desconto: 0,
            cliente: cliente,
            status: "",
        }

        onSave(newPedido);
    };

    const enviarPedido = () => {

        if (selectedProducts.length === 0) {
            setMensagensDeErro(["Informe ao menos um item no pedido!"]);
            return;
        }

        const valorTotal = selectedProducts.reduce((total, product) => {
            return total + product.precoVenda * product.quantidade;
        }, 0);
        const descontoEmReais = (desconto / 100) * valorTotal;
        const valorTotalComDesconto = valorTotal - descontoEmReais;
        const pedido = {
            dataHora: new Date().toISOString(),
            valorTotal: valorTotalComDesconto,
            status: "FECHADO",
            desconto: desconto,
            cliente: cliente,
            pedidoItens: selectedProducts.map((product) => ({
                item: { id: product.id },
                descricaoItem: product.descricao,
                quantidade: product.quantidade,
                valorVenda: product.precoVenda * product.quantidade,
            })),
        };

        axios
            .post('http://localhost:8080/api/pedido', pedido)
            .then((response) => {
                if (response.status === 201) {
                    onCancel();
                    resetPedido();
                } else if (response.data && response.data.erro) {
                    setMensagensDeErro([response.data.erro]);
                }
            })
            .catch((error) => {
                if (error.response && error.response.data) {
                    const errorMessage = typeof error.response.data === 'string'
                        ? JSON.parse(error.response.data).erro
                        : error.response.data.erro;
                    setMensagensDeErro([errorMessage]);
                } else {
                    setMensagensDeErro([error.message]);
                }
            });
    }

    const total = selectedProducts.reduce((total, product) => {
        const descontoEmReais = (desconto / 100) * (product.precoVenda * product.quantidade);
        const subtotalComDesconto = (product.precoVenda * product.quantidade) - descontoEmReais;
        return total + subtotalComDesconto;
    }, 0).toFixed(2);
    const totItens = selectedProducts.reduce((acc, item) => acc + item.quantidade, 0);

    return (
        <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9">
            <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
            <div className="z-10 min-h-screen w-5/6 rounded-lg bg-white p-6 flex flex-row">
                <div id="a" className="flex-1 bg-gray-100 p-3 border-r border-gray-200 max-h-[your-height] overflow-y-auto">
                    <h2 className="text-lg font-semibold mb-3">Menu</h2>
                    <ul className="space-y-2">
                        {activeProducts.map((product) => (
                            <li key={product.id} className="flex items-center justify-between border-b border-gray-200 py-1">
                                <div className="flex flex-col">
                                    <span className="text-md font-semibold">{product.descricao}</span>
                                    <span className="text-sm text-gray-500">R$ {product.precoVenda}</span>
                                </div>
                                <button
                                    onClick={() => adicionarItemAoPedido(product)}
                                    className="bg-green-500 text-white px-3 py-1 rounded"
                                >
                                    Adicionar
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>

                <div id="b" className="flex-1 bg-gray-100 p-3">
                    <div className="flex flex-wrap">
                        <div className="w-1/2  mb-4 pl-2">
                            <label className="block">Cliente</label>
                            <input
                                type="text"
                                value={cliente}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                readOnly
                            />
                        </div>
                        <div className="w-1/2  mb-4 pl-2">
                            <label className="block">Desconto (%)</label>
                            <input
                                type="number"
                                value={desconto}
                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => setDesconto(event.target.valueAsNumber)}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            />
                        </div>
                    </div>
                    <ul className="space-y-2 border-t-2 py-8">
                        {selectedProducts.map((selectedProduct) => (
                            <li key={selectedProduct.id} className="flex items-center justify-between">
                                <span className="flex items-center space-x-2">
                                    <button
                                        onClick={() => decreasequantidade(selectedProduct)}
                                        className="text-red-400 font-bold px-2 rounded-l-lg mr-2"
                                    >
                                        -
                                    </button>
                                    <span className="text-sm">{selectedProduct.quantidade}</span>
                                    <button
                                        onClick={() => increasequantidade(selectedProduct)}
                                        className="text-green-400 font-bold px-2 rounded-r-lg ml-2 mr-2"
                                    >
                                        +
                                    </button>
                                    <span className="text-md">{selectedProduct.descricao}</span>
                                </span>
                                <span className="text-md">R${selectedProduct.precoVenda * selectedProduct.quantidade} x</span>
                                <button
                                    onClick={() => removerItemDoPedido(selectedProduct.descricao)}
                                    className="text-red-400 font-bold px-2 rounded-l-lg"
                                >
                                    Remover
                                </button>
                            </li>
                        ))}
                    </ul>
                    <div className="fixed flex bottom-52  items-center  space-x-96">
                        <div style={{ color: 'red' }}>
                            {mensagensDeErro.length > 0 && (
                                <div className="mensagens-de-erro">
                                    {mensagensDeErro.map((mensagem, index) => (
                                        <p key={index}>{mensagem}</p>
                                    ))}
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="fixed flex bottom-52 border-t-2 py-8  items-center  space-x-96">
                        <div>
                            <span className="text-sm ml-2 text-gray-400 font-semibold">{totItens} Itens</span>
                        </div>
                        <div >
                            <span className="text-lg ml-20 text-gray-400 font-semibold">Subtotal: </span>
                            <span className="text-lg  text-gray-400 font-semibold">R${total}</span>
                        </div>
                    </div>
                </div>
                <div className="fixed flex bottom-12 right-12 space-x-8">
                    <button onClick={resetPedido} className="bg-gray-400 hover:bg-gray-350 px-4 py-2 rounded">
                        Abandonar
                    </button>
                    <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded" type="submit" onClick={enviarPedido}>
                        PAGAR
                    </button>
                    <button onClick={savePedido} className="bg-green-400  hover:bg-gray-300 px-4 py-2 rounded mr-4">
                        Salvar
                    </button>
                    <button onClick={cancelPedido} className="bg-red-400 hover:bg-gray-350 px-4 py-2 rounded mr-4">
                        Cancelar
                    </button>
                </div>
            </div>
        </div>
    );
};

export default DetalhesPedido;