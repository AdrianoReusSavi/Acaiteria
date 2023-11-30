import MuiTableMonthly from "@/components/report/MuiTableMonthly"
import React from 'react';

    export default function CashClosingM(){
        const DadosdaTabela = [ ];
        const Dados = [ ];

            return (
                <div>
                    <h1>Balanceamento</h1>
                    <MuiTableMonthly data={DadosdaTabela} />
                </div>
        );
}
