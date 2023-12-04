'use client'
import React, { useState, useEffect } from "react";
import CartModal from "@/components/ModalCar/ModalCar";
import Filtro from "@/components/Navbartb/Filtro";
import { styled } from "styled-components";

interface ProductProps {
    id: number;
    descricao: string;
    precoVenda: number;
    imagem: string;
    ativo: boolean;
    filtro: string;
}

interface CartItem {
    id: number;
    descricao: string;
    precoVenda: number;
    quantidade: number;
}

export default function Menu() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [activeProducts, setActiveProducts] = useState<ProductProps[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<ProductProps[] | null>(null);
    const [productClickCount, setProductClickCount] = useState<{ [key: number]: number }>({});
    const [deleteProduct, setDeleteProduct] = useState<ProductProps | null>(null);


    const openModal = () => {
        if (selectedProducts && selectedProducts.length > 0) {
            setIsModalOpen(true);
        }
    };

    const selectProduct = (product: ProductProps) => {
        const isProductAlreadySelected = selectedProducts?.some((p) => p.id === product.id);

        if (!isProductAlreadySelected) {
            const updatedSelectedProducts = selectedProducts ? [...selectedProducts, product] : [product];
            setSelectedProducts(updatedSelectedProducts);

            setProductClickCount((prevCounts) => ({
                ...prevCounts,
                [product.id]: 1,
            }));
        } else {
            setProductClickCount((prevCounts) => ({
                ...prevCounts,
                [product.id]: (prevCounts[product.id] || 0) + 1,
            }));
        }
    };

    useEffect(() => {
        const savedData = localStorage.getItem("stockData");
        if (savedData) {
            const parsedData: ProductProps[] = JSON.parse(savedData);
            const filteredProducts = parsedData.filter((product) => product.ativo && product.quantidadeEstoque > 0);
            setActiveProducts(filteredProducts);
        }
    }, []);

    const convertToCartItems = (clickCount: { [key: number]: number }, selectedProducts: ProductProps[] | null): CartItem[] => {
        if (selectedProducts) {
            const cartItems: CartItem[] = selectedProducts.flatMap((product) => {
                const quantidade = clickCount[product.id] || 0;

                return {
                    id: product.id,
                    descricao: product.descricao,
                    precoVenda: product.precoVenda,
                    quantidade: quantidade,
                };
            });

            return cartItems;
        } else {
            return [];
        }
    };

    const getCategoryColor = (filtro: string) => {
        switch (filtro.toLowerCase()) {
            case 'para':
                return 'bg-red-500';
            case 'bebidas':
                return 'bg-blue-500';
            case 'acai':
                return 'bg-purple-500';
            default:
                return 'bg-gray-500';
        }
    };

    const CartCount = styled.span`
    width: 17px;
    height: 17px;
    background-color: var(--count-color);
    color: white;

    position:absolute;
    left:-10px;
    top:50%;
  `
    const Container = styled.div`
  position: relative;
  `

    const handleClearCart = () => {
        setSelectedProducts([]);
        setProductClickCount({});
        setIsModalOpen(false);
    };

    const handleCancelItemClick = (id: number) => {
        const newProducts = selectedProducts?.filter((product) => product.id !== id);

        setSelectedProducts(newProducts || []);

        setProductClickCount((prevCounts) => {
            const updatedCounts = { ...prevCounts };
            delete updatedCounts[id];

            if (Object.keys(updatedCounts).length === 0 && (!newProducts || newProducts.length === 0)) {
                setIsModalOpen(false);
            }

            return updatedCounts;
        });
    };

    const handleCartItemUpdate = (updatedCartItems: CartItem[]) => {
        const updatedClickCount: { [key: number]: number } = {};

        updatedCartItems.forEach((item) => {
            updatedClickCount[item.id] = item.quantidade;
        });

        setProductClickCount(updatedClickCount);
    };

    return (
        <div className="p-9 w-full">
            <Filtro />
            <div className="flex flex-wrap py-7 gap-4">
                {activeProducts.map((product, index) => (
                    <div
                        key={index}
                        className={`w-44 h-48 flex flex-col items-center justify-center p-15 hover:scale-105 ${getCategoryColor(product.filtro)}`}
                        onClick={() => selectProduct(product)}
                    >

                        <img
                            src={product.imagem}
                            alt={product.descricao}
                            className="w-20 h-22 mb-2"
                        />
                        <h3 className="text-xl font-bold">{product.descricao}</h3>
                        <h3 className="text-xl">{product.precoVenda}</h3>
                    </div>
                ))}
            </div>
            <button
                className="fixed bottom-8 right-9 bg-purple-700 hover:bg-purple-600 py-2 px-2 rounded-full"
                onClick={() => openModal()}
            >
                <Container>
                    {Object.keys(productClickCount).length > 0 && (
                        <CartCount>
                            {Object.values(productClickCount).reduce((total, count) => total + count, 0)}
                        </CartCount>
                    )}
                </Container>
                <img src="carrinho.png" alt="carrinho" />
            </button>
            {isModalOpen && selectedProducts ? (
                <CartModal
                    isOpen={isModalOpen}
                    products={selectedProducts}
                    cartItems={convertToCartItems(productClickCount, selectedProducts)}
                    onCancelItemClick={handleCancelItemClick}
                    onCancelAll={handleClearCart}
                    onClose={() => setIsModalOpen(false)}
                    onPaymentSuccess={() => setSelectedProducts([])}
                    onCartItemUpdate={handleCartItemUpdate}
                />
            ) : null}
        </div>
    );
}
