import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import styled from "styled-components";
import DropdownMenu from "./Dropdown";
import { BiImageAdd } from 'react-icons/bi'
import placeholder from '../../image.png';
import ProductService from '../../service/Product-service';
import { useCategoeiesContext } from '../../context/categories_context';
import axios from 'axios';
import { useProductsContext } from '../../context/product_context';
import { useUserContext } from '../../context/user_context';
import authHeader from "../../service/Auth-header";

const Products = () => {

    const { myUser } = useUserContext()
    const { categories } = useCategoeiesContext();
    const { value_product, index_product, updateProducts, addToProduct } = useProductsContext();

    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [selectedFile, setSelectedFile] = useState(null);
    const [product, setProduct] = useState(value_product);
    const [{ alt, src }, setImg] = useState({
        src: placeholder,
        alt: 'Upload an Image'
    });

    // console.log(product);
    // console.log(selectedFile)
    const onChangeImage = (event) => {
        event.preventDefault();
        if (event.target.files && event.target.files[0]) {
            let image = event.target.files[0];
            setSelectedFile(image);
            setImg({
                src: URL.createObjectURL(image),
                alt: image.name
            })
        }
    }

    const onSubmit = (data) => {
        console.log(data)
        let form_data = {
            model: data.model,
            name: data.name,
            price: data.price,
            description: data.description,
            categoryID: data.categoryID,
            userID: myUser.id,
        }

        if (index_product !== -1) {
            if (selectedFile === null) {
                // console.log('1')
                if (window.confirm("Cap nhat product ?")) {
                    update(product)
                }
            } else {
                if (window.confirm("Cap nhat product ?")) {
                    updateWithFile(product, selectedFile)
                }
            }

        } else if (index_product === -1) {
            if (window.confirm("Them product ?")) {
                create(selectedFile, form_data)
            }
        }

    }

    const create = (selectedFileImage, data) => {
        const formData = new FormData();

        formData.append("file", selectedFileImage);
        formData.append("properties", new Blob([JSON.stringify(data)],
            {
                type: "application/json"
            }));

        console.log(formData);
        const url = "http://localhost:8080/api/v1/products";

        axios({
            url: url,
            method: "POST",
            data: formData,
            headers: authHeader()

        }).then(res => {
            if (res.status === 201) {
                addToProduct(res.data.data)
                setSelectedFile(null)
                alert("Them product thanh cong !")
            }
        }).catch(err => {
            // console.log(err.response.data);
            if (err) {
                alert(err.response.data.errors.details)
            }
        })

    }

    const update = (data) => {
        let today = new Date();
        let form_data = {
            ...data,
            ['updatedAt']: today
        }
        ProductService.update(data.id, form_data).then(res => {
            if (res.status === 200) {
                updateProducts(res.data.data, index_product)
                alert("Cap nhat products thanh cong !")
            }
        }).catch(err => {
            console.log(err.response.data);
        })
    }

    const updateWithFile = (data, file) => {

        const formData = new FormData();

        formData.append("file", file);
        formData.append("properties", new Blob([JSON.stringify(data)],
            {
                type: "application/json"
            }));

        console.log(formData);
        const url = `http://localhost:8080/api/v1/products/${data.id}/file`;
        axios({
            url: url,
            method: "PUT",
            data: formData,
            headers: authHeader()

        }).then(res => {
            if (res.status === 200) {
                updateProducts(res.data.data, index_product)
                setSelectedFile(null)
                alert("Cap nhat products thanh cong !")
            }
        }).catch(err => {
            console.log(err.response.data);
        })
    }

    const onChangeHandle = (event) => {
        event.preventDefault()
        const { name, value } = event.target;

        setProduct({
            ...product, [name]: value
        });

    }
    const productModelField = register("model", { required: true });
    const productNameField = register("name", { required: true });
    const productPriceNumber = register("price", { required: true });
    const productCategorySelect = register("categoryID", { required: true });
    const productDescriptionField = register("description", { required: true });

    return (
        <Wrapper>

            <div className="container">
                <DropdownMenu />

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="base-container" >
                        <div className="content">
                            <div className="form">
                                <div className="form-group">
                                    <input type="text" name="model" placeholder="model"
                                        value={product.model}
                                        {...productModelField}
                                        onChange={(e) => {
                                            productModelField.onChange(e);
                                            onChangeHandle(e);
                                        }}
                                    />
                                </div>
                                <div className="form-group">
                                    <input type="text" name="name" placeholder="name"
                                        value={product.name}
                                        {...productNameField}
                                        onChange={(e) => {
                                            productNameField.onChange(e);
                                            onChangeHandle(e);
                                        }}
                                    />
                                </div>
                                <div className="form-group">
                                    <input type="number" name="price" placeholder="price"
                                        value={product.price}
                                        {...productPriceNumber}
                                        onChange={(e) => {
                                            productPriceNumber.onChange(e);
                                            onChangeHandle(e);
                                        }} />
                                </div>
                                <div className="form-group">
                                    <select
                                        value={product.categoryID}
                                        {...productCategorySelect}
                                        onChange={(e) => {
                                            productCategorySelect.onChange(e);
                                            onChangeHandle(e);
                                        }}>
                                        {
                                            categories.map((value, index) => {
                                                return (
                                                    <option key={index} value={value.id}>{value.name}</option>
                                                )
                                            })
                                        }

                                    </select>
                                </div>
                            </div>
                            <div className="boxImage">

                                <img src={src} alt={alt} className="img" />
                                <input type='file' name='file' id="input" className="choose-input" accept="image/*"
                                    onChange={e => onChangeImage(e)} />
                                <label className="images" htmlFor="input">
                                    <BiImageAdd style={{ width: '20px', height: '20px', paddingLeft: '20px' }} /> Choose your Photo
                                </label>

                            </div>
                            <div className="form-group">
                                <textarea placeholder="description" name="description"
                                    value={product.description}
                                    {...productDescriptionField}
                                    onChange={(e) => {
                                        productDescriptionField.onChange(e);
                                        onChangeHandle(e);
                                    }} />
                            </div>

                            <input type="submit" />
                        </div>
                    </div>
                </form>
            </div>

        </Wrapper >
    )
}

