import styled from "styled-components";
import hero1 from '../assets/hero-1.jpeg';
import hero2 from '../assets/hero-2.jpg';
import { Link } from 'react-router-dom';

const Hero = () => {
   

    return (
        <Wrapper className='section-center'>
            <article className='content'>
                <h1>
                    FREE GIFT <br />
                    WITH PURCHASE
                </h1>
                <p>
                    Receive a free watch winder with any watch purchase over $ 1000
                    or a free watch roll with any watch purchase over $ 800. While
                    stock lasts.
                </p>
                <Link to='/products' className='btn hero-btn'>
                    shop now
                </Link>
            </article>
            <article className='img-container'>
                <img src={hero1} alt='nice table' className='main-img' />
                <img src={hero2} alt='person working' className='accent-img' />
            </article>
        </Wrapper>

    )
}

const Wrapper = styled.section`
    min-height: 60vh;
    display: grid;
    place-items: center;

    .img-container{
        display: none;
    }

    p{
        line-height: 2;
        max-width: 45em;
        margin-bottom: 2rem;
        color: var(--clr-grey-5);
        font-size: 1rem;
    }
    
    @media (min-width: 992px) {
        height: calc(100vh - 5rem);
        grid-template-columns: 1fr 1fr;
        gap: 8rem;
        h1 {
            margin-bottom: 2rem;
        }
        p {
            font-size: 1.25rem;
        }
        .hero-btn {
            padding: 0.75rem 1.5rem;
            font-size: 1rem;
        }
        .img-container {
            display: block;
            position: relative;
        }
        .main-img {
            width: 100%;
            height: 550px;
            position: relative;
            border-radius: var(--radius);
            display: block;
            object-fit: cover;
        }
        .accent-img {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 250px;
            transform: translateX(-50%);
            border-radius: var(--radius);
        }
        .img-container::before {
            content: '';
            position: absolute;
            width: 10%;
            height: 80%;
            background: var(--clr-primary-9);
            bottom: 0%;
            left: -8%;
            border-radius: var(--radius);
        }
  }


`

export default Hero;