import React, { Component } from "react";
import { Row, Col, Form, Button, Container, Card ,Table} from 'react-bootstrap';
import MultipleAutoComplete from "../Common/MultipleAutoComplete"
import DatePicker from "react-datepicker";
import { ToastContainer, toast } from 'react-toastify';
import CustomerDataList from './CustomerDataList';
import "react-datepicker/dist/react-datepicker.css";
import 'react-toastify/dist/ReactToastify.css';

var dateFormat = require('dateformat');

class RegisterUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            email: '',
            birthday: new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
            password: '',
            countryId: '',
            cityId: '',
            countryList: this.props.countryList,
            cityList: this.props.cityList,
            checkMeOut: false,
            customerId: '0'
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.countryList.length > 0) {
            this.setState({
                countryList: nextProps.countryList,
            })
        }

        if (nextProps.cityList.length > 0) {
            this.setState({
                cityList: nextProps.cityList,
            })
        }
    }
    handleOnChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }
    handleOnDateChange = (e) => {
        this.setState({ birthday: e });
    }

    loginUser = () => {
        alert("loginUser")
    }

    onCountryChange = (e) => {
        let countryIdTemp = "";
        for (let i = 0; i < e.length; i++) {
            countryIdTemp += (e[i].key + (i < e.length - 1 ? "," : ""));
        }
        this.setState({
            countryId: countryIdTemp
        });
    }

    onCityChange = (e) =>{
        let cityIdTemp = "";
        for (let i = 0; i < e.length; i++) {
            cityIdTemp += (e[i].key + (i < e.length - 1 ? "," : ""));
        }
        this.setState({
            cityId: cityIdTemp
        });
    }

    notifyError = (message) => {
        toast.error(message, {
            position: "top-right",
            hideProgressBar: true,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false
        });
    }

    notifySuccess = (successMessage) => {
        toast.success(successMessage, {
            position: "top-right",
            hideProgressBar: true,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false
        });
    }

    validateSubmit = () => {
        let isValidated = true;
        this.setState({ mannualValidation: true });

        if (this.state.email === '') {
            this.notifyError("Email can not be empty!");
            isValidated = false;
        }
        if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.state.email)) {
            this.notifyError("Please enter a valid email address");
            isValidated = false;
        }
        return isValidated;
    }


    submitData = () => {
        if (this.validateSubmit()) {
            this.notifySuccess("Data Saved Successfully!");
        }
    }

    render() {      
        return (
            <div>
                <Row className="justify-content-md-center">
                    <Card border="primary">
                        <Card.Header as="h5">User Registration Page</Card.Header>
                        <Card.Body>
                            <Form>
                                <Form.Row>
                                    <Form.Group as={Col} controlId="formGridEmail">
                                        <Form.Label>Email</Form.Label>
                                        <Form.Control required controlid="email" type="email" placeholder="Enter email" name="email" id="email" onChange={(e) => this.handleOnChange(e)} />
                                    </Form.Group>

                                    <Form.Group as={Col} controlId="formGridPassword">
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control controlid="password" type="password" placeholder="Password" name="password" id="password" onChange={(e) => this.handleOnChange(e)} />
                                    </Form.Group>
                                </Form.Row>

                                <Form.Group controlId="formGriddateOfBirth">
                                    <Form.Label>Date Of Birth</Form.Label><br />
                                    <DatePicker
                                        selected={this.state.birthday} required
                                        onChange={this.handleOnDateChange}
                                        dateFormat="dd-MMM-yyyy" minDate={new Date()} />
                                </Form.Group>

                                <Form.Group controlId="formGridAddress1">
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control placeholder="1234 Main St" />
                                </Form.Group>

                                <Form.Group controlId="formGridAddress2">
                                    <Form.Label>Address 2</Form.Label>
                                    <Form.Control placeholder="Apartment, studio, or floor" />
                                </Form.Group>

                                <Form.Group controlId="formGridAddress2">
                                    <Form.Label>Country</Form.Label>

                                    <MultipleAutoComplete
                                        labelKey={"country"}
                                        options={this.state.countryList}
                                        placeholder={"Select Country(s)"}
                                        onChange={(e) => this.onCountryChange(e)}
                                    />

                                </Form.Group>

                                <Form.Row>
                                    <Form.Group as={Col} controlId="formGridCity">
                                        <Form.Label>City</Form.Label>
                                        {/* <Form.Control /> */}
                                        <MultipleAutoComplete
                                        labelKey={"city"}
                                        options={this.state.cityList}
                                        placeholder={"Select City(s)"}
                                        onChange={(e) => this.onCityChange(e)}
                                    />
                                    </Form.Group>

                                    <Form.Group as={Col} controlId="formGridState">
                                        <Form.Label>State</Form.Label>
                                        <Form.Control as="select">
                                            <option>Choose...</option>
                                            <option>...</option>
                                        </Form.Control>
                                    </Form.Group>

                                    <Form.Group as={Col} controlId="formGridZip">
                                        <Form.Label>Zip</Form.Label>
                                        <Form.Control />
                                    </Form.Group>
                                </Form.Row>

                                <Form.Group id="formGridCheckbox">
                                    <Form.Check type="checkbox" label="Check me out" />
                                </Form.Group>

                                <Button variant="primary" onClick={this.submitData}>Submit</Button>
                            </Form>
                        </Card.Body>
                    </Card>
         </Row>
         <Row className="justify-content-md-center">
              <Card border="primary">
                        <Card.Header as="h5">User saved Data</Card.Header>
                        <Card.Body>
                  <CustomerDataList
                   customerId={this.state.customerId}        
                  />    
         </Card.Body>
         </Card>
         </Row>
         </div>
        );
    }
}

export default RegisterUserComponent;