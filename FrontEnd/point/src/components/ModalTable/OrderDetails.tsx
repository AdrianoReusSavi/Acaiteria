
interface OrderDetailsProps {
  orders: string[]; // ou o tipo apropriado para seus dados de pedido
}

const OrderDetails: React.FC<OrderDetailsProps> = ({ orders }) => {
  return (
    <div>
      <h3>Pedidos:</h3>
      <ul>
        {orders.map((order, index) => (
          <li key={index}>{order}</li>
        ))}
      </ul>
    </div>
  );
};

export default OrderDetails;