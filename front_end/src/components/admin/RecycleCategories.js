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
import { recycle_categories_url as url } from '../../utils/constants';
import { useEffect } from 'react';

const RecycleCategories = () => {

    const {
        recycle_categories: categories,
        fetchRecycleCategories,
        addToCategory,
        removeRecycleItem
    } = useCategoeiesContext();


    useEffect(() => {
        fetchRecycleCategories(`${url}`)
    }, [url])

    const tbnOnClickHandleRestore = (e, value) => {
        e.stopPropagation();
        let data = {
            ...value,
            ['deletedAt']: null
        }

        if (window.confirm("Khôi phục category ?")) {
            CateService.update(data.id, data).then(res => {
                if (res.status === 200) {
                    removeRecycleItem(data.id)
                    addToCategory(res.data.data)
                    alert("Khoi phuc thanh cong !")
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
                                <TableCell>updated_at</TableCell>
                                <TableCell>deteled_at</TableCell>
                                <TableCell>action</TableCell>
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
                                            <TableCell>{formatDate(value.deletedAt)}</TableCell>
                                            <TableCell>
                                                <button type="button" className="btn btn-primary"
                                                    onClick={(e) => tbnOnClickHandleRestore(e, value)}
                                                >data recovery</button>
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
export default RecycleCategories;