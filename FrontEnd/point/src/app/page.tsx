'use client'
import React, { useState, useEffect } from "react";
import CartModal from "@/components/ModalCar/ModalCar";
import Filtro from "@/components/Navbartb/Filtro";

interface ProductProps {
  name: string;
  vlrfl: string;
  image: string;
  checked: boolean;
  price: number;
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

  const openModal = () => {
    setIsModalOpen(true);
  };

  const selectProduct = (product: ProductProps) => {
    setSelectedProduct(product);
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

  const convertToCartItems = (clickCount: { [key: string]: number }): CartItem[] => {
    return Object.entries(clickCount).map(([name, quantity]) => ({
      name,
      price: selectedProduct ? selectedProduct.price : 0, // Use o preço do produto selecionado
      quantity,
    }));
  };

  return (
    <div className="p-9 w-full">
      <Filtro />
      <div className="flex flex-wrap py-7 gap-4">
        {activeProducts.map((product, index) => (
          <div
            key={index}
            className="w-44 h-48 flex flex-col items-center justify-center bg-gray-200 p-15 hover:scale-105"
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
        className="fixed bottom-8 right-9 bg-blue-500 hover:bg-blue-600 text-white py-2 px-2 rounded-full"
        onClick={() => openModal()}
      >
        <img src="bag.png" alt="Shop bag" />
      </button>
      {isModalOpen && selectedProduct ? ( 
        <CartModal
          isOpen={isModalOpen}
          product={selectedProduct}
          cartItems={convertToCartItems(productClickCount)}
          onClose={() => setIsModalOpen(false)}
        />
      ) : null}
    </div>
  );
}
