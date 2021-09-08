import styled from "styled-components";
import { Link, useParams, useHistory } from "react-router-dom";
import { useProductsContext } from '../context/product_context'
import { useEffect } from "react";
import { single_product_url as url } from "../utils/constants";
import { formatPrice } from "../utils/helpers";
import ProductImage from "../components/products/ProductImage";
import AddToCart from "../components/carts/AddToCart";


const SingleProductPage = () => {
    const { model } = useParams();
    const  history  = useHistory();
    // console.log(model)

    const {
        // single_product_loading: loading,
        single_product_error: error,
        single_product: product,
        fetchSingleProduct
    } = useProductsContext();

    useEffect(() => {
        fetchSingleProduct(`${url}${model}`)
    }, [model])

    useEffect(() => {
        if (error) {
            setTimeout(() => {
                history.push('/')
            }, 3000)
        }
    }, [error])

    const {
        name,
        model: sku,
        price,
        description,
        image,
        categoryName: company,
    } = product

    // console.log(product)

    return (
        <Wrapper>
            <div className='section section-center page'>
                <Link to="/products" className='btn'>
                    Back to products
                </Link>
                <div className='product-center'>
                    <ProductImage fileName={image} />
                    <section className='content'>
                        <div className='box1'>
                            <h2>{name}</h2>
                            {/* <p></p> */}
                            <h5 className='price'>{formatPrice(price)}</h5>
                            <p className='desc'>{description}</p>
                            <p className='info'>
                                <span>Model:</span>
                                {sku}
                            </p>
                            <p className='info'>
                                <span>Brand : </span>
                                {company}
                            </p>
                        </div>
                        <div className='box2'>
                            <hr />
                            <AddToCart product={product} />
                        </div>
                    </section>
                </div>
            </div>
        </Wrapper>
    )
}

const Wrapper = styled.main`
    .product-center{
        display: grid;
        gap: 4rem;
        margin-top: 2rem;
    }
    .content{
        /* margin-top: -1.5rem; */
        display: grid;
        grid-template-columns: repeat(1, 1fr);
        /* grid-auto-rows:496px; */

    }
    .box1 {
        height: 400px;
        grid-column-start: 1;
        grid-column-end: 4;
        grid-row-start: 1;
        grid-row-end: 3;
    }
    .box2 {
        grid-column-start: 1;
        grid-row-start: 3;
        grid-row-end: 5;
    }
    .price {
        color: var(--clr-primary-5);
    }
    .desc {
        line-height: 2;
        max-width: 45em;
        /* height:10rem; */
    }
    .info {
        text-transform: capitalize;
        width: 300px;
        display: grid;
        grid-template-columns: 125px 1fr;
        span {
            font-weight: 700;
        }
    }
    
    @media (min-width: 992px) {
        .product-center {
            grid-template-columns: 1fr 1fr;
            align-items: center;
        }
        .price {
            font-size: 1.25rem;
        }
    }

`
export default SingleProductPage;