const Wrapper = styled.div`

    /* margin: 0px;
    padding: 15px 0px 0px 0px;
    max-width: 1210px;
    margin: 0 auto;
    border-radius: 0.5rem; */

    height: 95vh;
    background:linear-gradient(135deg, #8254EA 0%, #E86DEC 100%);
            
    .container {
        display: grid;
        gap: 3rem 1.5rem;
        margin: 2rem auto;
    }

    .base-container {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .content {
        display: flex;
        flex-direction: column;
    }
    .form {
        margin-top: 2em;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-right: 30rem;
    }
    .form-group {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        width: fit-content;
    }

    
    textarea {
        /* margin-top: 6px; */
            /* min-width: 26rem; */
        width: 50rem;
        height: 9rem;
        padding: 0px 10px;
        font-size: 16px;
        font-family: "Open Sans", sans-serif;
        background-color: #f3f3f3;
        border: 0;
        border-radius: 4px;
        margin-bottom: 31px;
        transition: all 250ms ease-in-out;
        margin-top: 0.5rem;
        
           
    }

    .boxImage{
        position: absolute;
        width:15rem;
        border-radius: 0.5rem;
        border: 1px solid yellow;
        height:17.3rem;
        // hieght:10rem;
        /* background:hsl(0, 0%, 48%); */
        /* top:21%;
        right:17.5%; */
        margin-left: 34rem;
        margin-top: 2.9rem;
    }

    .images{
        position: absolute;
        margin: auto;
        width:13.5rem;
        height: 2.7rem;
        // display: flex;
        color: white;
        border-radius: 0.25rem;
        background-color: tomato;
        text-align: center;
        cursor: pointer;
        line-height: 2.8;
        bottom:1.7%;
        left:4%;
        align-items: center;
        
    }
    .img{
        position: absolute;
        border-radius: 0.5rem;
        width:12rem;
        height:12.5rem;
        top :5%;
        left:10%;
    }
    .choose-input {
        position: absolute;
        padding: 0.5rem;
        background: hsl(210, 36%, 96%);;
        border-radius: 0.25rem;
        border-color: tomato;
        letter-spacing:0.1rem;
        width:13.5rem;
        height:2.5rem;
        bottom:2%;
        left:4%
      }
      .choose-input::placeholder {
        text-transform: capitalize;
      }

      input[type="submit"]
    {
        width:50rem;
        border-radius: 0.5rem;
        // border-color: tomato;
        background: #ec5990;
        color: white;
        text-transform: uppercase;
        border: none;
        margin-top: 20px;
        padding: 20px;
        font-size: 16px;
        font-weight: 100;
        letter-spacing: 10px;
      }

      
    /* button[type="button"]:hover  */
    input[type="submit"]:hover{
        background: #bf1650;
    }
    input[type="submit"]:active
    {
        transition: 0.3s all;
        transform: translateY(3px);
        border: 1px solid transparent;
        opacity: 0.8;
    }

    input[type="submit"]{
        -webkit-appearance: none;
    }

    @media (min-width: 900px) {
        .container {
            grid-template-columns: 300px 1fr;
        
        }
    }
`
export default Products;