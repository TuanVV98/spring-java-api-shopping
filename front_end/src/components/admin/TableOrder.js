import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import styled from 'styled-components';
import { useForm } from 'react-hook-form';
import DropdownMenu from './Dropdown';
import { formatDate } from '../../utils/helpers';
import { formatPrice } from '../../utils/helpers';
import { useState } from 'react';
import { useOrdersContext } from '../../context/order_context';

import TableOrderDetails from './TableOrderDetails';
import OrderStatus from './OrderStatus';
import OrderService from '../../service/Order-service';
// import 'bootstrap/dist/css/bootstrap.min.css';
const TableOrder = () => {

    const { orders, updateOrders } = useOrdersContext();
    const [show, setShow] = useState(false);
    const [orderStatus, setOrderStatus] = useState([]);
    const [orderId, setOrderId] = useState(1);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const tbnOnClickHandleEdit = (e, value, index) => {
        let today = new Date();
        let data = {
            ...orderStatus,
            ['updatedAt']: today
        }

        console.log(data)

        if (window.confirm('Ban co muon cap nhat lai status ?')) {
            OrderService.update_order(data.id, data).then(res => {
                if (res.status === 200) {
                    updateOrders(res.data.data, index)
                    alert("Cap nhat thanh cong !")
                }
            }).catch(err =>{
                throw err
            })

        }
    }
    const tbnOnClickHandleDetail = (e, value) => {
        console.log(value)
        setOrderId(value.id)
    }

    console.log(orderStatus)
    return (
        <Wrapper>
            <div className='container'>
                <DropdownMenu />
                <TableOrderDetails orderId={orderId} show={show} handleClose={handleClose} />
                <div className='base-container'>
                    <Table >

                        {/* <div className='content'> */}
                        <TableHead>
                            <TableRow>
                                <TableCell>full name</TableCell>
                                <TableCell>mobile</TableCell>
                                <TableCell>address</TableCell>
                                <TableCell>subTotal</TableCell>
                                <TableCell>created_at</TableCell>
                                <TableCell>updated_at</TableCell>
                                <TableCell>detail</TableCell>
                                <TableCell>status</TableCell>
                                <TableCell>action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                orders.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableCell>{value.fullName}</TableCell>
                                            <TableCell>{value.mobile}</TableCell>
                                            <TableCell>{value.address}</TableCell>
                                            <TableCell>{formatPrice(value.subTotal)}</TableCell>
                                            <TableCell>{formatDate(value.createdAt)}</TableCell>
                                            <TableCell style={{ color: 'white' }}>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                            <TableCell>
                                                <button type="button" className="btn btn-primary"
                                                    onClick={(e) => {
                                                        handleShow()
                                                        tbnOnClickHandleDetail(e, value)
                                                    }}>Details</button>
                                            </TableCell>
                                            <TableCell>
                                                <OrderStatus value={value} setOrderStatus={setOrderStatus} />
                                            </TableCell>
                                            <TableCell>
                                                <div style={{ backgroundColor: '#32CD32', color: 'red' }}
                                                    className="ui animated button" tabIndex="0"
                                                    onClick={(event) => tbnOnClickHandleEdit(event, value, index)}

                                                >
                                                    <div className="visible content">Edit</div>
                                                    <div className="hidden content">
                                                        <i className="edit icon"></i>
                                                    </div>
                                                </div>
                                            </TableCell>

                                        </TableRow>

                                    )
                                })
                            }
                        </TableBody>
                    </Table>
                </div>
            </div>
        </Wrapper>
    )
}


const Wrapper = styled.div`


    height: 95vh;
    background:linear-gradient(135deg, #8254EA 0%, #E86DEC 100%);
            
    .container {
        display: grid;
        gap: 3rem 1.5rem;
        margin: 2rem auto;
    }

    .base-container {
        margin: 0px;
        padding: 10px 0px 0px 0px; 
        max-width: 1200px;
        /* margin: 0 auto; */
        margin-left: -5rem;
        border-radius: 0.5rem;
        
        /* color: var(--clr-white); */
        /* align-items: center; */
    }

    .status-input {
        border-color: transparent;
        font-size: 1rem;
        text-transform: capitalize;
        padding: 0.25rem 0.5rem;
        width: 120px;
    }
    .visibleCursor { 
        /* cursor: default;
        background-color:gold; */
        display:none;
    }
    @media (min-width: 900px) {
        
        .container {
            grid-template-columns: 300px 1fr;
        
        }
       
    }
`
export default TableOrder;