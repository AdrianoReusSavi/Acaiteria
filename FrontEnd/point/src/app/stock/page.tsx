'use client'
import React, { useState, useEffect } from "react";
import ModalProduct from "@/components/ModalStock/Modalproduct";
import Filtro from "@/components/Navbartb/Filtro";

interface ProductProps {
  name: string;
  vlrfl: string;
  image: string;
  quantidade: string;
  checked: boolean;
  descricao:string;
}

const Stock = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [products, setProducts] = useState<ProductProps[]>([]);
  const [editProduct, setEditProduct] = useState<ProductProps | null>(null);
  const [deleteProduct, setDeleteProduct] = useState<ProductProps | null>(null);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setEditProduct(null);
  };

  const openEditModal = (product: ProductProps) => {
    setEditProduct(product);
    setIsModalOpen(true);
  };

  const handleFormSubmit = (product: ProductProps) => {
    if (editProduct) {
      setProducts((prevProducts) =>
        prevProducts.map((prevProduct) =>
          prevProduct === editProduct ? { ...product } : prevProduct
        )
      );
    } else {
      setProducts((prevProducts) => [...prevProducts, product]);
    }
    setEditProduct(null);
    closeModal();
  };
  
  const saveDataToLocalStorage = (data: ProductProps[]) => {
    localStorage.setItem("stockData", JSON.stringify(data));
  };

  const loadDataFromLocalStorage = () => {
    const savedData = localStorage.getItem("stockData");
    if (savedData) {
      const parsedData = JSON.parse(savedData);
      setProducts(parsedData);
    }
  };
  const deleteProductHandler = (productToDelete: ProductProps) => {
    setProducts((prevProducts) => prevProducts.filter((product) => product !== productToDelete));
  };

  useEffect(() => {
    loadDataFromLocalStorage();
  }, []);

  useEffect(() => {
    saveDataToLocalStorage(products);
  }, [products]);


  return (
    <div className="p-9 w-full">
      <Filtro/>

      <div className="flex flex-wrap py-7 gap-4">
        {products.map((product, index) => (
          <div
            key={index}
            className="w-44 h-48 flex flex-col items-center justify-center bg-gray-200 p-15 hover:scale-105"
            onClick={() => openEditModal(product)}
          >
            <img src={product.image} alt={product.name} className="w-20 h-22 mb-2" />
            <h3 className="text-xl font-bold">{product.name}</h3>
            <h3 className="text-xl">{product.vlrfl}</h3>
            <p className="text-gray-600">{product.quantidade}</p>
          </div>
        ))}
      </div>
      <button
        className="fixed bottom-8 right-9 bg-blue-500 hover:bg-blue-600 text-white py-2 px-2 rounded-full"
        onClick={openModal}
      >
        <img src="newProduto.png" alt="Adicionar Produto" />
      </button>

      {isModalOpen && (
        <ModalProduct
          closeModal={closeModal}
          handleFormSubmit={handleFormSubmit}
          editProduct={editProduct}
          deleteProductHandler={deleteProductHandler}
        />
      )}
    </div>
  );
};

export default Stock;
