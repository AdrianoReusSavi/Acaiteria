import React, { useState, useEffect } from "react";
import ToggleSwitch from "@/components/switch";

interface ProductProps {
  name: string;
  vlrfl: string;
  image: string;
  descricao: string;
  quantidade: string;
  checked:boolean;
}

interface ModalProductProps {
  closeModal: () => void;
  handleFormSubmit: (product: ProductProps) => void;
  editProduct?: ProductProps | null;
}

const ModalProduct: React.FC<ModalProductProps> = ({ closeModal, handleFormSubmit, editProduct }) => {
  const [nome, setNome] = useState("");
  const [descricao, setDescricao] = useState("");
  const [quantidade, setQuantidade] = useState("");
  const [vlrfl, setVlrFl] = useState("");
  
  const [pic, setPic] = useState("");
  const [checked, setChecked] = useState(false);

  useEffect(() => {
    if (editProduct) {
      setNome(editProduct.name);
      setQuantidade(editProduct.quantidade);
      setVlrFl(editProduct.vlrfl);
      setDescricao(editProduct.descricao)
      setPic(editProduct.image);
      setChecked(editProduct.checked);
    }
  }, [editProduct]);
  
  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    console.log()
    const newProduct: ProductProps = {
      name: nome,
      vlrfl: vlrfl,
      image: pic,
      quantidade: quantidade,
      descricao:descricao,
      checked:checked,

    };
    handleFormSubmit(newProduct);
    resetInputs();
  };

  const resetInputs = () => {
    setNome("");
    setDescricao("");
    setQuantidade("");
    setVlrFl("");
    setPic("");
  };

  const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files && files.length > 0) {
      const file = files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        if (e.target && typeof e.target.result === "string") {
          setPic(e.target.result);
        }
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9 pr-9">
      <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
      <div className="z-10 h-full w-2/5 rounded-lg bg-white p-6">
        <h2 className="mb-4 text-center text-lg font-bold">{editProduct ? "Editar Produto" : "Adicionar Produto"}</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-6">
            <div className="flex flex-wrap">
              <div className="w-1/2 mb-4 pr-2">
                <label className="block">Nome</label>
                <input
                  type="text"
                  value={nome}
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => setNome(event.target.value)}
                  className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                />
              </div>
              <div className="w-1/2 mb-4 pl-2">
                <label className="block">Descrição</label>
                <select
                  className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                  value={descricao}
                  onChange={(event: React.ChangeEvent<HTMLSelectElement>) => setDescricao(event.target.value)}
                >
                  <option value="">Selecione</option>
                  <option value="acai">Acaí</option>
                  <option value="para">Pará</option>
                  <option value="bebidas">Bebidas</option>
                </select>
              </div>
              <div className="w-1/2 mb-4 pr-2">
                <label className="block">Quantidade</label>
                <input
                  type="number"
                  value={quantidade}
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => setQuantidade(event.target.value)}
                  className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                />
              </div>
              <div className="w-1/2 mb-4 pr-2">
                <label className="block">Valor Final</label>
                <input
                  type="text"
                  value={vlrfl}
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => setVlrFl(event.target.value)}
                  className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
                />
              </div>
              <div className="w-1/2 mb-4 pl-2">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-black">Adicionar Imagem</label>
                <input
                  type="file"
                  accept="image/*"
                  id="finalValue"
                  onChange={handleImageChange}
                  className="block w-full h-7 mb-5 text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
                />
              </div>
              <div className="w-full flex justify-center mb-4">
                <label className="mr-2">Ativo</label>
                <ToggleSwitch checked={checked} onChange={() => setChecked(!checked)} />
              </div>
            </div>
            <div className="flex justify-end mt-12">
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
