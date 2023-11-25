
import React, { useState, useEffect } from 'react';
import { debug } from 'util';
import axios from 'axios';
interface ProductProps {
    id: number;
    descricao: string;
    precoVenda: number;
}

interface CartItem {
    id: number;
    descricao: string;
    precoVenda: number;
    quantidade: number;
}

interface CartModalProps {
    isOpen: boolean;
    products: ProductProps[] | null;
    cartItems: CartItem[];
    onClose: () => void;
    onCancelItemClick: (id: number) => void;
    onCancelAll: () => void;
    onPaymentSuccess: () => void;
    onCartItemUpdate: (updatedCartItems: CartItem[]) => void;
}

const CartModal: React.FC<CartModalProps> = ({ isOpen, products, cartItems, onClose, onCancelItemClick, onCancelAll, onPaymentSuccess, onCartItemUpdate }) => {
    const [cartItem, setCartItems] = useState<CartItem[]>(cartItems);
    const [cliente, setCliente] = useState("");
    const [desconto, setDesconto] = useState(0);
    const [mensagensDeErro, setMensagensDeErro] = useState<string[]>([]);

    useEffect(() => {
        setCartItems(cartItems); // Update cartItem state when cartItems prop changes
    }, [cartItems]);

    const findCartItemIndex = (productName: string) => {
        return cartItem.findIndex((item) => item.descricao === productName);
    };


    const decreasequantidade = (item: CartItem) => {
        const itemIndex = findCartItemIndex(item.descricao);
        if (itemIndex !== -1) {
            const updatedCartItems = [...cartItem];
            updatedCartItems[itemIndex].quantidade -= 1;
            setCartItems(updatedCartItems);
            onCartItemUpdate(updatedCartItems);
        }
    };

    const increasequantidade = (item: CartItem) => {
        const itemIndex = findCartItemIndex(item.descricao);
        if (itemIndex !== -1) {
            const updatedCartItems = [...cartItem];
            updatedCartItems[itemIndex].quantidade += 1;
            setCartItems(updatedCartItems);
            onCartItemUpdate(updatedCartItems);
        }
    };

    const handleDeleteClick = (itemName: string) => {
        const updatedCartItems = cartItem.filter((item) => item.descricao !== itemName);
        setCartItems(updatedCartItems);
        onCartItemUpdate(updatedCartItems);
    };

    const enviarPedido = () => {
        const valorTotal = cartItem.reduce((total, product) => {
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
            pedidoItens: cartItem.map((product) => ({
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
                    onClose();
                    onCancelAll();
                    resetInputs();
                    onPaymentSuccess()
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

    const resetInputs = () => {
        setCliente("");
        setDesconto(0);
        setMensagensDeErro([]);
    };

    const total = cartItem.reduce((total, product) => {
        const descontoEmReais = (desconto / 100) * (product.precoVenda * product.quantidade);
        const subtotalComDesconto = (product.precoVenda * product.quantidade) - descontoEmReais;
        return total + subtotalComDesconto;
    }, 0).toFixed(2);
    const totItens = cartItem.reduce((acc, item) => acc + item.quantidade, 0);
    //console.log('Valores iniciais do carrinho:', cartItems);
    return (
        <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9 ">
            <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
            <div className="z-10 min-h-screen w-2/5 rounded-lg bg-white p-6">
                <div className="flex flex-wrap">
                    <div className="w-1/2  mb-4 pl-2">
                        <label className="block">Cliente</label>
                        <input
                            type="text"
                            value={cliente}
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                const newValue = event.target.value.replace(/[^a-zA-Z0-8 ]+/g, '');
                                setCliente(newValue);
                            }}
                            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                            required
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
                <div className="fixed flex bottom-52 border-t-2 py-8  items-center  space-x-96">
                </div>
                <ul className="space-y-2 border-t-2 py-8">
                    {cartItems.map((item) => (
                        <li key={item.descricao} className="flex items-center justify-between">
                            <span>
                                <button
                                    onClick={() => decreasequantidade(item)}
                                    className=" text-red-400 font-bold   px-2 rounded-l-lg mr-2"
                                >
                                    -
                                </button>
                                {item.quantidade}
                                <button
                                    onClick={() => increasequantidade(item)}
                                    className=" text-green-400 font-bold  px-2 rounded-r-lg ml-2 mr-2"
                                >
                                    +
                                </button>
                                {item.descricao}
                            </span>
                            <span>R${item.precoVenda}  x</span>
                            <button
                                onClick={() => onCancelItemClick(item.id)}

                                className="text-red-400 font-bold px-2 rounded-l-lg "
                            >
                                Remover
                            </button>
                        </li>
                    ))}
                </ul>

                <footer className='w-{100%} inset-0 z-10'>

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

                    <div className="fixed flex bottom-12 right-12 space-x-8">
                        <div>
                            <button
                                className="bg-red-400 hover:bg-gray-350 px-4 py-2 rounded mr-4"
                                onClick={onCancelAll}>
                                Remover Todos
                            </button>
                        </div>

                        <div>
                            <button className="bg-gray-200 hover:bg-gray-300 px-4 py-2 rounded mr-4" onClick={onClose}>
                                Fechar Modal
                            </button>
                            <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded" type="submit" onClick={enviarPedido}>
                                PAGAR
                            </button>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
    );
};

export default CartModal;