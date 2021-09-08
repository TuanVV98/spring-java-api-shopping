import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import styled from 'styled-components';
import DropdownMenu from './Dropdown';
import { formatDate } from '../../utils/helpers';
import { formatPrice } from '../../utils/helpers';
import { useEffect, useState } from 'react';
import { useUserContext } from '../../context/user_context';
import { account_status, get_users_url as url } from '../../utils/constants';
import TableUserProfile from './TableUserDetails';
import UserStatus from './UserStatus';
import UserService from '../../service/User-service';
// import 'bootstrap/dist/css/bootstrap.min.css';
const TableUser = () => {

    const { myUser, users, fetchUsers, updateUser } = useUserContext();
    const [show, setShow] = useState(false);
    const [userStatus, setUserStatus] = useState([]);
    const [userId, seUserId] = useState(1);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        fetchUsers(`${url}`)
    }, [url])

    const tbnOnClickHandleDetail = (e, value) => {
        seUserId(value.id)
    }

    const tbnOnClickHandleEdit = (e, index) => {
        let today = new Date();
        let data = {
            ...userStatus,
            ['deletedAt']: today
        }
        UserService.update(data.id, data).then(res => {
            if (res.status === 200) {
                updateUser(res.data.data, index)
                alert("Cập nhật thành công !")
            }
        }).catch(err => {
            throw err;
        })
    }
    console.log(userStatus)
    return (
        <Wrapper>
            <div className='container'>
                <DropdownMenu />
                <TableUserProfile userId={userId} show={show} handleClose={handleClose} />
                <div className='base-container'>
                    <Table >

                        {/* <div className='content'> */}
                        <TableHead>
                            <TableRow>
                                <TableCell>username</TableCell>
                                <TableCell>mobile</TableCell>
                                <TableCell>email</TableCell>
                                <TableCell>status</TableCell>
                                <TableCell>role</TableCell>
                                <TableCell>registered_at</TableCell>
                                <TableCell>updated_at</TableCell>
                                <TableCell>deleted_at</TableCell>
                                <TableCell>profile</TableCell>
                                <TableCell>action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                users.map((value, index) => {
                                    return (
                                        <TableRow key={index}>
                                            <TableCell>{value.username}</TableCell>
                                            <TableCell>{value.mobile}</TableCell>
                                            <TableCell>{value.email}</TableCell>
                                            <TableCell>{
                                                value.status === 'NOT_ACTIVE' || value.role === 'ROLE_ADMIN' ?
                                                    value.status :
                                                    (
                                                        <UserStatus value={value} setUserStatus={setUserStatus} />
                                                    )}</TableCell>
                                            <TableCell>{value.role.slice(5)}</TableCell>
                                            <TableCell>{formatDate(value.registeredAt)}</TableCell>
                                            <TableCell style={{ color: 'white' }}>{value.updatedAt ? formatDate(value.updatedAt) : 'N/A'}</TableCell>
                                            <TableCell>{value.deletedAt ? formatDate(value.deletedAt) : 'N/A'}</TableCell>
                                            <TableCell>
                                                <button type="button" className="btn btn-primary"
                                                    onClick={(e) => {
                                                        handleShow()
                                                        tbnOnClickHandleDetail(e, value)
                                                    }}
                                                >Detail</button>
                                            </TableCell>
                                            <TableCell>
                                                {
                                                   value.status === 'NOT_ACTIVE' || value.role === 'ROLE_ADMIN' ?
                                                        'N/A' : (
                                                            <button type="button"
                                                                className={'btn btn-primary notVisibility'}
                                                                onClick={(e) => tbnOnClickHandleEdit(e, index)}
                                                            >Edit</button>
                                                        )
                                                }


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

/* backgroundColor: '#32CD32', color: 'red' */
    /* width: 90vh; */
    height: 95vh;
    background:linear-gradient(135deg, #8254EA 0%, #E86DEC 100%);
            
    .container {
        display: grid;
        gap: 3rem 1.5rem;
        margin: 2rem auto;
    }

    .base-container {
        /* margin: 0px; */
        padding: 5px 2px 2px 0px; 
        max-width: 1200px; 
        margin: 0 auto;
        margin-left: -7.7rem;
        border-radius: 0.5rem;
        
        /* color: var(--clr-white); */
        /* align-items: center; */
    }
    article {
        border: 1px solid var(--clr-grey-8);
        border-radius: var(--radius);
        padding: 1.5rem 3rem;
    }
    h4,
    h5,
    p {
        display: grid;
        grid-template-columns: 200px 1fr;
    }
    p {
        text-transform: capitalize;
    }
    .status-input {
        border-color: transparent;
        font-size: 1rem;
        text-transform: capitalize;
        padding: 0.25rem 0.5rem;
        width: 125px;
    }

    .visibleCursor { 
        cursor: default;
        background-color:gold;
        visibility: hidden;
    }

    .notVisibility{
        background-color:green;
        visibility: visible;
    }
    @media (min-width: 900px) {
        .container {
            grid-template-columns: 300px 1fr;
        
        }
       
    }
`
export default TableUser;