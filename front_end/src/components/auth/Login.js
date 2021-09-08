import './style.scss';
import { useForm } from 'react-hook-form';
import authService from '../../service/Auth-service';
import styled from 'styled-components';
import image from '../../homePage.png';
import { useUserContext } from '../../context/user_context';
import { user_url as url } from '../../utils/constants';
import { Link, useHistory } from 'react-router-dom';
import { useState } from 'react';

const Login = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const { myUser, fetchSingleUser } = useUserContext();
    const [error, setError] = useState('')
    const history = useHistory();

    const onSubmit = data => {
        console.log(data)
        let form_data = {
            email: data.username,
            password: data.password
        }

        authService.login(form_data).then(response => {
            console.log(response.data.data.token);
            if (response.data.data.token) {
                localStorage.setItem("access_token", JSON.stringify(response.data.data));
                fetchSingleUser(`${url}`)
                
                history.push('/')
            }
        }).catch(err => {
            if (err) {
                setError(err.response.data.errors.details);
            }
            throw err;
        })
    }

    return (
        <Wrapper>

            <form onSubmit={handleSubmit(onSubmit)} >
                <div className="modal-overlays" />
                <div className="modal-wrapper" aria-modal aria-hidden tabIndex={-1} role="Dialog">
                    <div className="container" >
                        <div className='base-container' >
                            <div className='header'>Login</div>
                            {
                                error !== '' ?
                                    <div className="erros">{error}</div> : null
                            }
                            <div className='content'>
                                <div className='form'>
                                    <div className='form-group'>
                                        <input type='text' name='keyword' placeholder='email '
                                            {...register("username", { required: true })} />
                                        {errors.username && (<span> Email cannot be null !</span>)}
                                    </div>
                                    <div className='form-group'>
                                        <input type='password' name='password' placeholder='password'
                                            {...register("password", { required: true })} />
                                        {errors.password && (<span> Password cannot be null !</span>)}
                                    </div>
                                </div>
                            </div>
                            <div className='footer'>
                                <button type='submit' className='btn'>
                                    Login
                                </button>
                                <div className='link'>
                                    <p>Don't have an account ? <Link to='/register' >Register</Link></p>
                                </div>
                                <br />

                            </div>
                        </div>
                    </div>
                </div>

            </form>

        </Wrapper>
    )
}

const Wrapper = styled.section`
/* width:100%;
    height:100vh; */
    
    display:flex;
    justify-content:center;
    align-items:center;
    .container{
        /* background: #0e101c; */
        width: 450px;
        margin: 0 auto;
        /* border: 1px solid black; */
        height: 25vw;
        /* border-radius: 0.5rem; */
        /* border-color: tomato; */
        display:flex;
        justify-content:center;
        /* align-items:center; */
        margin-top:100px;
        background: var(--clr-white);
        border-radius: var(--radius);
        box-shadow: var(--dark-shadow);
    }
    .modal-overlays {
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1040;
        width: 100%;
        height: 100vh;
        background-color: #000;
        /* opacity: 0.7; */
        background-image:url(${image});
        background-repeat:no-repeat;
        background-size:cover;
    }

    .modal-wrapper {
        position: fixed;
        top: 0px;
        left: 0;
        z-index: 1050;
        width: 100%;
        height: 100%;
        overflow-x: hidden;
        overflow-y: auto;
        outline: 0;
        
    }

    .modal {
        z-index: 100;
        background: white;
        position: relative;
        margin: 1.75rem auto;
        border-radius: 3px;
        max-width: 500px;
        padding: 2rem;
        border:1px solid red;
        border-radius:0.25rem;
    }

    

`
export default Login;