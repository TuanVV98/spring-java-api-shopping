import styled from "styled-components"

const ProductImage = ({ fileName }) => {

    // console.log(fileName)

    return (
        <Wrapper>
            <div className='styleImage'>
                <img src={`http://localhost:8080/api/v1/products/image?filename=${fileName}`} className='main' />
            </div>
        </Wrapper>
    )
}

const Wrapper = styled.section`
    .main {
        height: 600px;
    }

    .styleImage {
        display:flex;
        width: 90%;
        display: block;
        border-radius:1rem;
        height: 530px;
        object-fit: cover;
        padding-top:3rem;
        /* padding-left:-5rem;
        padding-bottom:3rem; */
        border: 2px solid hsl(17, 100%, 74%);
        justify-content:center;
        justify-items:center;
        /* margin-left:3rem; */
       
    }

    .styleImage img{
        width: 100%;
        height: 420px;
        display: block;
        border-radius: var(--radius);
        object-fit: cover;
        
    }
    .styleImage:hover{
        -webkit-box-shadow:0px 11px 54px 0px rgba(0,0,0,0.75);
        -moz-box-shadow:0px 11px 54px 0px rgba(0,0,0,0.75);
         box-shadow:0px 20px 54px 0px rgba(0,0,0,0.75);
    }
    /* img {
        width: 100%;
        display: block;
        border-radius: var(--radius);
        object-fit: cover;
    } */
    
    @media (max-width: 576px) {
        .main {
            height: 300px;
        }
    }
    @media (min-width: 992px) {
        .main {
            height: 500px;
        }
    }
`
export default ProductImage;