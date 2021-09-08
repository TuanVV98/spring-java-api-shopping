
export const links = [
  {
    id: 1,
    text: 'home',
    url: '/',
  },
  {
    id: 2,
    text: 'about',
    url: '/about',
  },
  {
    id: 3,
    text: 'products',
    url: '/products',
  },
]

export const order_status =[
  {
    id:1,
    name:'NEW',
  },
  {
    id:2,
    name:'SHIPPED',
  },
  {
    id:3,
    name:'DELIVERED'
  },
  {
    id:4,
    name:'COMPLETE',
  }
]

export const account_status=[
  {
    id:1,
    name:'IS_ACTIVE',
  },
  {
    id:2,
    name:'IS_BLOCKED',
  }
]

export const categories_url = 'http://localhost:8080/api/v1/categories';

export const products_url = 'http://localhost:8080/api/v1/products';

export const recycle_categories_url = 'http://localhost:8080/api/v1/categories/soft-delete';

export const recycle_products_url = 'http://localhost:8080/api/v1/products/soft-delete';

export const single_product_url = `http://localhost:8080/api/v1/products/byModel?model=`;

export const user_url ='http://localhost:8080/api/v1/user/auth';

export const get_users_url ='http://localhost:8080/api/v1/users';

export const orders_url='http://localhost:8080/api/v1/orders'

export const load_image_url = `http://localhost:8080/api/v1/products/image?filename=`;

export const products_by_category_url = (name) => {
    return `http://localhost:8080/api/v1/products/byCategory?name=${name}`;
}