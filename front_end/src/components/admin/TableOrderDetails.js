import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { FaTimes } from 'react-icons/fa';
import { formatDate } from '../../utils/helpers';
import { formatPrice } from '../../utils/helpers';
import OrderService from '../../service/Order-service';
import { useState } from 'react';
import { useEffect } from 'react';

const TableOrderDetails = ({ orderId, show, handleClose }) => {

    const [orderDetais, setOrderDetails] = useState([]);

    useEffect(() => {
        OrderService.findByOrderId(orderId).then(res => {
            if (res.status === 200) {
                setOrderDetails(res.data.data)
            }
        }).catch(err => {
            console.log(err)
        })
    }, [orderId])

    // console.log(orderDetais)
    return (
        <div
            className={`${show ? 'modal-overlay show-modal' : 'modal-overlay'
                }`}
        >
            <div className='modal-container'>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>product name</TableCell>
                            <TableCell>price</TableCell>
                            <TableCell>quantity</TableCell>
                            <TableCell>created_at</TableCell>
                            <TableCell>updated_at</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            orderDetais.map((value, index) => {
                                return (
                                    <TableRow key={index}>
                                        <TableCell>{value.productName}</TableCell>
                                        <TableCell>{formatPrice(value.price)}</TableCell>
                                        <TableCell>{value.quantity}</TableCell>
                                        <TableCell>{formatDate(value.createdAt)}</TableCell>
                                        <TableCell>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                    </TableRow>
                                )
                            })
                        }

                    </TableBody>
                </Table>
                <button className='close-modal-btn' onClick={handleClose}>
                    <FaTimes></FaTimes>
                </button>
            </div>
        </div>
    )
}


export default TableOrderDetails;