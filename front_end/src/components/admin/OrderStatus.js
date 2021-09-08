import { order_status } from '../../utils/constants';
import { useForm } from 'react-hook-form';
import { useEffect, useState } from 'react';

const OrderStatus = ({ value, setOrderStatus }) => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [status, setStatus] = useState(value);
    const [indexStatus, setIndexStatus] = useState(0);
    const hanldeOnChange = (event) => {
        event.preventDefault()
        const { name, value } = event.target;
        
        setStatus({
            ...status, [name]: value
        });

        setOrderStatus({
            ...status, [name]: value
        })

    }

    console.log(status)
    useEffect(() => {
        let tempValue = order_status.filter((item) => item.name === status.status)
        console.log(tempValue[0])
        setIndexStatus(tempValue[0])
    }, [value.status])

    console.log(indexStatus)
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
                    order_status.map((val, index) => {
                        return (
                            <option key={index}
                                className={`${(index + 1) < indexStatus.id ? 'visibleCursor' : null
                                    }`}
                                // className='visibleCursor'
                                value={val.name}>{val.name}</option>

                        )
                    })
                }
            </select>
        </div>
    )
}

export default OrderStatus;