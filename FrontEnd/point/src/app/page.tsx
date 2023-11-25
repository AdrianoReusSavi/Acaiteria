'use client'
import React, { useState, useEffect } from "react";
import CartModal from "@/components/ModalCar/ModalCar";
import Filtro from "@/components/Navbartb/Filtro";
import { styled } from "styled-components";

interface ProductProps {
  name: string;
  vlrfl: number;
  image: string;
  checked: boolean;
  price: number;
  descricao: string;
}

interface CartItem {
  name: string;
  price: number;
  quantity: number;
}

export default function Menu() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [activeProducts, setActiveProducts] = useState<ProductProps[]>([]);
  const [selectedProduct, setSelectedProduct] = useState<ProductProps | null>(null);
  const [productClickCount, setProductClickCount] = useState<{ [key: string]: number }>({});
  const [deleteProduct, setDeleteProduct] = useState<ProductProps | null>(null);


  const openModal = () => {
    setIsModalOpen(true);
  };

  const selectProduct = (product: ProductProps) => {
    setSelectedProduct({ ...product, price: product.vlrfl });
    handleProductClick(product.name);
  };

  const handleProductClick = (productName: string) => {
    setProductClickCount((prevCounts) => ({
      ...prevCounts,
      [productName]: (prevCounts[productName] || 0) + 1,
    }));
  };

  useEffect(() => {
    const savedData = localStorage.getItem("stockData");
    if (savedData) {
      const parsedData: ProductProps[] = JSON.parse(savedData);
      const filteredProducts = parsedData.filter((product) => product.checked);
      setActiveProducts(filteredProducts);
    }
  }, []);

  const convertToCartItems = (clickCount: { [key: string]: number }, selectedProduct: ProductProps | null): CartItem[] => {
    if (selectedProduct) {
      const cartItems: CartItem[] = Object.entries(clickCount).map(([name, quantity]) => ({
        name,
        price: selectedProduct.price,
        quantity,
      }));
      return cartItems;
    } else {
      return [];
    }
  };
  //const cartItems = convertToCartItems(clickCount, selectedProduct);
  const getCategoryColor = (descricao: string) => {
    switch (descricao.toLowerCase()) {
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

  const removeFromCart = (productName: string) => {
    setProductClickCount((prevCounts) => {
      const updatedCounts = { ...prevCounts };
      delete updatedCounts[productName];
      return updatedCounts;
    });
  };
  const handleClearCart = () => {
    setProductClickCount({});
  };

  const handleCancelItemClick = (productName: string) => {
    removeFromCart(productName); 
  };
  //console.log('produto selecionado :', quantity);
  return (
    <div className="p-9 w-full">
      <Filtro />
      <div className="flex flex-wrap py-7 gap-4">
        {activeProducts.map((product, index) => (
          <div
            key={index}
            className={`w-44 h-48 flex flex-col items-center justify-center p-15 hover:scale-105 ${getCategoryColor(product.descricao)}`}
            onClick={() => selectProduct(product)}
          >

            <img
              src={product.image}
              alt={product.name}
              className="w-20 h-22 mb-2"
            />
            <h3 className="text-xl font-bold">{product.name}</h3>
            <h3 className="text-xl">{product.vlrfl}</h3>
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
      {isModalOpen && selectedProduct ? (
        <CartModal
          isOpen={isModalOpen}
          product={selectedProduct}
          cartItems={convertToCartItems(productClickCount, selectedProduct)}
          onCancelItemClick={handleCancelItemClick}  
          onCancelAll={handleClearCart}
          onClose={() => setIsModalOpen(false)}
        />
      ) : null}
    </div>
  );
}
