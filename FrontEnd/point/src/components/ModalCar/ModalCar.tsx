import React, { useState } from 'react';

interface ProductProps {
  name: string;
  vlrfl: string;
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
  const [cartItem, setCartItems] = useState<CartItem[]>([]);


  const decreaseQuantity = (item: CartItem) => {
    const updatedCartItems = cartItem.map((cartItem) =>
      cartItem.name === item.name ? { ...cartItem, quantity: cartItem.quantity - 1 } : cartItem
    );
    setCartItems(updatedCartItems);
  };

  const increaseQuantity = (item: CartItem) => {
    const updatedCartItems = cartItem.map((cartItem) =>
      cartItem.name === item.name ? { ...cartItem, quantity: cartItem.quantity + 1 } : cartItem
    );
    setCartItems(updatedCartItems);
  };

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
                  className="bg-blue-500 hover:bg-blue-600 text-white py-1 px-2 rounded-l-lg"
                >
                  -
                </button>
                {item.quantity}
                <button
                  onClick={() => increaseQuantity(item)}
                  className="bg-blue-500 hover:bg-blue-600 text-white py-1 px-2 rounded-r-lg"
                >
                  +
                </button>
                x R${item.price}
              </span>
            </li>
          ))}
        </ul>

        <div className="fixed  bottom-52 justify-between">
          <span className="text-lg font-semibold">Total: R$</span>
          <span className="text-lg font-semibold">Resul</span>
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
