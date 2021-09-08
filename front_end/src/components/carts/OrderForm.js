import styled from "styled-components";
import { useForm } from "react-hook-form";
import { FaTimes } from 'react-icons/fa';
import { user_url as url } from "../../utils/constants";
import { useGlobalContext } from "../../context/app_context";
import { useCartContext } from "../../context/cart_context";
import { useUserContext } from "../../context/user_context";
import { useEffect } from "react";
import OrderService from "../../service/Order-service";

const OrderForm = () => {

  const { isModalOpen, closeModal } = useGlobalContext();
  const { register, handleSubmit, watch, formState: { errors } } = useForm();
  const { cart, total_amount, clearCart } = useCartContext();

  const { myUser, fetchUser } = useUserContext();
  // useEffect(() => {
  //   fetchUser(`${url}`)
  // }, [])

  console.log(myUser)

  const onSubmit = (data) => {
    // console.log(data)
    let form_data = {
      subTotal: total_amount,
      fullName: data.name,
      mobile: data.mobile,
      address: data.address,
      createdAt: new Date(),
      userId: myUser.id
    }

    OrderService.create_order(form_data).then(res => {
      // console.log(res.data.data)
      if (res.status === 201) {
        cart.map((value) => {
          const { id, amount, price } = value
          let order = {
            price: price,
            quantity: amount,
            productId: id,
            createdAt: new Date(),
            orderId: res.data.data.id
          }
          create_order_details(order)
        })
        clearCart();
        alert('OK')
        closeModal()
      }
    }).catch(err => {
      console.log(err)
    })

    // console.log(form_data)
  }

  const create_order_details = (data) => {
    OrderService.create_order_details(data).then(res => {
      // console.log('ok')
    }).catch(err => {
      console.log(err)
    })
  }

  const yourNameOrderFiled = register("name", { required: true });
  const yourEmailOrderFiled = register("mobile", { required: true });
  const yourAddressOrderFiled = register("address", { required: true });
  return (
    <Wrapper>
      <div

        className={`${isModalOpen ? 'modal-overlay show-modal' : 'modal-overlay'
          }`}
      >
        <div className='modal-container'>

          <section className="contact-page">
            <form className="form contact-form"
              onSubmit={handleSubmit(onSubmit)}>
              <div className="form-group">
                <label htmlFor="name">your name</label>
                <input type="text" name="name" id="name"
                  {...yourNameOrderFiled}
                />
              </div>
              <div className="form-group">
                <label htmlFor="mobile">your phone number</label>
                <input type="text" name="mobile" id="mobile"
                  {...yourEmailOrderFiled}
                />
              </div>
              <div className="form-group">
                <label htmlFor="address">address</label>
                <textarea name="address" id="address"
                  {...yourAddressOrderFiled}
                />
              </div>
              <div className="footer">
                <button type="submit" className="btn">
                  Submit
                </button>
              </div>
            </form>

          </section>

          <button className='close-modal-btn' onClick={closeModal}>
            <FaTimes></FaTimes>
          </button>
        </div>
      </div>
    </Wrapper>
  )
}

const Wrapper = styled.section`
    /* margin-top: -17rem; */
    display: flex;
    justify-content: center;

    .contact-page {
        display: grid;
        gap: 2rem 3rem;
        padding-bottom: 3rem;
    }
    .form {
        width: 100%;
        background: var(--clr-white);
        border-radius: var(--radius);
        box-shadow: var(--dark-shadow);
        padding: 2rem 2.5rem;
        margin-top: 2.5rem;
    }

    .form-group {
          display: flex;
          flex-direction: column;
          align-items: flex-start;
          width: fit-content;

          label {
            font-size: 20px;
          }
          input,select ,textarea{
            margin-top: 6px;
            min-width: 26em;
            height: 37px;
            padding: 0px 10px;
            font-size: 16px;
            font-family: "Open Sans", sans-serif;
            background-color: #f3f3f3;
            border: 0;
            border-radius: 4px;
            margin-bottom: 31px;
            transition: all 250ms ease-in-out;
            &:hover {
              background-color: #ffffff;
              box-shadow: 0px 0px 14px 0.3px #0e81ce96;
            }
  
            &:focus {
              outline: none;
              box-shadow: 0px 0px 12px 0.8px #3474dbb2;
            }
          }
          textarea{
            display: block; 
            text-align: justify;
            margin: 0 auto;
            line-height: 2.2em;
          }
        }
      
    @media (min-width: 776px) {
        justify-content: flex-end;
    }

    .footer {
      margin-top: 2em;
      button{
        height: 40px;
        background-color: hsl(244, 89%, 65%);
        min-width: 30em;
        &:hover{
          background-color:hsl(244, 84%, 76%);
        }
      }
      
      
    }

`
export default OrderForm;