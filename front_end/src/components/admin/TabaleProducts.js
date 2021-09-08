import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import styled from 'styled-components';
import DropdownMenu from './Dropdown';
import { useProductsContext } from '../../context/product_context';
import { formatPrice } from '../../utils/helpers';
import { Link } from 'react-router-dom';
import { formatDate } from '../../utils/helpers';
import ProductService from '../../service/Product-service';

const TableProducts = () => {

    const { products, getValueProduct, removeItem } = useProductsContext();

    const tbnOnClickHandleEdit = (e, value, index) => {
        console.log(value)
        console.log(index)
        getValueProduct(value, index)
    }

    const tblOnClickHandleDelete = (e, value) => {
        let today = new Date();
        let data = {
            ...value,
            ['deletedAt']: today
        }

        if (window.confirm("Xoa product ?")) {
            ProductService.update(data.id, data).then(res => {
                if (res.status === 200) {
                    removeItem(data.id)
                    alert("Da xoa product !")
                }
            }).catch(err => {
                console.log(err)
            })
        }
    }


    return (
        <Wrapper>
            <div className='container'>
                <DropdownMenu />
                <div className='base-container'>
                    <Table >

                        {/* <div className='content'> */}
                        <TableHead>
                            <TableRow>
                                <TableCell>name</TableCell>
                                <TableCell>model</TableCell>
                                <TableCell>category</TableCell>
                                <TableCell>price</TableCell>
                                <TableCell>create_at</TableCell>
                                <TableCell>update_at</TableCell>
                                <TableCell>Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                products.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableCell>{value.name}</TableCell>
                                            <TableCell>{value.model}</TableCell>
                                            <TableCell>{value.categoryName}</TableCell>
                                            <TableCell>{formatPrice(value.price)}</TableCell>
                                            <TableCell>{formatDate(value.createdAt)}</TableCell>
                                            <TableCell>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                            <TableCell>
                                                <Link to="/admin/add/products" >
                                                    <div style={{ backgroundColor: '#32CD32', color: 'red' }}
                                                        className="ui animated button" tabIndex="0"
                                                        onClick={(event) => tbnOnClickHandleEdit(event, value, index)}

                                                    >
                                                        <div className="visible content">Edit</div>
                                                        <div className="hidden content">
                                                            <i className="edit icon"></i>
                                                        </div>
                                                    </div>
                                                </Link>

                                                <div style={{ backgroundColor: '#FE9A76', color: '#B03060' }}
                                                    className="ui vertical animated button" tabIndex="0"
                                                    onClick={(event) => tblOnClickHandleDelete(event, value)}
                                                >
                                                    <div className="visible content">Delete</div>
                                                    <div className="hidden content">
                                                        <i className="archive icon"></i>
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
        margin-left: -4rem;
        border-radius: 0.5rem;
        
        /* color: var(--clr-white); */
        /* align-items: center; */
    }

    @media (min-width: 900px) {
        
        .container {
            grid-template-columns: 300px 1fr;
        
        }
       
    }
`
export default TableProducts;