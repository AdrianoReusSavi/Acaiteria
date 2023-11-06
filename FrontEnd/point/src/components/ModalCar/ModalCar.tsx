
import React, { useState,useEffect } from 'react';
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
}

const CartModal: React.FC<CartModalProps> = ({ isOpen, product, cartItems, onClose }) => {
  const [cartItem, setCartItems] = useState<CartItem[]>(cartItems);

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

  const total = cartItem.reduce((acc, item) => acc + item.price * item.quantity, 0);

  console.log('Valores iniciais do carrinho:', cartItems);
  return (
    <div className="fixed inset-0 flex items-center justify-end pt-9 pb-9 pr-9">
      <div className="z-2 absolute inset-0 bg-gray-800 opacity-50" />
      <div className="z-10 h-full w-2/5 rounded-lg bg-white p-6">
        <h2 className="text-lg font-semibold mb-4">Carrinho de Compras</h2>

        <ul className="space-y-2">
          {cartItems.map((item) => (
            <li key={item.name} className="flex items-center justify-between">
              <span>{item.name}</span>
              <span>
                <button
                  onClick={() => decreaseQuantity(item)}
                  className="bg-blue-500 hover:bg-blue-600 text-white py-1 px-2 rounded-l-lg mr-2"
                >
                  -
                </button>
                {item.quantity}
                <button
                  onClick={() => increaseQuantity(item)}
                  className="bg-blue-500 hover:bg-blue-600 text-white py-1 px-2 rounded-r-lg ml-2 mr-2"
                >
                  +
                </button>
                x R${item.price}
              </span>
            </li>
          ))}
        </ul>

        <div className="fixed  bottom-52  items-center justify-between">
          <span className="text-lg font-semibold">Total: </span>
          <span className="text-lg font-semibold">R${total}</span>
        

        </div>
        <div className="fixed bottom-32">
          <p className="text-lg">Formas de Pagamento:</p>
          <ul className="mt-2 space-x-4">
            <button className='bg-gray-400 rounded-lg py-2 px-4' value='credito'>Crédito</button>
            <button className='bg-gray-400 rounded-lg py-2 px-4' value='debito'>Débito</button>
            <button className='bg-gray-400 rounded-lg py-2 px-4' value='dinheiro'>Dinheiro</button>
            <button className='bg-gray-400 rounded-lg py-2 px-4' value='pix'>Pix</button>
          </ul>
        </div>
        <div className='fixed bottom-12 right-12 space-x-2'>
          <button
            className="mt-4 bg-gray-300 hover:bg-gray-400 py-2 px-4 rounded-lg"
            onClick={onClose}
          >
            Fechar
          </button>
          <button
            className="mt-4 bg-green-300 hover:bg-green-400 py-2 px-4 rounded-lg"
            onClick={onClose}
          >
            Pagar
          </button>
        </div>
      </div>
    </div>
  );
};

export default CartModal;
