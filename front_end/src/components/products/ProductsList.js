import GridView from "./GridView";
import { useFilterContext } from '../../context/filters_context';


const ProductsList = () => {
    const { filtered_products: products } = useFilterContext()
    console.log(products);
    // console.log('abc');
    if (products.length < 1) {
        return (
            <h5 style={{ textTransform: 'none' }}>
                Sorry, no products matched your search...
            </h5>
        )
    }
    return (
        <GridView products={products}> products list</GridView>
    )
}

export default ProductsList;