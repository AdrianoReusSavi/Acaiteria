import React from 'react';
import TableCard from '../../components/ModalTable/TableCard';

const Table = () => {
  return (
    <div className="flex p-9">
      <div className="flex flex-wrap py-7 gap-4">
        {[1, 2, 3, 4, 5, 6, 7, 8].map((tableNumber) => (
          <TableCard key={tableNumber} tableNumber={tableNumber} />
        ))}
      </div>
    </div>
  );
};

export default Table;