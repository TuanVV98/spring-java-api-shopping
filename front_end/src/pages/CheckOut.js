import { Table, TableBody, TableCell, TableHead, TableRow } from "@material-ui/core"
import { useState } from "react";
import { useEffect } from "react";
import styled from "styled-components";
import TableOrderDetails from "../components/admin/TableOrderDetails";
import { useUserContext } from "../context/user_context";
import OrderService from "../service/Order-service";
import { formatDate, formatPrice } from "../utils/helpers";
import { user_url as url } from '../utils/constants';

const CheckOut = () => {
    const { myUser, fetchSingleUser } = useUserContext();
    const [order, setOrder] = useState([]);
    const [show, setShow] = useState(false);
    const [orderId, setOrderId] = useState(1);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    // console.log(myUser)


    useEffect(() => {
        if (myUser) {
            OrderService.findByUserId(myUser.id).then(res => {
                if (res.status === 200) {
                    setOrder(res.data.data)
                }
            }).catch(err => {
                console.log(err)
            })
        } else {
            fetchSingleUser(`${url}`)
        }
    }, [myUser, url])

    const tbnOnClickHandleDetail = (e, value) => {
        setOrderId(value.id)
    }
    return (
        <Wrapper className='page'>
            <div className='section-center products'>
                <TableOrderDetails orderId={orderId} show={show} handleClose={handleClose} />
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>full name</TableCell>
                            <TableCell>mobile</TableCell>
                            <TableCell>address</TableCell>
                            <TableCell>subTotal</TableCell>
                            <TableCell>createdAt</TableCell>
                            <TableCell>updatedAt</TableCell>
                            <TableCell>status</TableCell>
                            <TableCell>detail</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            order.map((value, index) => {
                                return (
                                    <TableRow key={index}>
                                        <TableCell>{value.fullName}</TableCell>
                                        <TableCell>{value.mobile}</TableCell>
                                        <TableCell>{value.address}</TableCell>
                                        <TableCell>{formatPrice(value.subTotal)}</TableCell>
                                        <TableCell>{formatDate(value.createdAt)}</TableCell>
                                        <TableCell>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                        <TableCell>{value.status}</TableCell>
                                        <TableCell>

                                            <button type="button" className="btn btn-primary"
                                                onClick={(e) => {
                                                    handleShow()
                                                    tbnOnClickHandleDetail(e, value)
                                                }}
                                            >Details</button>
                                        </TableCell>
                                    </TableRow>
                                )
                            })
                        }
                    </TableBody>
                </Table>
            </div>
        </Wrapper>
    )
}

const Wrapper = styled.div`
    //background:linear-gradient(135deg, #8254EA 0%, #E86DEC 100%);
    .products{
        display: grid;
        gap: 3rem 1.5rem;
        margin: 4rem auto;
    }

    .status-input {
        border-color: transparent;
        font-size: 1rem;
        text-transform: capitalize;
        padding: 0.25rem 0.5rem;
        width: 120px;
        border-radius: 0.25rem;
        border-color: tomato;
    }
    .btn-primary{
        background-color: blue;
    }
    @media (min-width: 768px) {
        /* .products {
            grid-template-columns: 200px 1fr;
        } */
    }

`
export default CheckOut;