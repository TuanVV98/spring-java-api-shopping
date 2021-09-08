import styled from "styled-components"
import Filters from "../components/products/Filters"
import ProductsList from "../components/products/ProductsList"
import Sort from "../components/products/Sort"


const ProductsPage = () => {
    return (
        <Wrapper className='page'>
            <div className='section-center products'>
                <Filters/>
                <div>
                    <Sort/>
                    <ProductsList />
                </div>

            </div>
        </Wrapper>
    )
}

const Wrapper = styled.div`

    .products{
        display: grid;
        gap: 3rem 1.5rem;
        margin: 4rem auto;
    }

    @media (min-width: 768px) {
        .products {
        grid-template-columns: 200px 1fr;
        }
    }

`
export default ProductsPage;