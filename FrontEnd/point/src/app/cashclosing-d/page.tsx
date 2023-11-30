import MuiTable from "@/components/report/MuiTable"
import React from 'react';
import Date from "@/components/report/Date";

    export default function CashClosing(){
        const DadosdaTabela = [ ];
        const Dados = [ ];

            return (
                <div>
                    <h1>Vendas</h1>
                    <Date data={Dados} />
                    <MuiTable data={DadosdaTabela} />
                </div>
        );
}




    
