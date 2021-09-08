import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { links } from '../../utils/constants';
import CartButton from '../cart/CartButton';
import { useUserContext } from '../../context/user_context';

const Navbar = () => {
    const { myUser } = useUserContext()

    // console.log(myUser)
    return (
        <NavContainer>
            <div className='nav-center'>
                <div className='nav-header'>
                    <Link to='/'>
                        <img
                            src="https://pngimg.com/uploads/amazon/amazon_PNG21.png"
                            className="header__logo"
                        />
                    </Link>
                </div>
                <ul className='nav-links'>
                    {
                        links.map((link) => {
                            const { id, text, url } = link
                            return (
                                <li key={id}>
                                    <Link to={url}>{text}</Link>
                                </li>
                            )
                        })
                    }
                    {myUser && myUser.role === 'ROLE_ADMIN' && (
                        <li>
                            <Link to="/admin/table/products">Admin</Link>
                        </li>
                    )}
                    {myUser && myUser.role === 'ROLE_USER' && (
                        <li>
                            <Link to='/checkout'>checkout</Link>
                        </li>
                    )}
                </ul>
                <CartButton />
            </div>
        </NavContainer>
    )

}

const NavContainer = styled.nav`

    height: 5rem;
    display: flex;
    align-items: center;
    justify-content: center;
    
    .nav-center {
        width: 90vw;
        margin: 0 auto;
        max-width: var(--max-width);
    }

    .nav-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        img{
            width: 175px;
            margin-left: -15px;
        }
    }

    .header__logo {
        width: 100px;
        object-fit: contain;
        margin: 0 20px;
        margin-top: 18px;
    }

    .nav-toggle{
        background: transparent;
        border: transparent;
        color: var(--clr-primary-5);
        cursor: pointer;
        svg{
            font-size: 2rem;
        }
    }

    .nav-links{
        display: none;
    }

    .cart-btn-wrapper{
        display: none;
    }

    @media (min-width: 992px){
        .nav-toggle {
            display: none;
        }

        .nav-center {
            display: grid;
            grid-template-columns: auto 1fr auto;
            align-items: center;
        }

        .nav-links {
            display: flex;
            justify-content: center;
            /* padding-left: 10rem; */
        li {
            margin: 0 0.5rem;
        }

        a {
            color: var(--clr-grey-3);
            font-size: 1.1rem;
            text-transform: capitalize;
            letter-spacing: var(--spacing);
            padding: 0.5rem;
            &:hover {
                 border-bottom: 2px solid var(--clr-primary-7);
            }
        }
        }

        .cart-btn-wrapper {
            display: grid;
        }
    }

`
export default Navbar;