import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { FaTimes } from 'react-icons/fa';
import { formatDate } from '../../utils/helpers';
import { formatPrice } from '../../utils/helpers';
import UserService from '../../service/User-service';
import { useState } from 'react';
import { useEffect } from 'react';

const TableUserProfile = ({ userId, show, handleClose }) => {

    const [userProfile, setUserProfile] = useState([]);

    useEffect(() => {
        UserService.findByUserId(userId).then(res => {
            if (res.status === 200) {
                setUserProfile(res.data.data)
            }
        }).catch(err => {
            console.log(err)
        })
    }, [userId])

    // console.log(userProfile)
    return (
        <div
            className={`${show ? 'modal-overlay show-modal' : 'modal-overlay'
                }`}
        >
            <div className='modal-container'>
                
                <article>
                    <h5>
                        FullName : <p>{userProfile.firstName + ' ' + userProfile.middleName + ' ' + userProfile.lastName}</p>
                    </h5>
                    <h5>
                        Gender : <p>{userProfile.gender}</p>
                    </h5>
                </article>
                <button className='close-modal-btn' onClick={handleClose}>
                    <FaTimes></FaTimes>
                </button>
            </div>
        </div>
    )
}


export default TableUserProfile;