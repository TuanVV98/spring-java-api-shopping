import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import styled from 'styled-components';
import DropdownMenu from './Dropdown';
import { useCategoeiesContext } from '../../context/categories_context';
import CateService from '../../service/Cate-service';

const Categories = () => {

    const { value_category, addToCategory, index_category, updateCategories } = useCategoeiesContext();
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [category, setCategory] = useState(value_category);

    const hanldeOnChange = (event) => {
        event.preventDefault()
        const { name, value } = event.target;

        setCategory({
            ...category,
            [name]: value,
            ['updatedAt']: new Date()
        });
    }

    // console.log(in)
    const onSubmit = (data) => {
        console.log(data);
        let form_data = {
            name: data.name,
            context: data.context
        }

        if (index_category !== -1) {
            if (window.confirm("Cap nhat category ?")) {
                update(category.id, category)
            }
        } else if (index_category === -1) {
            // console.log(index_category)
            if (window.confirm("Them category ?")) {
                created(form_data)
            }
        }

    }

    const created = (data) => {
        CateService.create(data).then(res => {
            if (res.status === 201) {
                addToCategory(res.data.data)
                alert("Them thanh cong !")
            }
        }).catch(err => {
            console.log(err)
        })
    }

    const update = (id, data) => {
        CateService.update(id, data).then(res => {
            if (res.status === 200) {
                updateCategories(res.data.data, index_category)
                // console.log("ok")
                alert("Cap nhat thanh cong !")
            }
        }).catch(err => {
            console.log(err)
        })
    }
    const categoryNameField = register("name", { required: true });
    const categoryContextField = register("context", { required: true });
    return (
        <Wrapper>
            <div className="container">
                <DropdownMenu />

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="base-container" >
                        <div className="content">
                            <div className="form">
                                <div className="form-group">
                                    <input type="text" name="name" placeholder="name" className="input"

                                        value={category.name}
                                        {...categoryNameField}
                                        onChange={(e) => {
                                            categoryNameField.onChange(e);
                                            hanldeOnChange(e);
                                        }}
                                    />
                                </div>
                            </div>
                            <div className="form-group">
                                <textarea placeholder="description" name="context"

                                    value={category.context}
                                    {...categoryContextField}
                                    onChange={(e) => {
                                        categoryContextField.onChange(e);
                                        hanldeOnChange(e);
                                    }}
                                />
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
        margin-left: 3rem;
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
    .input{
        width: 50rem;
       
    }
    
    input[type="submit"]{
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
export default Categories;