import pdfMake from 'pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts'
import React, { useState, useEffect } from "react";
import ToggleSwitch from "@/components/switch";
import styled from "styled-components";

function gerarPDF(perDay){
    pdfMake.vfs = pdfFonts.pdfMake.vsf;

    const reportTitle = [
        {
            text: 'Produtos',
            fontSize: 15,
            bold: true,
            margin: [15, 20, 0, 45]

        }
    ];

    const details = [
        {
            table: {
                headerRows: 1,
                widths: [*, *, *, *],
                body: [
                    [
                        {text: 'Código', style: 'tableHeader', fontSize: 10},
                        {text: 'Descrição', style: 'tableHeader', fontSize: 10},
                        {text: 'Quantidade', style: 'tableHeader', fontSize: 10},
                        {text: 'Valor', style: 'tableHeader', fontSize: 10},

                    ]
                ]


            }
            layout: 'headerLineOnly'
        }
    ];

    const rodape (currentPage, pageCount){
        return[
            text: currentPage + ' / ' + pageCount,
            alignment: 'right',
            fontSize: 9,
            margin: [0, 10, 20, 0]
        ]
    }

    const docDefinitios = {
        pageSize: 'A4',
        pageMargins: [15, 50, 15, 40],

        header: [reportTitle],
        content: [details],
        footer: [rodape]
    }

    pdfMake.createPdf(docDefinitios).download();


}

document.addEventListener('DOMContentLoaded', function () {
    const generatePdfButton = document.getElementById('generatePdfButton');

    if (generatePdfButton) {
        generatePdfButton.addEventListener('click', () => {
            gerarPDF();
        });
    }
});

function gerarPDF() {
    const doc = new pdfmake/();

    // Add content to the PDF
    doc.text('PDF Estoque!', 10, 10);

    // Save the PDF
    doc.save('gerar-pdf.pdf');

}




export default gerarPDF;