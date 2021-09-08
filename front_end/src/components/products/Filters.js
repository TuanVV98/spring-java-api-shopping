import styled from "styled-components"
import { useCategoeiesContext } from "../../context/categories_context";
import { getUniqueValues, formatPrice } from "../../utils/helpers"
import { useFilterContext } from "../../context/filters_context";

const Filters = () => {
    const {
        filters: {
            text,
            category,
            min_price,
            price,
            max_price,
        },
        updateFilters,
        clearFilters,
    } = useFilterContext()
    

    const { categories } = useCategoeiesContext();
    const all_categories = getUniqueValues(categories, 'name');
    // console.log(category.toLowerCase())
    // console.log(all_categories);
    return (
        <Wrapper>
            <div className='content'>
                <form>
                    <div className='form_control'>
                        <input type='text' name='text' placeholder='search' className='search-input'
                        onChange={updateFilters} />

                    </div>
                    {/* end search input */}
                    {/* categories */}
                    <div className='form_control'>
                        <h5>category</h5>
                        {
                            all_categories.map((c, index) => {
                                return (
                                    <button key={index} type='button' name='category' entry={index}
                                        onClick={updateFilters}
                                        className={`${
                                            category === c ? 'active':null
                                            }`}
                                    >

                                        {c}
                                    </button>
                                )
                            })
                        }


                    </div>
                    {/* end categories */}
                    <div className='form-control'>
                        <h5>price</h5>
                        <p className='price'>{formatPrice(price)}</p>
                        <input
                            type='range' name='price'
                            value={price}
                            min={min_price}
                            max={max_price}
                            onChange={updateFilters}
                        />
                    </div>
                </form>
                <button type='button' className='clear-btn'>
                    clear filters
                </button>
            </div>

        </Wrapper>
    )
}

const Wrapper = styled.section`

    .form_control{
        margin-bottom:1.25rem;
        h5{
            margin-bottom: 0.5rem;
        }
    }

    .search-input {
        padding: 0.5rem;
        background: var(--clr-grey-10);
        border-radius: var(--radius);
        border-color: tomato;
        letter-spacing: var(--spacing);
    }
    
    .search-input::placeholder {
        text-transform: capitalize;
    }

    button {
        display: block;
        margin: 0.25em 0;
        padding: 0.25rem 0;
        text-transform: capitalize;
        background: transparent;
        border: none;
        border-bottom: 1px solid transparent;
        letter-spacing: var(--spacing);
        color: var(--clr-grey-5);
        cursor: pointer;
    }
    button.active {
        border-color: hsl(22, 31%, 52%);
        background-color: red;
        color:black;
        transition: 0.3s all;
        /* transform: translateY(-3px); */
        transform: translateX(-5px);
        border-radius: 0.3rem;
    }

    button:hover:not(.active){
        background-color:  hsl(205, 63%, 48%);
        border-radius: 0.25rem;
        color:black;
    }
    .active {
        border-color: var(--clr-grey-5);
    }

    .clear-btn {
        background: var(--clr-red-dark);
        color: var(--clr-white);
        padding: 0.25rem 0.5rem;
        border-radius: var(--radius);
    }
    @media (min-width: 768px) {
        .content {
        position: sticky;
        top: 1rem;
        }
    }

`
export default Filters;