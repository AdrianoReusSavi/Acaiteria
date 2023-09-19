import React from "react";

const CardTb: React.FC<{ count: number }> = ({ count }) => {
  const divs = [];

  for (let i = 1; i <= count; i++) {
    divs.push(

          <div className="w-44 h-48 flex flex-col items-center justify-center bg-gray-200 p-15 hover:scale-105">
            <h1>Mesa {i}</h1>
          </div>
       
    );
  }

  return <>{divs}</>;
};

export default CardTb;