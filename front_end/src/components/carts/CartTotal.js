import styled from "styled-components";
import { useCartContext } from "../../context/cart_context";
import { useUserContext } from "../../context/user_context";
import { formatPrice } from "../../utils/helpers";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import { user_url as url } from "../../utils/constants";
import OrderForm from "./OrderForm";
import { useGlobalContext } from "../../context/app_context";
const CartTotal = () => {

    const { total_amount } = useCartContext()
    const { myUser, fetchUser } = useUserContext();
    const { openModal } = useGlobalContext();

    // useEffect(() => {
    //     fetchUser(`${url}`)
    // }, [])

    // console.log(user)
    return (
        <main>
            <Wrapper className='section section-center'>
                <div>
                    <article>
                        <h5>
                            subtotal : <span>{formatPrice(total_amount)}</span>
                        </h5>
                        <p>
                            shipping fee : <span>$0</span>
                        </p>
                        <hr />
                        <h4>
                            order total :
                            <span>{formatPrice(total_amount)}</span>
                        </h4>
                    </article>
                    {
                        myUser ? (
                            <button onClick={openModal} className='btn'>
                                proceed to checkout
                            </button>
                        ) : (
                            <Link to='/login' className='btn'>
                                login
                            </Link>
                        )
                    }
                </div>
            </Wrapper>
            {/* <OrderForm /> */}
        </main>
    )
}

const Wrapper = styled.section`
    /* margin-top: 3rem; */
    display: flex;
    justify-content: center;
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
    h4 {
        margin-top: 2rem;
    }
    @media (min-width: 776px) {
        justify-content: flex-end;
    }
    .btn {
        width: 100%;
        margin-top: 1rem;
        text-align: center;
        font-weight: 700;
    }
`

export default CartTotal;