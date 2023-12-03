'use client'
import React, { useState } from 'react';
import OrderDetails from './OrderDetails';

interface TableCardProps {
  tableNumber: number;
}

const TableCard: React.FC<TableCardProps> = ({ tableNumber }) => {
  const [orders, setOrders] = useState<string[]>([]);
  const [status, setStatus] = useState('livre'); // Pode ser 'livre', 'ocupada', etc.
  const [newOrder, setNewOrder] = useState('');

  const addOrder = () => {
    if (newOrder.trim() !== '') {
      setOrders([...orders, newOrder]);
      setNewOrder(''); // Limpa o campo do novo pedido após adicioná-lo
    }
  };

  return (
    <div className={`card ${status}`}>
      <h2>Mesa {tableNumber}</h2>
      <p>Status: {status}</p>
      <button onClick={() => setStatus('ocupada')}>Ocupar</button>

      <div>
        <input
          type="text"
          value={newOrder}
          onChange={(e) => setNewOrder(e.target.value)}
          placeholder="Novo Pedido"
        />
        <button onClick={addOrder}>Adicionar Pedido</button>
      </div>

      <OrderDetails orders={orders} />
    </div>
  );
};

export default TableCard;