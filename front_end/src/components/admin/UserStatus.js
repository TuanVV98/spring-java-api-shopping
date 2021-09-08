import { account_status } from '../../utils/constants';
import { useForm } from 'react-hook-form';
import { useEffect, useState } from 'react';

const UserStatus = ({ value, setUserStatus }) => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [status, setStatus] = useState(value);
    
    // const [indexStatus, setIndexStatus] = useState(0);
    const hanldeOnChange = (event) => {
        event.preventDefault()
        const { name, value } = event.target;

        setStatus({
            ...status, [name]: value
        });

        setUserStatus({
            ...status, [name]: value
        })
      
    }
    const statusSelect = register("status", { required: true });
    return (
        <div>
            <select
                name='status'
                id='status'
                className='status-input'
                value={status.status}
                {...statusSelect}
                onChange={(e) => {
                    statusSelect.onChange(e);
                    hanldeOnChange(e);
                }}
            >
                {
                    account_status.map((val, index) => {
                        return (
                            <option key={index}
                                // className='visibleCursor'
                                value={val.name}>{val.name}</option>

                        )
                    })
                }
            </select>
        </div>
    )
}

export default UserStatus;