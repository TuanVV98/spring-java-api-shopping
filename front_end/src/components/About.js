import styled from "styled-components";
import hero2 from '../assets/hero-2.jpg';

const About = () => {
    return (
        <Wrapper className='page section section-center'>
            <img src={hero2} alt='nice desk' />
            <article>
                <div className='title'>
                    <h2>our story</h2>
                    <div className='underline'></div>
                </div>
                <p>
                    The history of Rolex is inextricably linked to the visionary spirit of
                    Hans Wilsdorf,its founder. In 1905, at the age of 24, Hans Wilsdorf
                    founded a company in London specialising in the distribution of timepieces.
                    He began to dream of a watch worn on the wrist. Wristwatches were not very
                    precise at the time, but Hans Wilsdorf foresaw that they could become not only
                    elegant, but also reliable.

                    To convince the public of the reliability of his resolutely innovative
                    timepieces, he equipped them with small, very precise movements
                    manufactured by a Swiss watchmaking company in Bienne.
                </p>
            </article>
        </Wrapper>

    )
}

const Wrapper = styled.section`
  display: grid;
  gap: 4rem;
  img {
    width: 100%;
    display: block;
    border-radius: var(--radius);
    height: 500px;
    object-fit: cover;
  }
  p {
    line-height: 2;
    max-width: 45em;
    margin: 0 auto;
    margin-top: 2rem;
    color: var(--clr-grey-5);
  }
  .title {
    text-align: left;
  }
  .underline {
    margin-left: 0;
  }
  @media (min-width: 992px) {
    grid-template-columns: 1fr 1fr;
  }
`
export default About;