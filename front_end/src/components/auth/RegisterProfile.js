import './style.scss';
import { useForm } from 'react-hook-form';
import authService from '../../service/Auth-service'
import styled from 'styled-components';
import image from '../../homePage.png';
import { useHistory } from 'react-router-dom';


const RegisterProfile = () => {


    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const history = useHistory();
    const onSubmit = (data) => {
        const current = JSON.parse(localStorage.getItem("user"));

        let from_data = {
            firstName: data.firstname,
            lastName: data.lastname,
            middleName: data.middlename,
            gender: data.gender,
            userID: current.data.id
        }

        registers(from_data);
        console.log(current.data.id);
        console.log(data);
    }

    const registers = (data) => {
        authService.registerProfile(data).then(res => {
            if (res.status === 201) {
                localStorage.removeItem("user")
                history.push('/')
                alert("ok !");
            }
        }).catch(err => {
            console.log(err);
        })
    }


    return (
        <Wrapper>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="modal-overlays" />
                <div className="modal-wrapper" aria-modal aria-hidden tabIndex={-1} role="Dialog">
                    <div className="container" >
                        <div className="base-container" >
                            <div className="header">User Profile</div>
                            <div className="content">

                                <div className="form">
                                    <div className="form-group">
                                        <input type="text" name="firstname" placeholder="first name"
                                            {...register("firstname", { required: true })}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <input type="text" name="middlename" placeholder="middle name"
                                            {...register("middlename", { required: true })}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <input type="text" name="lastname" placeholder="last name"
                                            {...register("lastname", { required: true })}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <select {...register("gender", { required: true })} >
                                            <option value="MALE">MALE</option>
                                            <option value="FEMALE">FEMALE</option>
                                            <option value="UNKNOWN">UNKNOWN</option>
                                        </select>
                                    </div>
                                    <div className="footer">
                                        <button type="submit" className="btn">
                                            Submit
                                        </button>
                                    </div>
                                </div>
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
        width: 450px;
        margin: 0 auto;
        height: 35vw;
        display:flex;
        justify-content:center;
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
export default RegisterProfile;