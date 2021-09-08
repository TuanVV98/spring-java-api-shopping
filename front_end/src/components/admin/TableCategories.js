import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import styled from 'styled-components';
import DropdownMenu from './Dropdown';
import { Link } from 'react-router-dom';
import { useCategoeiesContext } from '../../context/categories_context';
import CateService from '../../service/Cate-service';
import { formatDate } from '../../utils/helpers';

const TableCategories = () => {
    const {
        categories,
        getValueCategory,
        removeItem,
        addToRecycleCategory
    } = useCategoeiesContext();


    const tbnOnClickHandleEdit = (e, value, index) => {
        getValueCategory(e, value, index)
    }

    const tblOnClickHandleDelete = (e, value) => {
        let today = new Date();
        // let date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate()
        //     + ' ' + today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();

        let data = {
            ...value,
            ['deletedAt']: today
        }
 
        if (window.confirm("Xac nhan xoa ?")) {
            CateService.update(value.id, data).then(res => {
                if (res.status === 200) {
                    removeItem(res.data.data.id)
                    addToRecycleCategory(res.data.data)
                    alert("Xoa thanh cong")
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
                                <TableCell>create_at</TableCell>
                                <TableCell>update_at</TableCell>
                                <TableCell>Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                categories.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableCell>{value.name}</TableCell>
                                            <TableCell>{formatDate(value.createdAt)}</TableCell>
                                            <TableCell>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                            <TableCell>
                                                <Link to="/admin/add/categories" >
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
        max-width: 900px;
        /* margin: 0 auto; */
        margin-left: 1.5rem;
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
export default TableCategories;