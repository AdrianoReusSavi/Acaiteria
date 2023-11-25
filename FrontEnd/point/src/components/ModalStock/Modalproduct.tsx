import React, { useState, useEffect } from "react";
import ToggleSwitch from "@/components/switch";
import axios from 'axios';

interface ProductProps {
    id: number | null;
    descricao: string;
    precoCompra: number;
    precoVenda: number;
    imagem: string;
    quantidadeEstoque: number;
    ativo: boolean;
    filtro: string;
    unidadeMedida: { id: number };
}

interface UnidadeMedida {
    id: number;
    sigla: string;
    descricao: string;
}

interface ModalProductProps {
    closeModal: () => void;
    handleFormSubmit: (product: ProductProps) => void;
    editProduct?: ProductProps | null;
    deleteProductHandler: (productToDelete: ProductProps) => void;
}

const ModalProduct: React.FC<ModalProductProps> = ({ closeModal, handleFormSubmit, editProduct, deleteProductHandler }) => {
    const [produtoId, setProdutoId] = useState<number | null>(null);
    const [descricao, setDescricao] = useState("");
    const [filtro, setFiltro] = useState("ACAIS");
    const [quantidadeEstoque, setQuantidade] = useState(0);
    const [precoCompra, setPrecoCompra] = useState(0);
    const [precoVenda, setPrecoVenda] = useState(0);
    const [imagem, setImagem] = useState("");
    const [unidadeMedidaId, setUnidadeMedidaId] = useState(-1);
    const [ativo, setAtivo] = useState(false);
    const [deleteProduct, setDeleteProduct] = useState(false);

    const [unidadesDeMedida, setUnidadesDeMedida] = useState<UnidadeMedida[]>([]);
    const [mensagensDeErro, setMensagensDeErro] = useState<string[]>([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/unidadeMedida')
            .then(response => {
                const data = response.data;
                setUnidadesDeMedida(data.content);
            })
            .catch(error => {
                console.error('Erro ao obter unidades de medida:', error);
            });
    }, []);

    useEffect(() => {
        if (editProduct) {
            setProdutoId(editProduct.id);
            setDescricao(editProduct.descricao);
            setQuantidade(editProduct.quantidadeEstoque);
            setPrecoCompra(editProduct.precoCompra);
            setPrecoVenda(editProduct.precoVenda);
            setFiltro(editProduct.filtro)
            setImagem(editProduct.imagem);
            setAtivo(editProduct.ativo);
            setUnidadeMedidaId(editProduct.unidadeMedida.id);
        }
    }, [editProduct]);

    const handleDeleteClick = () => {
        setDeleteProduct(true);
    };

    useEffect(() => {
        if (deleteProduct && editProduct) {
            deleteProductHandler(editProduct);
            setDeleteProduct(false);
        }
    }, [deleteProduct, deleteProductHandler, editProduct]);

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();

        const erros = validarDados();
        setMensagensDeErro(erros);

        if (erros.length !== 0) {
            return;
        }
        const prodId = produtoId;
        const selectedUnidadeMedida = unidadesDeMedida?.find((unidade) => unidade.id === unidadeMedidaId)?.id ?? -1;

        if (prodId !== null) {
            const editProduct = {
                id: produtoId,
                descricao: descricao,
                quantidadeEstoque: quantidadeEstoque,
                precoCompra: precoCompra,
                precoVenda: precoVenda,
                imagem: imagem,
                filtro: filtro,
                unidadeMedida: { id: selectedUnidadeMedida },
                ativo: ativo,
            };
            axios
                .put(`http://localhost:8080/api/item/${editProduct.id}`, editProduct)
                .then((response) => {
                    if (response.status === 200) {
                        closeModal();
                        handleFormSubmit(editProduct);
                        resetInputs();
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
        else {
            const newProduct = {
                id: produtoId,
                descricao: descricao,
                quantidadeEstoque: quantidadeEstoque,
                precoCompra: precoCompra,
                precoVenda: precoVenda,
                imagem: imagem,
                filtro: filtro,
                unidadeMedida: { id: selectedUnidadeMedida },
                ativo: ativo,
            };

            axios
                .post('http://localhost:8080/api/item', newProduct)
                .then((response) => {
                    if (response.status === 201) {
                        closeModal();
                        handleFormSubmit(newProduct);
                        resetInputs();
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
    };

    const resetInputs = () => {
        setDescricao("");
        setFiltro("");
        setQuantidade(0);
        setPrecoCompra(0);
        setPrecoVenda(0);
        setImagem("");
        setMensagensDeErro([]);
    };


    const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const files = event.target.files;
        if (files && files.length > 0) {
            const file = files[0];
            const reader = new FileReader();
            reader.onload = (e) => {
                if (e.target && typeof e.target.result === "string") {
                    setImagem(e.target.result);
                }
            };
            reader.readAsDataURL(file);
        }
    };

    const validarDados = () => {
        const erros = [];
        if (precoVenda <= 0) {
            erros.push("Preço de venda deve ser maior que zero!");
        }

        if (precoCompra <= 0) {
            erros.push("Preço de compra deve ser maior que zero!");
        }

        if (precoVenda <= precoCompra) {
            erros.push("Preço de venda deve ser maior que o preço de compra!");
        }

        if (unidadeMedidaId === -1) {
            erros.push("Selecione uma unidade de medida!");
        }

        return erros;
    }

    return (
        <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9 ">
            <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
            <div className="z-10 min-h-screen w-2/5 rounded-lg bg-white p-6">
                <h2 className="mb-4 text-center text-lg font-bold">{editProduct ? "Editar Produto" : "Adicionar Produto"}</h2>
                <form onSubmit={handleSubmit}>

                    <div className="flex flex-wrap">
                        <div className="w-1/2 mb-4 ">
                            <label className="block">Nome</label>
                            <input
                                type="text"
                                value={descricao}
                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                    const newValue = event.target.value.replace(/[^a-zA-Z0-8 ]+/g, '');
                                    setDescricao(newValue);
                                }}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            />
                        </div>
                        <div className="w-1/2 mb-4 pl-2">
                            <label className="block">Categoria</label>
                            <select
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                                value={filtro}
                                onChange={(event: React.ChangeEvent<HTMLSelectElement>) => setFiltro(event.target.value)}
                            >
                                <option value="ACAIS">Açaí</option>
                                <option value="PARA">Pará</option>
                                <option value="BEBIDAS">Bebida</option>
                            </select>
                        </div>
                        <div className="w-1/2 mb-4 ">
                            <label className="block">Quantidade</label>
                            <input
                                type="number"
                                value={quantidadeEstoque}
                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => setQuantidade(event.target.valueAsNumber)}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            />
                        </div>
                        <div className="w-1/2 mb-4 pl-2">
                            <label className="block">Valor Compra</label>
                            <input
                                type="number"
                                value={precoCompra}
                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => setPrecoCompra(event.target.valueAsNumber)}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            />
                        </div>
                        <div className="w-1/2 mb-4 ">
                            <label className="block">Unidade de Medida</label>
                            <select
                                value={unidadeMedidaId}
                                onChange={(e) => setUnidadeMedidaId(parseInt(e.target.value, 10))}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            >
                                <option value="-1">Escolha uma opção</option>
                                {unidadesDeMedida.map(unidade => (
                                    <option key={unidade.id} value={unidade.id}>
                                        {unidade.sigla} - {unidade.descricao}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="w-1/2 mb-4 pl-2">
                            <label className="block">Valor Venda</label>
                            <input
                                type="number"
                                value={precoVenda}
                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => setPrecoVenda(event.target.valueAsNumber)}
                                className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                                required
                            />
                        </div>
                        <div className="w-1/2 mb-4 ">
                            <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-black">Adicionar Imagem</label>
                            <input
                                type="file"
                                accept="image/*"
                                id="finalValue"
                                onChange={handleImageChange}
                                className="block w-full h-7 mb-5 text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
                            />
                        </div>
                        <div className="w-1/2 mt-7 pl-4">
                            <label className="mr-2" >Vai para o cardapio?</label>
                            <ToggleSwitch checked={ativo} onChange={() => setAtivo(!ativo)} />
                        </div>
                    </div>
                    <div style={{ color: 'red' }}>
                        {mensagensDeErro.length > 0 && (
                            <div className="mensagens-de-erro">
                                {mensagensDeErro.map((mensagem, index) => (
                                    <p key={index}>{mensagem}</p>
                                ))}
                            </div>
                        )}
                    </div>
                    <div className="fixed flex bottom-12 right-12 space-x-8">
                        {editProduct && (
                            <div>
                                <button className="bg-red-400 hover:bg-gray-350 px-4 py-2 rounded mr-4" onClick={handleDeleteClick}>Trash</button>
                            </div>
                        )}
                        <div>
                            <button className="bg-gray-200 hover:bg-gray-300 px-4 py-2 rounded mr-4" onClick={closeModal}>
                                Fechar Modal
                            </button>
                            <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded" type="submit">
                                {editProduct ? "Salvar Alterações" : "Adicionar"}
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    );
};

export default ModalProduct;