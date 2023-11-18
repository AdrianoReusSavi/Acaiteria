
import React, { useState, useEffect } from 'react';
interface ProductProps {
  name: string;
  vlrfl: number;
  price: number;
}

interface CartItem {
  name: string;
  price: number;
  quantity: number;
}

interface CartModalProps {
  isOpen: boolean;
  product: ProductProps | null;
  cartItems: CartItem[];
  onClose: () => void;
  onCancelItemClick: (productName: string) => void;
  onCancelAll:()=>void;
}

const CartModal: React.FC<CartModalProps> = ({ isOpen, product, cartItems, onClose, onCancelItemClick, onCancelAll }) => {
  const [cartItem, setCartItems] = useState<CartItem[]>(cartItems);
  const [deleteProduct, setDeleteProduct] = useState(false);

  useEffect(() => {
    setCartItems(cartItems); // Update cartItem state when cartItems prop changes
  }, [cartItems]);

  const findCartItemIndex = (productName: string) => {
    return cartItem.findIndex((item) => item.name === productName);
  };

  const decreaseQuantity = (item: CartItem) => {
    const itemIndex = findCartItemIndex(item.name);
    if (itemIndex !== -1) {
      const updatedCartItems = [...cartItem];
      updatedCartItems[itemIndex].quantity -= 1;
      setCartItems(updatedCartItems);
    }
  };

  const increaseQuantity = (item: CartItem) => {
    const itemIndex = findCartItemIndex(item.name);
    if (itemIndex !== -1) {
      const updatedCartItems = [...cartItem];
      updatedCartItems[itemIndex].quantity += 1;
      setCartItems(updatedCartItems);
    }
  };

  const handleDeleteClick = (itemName: string) => {
    const updatedCartItems = cartItem.filter((item) => item.name !== itemName);
    setCartItems(updatedCartItems);
  };

  const handleDeleteAllClick = () => {
    // Para apagar todos os itens e fechar o modal
    setCartItems([]);
    onClose();
  };


  const total = cartItem.reduce((acc, item) => acc + item.price * item.quantity, 0);
  const totItens = cartItem.reduce((acc, item) => acc + item.quantity, 0);
  //console.log('Valores iniciais do carrinho:', cartItems);
  return (
    <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9 ">
      <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
      <div className="z-10 min-h-screen w-2/5 rounded-lg bg-white p-6">
        <ul className="space-y-2">
          {cartItems.map((item) => (
            <li key={item.name} className="flex items-center justify-between">
              <span>
                <button
                  onClick={() => decreaseQuantity(item)}
                  className=" text-red-400 font-bold   px-2 rounded-l-lg mr-2"
                >
                  -
                </button>
                {item.quantity}
                <button
                  onClick={() => increaseQuantity(item)}
                  className=" text-green-400 font-bold  px-2 rounded-r-lg ml-2 mr-2"
                >
                  +
                </button>
                {item.name}
              </span>
              <span>R${item.price}  x</span>
              <button
                onClick={() => onCancelItemClick(item.name)}

                className="text-red-400 font-bold px-2 rounded-l-lg "
              >
                Remover
              </button>
            </li>
          ))}
        </ul>

        <footer className='w-{100%} inset-0 z-10'>
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
              <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded" type="submit">
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
