import React, { useRef } from 'react';
import './style.scss';
import { useForm } from 'react-hook-form';
import authService from '../../service/Auth-service'
import {Link, useHistory } from 'react-router-dom';
import styled from 'styled-components';
import image from '../../homePage.png';


const Register = () => {

    const { register, handleSubmit, watch, formState: { errors } } = useForm();

    const password = useRef({});
    password.current = watch("password", "");

    let history = useHistory();

    const onSubmit = data => {
        // console.log(data);
        let from_data = {
            username: data.username,
            mobile: data.mobile,
            email: data.email,
            password: data.password
        }

        registers(from_data);

    }
    const registers = (data) => {
        authService.register(data).then(res => {
            // console.log(res)
            if (res.status == 201) {
                localStorage.setItem("user", JSON.stringify(res.data));
                history.push('/user/profile');
                // console.log(res.data);
                // console.log("ok");

            }

        }).catch(err => {
            // console.log(err.response.data);
            if (err) {
                alert(err.response.data.errors.details)
            }
        })
    }


    return (
        <Wrapper>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="modal-overlays" />
                <div className="modal-wrapper" aria-modal aria-hidden tabIndex={-1} role="Dialog">
                    <div className="container" >
                        <div className="base-container" >
                            <div className="header">Register</div>
                            <div className="content">
                                {/* <div className="image">
              <img src={loginImg} />
            </div> */}
                                <div className="form">
                                    <div className="form-group">
                                        <input type="text"
                                            name="username"
                                            placeholder="username"
                                            {...register("username", { required: true })} />
                                        {errors.username && (<span> username cannot be null !</span>)}
                                    </div>

                                    <div className="form-group">
                                        <input type="tel"
                                            name="mobile"
                                            placeholder="mobile"
                                            {...register("mobile", { required: true, minLength: 10, maxLength: 10 })} />

                                        {errors.mobile && errors.mobile.type === "required" && (<span> mobile cannot be null !</span>)}
                                        {errors.mobile && errors.mobile.type === "minLength" && (<span> Mobile must contain 10 numbers !</span>)}
                                        {errors.mobile && errors.mobile.type === "maxLength" && (<span> Mobile must contain 10 numbers !</span>)}
                                    </div>

                                    <div className="form-group">
                                        <input type="text"
                                            name="email"
                                            placeholder="email"
                                            {...register("email", { required: true, pattern: /^\S+@\S+$/i })} />

                                        {errors.email && errors.email.type === "required" && (<span> email cannot be null !</span>)}
                                        {errors.email && errors.email.type === "pattern" && (<span> Invalid email !</span>)}
                                    </div>

                                    <div className="form-group">
                                        <input type="password"
                                            name="password"
                                            placeholder="password"
                                            {...register("password", {
                                                required: true,
                                                validate: value => {
                                                    return (
                                                        [/[a-z]/, /[A-Z]/, /[0-9]/, /[^a-zA-Z0-9]/].every((pattern) =>
                                                            pattern.test(value)
                                                        )
                                                        // || "must include lower, upper, number, and special chars"
                                                    );
                                                }
                                            })} />
                                        {errors.password && errors.password.type === "required" && (<span> password cannot be null !</span>)}
                                        {errors.password && errors.password.type === "validate" && (<span> must include lower, upper, number, and special chars !</span>)}
                                    </div>

                                    <div className="form-group">
                                        <input type="password"
                                            name="confirmPassword"
                                            placeholder="Confirm Password"
                                            {...register("confirmPassword", {
                                                validate: value => value === password.current
                                            })}

                                        />
                                        {errors.confirmPassword && errors.confirmPassword.type === "validate" &&
                                            (<span> the passwords do not match !</span>)}
                                    </div>
                                </div>
                            </div>

                            <div className="footer">
                                <button type="submit" className="btn">
                                    Register
                                </button>
                                <div className='link'>
                                    <p>Already have an account ? <Link to='/login' >Login</Link></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </Wrapper>
    );
}

const Wrapper = styled.section`
    
    display:flex;
    justify-content:center;
    align-items:center;
    .container{
        width: 450px;
        margin: 0 auto;
        height: 40vw;
        display:flex;
        justify-content:center;
        margin-top:50px;
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
export default Register;