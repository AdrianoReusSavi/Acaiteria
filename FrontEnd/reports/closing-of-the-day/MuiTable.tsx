import { TableContainer, Table, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material'
import './ReportTable.css';


const MuiTable = () => {
    return <TableContainer component={Paper} sx={{ maxHeight: '800px'}}>
        <Table aria-label='simple table' stickyHeader>
            <TableHead>
                <TableRow>
                    <TableCell className='column'>NVendas</TableCell>
                    <TableCell className='column'>Metodo</TableCell>
                    <TableCell className='column'>Valor</TableCell>
                    <TableCell className='column'>Hora</TableCell>
                    <TableCell className='column' align='center'>Status</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {TableData.map((row) => (
                    <TableRow
                        key={row.NVendas}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell className='cell'>{row.NVendas}</TableCell>
                            <TableCell className='cell'>{row.Metodo}</TableCell>
                            <TableCell className='cell'>{row.Valor}</TableCell>
                            <TableCell className='cell'>{row.Hora}</TableCell>
                            <TableCell className='cell' align='center'>{row.Status}</TableCell>
                        </TableRow>
                    ))}
            </TableBody>
        </Table>
    </TableContainer>

}

const TableData = 
[{
    "NVendas": 1,
    "Metodo": "Euro",
    "Valor": "$0.12",
    "Hora": "8:26 AM",
    "Status": false,
    "ip_address": "62.214.17.150"
  }, {
    "NVendas": 2,
    "Metodo": "Rupiah",
    "Valor": "$1.89",
    "Hora": "6:58 AM",
    "Status": false,
    "ip_address": "224.207.230.217"
  }, {
    "NVendas": 3,
    "Metodo": "Dollar",
    "Valor": "$6.03",
    "Hora": "9:06 AM",
    "Status": false,
    "ip_address": "152.226.20.12"
  }, {
    "NVendas": 4,
    "Metodo": "Yuan Renminbi",
    "Valor": "$0.29",
    "Hora": "5:21 PM",
    "Status": true,
    "ip_address": "221.90.201.241"
  }, {
    "NVendas": 5,
    "Metodo": "Rial",
    "Valor": "$5.81",
    "Hora": "9:17 AM",
    "Status": false,
    "ip_address": "95.158.248.144"
  }, {
    "NVendas": 6,
    "Metodo": "Peso",
    "Valor": "$7.21",
    "Hora": "5:30 AM",
    "Status": true,
    "ip_address": "235.59.143.190"
  }, {
    "NVendas": 7,
    "Metodo": "Rupiah",
    "Valor": "$7.04",
    "Hora": "10:49 AM",
    "Status": true,
    "ip_address": "9.138.83.102"
  }, {
    "NVendas": 8,
    "Metodo": "Bolivar",
    "Valor": "$4.59",
    "Hora": "9:28 AM",
    "Status": true,
    "ip_address": "56.64.218.152"
  }, {
    "NVendas": 9,
    "Metodo": "Yuan Renminbi",
    "Valor": "$6.05",
    "Hora": "7:30 AM",
    "Status": false,
    "ip_address": "91.144.54.28"
  }, {
    "NVendas": 10,
    "Metodo": "Euro",
    "Valor": "$2.42",
    "Hora": "10:04 PM",
    "Status": true,
    "ip_address": "58.237.89.230"
  }]

  export default MuiTable;
  
