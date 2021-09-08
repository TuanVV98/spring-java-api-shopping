import styled from "styled-components";
import { FaSearch } from 'react-icons/fa'
import { Link } from 'react-router-dom'
import { formatPrice } from '../../utils/helpers';

const Product = ({ image, name, price, id, model }) => {

    return (
        <Wrapper>
            <div className="container">
                <img src={`http://localhost:8080/api/v1/products/image?filename=${image}`} alt={name} />
                {/* <img alt={name} /> */}
                <Link to={`/products/${model}`} className='link'>
                    <FaSearch />
                </Link>
            </div>
            <footer>
                <h5>{name}</h5>
                <p>{formatPrice(price)}</p>
            </footer>

        </Wrapper >
    )
}

const Wrapper = styled.article`

    .container {
        position: relative;
        background: var(--clr-black);
        border-radius: var(--radius);
    }
    img {
        /* width: 100%; */
        width: 230px;
        height: 300px;
        display: block;
        object-fit: cover;
        /* background: var(--clr-grey-10); */
        border-radius: var(--radius);
        border: 1px solid hsl(17, 100%, 74%);
        transition: var(--transition);
    }
    .link {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background: var(--clr-primary-5);
        display: flex;
        align-items: center;
        justify-content: center;
        width: 2.5rem;
        height: 2.5rem;
        border-radius: 50%;
        transition: var(--transition);
        opacity: 0;
        cursor: pointer;
        svg {
        font-size: 1.25rem;
        color: var(--clr-white);
        }
    }
    .container:hover img {
        opacity: 0.5;
    }
    .container:hover .link {
        opacity: 1;
    }
    footer {
        margin-top: 1rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    h5{
        width: 150px;
    }
    
    footer h5,
    footer p {
        margin-bottom: 0;
        font-weight: 400;
    }
    footer p {
        color: var(--clr-primary-5);
        letter-spacing: var(--spacing);
    }
`
export default Product;